package pt.caires.marsrover.message.rest;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import pt.caires.marsrover.message.domain.MRDialect;


@Data
@ApiModel(
    value = "MRMessage",
    description = "A Message object on the Mars Rover API."
)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MRMessageRest
{
    @ApiModelProperty(value = "The ID of the message.", readOnly = true)
    private final long id;

    @ApiModelProperty(value = "The text of the movement.")
    private final String text;

    @ApiModelProperty(value = "The dialect by which the message will be transmitted.")
    private final MRDialect dialect;
}
