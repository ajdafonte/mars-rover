package pt.caires.marsrover.multimedia.rest.mapper;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static pt.caires.marsrover.multimedia.MultimediaTestHelper.MOCK_ID1;
import static pt.caires.marsrover.multimedia.MultimediaTestHelper.MOCK_TITLE1;
import static pt.caires.marsrover.multimedia.MultimediaTestHelper.MOCK_TYPE_PHOTO;

import org.junit.jupiter.api.Test;

import pt.caires.marsrover.multimedia.MultimediaTestHelper;
import pt.caires.marsrover.multimedia.bizz.CreateMultimediaParameter;
import pt.caires.marsrover.multimedia.bizz.UpdateMultimediaParameter;
import pt.caires.marsrover.multimedia.domain.MRMultimedia;
import pt.caires.marsrover.multimedia.rest.CreateMultimediaRequestBody;
import pt.caires.marsrover.multimedia.rest.MRMultimediaRest;
import pt.caires.marsrover.multimedia.rest.UpdateMultimediaRequestBody;


class MultimediaRestMapperTest
{
    // map multimediarest - ok
    @Test
    void givenValidMultimedia_whenMapToRest_thenReturnMultimediaRestObject()
    {
        // given
        final MRMultimedia mockMultimedia = MultimediaTestHelper.generateMultimedia(MOCK_TYPE_PHOTO, MOCK_TITLE1);
        final MRMultimediaRest expectedMultimediaRest =
            MultimediaTestHelper.generateMultimediaRest(mockMultimedia.getId(), MOCK_TYPE_PHOTO, MOCK_TITLE1);

        // when
        final MRMultimediaRest result = MultimediaRestMapper.makeMultimediaRest(mockMultimedia);

        // then
        assertNotNull(result);
        assertThat(result.getId(), is(expectedMultimediaRest.getId()));
        assertThat(result.getType(), is(expectedMultimediaRest.getType()));
        assertThat(result.getTitle(), is(expectedMultimediaRest.getTitle()));
    }

    // map multimediarest - null
    @Test
    void givenNullMultimedia_whenMapToRest_thenReturnNullValue()
    {
        // given + when + then
        assertNull(MultimediaRestMapper.makeMultimediaRest(null));
    }

    // map createmultimediaparameter - ok
    @Test
    void givenValidCreateMultimediaRequestBody_whenMapToBizz_thenReturnCreateMultimediaParameterObject()
    {
        // given
        final CreateMultimediaRequestBody mockRequestBody = MultimediaTestHelper.generateCreateMultimediaRequestBody(MOCK_TYPE_PHOTO);
        final CreateMultimediaParameter expectedParameter = MultimediaTestHelper.generateCreateMultimediaParameter(MOCK_TYPE_PHOTO);

        // when
        final CreateMultimediaParameter result = MultimediaRestMapper.makeCreateMultimediaParameter(mockRequestBody);

        // then
        assertNotNull(result);
        assertThat(result.getType(), is(expectedParameter.getType()));
    }

    // map createmultimediaparameter - null
    @Test
    void givenNullCreateMultimediaRequestBody_whenMapToBizz_thenReturnNullValue()
    {
        // given + when + then
        assertNull(MultimediaRestMapper.makeCreateMultimediaParameter(null));
    }

    // map updatemultimediaparameter - ok
    @Test
    void givenValidUpdateMultimediaRequestBody_whenMapToBizz_thenReturnUpdateMultimediaParameterObject()
    {
        // given
        final long mockMultimediaId = MOCK_ID1;
        final UpdateMultimediaRequestBody mockRequestBody = MultimediaTestHelper.generateUpdateMultimediaRequestBody(MOCK_TITLE1);
        final UpdateMultimediaParameter expectedParameter = MultimediaTestHelper.generateUpdateMultimediaParameter(mockMultimediaId, MOCK_TITLE1);

        // when
        final UpdateMultimediaParameter result = MultimediaRestMapper.makeUpdateMultimediaParameter(mockMultimediaId, mockRequestBody);

        // then
        assertNotNull(result);
        assertThat(result.getId(), is(expectedParameter.getId()));
        assertThat(result.getTitle(), is(expectedParameter.getTitle()));
    }

    // map updatemultimediaparameter - null
    @Test
    void givenNullUpdateMultimediaRequestBody_whenMapToBizz_thenReturnNullValue()
    {
        // given + when + then
        assertNull(MultimediaRestMapper.makeUpdateMultimediaParameter(MOCK_ID1, null));
    }
}
