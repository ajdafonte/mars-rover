package pt.caires.marsrover.multimedia.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static pt.caires.marsrover.multimedia.MultimediaTestHelper.MOCK_ID1;
import static pt.caires.marsrover.multimedia.MultimediaTestHelper.MOCK_ID2;
import static pt.caires.marsrover.multimedia.MultimediaTestHelper.MOCK_TITLE1;
import static pt.caires.marsrover.multimedia.MultimediaTestHelper.MOCK_TITLE2;
import static pt.caires.marsrover.multimedia.MultimediaTestHelper.MOCK_TYPE_PHOTO;
import static pt.caires.marsrover.multimedia.MultimediaTestHelper.MOCK_TYPE_VIDEO;

import org.junit.jupiter.api.Test;

import pt.caires.marsrover.multimedia.MultimediaTestHelper;


class MRMultimediaRestTest
{
    // equals ok
    @Test
    void givenTwoEqualMRMultimediaRest_whenCheckIfEquals_thenBothMRMultimediaRestMustBeEquals()
    {
        // given
        final MRMultimediaRest mockMRMultimediaRest1 = MultimediaTestHelper.generateMultimediaRest(MOCK_ID1, MOCK_TYPE_PHOTO, MOCK_TITLE1);
        final MRMultimediaRest mockMRMultimediaRest2 = MultimediaTestHelper.generateMultimediaRest(MOCK_ID1, MOCK_TYPE_PHOTO, MOCK_TITLE1);

        // when + then
        assertEquals(mockMRMultimediaRest1.hashCode(), mockMRMultimediaRest2.hashCode());
        assertEquals(mockMRMultimediaRest1, mockMRMultimediaRest2);
    }

    // equals nok
    @Test
    void givenTwoDifferentMRMultimediaRest_whenCheckIfEquals_thenBothMRMultimediaRestMustNotBeEquals()
    {
        // given
        final MRMultimediaRest mockMRMultimediaRest1 = MultimediaTestHelper.generateMultimediaRest(MOCK_ID1, MOCK_TYPE_PHOTO, MOCK_TITLE1);
        final MRMultimediaRest mockMRMultimediaRest2 = MultimediaTestHelper.generateMultimediaRest(MOCK_ID2, MOCK_TYPE_VIDEO, MOCK_TITLE2);

        // when + then
        assertNotEquals(mockMRMultimediaRest1.hashCode(), mockMRMultimediaRest2.hashCode());
        assertNotEquals(mockMRMultimediaRest1, mockMRMultimediaRest2);
    }

    // toString
    @Test
    void givenMRMultimediaRest_whenCallToString_thenReturnExpectedValue()
    {
        // given
        final MRMultimediaRest mockMRMultimediaRest = MultimediaTestHelper.generateMultimediaRest(MOCK_ID1, MOCK_TYPE_PHOTO, MOCK_TITLE1);
        final String expected = "MRMultimediaRest(" +
            "id=" + mockMRMultimediaRest.getId() +
            ", type=" + mockMRMultimediaRest.getType() +
            ", title=" + mockMRMultimediaRest.getTitle() +
            ')';

        // when
        final String result = mockMRMultimediaRest.toString();

        // then
        assertEquals(expected, result);

    }
}
