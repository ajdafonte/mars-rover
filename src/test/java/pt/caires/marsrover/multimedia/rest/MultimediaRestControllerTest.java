package pt.caires.marsrover.multimedia.rest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static pt.caires.marsrover.multimedia.MultimediaTestHelper.MOCK_ID1;
import static pt.caires.marsrover.multimedia.MultimediaTestHelper.MOCK_TITLE1;
import static pt.caires.marsrover.multimedia.MultimediaTestHelper.MOCK_TITLE2;
import static pt.caires.marsrover.multimedia.MultimediaTestHelper.MOCK_TYPE_PHOTO;
import static pt.caires.marsrover.multimedia.MultimediaTestHelper.MOCK_TYPE_VIDEO;
import static pt.caires.marsrover.multimedia.MultimediaTestHelper.MOCK_UNKNOWN_ID;

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
import pt.caires.marsrover.multimedia.MultimediaTestHelper;
import pt.caires.marsrover.multimedia.bizz.CreateMultimediaParameter;
import pt.caires.marsrover.multimedia.bizz.MultimediaService;
import pt.caires.marsrover.multimedia.bizz.UpdateMultimediaParameter;
import pt.caires.marsrover.multimedia.domain.MRMultimedia;


@ExtendWith(SpringExtension.class)
@WebMvcTest(MultimediaRestController.class)
class MultimediaRestControllerTest
{
    private static final String MULTIMEDIA_URI = "/v1/multimedia";
    private static final String MULTIMEDIA_BY_ID_URI = "/v1/multimedia/{multimediaId}";

    private static final String INVALID_REQUEST = "Invalid request";
    private static final String INVALID_REQUEST_PARAMETER = "Invalid request parameter";
    private static final String RESOURCE_NOT_FOUND = "Resource not found";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MultimediaService multimediaService;

    // get multimedia - with data
    @Test
    void givenExistentMultimedia_whenGetMultimediaCollection_thenReturnAllMultimedia() throws Exception
    {
        // given
        final MRMultimedia mockMultimedia1 = MultimediaTestHelper.generateMultimedia(MOCK_TYPE_PHOTO, MOCK_TITLE1);
        final MRMultimedia mockMultimedia2 = MultimediaTestHelper.generateMultimedia(MOCK_TYPE_VIDEO, MOCK_TITLE2);
        final List<MRMultimedia> allMultimedia = Arrays.asList(mockMultimedia1, mockMultimedia2);

        final MRMultimediaRest mockMultimediaRest1 = MultimediaTestHelper.generateMultimediaRest(mockMultimedia1.getId(),
            MOCK_TYPE_PHOTO, MOCK_TITLE1);
        final MRMultimediaRest mockMultimediaRest2 = MultimediaTestHelper.generateMultimediaRest(mockMultimedia2.getId(),
            MOCK_TYPE_VIDEO, MOCK_TITLE2);
        final List<MRMultimediaRest> expectedResult = Arrays.asList(mockMultimediaRest1, mockMultimediaRest2);

        doReturn(allMultimedia).when(multimediaService).getMultimediaCollection();
        final String expectedContent = generateSuccessBody(expectedResult);

        // when
        final ResultActions result = mvc.perform(get(MULTIMEDIA_URI)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());
        result.andExpect(MockMvcResultMatchers.content().string(expectedContent));
        verify(multimediaService, times(1)).getMultimediaCollection();
        verifyNoMoreInteractions(multimediaService);
    }

    // get multimedia - with no data
    @Test
    void givenEmptyMultimedia_whenGetMultimediaCollection_thenReturnEmptyResult() throws Exception
    {
        // given
        final List emptyList = Collections.emptyList();
        doReturn(emptyList).when(multimediaService).getMultimediaCollection();
        final String expectedContent = generateSuccessBody(emptyList);

        // when
        final ResultActions result = mvc.perform(get(MULTIMEDIA_URI)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());
        result.andExpect(MockMvcResultMatchers.content().string(expectedContent));
        verify(multimediaService, times(1)).getMultimediaCollection();
        verifyNoMoreInteractions(multimediaService);
    }

