package pt.caires.marsrover.movement.bizz;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static pt.caires.marsrover.movement.MovementTestHelper.MOCK_LATITUDE1;
import static pt.caires.marsrover.movement.MovementTestHelper.MOCK_LATITUDE2;
import static pt.caires.marsrover.movement.MovementTestHelper.MOCK_LONGITUDE1;
import static pt.caires.marsrover.movement.MovementTestHelper.MOCK_LONGITUDE2;
import static pt.caires.marsrover.movement.MovementTestHelper.MOCK_UNKNOWN_ID;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import pt.caires.marsrover.common.error.MarsRoverApiException;
import pt.caires.marsrover.movement.MovementTestHelper;
import pt.caires.marsrover.movement.domain.GeoCoordinate;
import pt.caires.marsrover.movement.domain.MRMovement;
import pt.caires.marsrover.movement.repo.MovementRepository;


@ExtendWith(MockitoExtension.class)
class MovementServiceTest
{
    @Mock
    private MovementRepository movementRepository;

    private MovementService movementService;

    @BeforeEach
    void setUp()
    {
        this.movementService = new DefaultMovementService(movementRepository);
    }

    // get movements - with data
    @Test
    void givenExistentMovements_whenGetMovements_thenReturnAllMovements()
    {
        // given
        final GeoCoordinate mockGeoCoordinate1 = MovementTestHelper.generateGeoCoordinate(MOCK_LATITUDE1, MOCK_LONGITUDE1);
        final MRMovement mockMovement1 = MovementTestHelper.generateMovement(mockGeoCoordinate1);
        final GeoCoordinate mockGeoCoordinate2 = MovementTestHelper.generateGeoCoordinate(MOCK_LATITUDE2, MOCK_LONGITUDE2);
        final MRMovement mockMovement2 = MovementTestHelper.generateMovement(mockGeoCoordinate2);
        final List<MRMovement> expected = Arrays.asList(mockMovement1, mockMovement2);
        when(movementRepository.findAll()).thenReturn(expected);

        // when
        final List<MRMovement> result = movementService.getMovements();

        // then
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertThat(result.size(), is(expected.size()));
        assertThat(result, containsInAnyOrder(mockMovement1, mockMovement2));
        verify(movementRepository, times(1)).findAll();
        verifyNoMoreInteractions(movementRepository);
    }

    // get movements - with no data
    @Test
    void givenNoMovements_whenGetMovements_thenReturnEmptyCollection()
    {
        // given
        when(movementRepository.findAll()).thenReturn(Collections.emptyList());

        // when
        final List<MRMovement> result = movementService.getMovements();

        // then
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(movementRepository, times(1)).findAll();
        verifyNoMoreInteractions(movementRepository);
    }

    // get movement by id - ok
    @Test
    void givenMovementsAndExistentId_whenGetMovementById_thenReturnSpecificMovement()
    {
        // given
        final GeoCoordinate mockGeoCoordinate1 = MovementTestHelper.generateGeoCoordinate(MOCK_LATITUDE1, MOCK_LONGITUDE1);
        final MRMovement mockMovement1 = MovementTestHelper.generateMovement(mockGeoCoordinate1);
        final long mockTargetId = mockMovement1.getId();
        when(movementRepository.findById(anyLong())).thenReturn(Optional.of(mockMovement1));

        // when
        final MRMovement result = movementService.getMovement(mockTargetId);

        // then
        assertNotNull(result);
        assertThat(result.getId(), is(mockTargetId));
        assertThat(result.getGeoCoordinate(), is(mockGeoCoordinate1));
        verify(movementRepository, times(1)).findById(anyLong());
        verifyNoMoreInteractions(movementRepository);
    }

    // get movement by id - nok (id not found)
    @Test
    void givenMovementsAndUnknownId_whenGetMovementById_thenThrowSpecificException()
    {
        // given
        when(movementRepository.findById(anyLong())).thenReturn(Optional.empty());

        // when + then
        assertThrows(MarsRoverApiException.class, () -> movementService.getMovement(MOCK_UNKNOWN_ID));
        verify(movementRepository, times(1)).findById(MOCK_UNKNOWN_ID);
        verifyNoMoreInteractions(movementRepository);
    }

    // create movement - ok
    @Test
    void givenValidParameter_whenCreateMovement_thenReturnMovementCreated()
    {
        // given
        final GeoCoordinate mockGeoCoordinate1 = MovementTestHelper.generateGeoCoordinate(MOCK_LATITUDE1, MOCK_LONGITUDE1);
        final MRMovement expectedMovement = MovementTestHelper.generateMovement(mockGeoCoordinate1);
        when(movementRepository.save(any(MRMovement.class))).thenReturn(expectedMovement);

        // when
        final MRMovement result = movementService.createMovement(mockGeoCoordinate1);

        // then
        assertNotNull(result);
        assertThat(result.getId(), is(expectedMovement.getId()));
        assertThat(result.getGeoCoordinate(), is(expectedMovement.getGeoCoordinate()));
        verify(movementRepository, times(1)).save(any(MRMovement.class));
        verifyNoMoreInteractions(movementRepository);
    }
}
