package pt.caires.marsrover.common.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;


class MultimediaConfigTest
{
    private static final String MOCK_TITLE_PREFIX1 = "mockTitlePrefix1";
    private static final String MOCK_TITLE_PREFIX2 = "mockTitlePrefix2";

    // equals ok
    @Test
    void givenTwoEqualMultimediaConfig_whenCheckIfEquals_thenBothMultimediaConfigMustBeEquals()
    {
        // given
        final MultimediaConfig mockMultimediaConfig1 = generateMultimediaConfig(MOCK_TITLE_PREFIX1);
        final MultimediaConfig mockMultimediaConfig2 = generateMultimediaConfig(MOCK_TITLE_PREFIX1);

        // when + then
        assertEquals(mockMultimediaConfig1.hashCode(), mockMultimediaConfig2.hashCode());
        assertEquals(mockMultimediaConfig1, mockMultimediaConfig2);
    }

    // equals nok
    @Test
    void givenTwoDifferentMultimediaConfig_whenCheckIfEquals_thenBothMultimediaConfigMustNotBeEquals()
    {
        // given
        final MultimediaConfig mockMultimediaConfig1 = generateMultimediaConfig(MOCK_TITLE_PREFIX1);
        final MultimediaConfig mockMultimediaConfig2 = generateMultimediaConfig(MOCK_TITLE_PREFIX2);

        // when + then
        assertNotEquals(mockMultimediaConfig1.hashCode(), mockMultimediaConfig2.hashCode());
        assertNotEquals(mockMultimediaConfig1, mockMultimediaConfig2);
    }

    // toString
    @Test
    void givenMultimediaConfig_whenCallToString_thenReturnExpectedValue()
    {
        // given
        final MultimediaConfig mockMultimediaConfig = generateMultimediaConfig(MOCK_TITLE_PREFIX1);
        final String expected = "MultimediaConfig(" +
            "titlePrefix=" + mockMultimediaConfig.getTitlePrefix() +
            ')';

        // when
        final String result = mockMultimediaConfig.toString();

        // then
        assertEquals(expected, result);

    }

    private MultimediaConfig generateMultimediaConfig(final String titlePrefix)
    {
        final MultimediaConfig multimediaConfig = new MultimediaConfig();
        multimediaConfig.setTitlePrefix(titlePrefix);
        return multimediaConfig;
    }
}
