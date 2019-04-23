package pt.caires.marsrover.multimedia.repo;

import java.util.List;
import java.util.Optional;

import pt.caires.marsrover.common.error.MarsRoverApiException;
import pt.caires.marsrover.multimedia.domain.MRMultimedia;


public interface MultimediaRepository
{
    /**
     * Returns all the multimedia items of the Mars Rover.
     *
     * @return a list with all the multimedia items.
     */
    List<MRMultimedia> findAll();

    /**
     * Inserts or updates a multimedia item.
     *
     * @param multimedia a multimedia item.
     * @return the inserted or updated multimedia item.
     */
    MRMultimedia save(MRMultimedia multimedia);

    /**
     * Return a multimedia item identified by its ID.
     *
     * @param id the id of the multimedia.
     * @return a multimedia item if found, otherwise an empty value.
     * @throws MarsRoverApiException if no multimedia item with the given id was found.
     */
    Optional<MRMultimedia> findById(long id);

    /**
     * Removes a multimedia item.
     *
     * @param multimedia the multimedia item to be removed.
     * @return true if the item was removed successfully, false otherwise.
     * @throws MarsRoverApiException if no multimedia item with the given id was found.
     */
    boolean delete(final MRMultimedia multimedia);
}
