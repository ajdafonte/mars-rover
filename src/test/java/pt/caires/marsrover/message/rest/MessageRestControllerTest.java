package pt.caires.marsrover.message.rest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static pt.caires.marsrover.message.MessageTestHelper.MOCK_DIALECT1;
import static pt.caires.marsrover.message.MessageTestHelper.MOCK_DIALECT2;
import static pt.caires.marsrover.message.MessageTestHelper.MOCK_INVALID_TEXT;
import static pt.caires.marsrover.message.MessageTestHelper.MOCK_TEXT1;
import static pt.caires.marsrover.message.MessageTestHelper.MOCK_TEXT2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pt.caires.marsrover.common.error.MarsRoverApiError;
import pt.caires.marsrover.common.error.MarsRoverApiException;
import pt.caires.marsrover.message.MessageTestHelper;
import pt.caires.marsrover.message.bizz.CreateMessageParameter;
import pt.caires.marsrover.message.bizz.MessageService;
import pt.caires.marsrover.message.domain.MRMessage;


@ExtendWith(SpringExtension.class)
@WebMvcTest(MessageRestController.class)
class MessageRestControllerTest
{
    private static final String MESSAGES_URI = "/v1/messages";
    private static final String MESSAGE_BY_ID_URI = "/v1/messages/{messageId}";

    private static final String INVALID_REQUEST = "Invalid request";
    private static final String INVALID_REQUEST_PARAMETER = "Invalid request parameter";
    private static final String RESOURCE_NOT_FOUND = "Resource not found";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MessageService messageService;

    // get messages - with data
    @Test
    void givenExistentMessages_whenGetMessages_thenReturnAllMessages() throws Exception
    {
        // given
        final MRMessage mockMessage1 = MessageTestHelper.generateMessage(MOCK_TEXT1, MOCK_DIALECT1);
        final MRMessage mockMessage2 = MessageTestHelper.generateMessage(MOCK_TEXT2, MOCK_DIALECT2);
        final List<MRMessage> allMessages = Arrays.asList(mockMessage1, mockMessage2);

        final MRMessageRest mockMessageRest1 = MessageTestHelper.generateMessageRest(mockMessage1.getId(),
            MOCK_TEXT1, MOCK_DIALECT1);
        final MRMessageRest mockMessageRest2 = MessageTestHelper.generateMessageRest(mockMessage2.getId(),
            MOCK_TEXT2, MOCK_DIALECT2);
        final List<MRMessageRest> expectedResult = Arrays.asList(mockMessageRest1, mockMessageRest2);

        doReturn(allMessages).when(messageService).getMessages();
        final String expectedContent = generateSuccessBody(expectedResult);

        // when
        final ResultActions result = mvc.perform(get(MESSAGES_URI)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());
        result.andExpect(MockMvcResultMatchers.content().string(expectedContent));
        verify(messageService, times(1)).getMessages();
        verifyNoMoreInteractions(messageService);
    }

    // get messages - with no data
    @Test
    void givenEmptyMessages_whenGetMessages_thenReturnEmptyResult() throws Exception
    {
        // given
        final List emptyList = Collections.emptyList();
        doReturn(emptyList).when(messageService).getMessages();
        final String expectedContent = generateSuccessBody(emptyList);

        // when
        final ResultActions result = mvc.perform(get(MESSAGES_URI)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());
        result.andExpect(MockMvcResultMatchers.content().string(expectedContent));
        verify(messageService, times(1)).getMessages();
        verifyNoMoreInteractions(messageService);
    }

    // get message by id - expected id
    @Test
    void givenExistentId_whenGetMessage_thenReturnExistentMessage() throws Exception
    {
        // given
        final MRMessage mockMessage = MessageTestHelper.generateMessage(MOCK_TEXT1, MOCK_DIALECT1);
        final MRMessageRest expectedMessage = MessageTestHelper.generateMessageRest(mockMessage.getId(), MOCK_TEXT1, MOCK_DIALECT1);
        doReturn(mockMessage).when(messageService).getMessage(anyLong());
        final String expectedContent = generateSuccessBody(expectedMessage);

        // when
        final ResultActions result = mvc.perform(get(MESSAGE_BY_ID_URI, expectedMessage.getId())
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());
        result.andExpect(MockMvcResultMatchers.content().string(expectedContent));
        verify(messageService, times(1)).getMessage(mockMessage.getId());
        verifyNoMoreInteractions(messageService);
    }

