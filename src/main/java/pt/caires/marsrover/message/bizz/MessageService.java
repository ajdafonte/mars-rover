package pt.caires.marsrover.message.bizz;

import java.util.List;

import pt.caires.marsrover.common.error.MarsRoverApiException;
import pt.caires.marsrover.message.domain.MRMessage;


public interface MessageService
{
    /**
     * @return
     */
    List<MRMessage> getMessages();

    /**
     * @param messageId
     * @return
     * @throws MarsRoverApiException
     */
    MRMessage getMessage(long messageId);

    /**
     * @param parameter
     * @return
     */
    MRMessage createMessage(CreateMessageParameter parameter);
}
