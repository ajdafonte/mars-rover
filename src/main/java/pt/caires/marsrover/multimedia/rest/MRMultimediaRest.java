package pt.caires.marsrover.multimedia.rest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import pt.caires.marsrover.multimedia.domain.MRMultimediaType;


@Data
@ApiModel(
    value = "MRMultimediat",
    description = "A Multimedia object on the Mars Rover API."
)
public class MRMultimediaRest
{
    @ApiModelProperty(value = "The ID of the multimedia item.", readOnly = true)
    private final long id;

    @ApiModelProperty(value = "The type of the multimedia item.")
    private final MRMultimediaType type;

    @ApiModelProperty(value = "The title of the multimedia item.")
    private final String title;
}
