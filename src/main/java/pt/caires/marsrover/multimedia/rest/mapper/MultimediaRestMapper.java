package pt.caires.marsrover.multimedia.rest.mapper;

import pt.caires.marsrover.multimedia.bizz.CreateMultimediaParameter;
import pt.caires.marsrover.multimedia.bizz.UpdateMultimediaParameter;
import pt.caires.marsrover.multimedia.domain.MRMultimedia;
import pt.caires.marsrover.multimedia.rest.CreateMultimediaRequestBody;
import pt.caires.marsrover.multimedia.rest.MRMultimediaRest;
import pt.caires.marsrover.multimedia.rest.UpdateMultimediaRequestBody;


public class MultimediaRestMapper
{
    public static MRMultimediaRest makeMultimediaRest(final MRMultimedia multimedia)
    {
        if (multimedia != null)
        {
            return new MRMultimediaRest(multimedia.getId(), multimedia.getType(), multimedia.getTitle());
        }

        return null;
    }

    public static CreateMultimediaParameter makeCreateMultimediaParameter(final CreateMultimediaRequestBody requestBody)
    {
        if (requestBody != null)
        {
            return new CreateMultimediaParameter(requestBody.getType());
        }
        return null;
    }

    public static UpdateMultimediaParameter makeUpdateMultimediaParameter(final long multimediaId, final UpdateMultimediaRequestBody requestBody)
    {
        if (requestBody != null)
        {
            return new UpdateMultimediaParameter(multimediaId, requestBody.getTitle());
        }
        return null;
    }
}
