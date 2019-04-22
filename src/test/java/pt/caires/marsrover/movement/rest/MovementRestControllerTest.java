package pt.caires.marsrover.movement.rest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static pt.caires.marsrover.movement.MovementTestHelper.MOCK_INVALID_LATITUDE;
import static pt.caires.marsrover.movement.MovementTestHelper.MOCK_INVALID_LONGITUDE;
import static pt.caires.marsrover.movement.MovementTestHelper.MOCK_LATITUDE1;
import static pt.caires.marsrover.movement.MovementTestHelper.MOCK_LATITUDE2;
import static pt.caires.marsrover.movement.MovementTestHelper.MOCK_LONGITUDE1;
import static pt.caires.marsrover.movement.MovementTestHelper.MOCK_LONGITUDE2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pt.caires.marsrover.common.error.MarsRoverApiError;
import pt.caires.marsrover.common.error.MarsRoverApiException;
import pt.caires.marsrover.movement.MovementTestHelper;
import pt.caires.marsrover.movement.bizz.MovementService;
import pt.caires.marsrover.movement.domain.GeoCoordinate;
import pt.caires.marsrover.movement.domain.MRMovement;


@ExtendWith(SpringExtension.class)
@WebMvcTest(MovementRestController.class)
class MovementRestControllerTest
{
    private static final String MOVEMENTS_URI = "/v1/movements";
    private static final String MOVEMENT_BY_ID_URI = "/v1/movements/{movementId}";

    private static final String INVALID_REQUEST = "Invalid request";
    private static final String INVALID_REQUEST_PARAMETER = "Invalid request parameter";
    private static final String RESOURCE_NOT_FOUND = "Resource not found";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MovementService movementService;

    // get movements - with data
    @Test
    void givenExistentMovements_whenGetMovements_thenReturnAllMovements() throws Exception
    {
        // given
        final MRMovement mockMovement1 =
            MovementTestHelper.generateMovement(MovementTestHelper.generateGeoCoordinate(MOCK_LATITUDE1, MOCK_LONGITUDE1));
        final MRMovement mockMovement2 =
            MovementTestHelper.generateMovement(MovementTestHelper.generateGeoCoordinate(MOCK_LATITUDE2, MOCK_LONGITUDE2));
        final List<MRMovement> allMovements = Arrays.asList(mockMovement1, mockMovement2);

        final MRMovementRest mockMovementRest1 = MovementTestHelper.generateMovementRest(mockMovement1.getId(),
            MovementTestHelper.generateGeoCoordinateRest(MOCK_LATITUDE1, MOCK_LONGITUDE1));
        final MRMovementRest mockMovementRest2 = MovementTestHelper.generateMovementRest(mockMovement2.getId(),
            MovementTestHelper.generateGeoCoordinateRest(MOCK_LATITUDE2, MOCK_LONGITUDE2));
        final List<MRMovementRest> expectedResult = Arrays.asList(mockMovementRest1, mockMovementRest2);

        doReturn(allMovements).when(movementService).getMovements();
        final String expectedContent = generateSuccessBody(expectedResult);

        // when
        final ResultActions result = mvc.perform(get(MOVEMENTS_URI)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());
        result.andExpect(MockMvcResultMatchers.content().string(expectedContent));
        verify(movementService, times(1)).getMovements();
        verifyNoMoreInteractions(movementService);
    }

    // get movements - with no data
    @Test
    void givenEmptyMovements_whenGetMovements_thenReturnEmptyResult() throws Exception
    {
        // given
        final List emptyList = Collections.emptyList();
        doReturn(emptyList).when(movementService).getMovements();
        final String expectedContent = generateSuccessBody(emptyList);

        // when
        final ResultActions result = mvc.perform(get(MOVEMENTS_URI)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());
        result.andExpect(MockMvcResultMatchers.content().string(expectedContent));
        verify(movementService, times(1)).getMovements();
        verifyNoMoreInteractions(movementService);
    }

    // get movement by id - expected id
    @Test
    void givenExistentId_whenGetMovement_thenReturnExistentMovement() throws Exception
    {
        // given
        final MRMovement mockMovement =
            MovementTestHelper.generateMovement(MovementTestHelper.generateGeoCoordinate(MOCK_LATITUDE1, MOCK_LONGITUDE1));
        final MRMovementRest expectedMovement = MovementTestHelper.generateMovementRest(mockMovement.getId(),
            MovementTestHelper.generateGeoCoordinateRest(MOCK_LATITUDE1, MOCK_LONGITUDE1));
        doReturn(mockMovement).when(movementService).getMovement(anyLong());
        final String expectedContent = generateSuccessBody(expectedMovement);

        // when
        final ResultActions result = mvc.perform(get(MOVEMENT_BY_ID_URI, expectedMovement.getId())
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());
        result.andExpect(MockMvcResultMatchers.content().string(expectedContent));
        verify(movementService, times(1)).getMovement(mockMovement.getId());
        verifyNoMoreInteractions(movementService);
    }

