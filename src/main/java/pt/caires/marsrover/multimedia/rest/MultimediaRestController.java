package pt.caires.marsrover.multimedia.rest;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
import pt.caires.marsrover.multimedia.bizz.MultimediaService;
import pt.caires.marsrover.multimedia.rest.mapper.MultimediaRestMapper;


@RestController
@RequestMapping(value = "/v1/multimedia", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(
    tags = "Multimedia",
    value = "Resources for managing multimedia items in the Mars Rover."
)
public class MultimediaRestController
{
    private static final Logger LOG = LoggerFactory.getLogger(MultimediaRestController.class);

    private final MultimediaService multimediaService;

    @Autowired
    public MultimediaRestController(final MultimediaService multimediaService)
    {
        this.multimediaService = multimediaService;
    }

    @GetMapping
    @ApiOperation(value = "Retrieve all the multimedia items made by the Mars Rover.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Returns a collection with all the multimedia made by the Mars Rover.")})
    public List<MRMultimediaRest> getMultimedia()
    {
        LOG.info(">> Request received in order to retrieve all the multimedia made by the Mars Rover.");
        return multimediaService.getMultimediaCollection()
            .stream()
            .map(MultimediaRestMapper::makeMultimediaRest)
            .collect(Collectors.toList());
    }

    @PostMapping
    @ApiOperation(value = "Create a new multimedia item in the Mars Rover.")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "A new multimedia item was created with success."),
        @ApiResponse(code = 400, message = "Bad Request.")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public MRMultimediaRest createMultimedia(@RequestBody
                                             @ApiParam(value = "Request body parameter to create a new multimedia item.", required = true)
                                             @Valid final CreateMultimediaRequestBody requestBody)
    {
        LOG.info(">> Request received in order to create a new multimedia in the Mars Rover.");
        return MultimediaRestMapper.makeMultimediaRest(
            multimediaService.createMultimedia(MultimediaRestMapper.makeCreateMultimediaParameter(requestBody)));
    }

    @PatchMapping("/{multimediaId}")
    @ApiOperation(value = "Update the title of a multimedia item in the Mars Rover specified by the ID.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns the updated multimedia item."),
        @ApiResponse(code = 404, message = "The multimedia specified by the ID was not found.")})
    public MRMultimediaRest updateMultimediaTitle(@PathVariable
                                                  @ApiParam(value = "The ID of the multimedia.", required = true) final long multimediaId,
                                                  @RequestBody
                                                  @ApiParam(value = "Request body parameter to update a multimedia item.", required = true)
                                                  @Valid final UpdateMultimediaRequestBody requestBody)
    {
        LOG.info(">> Request received in order to update the title of a multimedia item.");
        return MultimediaRestMapper.makeMultimediaRest(
            multimediaService.updateMultimediaTitle(MultimediaRestMapper.makeUpdateMultimediaParameter(multimediaId, requestBody)));
    }

    @DeleteMapping("/{multimediaId}")
    @ApiOperation(value = "Delete a multimedia item in the Mars Rover specified by the ID.")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "Deleted multimedia item."),
        @ApiResponse(code = 404, message = "The multimedia specified by the ID was not found.")})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMultimedia(@PathVariable
                                 @ApiParam(value = "The ID of the multimedia.", required = true) final long multimediaId)
    {
        LOG.info(">> Request received in order to delete a multimedia item.");
        multimediaService.deleteMultimedia(multimediaId);
    }
}
