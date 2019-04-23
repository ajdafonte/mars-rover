package pt.caires.marsrover.multimedia.rest;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@ApiModel(
    value = "UpdateMultimediaRequestBody",
    description = "Request body parameter to update a multimedia item."
)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateMultimediaRequestBody
{
    @ApiModelProperty(value = "The new title of the multimedia item.", required = true)
    @NotNull
    @Size(min = 1, max = 256)
    private String title;
}
