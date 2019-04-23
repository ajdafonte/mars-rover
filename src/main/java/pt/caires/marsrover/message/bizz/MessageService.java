package pt.caires.marsrover.message.bizz;

import java.util.List;

import pt.caires.marsrover.common.error.MarsRoverApiException;
import pt.caires.marsrover.message.domain.MRMessage;


public interface MessageService
{
    /**
     * Returns a collection of messages sent by the Mars Rover.
     *
     * @return a list of messages.
     */
    List<MRMessage> getMessages();

    /**
     * Return a message item sent by the Mars Rover.
     *
     * @param messageId the id of the message.
     * @return a message item.
     * @throws MarsRoverApiException if no message with the given id was found.
     */
    MRMessage getMessage(long messageId);

    /**
     * Creates a new message item.
     *
     * @param parameter object that contains all the input parameters provided to create a new message.
     * @return new message.
     */
    MRMessage createMessage(CreateMessageParameter parameter);
}
