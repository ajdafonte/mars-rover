package pt.caires.marsrover.message.repo;

import java.util.List;
import java.util.Optional;

import pt.caires.marsrover.message.domain.MRMessage;


public interface MessageRepository
{
    /**
     * Returns all the messages sent by the Mars Rover.
     *
     * @return a list with all the messages.
     */
    List<MRMessage> findAll();

    /**
     * Return a message item identified by its ID.
     *
     * @param id the id of the message.
     * @return a message item if found, otherwise an empty value.
     */
    Optional<MRMessage> findById(long id);

    /**
     * Saves a message item.
     *
     * @param message a message item.
     * @return the saved message item.
     */
    MRMessage save(MRMessage message);
}
