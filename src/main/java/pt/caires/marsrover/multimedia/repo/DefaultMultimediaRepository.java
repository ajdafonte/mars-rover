package pt.caires.marsrover.multimedia.repo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import pt.caires.marsrover.multimedia.domain.MRMultimedia;


@Component
public class DefaultMultimediaRepository implements MultimediaRepository
{
    private final List<MRMultimedia> multimediaCollection = new ArrayList<>();

    @Override
    public List<MRMultimedia> findAll()
    {
        return multimediaCollection;
    }

    @Override
    public MRMultimedia save(final MRMultimedia multimedia)
    {
        // check if item already exist
        final Optional<MRMultimedia> itemToUpdate = findById(multimedia.getId());
        if (itemToUpdate.isPresent())
        {
            // update scenario
            final int idx = multimediaCollection.indexOf(itemToUpdate.get());
            multimediaCollection.set(idx, multimedia);
        }
        else
        {
            // insert scenario
            multimediaCollection.add(multimedia);
        }

        return multimedia;
    }

    @Override
    public boolean delete(final MRMultimedia multimedia)
    {
        return multimediaCollection.remove(multimedia);
    }

    @Override
    public Optional<MRMultimedia> findById(final long id)
    {
        return multimediaCollection.stream().filter(message -> message.getId() == id).findFirst();
    }
}
