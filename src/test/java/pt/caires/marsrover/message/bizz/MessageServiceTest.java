package pt.caires.marsrover.message.bizz;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static pt.caires.marsrover.message.MessageTestHelper.MOCK_DIALECT1;
import static pt.caires.marsrover.message.MessageTestHelper.MOCK_DIALECT2;
import static pt.caires.marsrover.message.MessageTestHelper.MOCK_TEXT1;
import static pt.caires.marsrover.message.MessageTestHelper.MOCK_TEXT2;
import static pt.caires.marsrover.message.MessageTestHelper.MOCK_UNKNOWN_ID;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import pt.caires.marsrover.common.error.MarsRoverApiException;
import pt.caires.marsrover.message.MessageTestHelper;
import pt.caires.marsrover.message.domain.MRMessage;
import pt.caires.marsrover.message.repo.MessageRepository;


@ExtendWith(MockitoExtension.class)
class MessageServiceTest
{
    @Mock
    private MessageRepository messageRepository;

    private MessageService messageService;

    @BeforeEach
    void setUp()
    {
        this.messageService = new DefaultMessageService(messageRepository);
    }

    // get messages - with data
    @Test
    void givenExistentMessages_whenGetMessages_thenReturnAllMessages()
    {
        // given
        final MRMessage mockMessage1 = MessageTestHelper.generateMessage(MOCK_TEXT1, MOCK_DIALECT1);
        final MRMessage mockMessage2 = MessageTestHelper.generateMessage(MOCK_TEXT2, MOCK_DIALECT2);
        final List<MRMessage> expected = Arrays.asList(mockMessage1, mockMessage2);
        when(messageRepository.findAll()).thenReturn(expected);

        // when
        final List<MRMessage> result = messageService.getMessages();

        // then
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertThat(result.size(), is(expected.size()));
        assertThat(result, containsInAnyOrder(mockMessage1, mockMessage2));
        verify(messageRepository, times(1)).findAll();
        verifyNoMoreInteractions(messageRepository);
    }

    // get messages - with no data
    @Test
    void givenNoMessages_whenGetMessages_thenReturnEmptyCollection()
    {
        // given
        when(messageRepository.findAll()).thenReturn(Collections.emptyList());

        // when
        final List<MRMessage> result = messageService.getMessages();

        // then
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(messageRepository, times(1)).findAll();
        verifyNoMoreInteractions(messageRepository);
    }

    // get message by id - ok
    @Test
    void givenMessagesAndExistentId_whenGetMessageById_thenReturnSpecificMessage()
    {
        // given
        final MRMessage mockMessage1 = MessageTestHelper.generateMessage(MOCK_TEXT1, MOCK_DIALECT1);
        final long mockTargetId = mockMessage1.getId();
        when(messageRepository.findById(anyLong())).thenReturn(Optional.of(mockMessage1));

        // when
        final MRMessage result = messageService.getMessage(mockTargetId);

        // then
        assertNotNull(result);
        assertThat(result.getId(), is(mockTargetId));
        assertThat(result.getText(), is(mockMessage1.getText()));
        assertThat(result.getDialect(), is(mockMessage1.getDialect()));
        verify(messageRepository, times(1)).findById(anyLong());
        verifyNoMoreInteractions(messageRepository);
    }

    // get message by id - nok (id not found)
    @Test
    void givenMessagesAndUnknownId_whenGetMessageById_thenThrowSpecificException()
    {
        // given
        when(messageRepository.findById(anyLong())).thenReturn(Optional.empty());

        // when + then
        assertThrows(MarsRoverApiException.class, () -> messageService.getMessage(MOCK_UNKNOWN_ID));
        verify(messageRepository, times(1)).findById(MOCK_UNKNOWN_ID);
        verifyNoMoreInteractions(messageRepository);
    }

    // create message - ok
    @Test
    void givenValidParameter_whenCreateMessage_thenReturnMessageCreated()
    {
        // given
        final CreateMessageParameter mockParameter = MessageTestHelper.generateCreateMessageParameter(MOCK_TEXT1, MOCK_DIALECT1);
        final MRMessage expectedMessage = MessageTestHelper.generateMessage(MOCK_TEXT1, MOCK_DIALECT1);
        when(messageRepository.save(any(MRMessage.class))).thenReturn(expectedMessage);

        // when
        final MRMessage result = messageService.createMessage(mockParameter);

        // then
        assertNotNull(result);
        assertThat(result.getId(), is(expectedMessage.getId()));
        assertThat(result.getText(), is(expectedMessage.getText()));
        assertThat(result.getDialect(), is(expectedMessage.getDialect()));
        verify(messageRepository, times(1)).save(any(MRMessage.class));
        verifyNoMoreInteractions(messageRepository);
    }
}
