package pt.caires.marsrover.movement.repo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static pt.caires.marsrover.movement.MovementTestHelper.MOCK_LATITUDE1;
import static pt.caires.marsrover.movement.MovementTestHelper.MOCK_LATITUDE2;
import static pt.caires.marsrover.movement.MovementTestHelper.MOCK_LONGITUDE1;
import static pt.caires.marsrover.movement.MovementTestHelper.MOCK_LONGITUDE2;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pt.caires.marsrover.movement.MovementTestHelper;
import pt.caires.marsrover.movement.domain.GeoCoordinate;
import pt.caires.marsrover.movement.domain.MRMovement;


class MovementRepositoryTest
{
    private MovementRepository movementRepository;

    @BeforeEach
    void setUp()
    {
        this.movementRepository = new DefaultMovementRepository();
    }

    // find all - empty repo
    @Test
    void givenEmptyMovements_whenFindAll_thenReturnEmptyCollection()
    {
        // given

        // when
        final List<MRMovement> result = movementRepository.findAll();

        // then
        assertNotNull(result);
        assertThat(result, hasSize(0));
    }

    // find all - existent movements
    @Test
    void givenExistentMovements_whenFindAll_thenReturnMovementCollection()
    {
        // given
        final GeoCoordinate mockGeoCoordinate1 = MovementTestHelper.generateGeoCoordinate(MOCK_LATITUDE1, MOCK_LONGITUDE1);
        final GeoCoordinate mockGeoCoordinate2 = MovementTestHelper.generateGeoCoordinate(MOCK_LATITUDE2, MOCK_LONGITUDE2);
        final MRMovement mockMovement1 = MovementTestHelper.generateMovement(mockGeoCoordinate1);
        final MRMovement mockMovement2 = MovementTestHelper.generateMovement(mockGeoCoordinate2);
        movementRepository.save(mockMovement1);
        movementRepository.save(mockMovement2);

        // when
        final List<MRMovement> result = movementRepository.findAll();

        // then
        assertNotNull(result);
        assertThat(result, hasSize(2));
        assertThat(result, containsInAnyOrder(mockMovement1, mockMovement2));
    }

    // find by - ok
    @Test
    void givenExistentMovementId_whenFindById_thenReturnExpectedMovement()
    {
        // given
        final GeoCoordinate mockGeoCoordinate1 = MovementTestHelper.generateGeoCoordinate(MOCK_LATITUDE1, MOCK_LONGITUDE1);
        final MRMovement mockMovement1 = MovementTestHelper.generateMovement(mockGeoCoordinate1);
        final MRMovement insertedMovement = movementRepository.save(mockMovement1);
        final long mockTargetId = insertedMovement.getId();

        // when
        final Optional<MRMovement> result = movementRepository.findById(mockTargetId);

        // then
        assertThat(result, is(Optional.of(mockMovement1)));
    }

    // find by - not found
    @Test
    void givenUnknownMovementId_whenFindById_thenReturnEmptyValue()
    {
        // given
        final GeoCoordinate mockGeoCoordinate1 = MovementTestHelper.generateGeoCoordinate(MOCK_LATITUDE1, MOCK_LONGITUDE1);
        final MRMovement mockMovement1 = MovementTestHelper.generateMovement(mockGeoCoordinate1);
        movementRepository.save(mockMovement1);
        final long mockTargetId = MovementTestHelper.MOCK_UNKNOWN_ID;

        // when
        final Optional<MRMovement> result = movementRepository.findById(mockTargetId);

        // then
        assertThat(result, is(Optional.empty()));
    }

    // save
    @Test
    void givenMovement_whenSaveMovement_thenReturnSavedMovement()
    {
        // given
        final GeoCoordinate mockGeoCoordinate1 = MovementTestHelper.generateGeoCoordinate(MOCK_LATITUDE1, MOCK_LONGITUDE1);
        final MRMovement mockMovement1 = MovementTestHelper.generateMovement(mockGeoCoordinate1);

        // when
        final MRMovement result = movementRepository.save(mockMovement1);

        // then
        assertNotNull(result);
        assertThat(result, is(mockMovement1));
        assertThat(movementRepository.findAll(), hasSize(1));
    }
}
