package pt.caires.marsrover.message.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static pt.caires.marsrover.message.MessageTestHelper.MOCK_DIALECT1;
import static pt.caires.marsrover.message.MessageTestHelper.MOCK_DIALECT2;
import static pt.caires.marsrover.message.MessageTestHelper.MOCK_TEXT1;
import static pt.caires.marsrover.message.MessageTestHelper.MOCK_TEXT2;

import org.junit.jupiter.api.Test;

import pt.caires.marsrover.message.MessageTestHelper;


class MRMessageTest
{
    // equals nok
    @Test
    void givenTwoMessages_whenCheckIfEquals_thenBothMessagesMustNotBeEquals()
    {
        // given
        final MRMessage mockMessage1 = MessageTestHelper.generateMessage(MOCK_TEXT1, MOCK_DIALECT1);
        final MRMessage mockMessage2 = MessageTestHelper.generateMessage(MOCK_TEXT2, MOCK_DIALECT2);

        // when + then
        assertNotEquals(mockMessage1.hashCode(), mockMessage2.hashCode());
        assertNotEquals(mockMessage1, mockMessage2);
    }

    // toString
    @Test
    void givenMessage_whenCallToString_thenReturnExpectedValue()
    {
        // given
        final MRMessage mockMessage =
            MessageTestHelper.generateMessage(MOCK_TEXT1, MOCK_DIALECT1);
        final String expected = "MRMessage(" +
            "id=" + mockMessage.getId() +
            ", text=" + mockMessage.getText() +
            ", dialect=" + mockMessage.getDialect() +
            ", dateCreated=" + mockMessage.getDateCreated() +
            ')';

        // when
        final String result = mockMessage.toString();

        // then
        assertEquals(expected, result);
    }
}
