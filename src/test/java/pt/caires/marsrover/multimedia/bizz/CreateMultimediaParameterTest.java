package pt.caires.marsrover.multimedia.bizz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static pt.caires.marsrover.multimedia.MultimediaTestHelper.MOCK_TYPE_PHOTO;
import static pt.caires.marsrover.multimedia.MultimediaTestHelper.MOCK_TYPE_VIDEO;

import org.junit.jupiter.api.Test;

import pt.caires.marsrover.multimedia.MultimediaTestHelper;


class CreateMultimediaParameterTest
{
    // equals ok
    @Test
    void givenTwoEqualCreateMultimediaParameters_whenCheckIfEquals_thenBothCreateMultimediaParametersMustBeEquals()
    {
        // given
        final CreateMultimediaParameter mockCreateMultimediaParameter1 =
            MultimediaTestHelper.generateCreateMultimediaParameter(MOCK_TYPE_PHOTO);
        final CreateMultimediaParameter mockCreateMultimediaParameter2 =
            MultimediaTestHelper.generateCreateMultimediaParameter(MOCK_TYPE_PHOTO);

        // when + then
        assertEquals(mockCreateMultimediaParameter1.hashCode(), mockCreateMultimediaParameter2.hashCode());
        assertEquals(mockCreateMultimediaParameter1, mockCreateMultimediaParameter2);
    }

    // equals nok
    @Test
    void givenTwoDifferentCreateMultimediaParameters_whenCheckIfEquals_thenBothCreateMultimediaParametersMustNotBeEquals()
    {
        // given
        final CreateMultimediaParameter mockCreateMultimediaParameter1 =
            MultimediaTestHelper.generateCreateMultimediaParameter(MOCK_TYPE_PHOTO);
        final CreateMultimediaParameter mockCreateMultimediaParameter2 =
            MultimediaTestHelper.generateCreateMultimediaParameter(MOCK_TYPE_VIDEO);

        // when + then
        assertNotEquals(mockCreateMultimediaParameter1.hashCode(), mockCreateMultimediaParameter2.hashCode());
        assertNotEquals(mockCreateMultimediaParameter1, mockCreateMultimediaParameter2);
    }

    // toString
    @Test
    void givenCreateMultimediaParameter_whenCallToString_thenReturnExpectedValue()
    {
        // given
        final CreateMultimediaParameter mockCreateMultimediaParameter =
            MultimediaTestHelper.generateCreateMultimediaParameter(MOCK_TYPE_PHOTO);
        final String expected = "CreateMultimediaParameter(" +
            "type=" + mockCreateMultimediaParameter.getType() +
            ')';

        // when
        final String result = mockCreateMultimediaParameter.toString();

        // then
        assertEquals(expected, result);
    }
}
