package pt.caires.marsrover.movement.repo;

import java.util.List;
import java.util.Optional;

import pt.caires.marsrover.movement.domain.MRMovement;


public interface MovementRepository
{
    /**
     * Returns all the movements made by the Mars Rover.
     *
     * @return a list with all the movements.
     */
    List<MRMovement> findAll();

    /**
     * Saves a movement item.
     *
     * @param movement a movement item.
     * @return the saved movement item.
     */
    MRMovement save(MRMovement movement);

    /**
     * Return a movement item identified by its ID.
     *
     * @param id the id of the movement.
     * @return a movement item if found, otherwise an empty value.
     */
    Optional<MRMovement> findById(long id);
}
