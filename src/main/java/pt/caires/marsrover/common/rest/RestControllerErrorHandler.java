package pt.caires.marsrover.common.rest;

import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import pt.caires.marsrover.common.error.MarsRoverApiError;
import pt.caires.marsrover.common.error.MarsRoverApiException;


/**
 * Handles all exceptions for all REST controllers and translates them to a proper error response.
 */
@ControllerAdvice
public class RestControllerErrorHandler
{
    private static final String REQUEST_BODY_MISSING = "Required request body is missing";

    @ExceptionHandler(MarsRoverApiException.class)
    @ResponseBody
    MarsRoverApiErrorRest handleVideoRentalStoreApiException(final HttpServletRequest request,
                                                             final HttpServletResponse response,
                                                             final MarsRoverApiException exception)
    {
        response.setStatus(exception.getError().getHttpStatus().value());
        return new MarsRoverApiErrorRest(request, exception.getError(), exception.getErrorParameters());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    MarsRoverApiErrorRest handleException(final HttpServletRequest request, final Exception e)
    {
        return new MarsRoverApiErrorRest(request, MarsRoverApiError.INTERNAL_SERVER_ERROR);
    }

    /**
     * Thrown when the RequestBody can't be parsed. Either due to malformed JSON or invalid field types.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    MarsRoverApiErrorRest handleHttpMessageNotReadableException(final HttpServletRequest request, final HttpMessageNotReadableException exception)
    {
        if (exception.getCause() instanceof InvalidFormatException)
        {
            return new MarsRoverApiErrorRest(
                request,
                MarsRoverApiError.INVALID_REQUEST_PARAMETER,
                ((InvalidFormatException) exception.getCause()).getPath().get(0).getFieldName());
        }
        else
        {
            final String errorDescription = isMissingBodyException(exception) ? REQUEST_BODY_MISSING : "";
            return new MarsRoverApiErrorRest(request,
                MarsRoverApiError.INVALID_REQUEST_BODY,
                errorDescription);
        }
    }

    /**
     * Thrown when an argument validation fails.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    MarsRoverApiErrorRest handleMethodArgumentNotValidException(final HttpServletRequest request, final MethodArgumentNotValidException exception)
    {
        final String errorMsg = exception.getBindingResult().getFieldErrors().stream()
            .map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage())
            .collect(Collectors.joining(", "));

        return new MarsRoverApiErrorRest(
            request,
            MarsRoverApiError.INVALID_REQUEST,
            errorMsg);
    }

    /**
     * Thrown when a request parameter can't be parsed because of an invalid field type.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    MarsRoverApiErrorRest handleMethodArgumentNotValid(final HttpServletRequest request,
                                                       final MethodArgumentTypeMismatchException ex)
    {
        return new MarsRoverApiErrorRest(request, MarsRoverApiError.INVALID_REQUEST_PARAMETER, ex.getName());
    }

    private boolean isMissingBodyException(final HttpMessageNotReadableException exception)
    {
        return exception.getMessage() != null && exception.getMessage().contains(REQUEST_BODY_MISSING);
    }
}
