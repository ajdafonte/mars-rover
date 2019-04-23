package pt.caires.marsrover.multimedia.bizz;

import java.util.List;

import pt.caires.marsrover.common.error.MarsRoverApiException;
import pt.caires.marsrover.multimedia.domain.MRMultimedia;


public interface MultimediaService
{
    /**
     * Returns a collection of multimedia items made by the Mars Rover.
     *
     * @return a list of multimedia items.
     */
    List<MRMultimedia> getMultimediaCollection();

    /**
     * Creates a new multimedia item.
     *
     * @param parameter object that contains all the input parameters provided to create a new multimedia item.
     * @return new multimedia item.
     */
    MRMultimedia createMultimedia(CreateMultimediaParameter parameter);

    /**
     * Updates the title of a multimedia item identified by its ID.
     *
     * @param parameter object that contains all the input parameters provided to update an existent multimedia item.
     * @return updated multimedia item
     * @throws MarsRoverApiException if no multimedia item with the given id was found.
     */
    MRMultimedia updateMultimediaTitle(UpdateMultimediaParameter parameter);

    /**
     * Deletes a multimedia item.
     *
     * @param multimediaId the id of the multimedia item.
     * @throws MarsRoverApiException if no multimedia item with the given id was found.
     */
    void deleteMultimedia(long multimediaId);
}
