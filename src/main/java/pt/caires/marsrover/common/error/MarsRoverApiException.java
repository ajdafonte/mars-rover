package pt.caires.marsrover.common.error;

import java.util.Arrays;


/**
 * Exception which contains an {@link MarsRoverApiError} and is thrown by business logic code.
 * <br>
 * <b>Note:</b> Information hold by this exception will be seen by users of the API.
 */
public class MarsRoverApiException extends RuntimeException
{
    private static final long serialVersionUID = 1L;
    private final MarsRoverApiError error;
    private final String[] errorParameters;

    /**
     * Creates a new API user visible exception.
     *
     * @param error error code that identifies the error
     * @param errorParameters parameters needed according to the <code>error</code> to create a error description
     */
    public MarsRoverApiException(final MarsRoverApiError error, final String... errorParameters)
    {
        super(formatMessage(error, errorParameters));
        this.error = error;
        this.errorParameters = Arrays.copyOf(errorParameters, errorParameters.length);
    }

    private static String formatMessage(final MarsRoverApiError error, final String... errorParameters)
    {
        return error.getErrorCode() + " - " + error.getErrorDescription(errorParameters);
    }

    public MarsRoverApiError getError()
    {
        return error;
    }

    public String[] getErrorParameters()
    {
        return Arrays.copyOf(errorParameters, errorParameters.length);
    }
}

