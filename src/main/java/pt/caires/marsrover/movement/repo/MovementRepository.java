package pt.caires.marsrover.movement.repo;

import java.util.List;
import java.util.Optional;

import pt.caires.marsrover.movement.domain.MRMovement;


public interface MovementRepository
{
    /**
     * @return
     */
    List<MRMovement> findAll();

    /**
     * @param movement
     * @return
     */
    MRMovement save(MRMovement movement);

    /**
     * @param id
     * @return
     */
    Optional<MRMovement> findById(long id);
}
