package pt.caires.marsrover.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import pt.caires.marsrover.common.domain.MultimediaConfig;


@Configuration
@ConfigurationProperties(prefix = "marsrover")
public class MarsRoverProperties
{
    private MultimediaConfig multimediaConfig;

    public MultimediaConfig getMultimediaConfig()
    {
        return multimediaConfig;
    }

    public void setMultimediaConfig(final MultimediaConfig multimediaConfig)
    {
        this.multimediaConfig = multimediaConfig;
    }

    public String getMultimediaPrefixTitle()
    {
        return multimediaConfig.getTitlePrefix();
    }
}
