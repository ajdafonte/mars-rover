package pt.caires.marsrover.multimedia.bizz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static pt.caires.marsrover.multimedia.MultimediaTestHelper.MOCK_ID1;
import static pt.caires.marsrover.multimedia.MultimediaTestHelper.MOCK_ID2;
import static pt.caires.marsrover.multimedia.MultimediaTestHelper.MOCK_TITLE1;
import static pt.caires.marsrover.multimedia.MultimediaTestHelper.MOCK_TITLE2;

import org.junit.jupiter.api.Test;

import pt.caires.marsrover.multimedia.MultimediaTestHelper;


class UpdateMultimediaParameterTest
{
    // equals ok
    @Test
    void givenTwoEqualUpdateMultimediaParameters_whenCheckIfEquals_thenBothUpdateMultimediaParametersMustBeEquals()
    {
        // given
        final UpdateMultimediaParameter mockUpdateMultimediaParameter1 =
            MultimediaTestHelper.generateUpdateMultimediaParameter(MOCK_ID1, MOCK_TITLE1);
        final UpdateMultimediaParameter mockUpdateMultimediaParameter2 =
            MultimediaTestHelper.generateUpdateMultimediaParameter(MOCK_ID1, MOCK_TITLE1);

        // when + then
        assertEquals(mockUpdateMultimediaParameter1.hashCode(), mockUpdateMultimediaParameter2.hashCode());
        assertEquals(mockUpdateMultimediaParameter1, mockUpdateMultimediaParameter2);
    }

    // equals nok
    @Test
    void givenTwoDifferentUpdateMultimediaParameters_whenCheckIfEquals_thenBothUpdateMultimediaParametersMustNotBeEquals()
    {
        // given
        final UpdateMultimediaParameter mockUpdateMultimediaParameter1 =
            MultimediaTestHelper.generateUpdateMultimediaParameter(MOCK_ID1, MOCK_TITLE1);
        final UpdateMultimediaParameter mockUpdateMultimediaParameter2 =
            MultimediaTestHelper.generateUpdateMultimediaParameter(MOCK_ID2, MOCK_TITLE2);

        // when + then
        assertNotEquals(mockUpdateMultimediaParameter1.hashCode(), mockUpdateMultimediaParameter2.hashCode());
        assertNotEquals(mockUpdateMultimediaParameter1, mockUpdateMultimediaParameter2);
    }

    // toString
    @Test
    void givenUpdateMultimediaParameter_whenCallToString_thenReturnExpectedValue()
    {
        // given
        final UpdateMultimediaParameter mockUpdateMultimediaParameter =
            MultimediaTestHelper.generateUpdateMultimediaParameter(MOCK_ID1, MOCK_TITLE1);
        final String expected = "UpdateMultimediaParameter(" +
            "id=" + mockUpdateMultimediaParameter.getId() +
            ", title=" + mockUpdateMultimediaParameter.getTitle() +
            ')';

        // when
        final String result = mockUpdateMultimediaParameter.toString();

        // then
        assertEquals(expected, result);

    }
}
