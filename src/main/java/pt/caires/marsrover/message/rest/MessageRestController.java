package pt.caires.marsrover.message.rest;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import pt.caires.marsrover.message.bizz.MessageService;
import pt.caires.marsrover.message.rest.mapper.MessageRestMapper;


@RestController
@RequestMapping(value = "/v1/messages", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(
    tags = "Messages",
    value = "Resources for accessing/transmitting messages of/for the Mars Rover."
)
public class MessageRestController
{
    private static final Logger LOG = LoggerFactory.getLogger(MessageRestController.class);

    private final MessageService messageService;

    @Autowired
    public MessageRestController(final MessageService messageService)
    {
        this.messageService = messageService;
    }

    @GetMapping
    @ApiOperation(value = "Retrieve all the messages sent by the Mars Rover.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Returns a collection with all the messages sent by the Mars Rover.")})
    public List<MRMessageRest> getMessages()
    {
        LOG.info(">> Request received in order to retrieve all the messages sent by the Mars Rover.");
        return messageService.getMessages()
            .stream()
            .map(MessageRestMapper::makeMessageRest)
            .collect(Collectors.toList());
    }

    @GetMapping("/{messageId}")
    @ApiOperation(value = "Retrieve a message sent by the Mars Rover specified by the ID.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns a message sent by the Mars Rover."),
        @ApiResponse(code = 404, message = "The message specified by the ID was not found.")})
    public MRMessageRest getMessage(@PathVariable
                                    @ApiParam(value = "The ID of the message.", required = true) final long messageId)
    {
        LOG.info(">> Request received in order to retrieve a message sent by the Mars Rover specified by the ID: {}", messageId);
        return MessageRestMapper.makeMessageRest(messageService.getMessage(messageId));
    }

    @PostMapping
    @ApiOperation(value = "Create a new message so that Mars Rover can transmit it.")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "A new message to be transmitted by the Mars Rovers was created with success."),
        @ApiResponse(code = 400, message = "Bad Request.")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public MRMessageRest createMessage(@RequestBody
                                       @ApiParam(value = "Request body parameter to create a new message.", required = true)
                                       @Valid final CreateMessageRequestBody requestBody)
    {
        LOG.info(">> Request received in order to create a new message so that Mars Rover can transmit it.");
        return MessageRestMapper.makeMessageRest(
            messageService.createMessage(MessageRestMapper.makeCreateMessageParameter(requestBody)));
    }
}
