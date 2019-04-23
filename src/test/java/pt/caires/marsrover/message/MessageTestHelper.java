package pt.caires.marsrover.message;

import pt.caires.marsrover.message.bizz.CreateMessageParameter;
import pt.caires.marsrover.message.domain.MRDialect;
import pt.caires.marsrover.message.domain.MRMessage;
import pt.caires.marsrover.message.rest.CreateMessageRequestBody;
import pt.caires.marsrover.message.rest.MRMessageRest;


public class MessageTestHelper
{
    public static final long MOCK_ID1 = 1L;
    public static final long MOCK_ID2 = 2L;
    public static final long MOCK_UNKNOWN_ID = 10000L;
    public static final String MOCK_TEXT1 = "To infinity... and beyond!";
    public static final String MOCK_TEXT2 = "Reach for the sky!";
    public static final String MOCK_INVALID_TEXT = "";
    public static final MRDialect MOCK_DIALECT1 = MRDialect.JAMAICA;
    public static final MRDialect MOCK_DIALECT2 = MRDialect.UK;

    public static MRMessage generateMessage(final String text, final MRDialect dialect)
    {
        return new MRMessage(text, dialect);
    }

    public static MRMessageRest generateMessageRest(final long id, final String text, final MRDialect dialect)
    {
        return new MRMessageRest(id, text, dialect);
    }

    public static CreateMessageRequestBody generateCreateMessageRequestBody(final String text, final MRDialect dialect)
    {
        final CreateMessageRequestBody requestBody = new CreateMessageRequestBody();
        requestBody.setText(text);
        requestBody.setDialect(dialect);
        return requestBody;
    }

    public static CreateMessageParameter generateCreateMessageParameter(final String text, final MRDialect dialect)
    {
        return new CreateMessageParameter(text, dialect);
    }
}
