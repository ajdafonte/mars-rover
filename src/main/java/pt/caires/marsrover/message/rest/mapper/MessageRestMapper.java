package pt.caires.marsrover.message.rest.mapper;

import pt.caires.marsrover.message.bizz.CreateMessageParameter;
import pt.caires.marsrover.message.domain.MRMessage;
import pt.caires.marsrover.message.rest.CreateMessageRequestBody;
import pt.caires.marsrover.message.rest.MRMessageRest;


public class MessageRestMapper
{
    public static CreateMessageParameter makeCreateMessageParameter(final CreateMessageRequestBody requestBody)
    {
        if (requestBody != null)
        {
            return new CreateMessageParameter(requestBody.getText(), requestBody.getDialect());
        }
        return null;
    }

    public static MRMessageRest makeMessageRest(final MRMessage mrMessage)
    {
        if (mrMessage != null)
        {
            return new MRMessageRest(mrMessage.getId(), mrMessage.getText(), mrMessage.getDialect());
        }

        return null;
    }
}
