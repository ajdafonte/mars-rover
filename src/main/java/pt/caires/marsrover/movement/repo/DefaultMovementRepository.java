package pt.caires.marsrover.movement.repo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import pt.caires.marsrover.movement.domain.MRMovement;


@Component
public class DefaultMovementRepository implements MovementRepository
{
    private final List<MRMovement> movements = new ArrayList<>();

    @Override
    public List<MRMovement> findAll()
    {
        return movements;
    }

    @Override
    public MRMovement save(final MRMovement movement)
    {
        movements.add(movement);
        return movement;
    }

    @Override
    public Optional<MRMovement> findById(final long id)
    {
        return movements.stream().filter(message -> message.getId() == id).findFirst();
    }
}
