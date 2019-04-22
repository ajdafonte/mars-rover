package pt.caires.marsrover.movement.rest;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel(
    value = "MRMovement",
    description = "A Movement object on the Mars Rover API."
)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MRMovementRest
{
    @ApiModelProperty(value = "The ID of the movement.", readOnly = true)
    private final long id;

    @ApiModelProperty(value = "The geographical coordinate of the movement.")
    private final GeoCoordinateRest geoCoordinate;
}
