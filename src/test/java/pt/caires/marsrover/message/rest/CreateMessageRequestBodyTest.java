package pt.caires.marsrover.message.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import pt.caires.marsrover.message.MessageTestHelper;


class CreateMessageRequestBodyTest
{
    // equals ok
    @Test
    void givenTwoEqualCreateMessageRequestBody_whenCheckIfEquals_thenBothCreateMessageRequestBodyMustBeEquals()
    {
        // given
        final CreateMessageRequestBody mockCreateMessageRequestBody1 =
            MessageTestHelper.generateCreateMessageRequestBody(MessageTestHelper.MOCK_TEXT1, MessageTestHelper.MOCK_DIALECT1);
        final CreateMessageRequestBody mockCreateMessageRequestBody2 =
            MessageTestHelper.generateCreateMessageRequestBody(MessageTestHelper.MOCK_TEXT1, MessageTestHelper.MOCK_DIALECT1);

        // when + then
        assertEquals(mockCreateMessageRequestBody1.hashCode(), mockCreateMessageRequestBody2.hashCode());
        assertEquals(mockCreateMessageRequestBody1, mockCreateMessageRequestBody2);
    }

    // equals nok
    @Test
    void givenTwoDifferentCreateMessageRequestBody_whenCheckIfEquals_thenBothCreateMessageRequestBodyMustNotBeEquals()
    {
        // given
        final CreateMessageRequestBody mockCreateMessageRequestBody1 =
            MessageTestHelper.generateCreateMessageRequestBody(MessageTestHelper.MOCK_TEXT1, MessageTestHelper.MOCK_DIALECT1);
        final CreateMessageRequestBody mockCreateMessageRequestBody2 =
            MessageTestHelper.generateCreateMessageRequestBody(MessageTestHelper.MOCK_TEXT2, MessageTestHelper.MOCK_DIALECT2);

        // when + then
        assertNotEquals(mockCreateMessageRequestBody1.hashCode(), mockCreateMessageRequestBody2.hashCode());
        assertNotEquals(mockCreateMessageRequestBody1, mockCreateMessageRequestBody2);
    }

    // toString
    @Test
    void givenCreateMessageRequestBody_whenCallToString_thenReturnExpectedValue()
    {
        // given
        final CreateMessageRequestBody mockCreateMessageRequestBody =
            MessageTestHelper.generateCreateMessageRequestBody(MessageTestHelper.MOCK_TEXT1, MessageTestHelper.MOCK_DIALECT1);
        final String expected = "CreateMessageRequestBody(" +
            "text=" + mockCreateMessageRequestBody.getText() +
            ", dialect=" + mockCreateMessageRequestBody.getDialect() +
            ')';

        // when
        final String result = mockCreateMessageRequestBody.toString();

        // then
        assertEquals(expected, result);

    }
}
