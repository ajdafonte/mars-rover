package pt.caires.marsrover.multimedia.repo;

import java.util.List;
import java.util.Optional;

import pt.caires.marsrover.multimedia.domain.MRMultimedia;


/**
 *
 */
public interface MultimediaRepository
{
    /**
     * @return
     */
    List<MRMultimedia> findAll();

    /**
     * @param movement
     * @return
     */
    MRMultimedia save(MRMultimedia movement);

    /**
     * @param id
     * @return
     */
    Optional<MRMultimedia> findById(long id);

    /**
     * @param multimedia
     * @return
     */
    boolean delete(final MRMultimedia multimedia);
}
