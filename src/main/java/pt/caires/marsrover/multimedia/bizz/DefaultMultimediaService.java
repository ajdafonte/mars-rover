package pt.caires.marsrover.multimedia.bizz;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.caires.marsrover.common.error.MarsRoverApiError;
import pt.caires.marsrover.common.error.MarsRoverApiException;
import pt.caires.marsrover.config.MarsRoverProperties;
import pt.caires.marsrover.multimedia.domain.MRMultimedia;
import pt.caires.marsrover.multimedia.repo.MultimediaRepository;


@Service
public class DefaultMultimediaService implements MultimediaService
{
    private final MultimediaRepository multimediaRepository;
    private final MarsRoverProperties marsRoverProperties;

    @Autowired
    public DefaultMultimediaService(final MultimediaRepository multimediaRepository,
                                    final MarsRoverProperties marsRoverProperties)
    {
        this.multimediaRepository = multimediaRepository;
        this.marsRoverProperties = marsRoverProperties;
    }

    @Override
    public List<MRMultimedia> getMultimediaCollection()
    {
        return multimediaRepository.findAll();
    }

    @Override
    public MRMultimedia createMultimedia(final CreateMultimediaParameter parameter)
    {
        final String title = marsRoverProperties.getMultimediaPrefixTitle() + "-" + UUID.randomUUID();
        final MRMultimedia multimediaToSave = new MRMultimedia(parameter.getType(), title);

        return multimediaRepository.save(multimediaToSave);
    }

    @Override
    public MRMultimedia updateMultimediaTitle(final UpdateMultimediaParameter parameter)
    {
        // find multimedia item to update
        final MRMultimedia multimediaToUpdate = findMultimediaChecked(parameter.getId());

        // save updated item
        multimediaToUpdate.setTitle(parameter.getTitle());
        return multimediaRepository.save(multimediaToUpdate);
    }

    @Override
    public void deleteMultimedia(final long multimediaId)
    {
        final MRMultimedia multimediaToDelete = findMultimediaChecked(multimediaId);
        multimediaRepository.delete(multimediaToDelete);
    }

    private MRMultimedia findMultimediaChecked(final long multimediaId) throws MarsRoverApiException
    {
        return multimediaRepository.findById(multimediaId)
            .orElseThrow(() -> new MarsRoverApiException(MarsRoverApiError.UNKNOWN_RESOURCE,
                "Multimedia item was not found."));
    }
}
