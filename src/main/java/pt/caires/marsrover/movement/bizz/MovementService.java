package pt.caires.marsrover.movement.bizz;

import java.util.List;

import pt.caires.marsrover.movement.domain.GeoCoordinate;
import pt.caires.marsrover.movement.domain.MRMovement;


public interface MovementService
{
    /**
     * @return
     */
    List<MRMovement> getMovements();

    /**
     * @param geoCoordinate
     * @return
     */
    MRMovement createMovement(GeoCoordinate geoCoordinate);

    /**
     * @param movementId
     * @return
     * @throws
     */
    MRMovement getMovement(long movementId);
}
