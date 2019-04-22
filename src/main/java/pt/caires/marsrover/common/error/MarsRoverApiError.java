package pt.caires.marsrover.common.error;

import org.springframework.http.HttpStatus;

import com.google.common.base.MoreObjects;


/**
 * Holds all specific errors returned by Mars Rover API.
 */
public enum MarsRoverApiError
{
    // Default error code
    INTERNAL_SERVER_ERROR("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR),

    // Common error codes for request handling
    UNKNOWN_RESOURCE("Resource not found. %s", HttpStatus.NOT_FOUND),
    INVALID_REQUEST("Invalid request: %s", HttpStatus.BAD_REQUEST),
    INVALID_REQUEST_BODY("Invalid request body. %s", HttpStatus.BAD_REQUEST),
    INVALID_REQUEST_PARAMETER("Invalid request parameter '%s'", HttpStatus.BAD_REQUEST);

    private final String errorText;
    private final HttpStatus httpStatus;

    MarsRoverApiError(final String errorText, final HttpStatus httpStatus)
    {
        this.errorText = errorText;
        this.httpStatus = httpStatus;
    }

    public String getErrorCode()
    {
        return name();
    }

    public String getErrorDescription(final String... parameters)
    {
        return String.format(errorText, (Object[]) parameters);
    }

    public HttpStatus getHttpStatus()
    {
        return httpStatus;
    }

    @Override
    public String toString()
    {
        return MoreObjects.toStringHelper(this)
            .add("errorText", errorText)
            .add("httpStatus", httpStatus)
            .toString();
    }
}
