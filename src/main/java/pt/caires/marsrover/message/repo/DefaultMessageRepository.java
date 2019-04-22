package pt.caires.marsrover.message.repo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import pt.caires.marsrover.message.domain.MRMessage;


@Component
public class DefaultMessageRepository implements MessageRepository
{
    private final List<MRMessage> messages = new ArrayList<>();

    @Override
    public List<MRMessage> findAll()
    {
        return messages;
    }

    @Override
    public Optional<MRMessage> findById(final long id)
    {
        return messages.stream().filter(message -> message.getId() == id).findFirst();
    }

    @Override
    public MRMessage save(final MRMessage message)
    {
        messages.add(message);
        return message;
    }
}
