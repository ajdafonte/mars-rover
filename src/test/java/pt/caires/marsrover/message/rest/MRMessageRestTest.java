package pt.caires.marsrover.message.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static pt.caires.marsrover.message.MessageTestHelper.MOCK_DIALECT1;
import static pt.caires.marsrover.message.MessageTestHelper.MOCK_DIALECT2;
import static pt.caires.marsrover.message.MessageTestHelper.MOCK_ID1;
import static pt.caires.marsrover.message.MessageTestHelper.MOCK_ID2;
import static pt.caires.marsrover.message.MessageTestHelper.MOCK_TEXT1;
import static pt.caires.marsrover.message.MessageTestHelper.MOCK_TEXT2;

import org.junit.jupiter.api.Test;

import pt.caires.marsrover.message.MessageTestHelper;


class MRMessageRestTest
{
    // equals ok
    @Test
    void givenTwoEqualMRMessageRest_whenCheckIfEquals_thenBothMRMessageRestMustBeEquals()
    {
        // given
        final MRMessageRest mockMRMessageRest1 = MessageTestHelper.generateMessageRest(MOCK_ID1, MOCK_TEXT1, MOCK_DIALECT1);
        final MRMessageRest mockMRMessageRest2 = MessageTestHelper.generateMessageRest(MOCK_ID1, MOCK_TEXT1, MOCK_DIALECT1);

        // when + then
        assertEquals(mockMRMessageRest1.hashCode(), mockMRMessageRest2.hashCode());
        assertEquals(mockMRMessageRest1, mockMRMessageRest2);
    }

    // equals nok
    @Test
    void givenTwoDifferentMRMessageRest_whenCheckIfEquals_thenBothMRMessageRestMustNotBeEquals()
    {
        // given
        final MRMessageRest mockMRMessageRest1 = MessageTestHelper.generateMessageRest(MOCK_ID1, MOCK_TEXT1, MOCK_DIALECT1);
        final MRMessageRest mockMRMessageRest2 = MessageTestHelper.generateMessageRest(MOCK_ID2, MOCK_TEXT2, MOCK_DIALECT2);

        // when + then
        assertNotEquals(mockMRMessageRest1.hashCode(), mockMRMessageRest2.hashCode());
        assertNotEquals(mockMRMessageRest1, mockMRMessageRest2);
    }

    // toString
    @Test
    void givenMRMessageRest_whenCallToString_thenReturnExpectedValue()
    {
        // given
        final MRMessageRest mockMRMessageRest = MessageTestHelper.generateMessageRest(MOCK_ID1, MOCK_TEXT1, MOCK_DIALECT1);
        final String expected = "MRMessageRest(" +
            "id=" + mockMRMessageRest.getId() +
            ", text=" + mockMRMessageRest.getText() +
            ", dialect=" + mockMRMessageRest.getDialect() +
            ')';

        // when
        final String result = mockMRMessageRest.toString();

        // then
        assertEquals(expected, result);
    }
}
