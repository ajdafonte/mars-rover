package pt.caires.marsrover.message.rest.mapper;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static pt.caires.marsrover.message.MessageTestHelper.MOCK_DIALECT1;
import static pt.caires.marsrover.message.MessageTestHelper.MOCK_TEXT1;

import org.junit.jupiter.api.Test;

import pt.caires.marsrover.message.MessageTestHelper;
import pt.caires.marsrover.message.bizz.CreateMessageParameter;
import pt.caires.marsrover.message.domain.MRMessage;
import pt.caires.marsrover.message.rest.CreateMessageRequestBody;
import pt.caires.marsrover.message.rest.MRMessageRest;


class MessageRestMapperTest
{
    // map messagerest - ok
    @Test
    void givenValidMessage_whenMapToRest_thenReturnMessageRestObject()
    {
        // given
        final MRMessage mockMessage = MessageTestHelper.generateMessage(MOCK_TEXT1, MOCK_DIALECT1);
        final MRMessageRest expectedMessageRest = MessageTestHelper.generateMessageRest(mockMessage.getId(), MOCK_TEXT1, MOCK_DIALECT1);

        // when
        final MRMessageRest result = MessageRestMapper.makeMRMessageRest(mockMessage);

        // then
        assertNotNull(result);
        assertThat(result.getId(), is(expectedMessageRest.getId()));
        assertThat(result.getText(), is(expectedMessageRest.getText()));
        assertThat(result.getDialect(), is(expectedMessageRest.getDialect()));
    }

    // map messagerest - null
    @Test
    void givenNullMessage_whenMapToRest_thenReturnNullValue()
    {
        // given + when + then
        assertNull(MessageRestMapper.makeMRMessageRest(null));
    }

    // map createmessageparameter - ok
    @Test
    void givenValidCreateMessageRequestBody_whenMapToBizz_thenReturnCreateMessageParameterObject()
    {
        // given
        final CreateMessageRequestBody mockRequestBody = MessageTestHelper.generateCreateMessageRequestBody(MOCK_TEXT1, MOCK_DIALECT1);
        final CreateMessageParameter expectedParameter = MessageTestHelper.generateCreateMessageParameter(MOCK_TEXT1, MOCK_DIALECT1);

        // when
        final CreateMessageParameter result = MessageRestMapper.makeCreateMessageParameter(mockRequestBody);

        // then
        assertNotNull(result);
        assertThat(result.getText(), is(expectedParameter.getText()));
        assertThat(result.getDialect(), is(expectedParameter.getDialect()));
    }

    // map createmessageparameter - null
    @Test
    void givenNullCreateMessageRequestBody_whenMapToBizz_thenReturnNullValue()
    {
        // given + when + then
        assertNull(MessageRestMapper.makeCreateMessageParameter(null));
    }
}
