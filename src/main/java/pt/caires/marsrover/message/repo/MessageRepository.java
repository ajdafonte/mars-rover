package pt.caires.marsrover.message.repo;

import java.util.List;
import java.util.Optional;

import pt.caires.marsrover.message.domain.MRMessage;


/**
 *
 */
public interface MessageRepository
{
    /**
     * @return
     */
    List<MRMessage> findAll();

    /**
     * @param id
     * @return
     */
    Optional<MRMessage> findById(long id);

    /**
     * @param movement
     * @return
     */
    MRMessage save(MRMessage movement);
}
