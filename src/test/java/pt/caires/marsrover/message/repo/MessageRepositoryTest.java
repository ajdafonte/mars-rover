package pt.caires.marsrover.message.repo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static pt.caires.marsrover.message.MessageTestHelper.MOCK_DIALECT1;
import static pt.caires.marsrover.message.MessageTestHelper.MOCK_DIALECT2;
import static pt.caires.marsrover.message.MessageTestHelper.MOCK_TEXT1;
import static pt.caires.marsrover.message.MessageTestHelper.MOCK_TEXT2;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pt.caires.marsrover.message.MessageTestHelper;
import pt.caires.marsrover.message.domain.MRMessage;


class MessageRepositoryTest
{
    private MessageRepository messageRepository;

    @BeforeEach
    void setUp()
    {
        this.messageRepository = new DefaultMessageRepository();
    }

    // find all - empty repo
    @Test
    void givenEmptyMessages_whenFindAll_thenReturnEmptyCollection()
    {
        // given

        // when
        final List<MRMessage> result = messageRepository.findAll();

        // then
        assertNotNull(result);
        assertThat(result, hasSize(0));
    }

    // find all - existent movements
    @Test
    void givenExistentMessages_whenFindAll_thenReturnMessagesCollection()
    {
        // given
        final MRMessage mockMessage1 = MessageTestHelper.generateMessage(MOCK_TEXT1, MOCK_DIALECT1);
        final MRMessage mockMessage2 = MessageTestHelper.generateMessage(MOCK_TEXT2, MOCK_DIALECT2);
        messageRepository.save(mockMessage1);
        messageRepository.save(mockMessage2);

        // when
        final List<MRMessage> result = messageRepository.findAll();

        // then
        assertNotNull(result);
        assertThat(result, hasSize(2));
        assertThat(result, containsInAnyOrder(mockMessage1, mockMessage2));
    }

    // find by - ok
    @Test
    void givenExistentMessageId_whenFindById_thenReturnExpectedMessage()
    {
        // given
        final MRMessage mockMessage1 = MessageTestHelper.generateMessage(MOCK_TEXT1, MOCK_DIALECT1);
        final MRMessage insertedMessage = messageRepository.save(mockMessage1);
        final long mockTargetId = insertedMessage.getId();

        // when
        final Optional<MRMessage> result = messageRepository.findById(mockTargetId);

        // then
        assertThat(result, is(Optional.of(mockMessage1)));
    }

    // find by - not found
    @Test
    void givenUnknownMessageId_whenFindById_thenEmptyValue()
    {
        // given
        final MRMessage mockMessage1 = MessageTestHelper.generateMessage(MOCK_TEXT1, MOCK_DIALECT1);
        messageRepository.save(mockMessage1);
        final long mockTargetId = MessageTestHelper.MOCK_UNKNOWN_ID;

        // when
        final Optional<MRMessage> result = messageRepository.findById(mockTargetId);

        // then
        assertThat(result, is(Optional.empty()));
    }

    // save
    @Test
    void givenMessage_whenSave_thenReturnSavedMessage()
    {
        // given
        final MRMessage mockMessage1 = MessageTestHelper.generateMessage(MOCK_TEXT1, MOCK_DIALECT1);

        // when
        final MRMessage result = messageRepository.save(mockMessage1);

        // then
        assertNotNull(result);
        assertThat(result, is(mockMessage1));
        assertThat(messageRepository.findAll(), hasSize(1));
    }
}
