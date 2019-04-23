package pt.caires.marsrover.multimedia.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static pt.caires.marsrover.multimedia.MultimediaTestHelper.MOCK_TYPE_PHOTO;
import static pt.caires.marsrover.multimedia.MultimediaTestHelper.MOCK_TYPE_VIDEO;

import org.junit.jupiter.api.Test;

import pt.caires.marsrover.multimedia.MultimediaTestHelper;


class CreateMultimediaRequestBodyTest
{
    // equals ok
    @Test
    void givenTwoEqualCreateMultimediaRequestBody_whenCheckIfEquals_thenBothCreateMultimediaRequestBodyMustBeEquals()
    {
        // given
        final CreateMultimediaRequestBody mockCreateMultimediaRequestBody1 =
            MultimediaTestHelper.generateCreateMultimediaRequestBody(MOCK_TYPE_PHOTO);
        final CreateMultimediaRequestBody mockCreateMultimediaRequestBody2 =
            MultimediaTestHelper.generateCreateMultimediaRequestBody(MOCK_TYPE_PHOTO);

        // when + then
        assertEquals(mockCreateMultimediaRequestBody1.hashCode(), mockCreateMultimediaRequestBody2.hashCode());
        assertEquals(mockCreateMultimediaRequestBody1, mockCreateMultimediaRequestBody2);
    }

    // equals nok
    @Test
    void givenTwoDifferentCreateMultimediaRequestBody_whenCheckIfEquals_thenBothCreateMultimediaRequestBodyMustNotBeEquals()
    {
        // given
        final CreateMultimediaRequestBody mockCreateMultimediaRequestBody1 =
            MultimediaTestHelper.generateCreateMultimediaRequestBody(MOCK_TYPE_PHOTO);
        final CreateMultimediaRequestBody mockCreateMultimediaRequestBody2 =
            MultimediaTestHelper.generateCreateMultimediaRequestBody(MOCK_TYPE_VIDEO);

        // when + then
        assertNotEquals(mockCreateMultimediaRequestBody1.hashCode(), mockCreateMultimediaRequestBody2.hashCode());
        assertNotEquals(mockCreateMultimediaRequestBody1, mockCreateMultimediaRequestBody2);
    }

    // toString
    @Test
    void givenCreateMultimediaRequestBody_whenCallToString_thenReturnExpectedValue()
    {
        // given
        final CreateMultimediaRequestBody mockCreateMultimediaRequestBody =
            MultimediaTestHelper.generateCreateMultimediaRequestBody(MOCK_TYPE_PHOTO);
        final String expected = "CreateMultimediaRequestBody(" +
            "type=" + mockCreateMultimediaRequestBody.getType() +
            ')';

        // when
        final String result = mockCreateMultimediaRequestBody.toString();

        // then
        assertEquals(expected, result);

    }
}
