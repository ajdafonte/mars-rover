package pt.caires.marsrover.multimedia.bizz;

import lombok.Data;
import pt.caires.marsrover.multimedia.domain.MRMultimediaType;


@Data
public class CreateMultimediaParameter
{
    private final MRMultimediaType type;
}
