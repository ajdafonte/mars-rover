package pt.caires.marsrover.message.bizz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.caires.marsrover.common.error.MarsRoverApiError;
import pt.caires.marsrover.common.error.MarsRoverApiException;
import pt.caires.marsrover.message.domain.MRMessage;
import pt.caires.marsrover.message.repo.MessageRepository;


@Service
public class DefaultMessageService implements MessageService
{
    private final MessageRepository messageRepository;

    @Autowired
    public DefaultMessageService(final MessageRepository messageRepository)
    {
        this.messageRepository = messageRepository;
    }

    @Override
    public List<MRMessage> getMessages()
    {
        return messageRepository.findAll();
    }

    @Override
    public MRMessage getMessage(final long messageId)
    {
        return messageRepository.findById(messageId).orElseThrow(() -> new MarsRoverApiException(MarsRoverApiError.UNKNOWN_RESOURCE,
            "Message was not found."));
    }

    @Override
    public MRMessage createMessage(final CreateMessageParameter parameter)
    {
        return messageRepository.save(new MRMessage(parameter.getText(), parameter.getDialect()));
    }
}
