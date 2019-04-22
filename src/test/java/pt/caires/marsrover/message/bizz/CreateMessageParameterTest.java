package pt.caires.marsrover.message.bizz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import pt.caires.marsrover.message.MessageTestHelper;


class CreateMessageParameterTest
{
    // equals ok
    @Test
    void givenTwoEqualCreateMessageParameters_whenCheckIfEquals_thenBothCreateMessageParametersMustBeEquals()
    {
        // given
        final CreateMessageParameter mockCreateMessageParameter1 =
            MessageTestHelper.generateCreateMessageParameter(MessageTestHelper.MOCK_TEXT1, MessageTestHelper.MOCK_DIALECT1);
        final CreateMessageParameter mockCreateMessageParameter2 =
            MessageTestHelper.generateCreateMessageParameter(MessageTestHelper.MOCK_TEXT1, MessageTestHelper.MOCK_DIALECT1);

        // when + then
        assertEquals(mockCreateMessageParameter1.hashCode(), mockCreateMessageParameter2.hashCode());
        assertEquals(mockCreateMessageParameter1, mockCreateMessageParameter2);
    }

    // equals nok
    @Test
    void givenTwoDifferentCreateMessageParameters_whenCheckIfEquals_thenBothCreateMessageParametersMustNotBeEquals()
    {
        // given
        final CreateMessageParameter mockCreateMessageParameter1 =
            MessageTestHelper.generateCreateMessageParameter(MessageTestHelper.MOCK_TEXT1, MessageTestHelper.MOCK_DIALECT1);
        final CreateMessageParameter mockCreateMessageParameter2 =
            MessageTestHelper.generateCreateMessageParameter(MessageTestHelper.MOCK_TEXT2, MessageTestHelper.MOCK_DIALECT2);

        // when + then
        assertNotEquals(mockCreateMessageParameter1.hashCode(), mockCreateMessageParameter2.hashCode());
        assertNotEquals(mockCreateMessageParameter1, mockCreateMessageParameter2);
    }

    // toString
    @Test
    void givenCreateMessageParameter_whenCallToString_thenReturnExpectedValue()
    {
        // given
        final CreateMessageParameter mockCreateMessageParameter =
            MessageTestHelper.generateCreateMessageParameter(MessageTestHelper.MOCK_TEXT1, MessageTestHelper.MOCK_DIALECT1);
        final String expected = "CreateMessageParameter(" +
            "text=" + mockCreateMessageParameter.getText() +
            ", dialect=" + mockCreateMessageParameter.getDialect() +
            ')';

        // when
        final String result = mockCreateMessageParameter.toString();

        // then
        assertEquals(expected, result);

    }
}
