package pt.caires.marsrover.movement.rest;

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
import pt.caires.marsrover.movement.bizz.MovementService;
import pt.caires.marsrover.movement.rest.mapper.MovementRestMapper;


@RestController
@RequestMapping(value = "/v1/movements", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(
    tags = "Movements",
    value = "Resources for accessing and creating movements made by the Mars Rover."
)
public class MovementRestController
{
    private static final Logger LOG = LoggerFactory.getLogger(MovementRestController.class);

    private final MovementService movementService;

    @Autowired
    public MovementRestController(final MovementService movementService)
    {
        this.movementService = movementService;
    }

    @GetMapping
    @ApiOperation(value = "Retrieve all the movements made by the Mars Rover.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Returns a collection with all the movements made by the Mars Rover.")})
    public List<MRMovementRest> getMovements()
    {
        LOG.info(">> Request received in order to retrieve all the movements made by the Mars Rover.");
        return movementService.getMovements()
            .stream()
            .map(MovementRestMapper::makeMovementRest)
            .collect(Collectors.toList());
    }

    @GetMapping("/{movementId}")
    @ApiOperation(value = "Retrieve a movement made by the Mars Rover specified by the ID.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns a movement made by the Mars Rover."),
        @ApiResponse(code = 404, message = "The movement specified by the ID was not found.")})
    public MRMovementRest getMessage(@PathVariable
                                     @ApiParam(value = "The ID of the movement.", required = true) final long movementId)
    {
        LOG.info(">> Request received in order to retrieve a movement made by the Mars Rover specified by the ID: {}", movementId);
        return MovementRestMapper.makeMovementRest(movementService.getMovement(movementId));
    }

    @PostMapping
    @ApiOperation(value = "Create a new movement of the Mars Rover.")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "A new movement was created with success."),
        @ApiResponse(code = 400, message = "Bad Request.")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public MRMovementRest createMovement(@RequestBody
                                         @ApiParam(value = "Coordinate of the new movement.", required = true)
                                         @Valid final GeoCoordinateRest coordinateRest)
    {
        LOG.info(">> Request received in order to create a new movement of the Mars Rover.");
        return MovementRestMapper.makeMovementRest(
            movementService.createMovement(MovementRestMapper.makeGeoCoordinate(coordinateRest)));
    }
}