    // create multimedia - ok
    @Test
    void givenValidRequest_whenCreateMultimedia_thenReturnNewMultimedia() throws Exception
    {
        // given
        final MRMultimedia mockMultimedia = MultimediaTestHelper.generateMultimedia(MOCK_TYPE_PHOTO, MOCK_TITLE1);
        doReturn(mockMultimedia).when(multimediaService).createMultimedia(any(CreateMultimediaParameter.class));

        final CreateMultimediaRequestBody mockRequestBody = MultimediaTestHelper.generateCreateMultimediaRequestBody(MOCK_TYPE_PHOTO);
        final String requestBody = generateRequestBody(mockRequestBody);

        final MRMultimediaRest expectedResult = MultimediaTestHelper.generateMultimediaRest(mockMultimedia.getId(), MOCK_TYPE_PHOTO, MOCK_TITLE1);
        final String expectedContent = generateSuccessBody(expectedResult);

        // when
        final ResultActions result = mvc.perform(post(MULTIMEDIA_URI)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(requestBody));

        // then
        result.andExpect(MockMvcResultMatchers.status().isCreated());
        result.andExpect(MockMvcResultMatchers.content().string(expectedContent));
        verify(multimediaService, times(1)).createMultimedia(any(CreateMultimediaParameter.class));
        verifyNoMoreInteractions(multimediaService);
    }