    // get movement by id - invalid id
    @Test
    void givenInvalidId_whenGetMovement_thenReturnBadRequest() throws Exception
    {
        // given
        final String unknownCustomerId = "lol";

        // then
        final ResultActions result = mvc.perform(get(MOVEMENT_BY_ID_URI, unknownCustomerId)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        // then
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());
        result.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString(INVALID_REQUEST_PARAMETER)));
        verifyZeroInteractions(movementService);
    }

    // get movement by id - unknown id
    @Test
    void givenUnknownId_whenGetMovement_thenReturnNotFound() throws Exception
    {
        // given
        final long unknownCustomerId = MovementTestHelper.MOCK_UNKNOWN_ID;
        doThrow(new MarsRoverApiException(MarsRoverApiError.UNKNOWN_RESOURCE, "Entity was not found."))
            .when(movementService)
            .getMovement(unknownCustomerId);

        // then
        final ResultActions result = mvc.perform(get(MOVEMENT_BY_ID_URI, unknownCustomerId)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        // then
        result.andExpect(MockMvcResultMatchers.status().isNotFound());
        result.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString(RESOURCE_NOT_FOUND)));
        verify(movementService, times(1)).getMovement(unknownCustomerId);
        verifyNoMoreInteractions(movementService);
    }

    // create movement - ok
    @Test
    void givenValidRequest_whenCreateMovement_thenReturnNewMovement() throws Exception
    {
        // given
        final MRMovement mockMovement =
            MovementTestHelper.generateMovement(MovementTestHelper.generateGeoCoordinate(MOCK_LATITUDE1, MOCK_LONGITUDE1));
        doReturn(mockMovement).when(movementService).createMovement(any(GeoCoordinate.class));

        final GeoCoordinateRest mockGeoCoordinateRest = MovementTestHelper.generateGeoCoordinateRest(MOCK_LATITUDE1, MOCK_LONGITUDE1);
        final String requestBody = generateRequestBody(mockGeoCoordinateRest);

        final MRMovementRest expectedResult = MovementTestHelper.generateMovementRest(mockMovement.getId(),
            MovementTestHelper.generateGeoCoordinateRest(MOCK_LATITUDE1, MOCK_LONGITUDE1));
        final String expectedContent = generateSuccessBody(expectedResult);

        // when
        final ResultActions result = mvc.perform(post(MOVEMENTS_URI)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(requestBody));

        // then
        result.andExpect(MockMvcResultMatchers.status().isCreated());
        result.andExpect(MockMvcResultMatchers.content().string(expectedContent));
        verify(movementService, times(1)).createMovement(any(GeoCoordinate.class));
        verifyNoMoreInteractions(movementService);
    }

    // create movement - no body
    @Test
    void givenNullRequestBody_whenCreateMovement_thenReturnBadRequest() throws Exception
    {
        // given
        final String requestBody = "{}";

        // when
        final ResultActions result = mvc.perform(post(MOVEMENTS_URI)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(requestBody));

        // then
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());
        result.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString(INVALID_REQUEST)));
        verifyZeroInteractions(movementService);
    }

    // create movement - invalid body (body with missing values)
    @Test
    void givenIncompleteRequestBody_whenInsertNewCustomer_thenReturnBadRequest() throws Exception
    {
        // given
        final GeoCoordinateRest mocGeoCoordinateRest = MovementTestHelper.generateGeoCoordinateRest(MOCK_LATITUDE1, null);
        final String requestBody = generateRequestBody(mocGeoCoordinateRest);

        // when
        final ResultActions result = mvc.perform(post(MOVEMENTS_URI)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(requestBody));

        // then
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());
        result.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString(INVALID_REQUEST)));
        verifyZeroInteractions(movementService);
    }

    // create movement - body with invalid value - semantics
    @Test
    void givenRequestBodyWithInvalidValues_whenInsertNewCustomer_thenReturnBadRequest() throws Exception
    {
        // given
        final GeoCoordinateRest mocGeoCoordinateRest = MovementTestHelper.generateGeoCoordinateRest(MOCK_INVALID_LATITUDE, MOCK_INVALID_LONGITUDE);
        final String requestBody = generateRequestBody(mocGeoCoordinateRest);

        // when
        final ResultActions result = mvc.perform(post(MOVEMENTS_URI)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(requestBody));

        // then
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());
        result.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString(INVALID_REQUEST)));
        verifyZeroInteractions(movementService);
    }

    private String generateSuccessBody(final List<MRMovementRest> movements) throws JsonProcessingException
    {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(movements);
    }

    private String generateSuccessBody(final MRMovementRest movement) throws JsonProcessingException
    {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(movement);
    }

    private String generateRequestBody(final GeoCoordinateRest geoCoordinateRest) throws JsonProcessingException
    {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(geoCoordinateRest);

    }

}
