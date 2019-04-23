package pt.caires.marsrover.config;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import pt.caires.marsrover.common.domain.MultimediaConfig;


@ExtendWith(SpringExtension.class)
@SpringBootTest()
class MarsRoverPropertiesTest
{
    @Autowired
    private MarsRoverProperties properties;

    private static final String MOCK_TITLE_PREFIX = "mars-media-test";

    // Remarks:
    // - This test uses the application.yaml defined in test/resources
    // - Consider also the values defined in the helper class

    // validate load properties
    @Test
    void givenValidAppProperties_whenStartingApp_thenShouldLoadPropertiesCorrectly()
    {
        // given
        final MultimediaConfig expected = generateMultimediaConfig(MOCK_TITLE_PREFIX);

        // when
        final MultimediaConfig result = properties.getMultimediaConfig();

        // then
        assertNotNull(result);
        assertThat(result, is(expected));
        assertThat(result.getTitlePrefix(), is(expected.getTitlePrefix()));
    }

    // validate title prefix
    @Test
    void givenValidAppProperties_whenGetMultimediaTitlePrefix_thenReturnExpectedTitlePrefix()
    {
        // given

        // when
        final String result = properties.getMultimediaPrefixTitle();

        // then
        assertThat(result, is(MOCK_TITLE_PREFIX));
    }

    private MultimediaConfig generateMultimediaConfig(final String titlePrefix)
    {
        final MultimediaConfig multimediaConfig = new MultimediaConfig();
        multimediaConfig.setTitlePrefix(titlePrefix);
        return multimediaConfig;
    }
}