    // create multimedia - no body
    @Test
    void givenNullRequestBody_whenCreateMultimedia_thenReturnBadRequest() throws Exception
    {
        // given
        final String requestBody = "{}";

        // when
        final ResultActions result = mvc.perform(post(MULTIMEDIA_URI)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(requestBody));

        // then
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());
        result.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString(INVALID_REQUEST)));
        verifyZeroInteractions(multimediaService);
    }

    // create multimedia - invalid body
    @Test
    void givenIncompleteRequestBody_whenCreateMultimedia_thenReturnBadRequest() throws Exception
    {
        // given
        final CreateMultimediaRequestBody mockRequestBody = MultimediaTestHelper.generateCreateMultimediaRequestBody(null);
        final String requestBody = generateRequestBody(mockRequestBody);

        // when
        final ResultActions result = mvc.perform(post(MULTIMEDIA_URI)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(requestBody));

        // then
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());
        result.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString(INVALID_REQUEST)));
        verifyZeroInteractions(multimediaService);
    }

    // update multimedia title - ok
    @Test
    void givenValidRequest_whenUpdateMultimediaTitle_thenReturnUpdatedMultimedia() throws Exception
    {
        // given
        final String mockNewTitle = MOCK_TITLE2;
        final long mockMultimediaIdToUpdate = MOCK_ID1;
        final MRMultimedia targetMultimedia = MultimediaTestHelper.generateMultimedia(MOCK_TYPE_PHOTO, mockNewTitle);
        doReturn(targetMultimedia).when(multimediaService).updateMultimediaTitle(any(UpdateMultimediaParameter.class));

        final UpdateMultimediaRequestBody mockRequestBody = MultimediaTestHelper.generateUpdateMultimediaRequestBody(mockNewTitle);
        final String requestBody = generateRequestBody(mockRequestBody);

        final MRMultimediaRest expectedResult = MultimediaTestHelper.generateMultimediaRest(targetMultimedia.getId(), MOCK_TYPE_PHOTO, mockNewTitle);
        final String expectedContent = generateSuccessBody(expectedResult);

        // when
        final ResultActions result = mvc.perform(patch(MULTIMEDIA_BY_ID_URI, mockMultimediaIdToUpdate)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(requestBody));

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());
        result.andExpect(MockMvcResultMatchers.content().string(expectedContent));
        verify(multimediaService, times(1)).updateMultimediaTitle(any(UpdateMultimediaParameter.class));
        verifyNoMoreInteractions(multimediaService);
    }

    // update multimedia title - nok (multimedia id not found)
    @Test
    void givenRequestWithUnknownMultimediaId_whenUpdateMultimediaTitle_thenReturnNotFound() throws Exception
    {
        // given
        final String mockNewTitle = MOCK_TITLE2;
        final long mockMultimediaIdToUpdate = MOCK_UNKNOWN_ID;
        final UpdateMultimediaRequestBody mockRequestBody = MultimediaTestHelper.generateUpdateMultimediaRequestBody(mockNewTitle);
        final String requestBody = generateRequestBody(mockRequestBody);
        doThrow(new MarsRoverApiException(MarsRoverApiError.UNKNOWN_RESOURCE, "Entity was not found."))
            .when(multimediaService)
            .updateMultimediaTitle(any(UpdateMultimediaParameter.class));

        // when
        final ResultActions result = mvc.perform(patch(MULTIMEDIA_BY_ID_URI, mockMultimediaIdToUpdate)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(requestBody));

        // then
        result.andExpect(MockMvcResultMatchers.status().isNotFound());
        result.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString(RESOURCE_NOT_FOUND)));
        verify(multimediaService, times(1)).updateMultimediaTitle(any(UpdateMultimediaParameter.class));
        verifyNoMoreInteractions(multimediaService);
    }

    // update multimedia title - nok (no body)
    @Test
    void givenNullRequestBody_whenUpdateMultimediaTitle_thenReturnBadRequest() throws Exception
    {
        // given
        final String requestBody = "{}";
        final long mockMultimediaIdToUpdate = MOCK_ID1;

        // when
        final ResultActions result = mvc.perform(patch(MULTIMEDIA_BY_ID_URI, mockMultimediaIdToUpdate)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(requestBody));

        // then
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());
        result.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString(INVALID_REQUEST)));
        verifyZeroInteractions(multimediaService);
    }

    // update multimedia title - nok (invalid body - empty string)
    @Test
    void givenInvalidRequestBody_whenUpdateMultimediaTitle_thenReturnBadRequest() throws Exception
    {
        // given
        final long mockMultimediaIdToUpdate = MOCK_ID1;
        final UpdateMultimediaRequestBody mockRequestBody = MultimediaTestHelper.generateUpdateMultimediaRequestBody("");
        final String requestBody = generateRequestBody(mockRequestBody);

        // when
        final ResultActions result = mvc.perform(patch(MULTIMEDIA_BY_ID_URI, mockMultimediaIdToUpdate)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(requestBody));

        // then
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());
        result.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString(INVALID_REQUEST)));
        verifyZeroInteractions(multimediaService);
    }

    // delete multimedia - ok
    @Test
    void givenValidRequest_whenDeleteMultimedia_thenReturnNoContent() throws Exception
    {
        // given
        final long mockMultimediaIdToDelete = MOCK_ID1;
        doNothing().when(multimediaService).deleteMultimedia(mockMultimediaIdToDelete);

        // when
        final ResultActions result = mvc.perform(delete(MULTIMEDIA_BY_ID_URI, mockMultimediaIdToDelete)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        // then
        result.andExpect(MockMvcResultMatchers.status().isNoContent());
        result.andExpect(MockMvcResultMatchers.content().string(""));
        verify(multimediaService, times(1)).deleteMultimedia(mockMultimediaIdToDelete);
        verifyNoMoreInteractions(multimediaService);
    }

    // delete multimedia - nok (multimedia id not found)
    @Test
    void givenRequestWithUnknownMultimediaId_whenDeleteMultimedia_thenReturnNotFound() throws Exception
    {
        // given
        final long mockMultimediaIdToDelete = MOCK_UNKNOWN_ID;
        doThrow(new MarsRoverApiException(MarsRoverApiError.UNKNOWN_RESOURCE, "Entity was not found."))
            .when(multimediaService)
            .deleteMultimedia(mockMultimediaIdToDelete);

        // when
        final ResultActions result = mvc.perform(delete(MULTIMEDIA_BY_ID_URI, mockMultimediaIdToDelete)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        // then
        result.andExpect(MockMvcResultMatchers.status().isNotFound());
        result.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString(RESOURCE_NOT_FOUND)));
        verify(multimediaService, times(1)).deleteMultimedia(mockMultimediaIdToDelete);
        verifyNoMoreInteractions(multimediaService);
    }

    private String generateSuccessBody(final List<MRMultimediaRest> multimedia) throws JsonProcessingException
    {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(multimedia);
    }

    private String generateSuccessBody(final MRMultimediaRest multimedia) throws JsonProcessingException
    {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(multimedia);
    }

    private String generateRequestBody(final CreateMultimediaRequestBody requestBody) throws JsonProcessingException
    {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(requestBody);

    }

    private String generateRequestBody(final UpdateMultimediaRequestBody requestBody) throws JsonProcessingException
    {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(requestBody);
    }

}
