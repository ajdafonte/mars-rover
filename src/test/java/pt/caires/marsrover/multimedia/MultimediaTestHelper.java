package pt.caires.marsrover.multimedia;

import java.time.ZonedDateTime;

import pt.caires.marsrover.multimedia.bizz.CreateMultimediaParameter;
import pt.caires.marsrover.multimedia.bizz.UpdateMultimediaParameter;
import pt.caires.marsrover.multimedia.domain.MRMultimedia;
import pt.caires.marsrover.multimedia.domain.MRMultimediaType;
import pt.caires.marsrover.multimedia.rest.CreateMultimediaRequestBody;
import pt.caires.marsrover.multimedia.rest.MRMultimediaRest;
import pt.caires.marsrover.multimedia.rest.UpdateMultimediaRequestBody;


public class MultimediaTestHelper
{
    public static final long MOCK_ID1 = 1L;
    public static final long MOCK_ID2 = 2L;
    public static final long MOCK_UNKNOWN_ID = 10000L;
    public static final String MOCK_TITLE1 = "Special title 1";
    public static final String MOCK_TITLE2 = "Special title 2";
    public static final MRMultimediaType MOCK_TYPE_VIDEO = MRMultimediaType.VIDEO;
    public static final MRMultimediaType MOCK_TYPE_PHOTO = MRMultimediaType.PHOTO;

    public static MRMultimedia generateMultimedia(final MRMultimediaType type, final String title)
    {
        return new MRMultimedia(type, title);
    }

    public static MRMultimedia generateMultimedia(final long id, final MRMultimediaType type, final String title, final ZonedDateTime dateCreated)
    {
        return new MRMultimedia(id, type, title, dateCreated);
    }

    public static MRMultimediaRest generateMultimediaRest(final long id, final MRMultimediaType type, final String title)
    {
        return new MRMultimediaRest(id, type, title);
    }

    public static CreateMultimediaRequestBody generateCreateMultimediaRequestBody(final MRMultimediaType type)
    {
        final CreateMultimediaRequestBody requestBody = new CreateMultimediaRequestBody();
        requestBody.setType(type);
        return requestBody;
    }

    public static CreateMultimediaParameter generateCreateMultimediaParameter(final MRMultimediaType type)
    {
        return new CreateMultimediaParameter(type);
    }

    public static UpdateMultimediaRequestBody generateUpdateMultimediaRequestBody(final String title)
    {
        final UpdateMultimediaRequestBody requestBody = new UpdateMultimediaRequestBody();
        requestBody.setTitle(title);
        return requestBody;
    }

    public static UpdateMultimediaParameter generateUpdateMultimediaParameter(final long id, final String title)
    {
        return new UpdateMultimediaParameter(id, title);
    }

}
