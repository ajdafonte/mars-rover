package pt.caires.marsrover.multimedia.rest;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.caires.marsrover.multimedia.domain.MRMultimediaType;


@Data
@NoArgsConstructor
@ApiModel(
    value = "CreateMultimediaRequestBody",
    description = "Request body parameter to create a new multimedia item."
)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateMultimediaRequestBody
{
    @ApiModelProperty(value = "The type of the multimedia.")
    @NotNull
    private MRMultimediaType type;
}
