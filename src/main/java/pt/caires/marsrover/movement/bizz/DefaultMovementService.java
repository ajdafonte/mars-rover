package pt.caires.marsrover.movement.bizz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.caires.marsrover.common.error.MarsRoverApiError;
import pt.caires.marsrover.common.error.MarsRoverApiException;
import pt.caires.marsrover.movement.domain.GeoCoordinate;
import pt.caires.marsrover.movement.domain.MRMovement;
import pt.caires.marsrover.movement.repo.MovementRepository;


@Service
public class DefaultMovementService implements MovementService
{
    private final MovementRepository movementRepository;

    @Autowired
    public DefaultMovementService(final MovementRepository movementRepository)
    {
        this.movementRepository = movementRepository;
    }

    @Override
    public List<MRMovement> getMovements()
    {
        return movementRepository.findAll();
    }

    @Override
    public MRMovement createMovement(final GeoCoordinate geoCoordinate)
    {
        return movementRepository.save(new MRMovement(geoCoordinate));
    }

    @Override
    public MRMovement getMovement(final long movementId)
    {
        return movementRepository.findById(movementId).orElseThrow(() -> new MarsRoverApiException(MarsRoverApiError.UNKNOWN_RESOURCE,
            "Message was not found."));
    }
}