    // get message by id - invalid id
    @Test
    void givenInvalidId_whenGetMessage_thenReturnBadRequest() throws Exception
    {
        // given
        final String unknownMessageId = "lol";

        // then
        final ResultActions result = mvc.perform(get(MESSAGE_BY_ID_URI, unknownMessageId)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        // then
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());
        result.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString(INVALID_REQUEST_PARAMETER)));
        verifyZeroInteractions(messageService);
    }

    // get message by id - unknown id
    @Test
    void givenUnknownId_whenGetMessage_thenReturnNotFound() throws Exception
    {
        // given
        final long unknownMessageId = MessageTestHelper.MOCK_UNKNOWN_ID;
        doThrow(new MarsRoverApiException(MarsRoverApiError.UNKNOWN_RESOURCE, "Entity was not found."))
            .when(messageService)
            .getMessage(unknownMessageId);

        // then
        final ResultActions result = mvc.perform(get(MESSAGE_BY_ID_URI, unknownMessageId)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        // then
        result.andExpect(MockMvcResultMatchers.status().isNotFound());
        result.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString(RESOURCE_NOT_FOUND)));
        verify(messageService, times(1)).getMessage(unknownMessageId);
        verifyNoMoreInteractions(messageService);
    }

    // create message - ok
    @Test
    void givenValidRequest_whenCreateMessage_thenReturnNewMessage() throws Exception
    {
        // given
        final MRMessage mockMessage = MessageTestHelper.generateMessage(MOCK_TEXT1, MOCK_DIALECT1);
        doReturn(mockMessage).when(messageService).createMessage(any(CreateMessageParameter.class));

        final CreateMessageRequestBody mockRequestBody = MessageTestHelper.generateCreateMessageRequestBody(MOCK_TEXT1, MOCK_DIALECT1);
        final String requestBody = generateRequestBody(mockRequestBody);

        final MRMessageRest expectedResult = MessageTestHelper.generateMessageRest(mockMessage.getId(), MOCK_TEXT1, MOCK_DIALECT1);
        final String expectedContent = generateSuccessBody(expectedResult);

        // when
        final ResultActions result = mvc.perform(post(MESSAGES_URI)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(requestBody));

        // then
        result.andExpect(MockMvcResultMatchers.status().isCreated());
        result.andExpect(MockMvcResultMatchers.content().string(expectedContent));
        verify(messageService, times(1)).createMessage(any(CreateMessageParameter.class));
        verifyNoMoreInteractions(messageService);
    }

    // create message - no body
    @Test
    void givenNullRequestBody_whenCreateMessage_thenReturnBadRequest() throws Exception
    {
        // given
        final String requestBody = "{}";

        // when
        final ResultActions result = mvc.perform(post(MESSAGES_URI)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(requestBody));

        // then
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());
        result.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString(INVALID_REQUEST)));
        verifyZeroInteractions(messageService);
    }

    // create message - invalid body (body with missing values)
    @Test
    void givenIncompleteRequestBody_whenCreateMessage_thenReturnBadRequest() throws Exception
    {
        // given
        final CreateMessageRequestBody mockRequestBody = MessageTestHelper.generateCreateMessageRequestBody(MOCK_TEXT1, null);
        final String requestBody = generateRequestBody(mockRequestBody);

        // when
        final ResultActions result = mvc.perform(post(MESSAGES_URI)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(requestBody));

        // then
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());
        result.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString(INVALID_REQUEST)));
        verifyZeroInteractions(messageService);
    }

    // create message - body with invalid value - semantics
    @Test
    void givenRequestBodyWithInvalidValues_whenCreateMessage_thenReturnBadRequest() throws Exception
    {
        // given
        final CreateMessageRequestBody mockRequestBody = MessageTestHelper.generateCreateMessageRequestBody(MOCK_INVALID_TEXT, MOCK_DIALECT1);
        final String requestBody = generateRequestBody(mockRequestBody);

        // when
        final ResultActions result = mvc.perform(post(MESSAGES_URI)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(requestBody));

        // then
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());
        result.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString(INVALID_REQUEST)));
        verifyZeroInteractions(messageService);
    }

    private String generateSuccessBody(final List<MRMessageRest> messages) throws JsonProcessingException
    {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(messages);
    }

    private String generateSuccessBody(final MRMessageRest message) throws JsonProcessingException
    {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(message);
    }

    private String generateRequestBody(final CreateMessageRequestBody requestBody) throws JsonProcessingException
    {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(requestBody);

    }

}
