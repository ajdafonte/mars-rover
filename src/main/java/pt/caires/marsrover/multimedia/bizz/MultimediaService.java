package pt.caires.marsrover.multimedia.bizz;

import java.util.List;

import pt.caires.marsrover.multimedia.domain.MRMultimedia;


public interface MultimediaService
{
    /**
     * @return
     */
    List<MRMultimedia> getMultimediaCollection();

    /**
     * @param parameter
     * @return
     */
    MRMultimedia createMultimedia(CreateMultimediaParameter parameter);

    /**
     * @param parameter
     * @return
     */
    MRMultimedia updateMultimediaTitle(UpdateMultimediaParameter parameter);

    /**
     * @param multimediaId
     */
    void deleteMultimedia(long multimediaId);
}
