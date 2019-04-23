package pt.caires.marsrover.multimedia.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static pt.caires.marsrover.multimedia.MultimediaTestHelper.MOCK_TITLE1;
import static pt.caires.marsrover.multimedia.MultimediaTestHelper.MOCK_TITLE2;

import org.junit.jupiter.api.Test;

import pt.caires.marsrover.multimedia.MultimediaTestHelper;


class UpdateMultimediaRequestBodyTest
{
    // equals ok
    @Test
    void givenTwoEqualUpdateMultimediaRequestBody_whenCheckIfEquals_thenBothUpdateMultimediaRequestBodyMustBeEquals()
    {
        // given
        final UpdateMultimediaRequestBody mockUpdateMultimediaRequestBody1 =
            MultimediaTestHelper.generateUpdateMultimediaRequestBody(MOCK_TITLE1);
        final UpdateMultimediaRequestBody mockUpdateMultimediaRequestBody2 =
            MultimediaTestHelper.generateUpdateMultimediaRequestBody(MOCK_TITLE1);

        // when + then
        assertEquals(mockUpdateMultimediaRequestBody1.hashCode(), mockUpdateMultimediaRequestBody2.hashCode());
        assertEquals(mockUpdateMultimediaRequestBody1, mockUpdateMultimediaRequestBody2);
    }

    // equals nok
    @Test
    void givenTwoDifferentUpdateMultimediaRequestBody_whenCheckIfEquals_thenBothUpdateMultimediaRequestBodyMustNotBeEquals()
    {
        // given
        final UpdateMultimediaRequestBody mockUpdateMultimediaRequestBody1 =
            MultimediaTestHelper.generateUpdateMultimediaRequestBody(MOCK_TITLE1);
        final UpdateMultimediaRequestBody mockUpdateMultimediaRequestBody2 =
            MultimediaTestHelper.generateUpdateMultimediaRequestBody(MOCK_TITLE2);

        // when + then
        assertNotEquals(mockUpdateMultimediaRequestBody1.hashCode(), mockUpdateMultimediaRequestBody2.hashCode());
        assertNotEquals(mockUpdateMultimediaRequestBody1, mockUpdateMultimediaRequestBody2);
    }

    // toString
    @Test
    void givenUpdateMultimediaRequestBody_whenCallToString_thenReturnExpectedValue()
    {
        // given
        final UpdateMultimediaRequestBody mockUpdateMultimediaRequestBody =
            MultimediaTestHelper.generateUpdateMultimediaRequestBody(MOCK_TITLE1);
        final String expected = "UpdateMultimediaRequestBody(" +
            "title=" + mockUpdateMultimediaRequestBody.getTitle() +
            ')';

        // when
        final String result = mockUpdateMultimediaRequestBody.toString();

        // then
        assertEquals(expected, result);
    }
}
