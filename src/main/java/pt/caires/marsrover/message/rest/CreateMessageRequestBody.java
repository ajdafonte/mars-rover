package pt.caires.marsrover.message.rest;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.caires.marsrover.message.domain.MRDialect;


@Data
@NoArgsConstructor
@ApiModel(
    value = "CreateMessageRequestBody",
    description = "Request body parameter to create a new message."
)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateMessageRequestBody
{
    @ApiModelProperty(value = "The text of the message.", required = true)
    @NotNull
    @Size(min = 1, max = 256)
    private String text;

    @ApiModelProperty(value = "The dialect by which the message should be transmitted.", required = true)
    @NotNull
    private MRDialect dialect;
}
