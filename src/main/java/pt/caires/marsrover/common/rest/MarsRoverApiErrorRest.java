package pt.caires.marsrover.common.rest;

import javax.servlet.http.HttpServletRequest;

import pt.caires.marsrover.common.error.MarsRoverApiError;


/**
 * General error rest response object for the Mars Rover API.
 */
public class MarsRoverApiErrorRest
{
    private final String description;
    private final String url;

    MarsRoverApiErrorRest(final HttpServletRequest request, final MarsRoverApiError error, final String... errorParameters)
    {
        this.description = error.getErrorDescription(errorParameters);
        this.url = request.getRequestURL().toString();
    }

    public String getDescription()
    {
        return description;
    }

    public String getUrl()
    {
        return url;
    }
}
