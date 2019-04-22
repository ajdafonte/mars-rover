package pt.caires.marsrover.movement.rest;

import java.math.BigDecimal;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@ApiModel(
    value = "GeoCoordinate",
    description = "A Geographical Coordinate object on the Mars Rover API."
)
public class GeoCoordinateRest
{
    @ApiModelProperty(value = "The latitude value of the geographical coordinate.")
    @NotNull
    @Min(-90)
    @Max(90)
    private BigDecimal latitude;

    @ApiModelProperty(value = "The longitude value of the geographical coordinate.")
    @NotNull
    @Min(0)
    @Max(360)
    private BigDecimal longitude;
}
