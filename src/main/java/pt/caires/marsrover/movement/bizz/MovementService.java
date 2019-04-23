package pt.caires.marsrover.movement.bizz;

import java.util.List;

import pt.caires.marsrover.common.error.MarsRoverApiException;
import pt.caires.marsrover.movement.domain.GeoCoordinate;
import pt.caires.marsrover.movement.domain.MRMovement;


public interface MovementService
{
    /**
     * Returns a collection of messages made by the Mars Rover.
     *
     * @return a list of movements.
     */
    List<MRMovement> getMovements();

    /**
     * Creates a new movement item.
     *
     * @param geoCoordinate object that represent a geographical coordinates.
     * @return new movement.
     */
    MRMovement createMovement(GeoCoordinate geoCoordinate);

    /**
     * Return a movement item made by the Mars Rover.
     *
     * @param movementId the id of the movement.
     * @return a movement item.
     * @throws MarsRoverApiException if no movement with the given id was found.
     */
    MRMovement getMovement(long movementId);
}
