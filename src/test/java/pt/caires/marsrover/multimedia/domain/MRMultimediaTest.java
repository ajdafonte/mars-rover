package pt.caires.marsrover.multimedia.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static pt.caires.marsrover.multimedia.MultimediaTestHelper.MOCK_TITLE1;
import static pt.caires.marsrover.multimedia.MultimediaTestHelper.MOCK_TITLE2;
import static pt.caires.marsrover.multimedia.MultimediaTestHelper.MOCK_TYPE_PHOTO;
import static pt.caires.marsrover.multimedia.MultimediaTestHelper.MOCK_TYPE_VIDEO;

import org.junit.jupiter.api.Test;

import pt.caires.marsrover.multimedia.MultimediaTestHelper;


class MRMultimediaTest
{
    // equals nok
    @Test
    void givenTwoMultimedia_whenCheckIfEquals_thenBothMultimediaMustNotBeEquals()
    {
        // given
        final MRMultimedia mockMultimedia1 = MultimediaTestHelper.generateMultimedia(MOCK_TYPE_PHOTO, MOCK_TITLE1);
        final MRMultimedia mockMultimedia2 = MultimediaTestHelper.generateMultimedia(MOCK_TYPE_VIDEO, MOCK_TITLE2);

        // when + then
        assertNotEquals(mockMultimedia1.hashCode(), mockMultimedia2.hashCode());
        assertNotEquals(mockMultimedia1, mockMultimedia2);
    }

    // toString
    @Test
    void givenMultimedia_whenCallToString_thenReturnExpectedValue()
    {
        // given
        final MRMultimedia mockMultimedia =
            MultimediaTestHelper.generateMultimedia(MOCK_TYPE_PHOTO, MOCK_TITLE1);
        final String expected = "MRMultimedia(" +
            "id=" + mockMultimedia.getId() +
            ", type=" + mockMultimedia.getType() +
            ", title=" + mockMultimedia.getTitle() +
            ", dateCreated=" + mockMultimedia.getDateCreated() +
            ')';

        // when
        final String result = mockMultimedia.toString();

        // then
        assertEquals(expected, result);
    }
}
