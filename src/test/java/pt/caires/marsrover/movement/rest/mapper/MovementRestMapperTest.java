package pt.caires.marsrover.movement.rest.mapper;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static pt.caires.marsrover.movement.MovementTestHelper.MOCK_LATITUDE1;
import static pt.caires.marsrover.movement.MovementTestHelper.MOCK_LONGITUDE1;

import org.junit.jupiter.api.Test;

import pt.caires.marsrover.movement.MovementTestHelper;
import pt.caires.marsrover.movement.domain.GeoCoordinate;
import pt.caires.marsrover.movement.domain.MRMovement;
import pt.caires.marsrover.movement.rest.GeoCoordinateRest;
import pt.caires.marsrover.movement.rest.MRMovementRest;


class MovementRestMapperTest
{
    // map movementrest - ok
    @Test
    void givenValidMovement_whenMapToRest_thenReturnMovementRestObject()
    {
        // given
        final GeoCoordinate mockGeoCoordinate1 = MovementTestHelper.generateGeoCoordinate(MOCK_LATITUDE1, MOCK_LONGITUDE1);
        final MRMovement mockMovement = MovementTestHelper.generateMovement(mockGeoCoordinate1);
        final GeoCoordinateRest expectedGeoCoordinateRest = MovementTestHelper.generateGeoCoordinateRest(MOCK_LATITUDE1, MOCK_LONGITUDE1);
        final MRMovementRest expectedMovementRest = MovementTestHelper.generateMovementRest(mockMovement.getId(), expectedGeoCoordinateRest);

        // when
        final MRMovementRest result = MovementRestMapper.makeMovementRest(mockMovement);

        // then
        assertNotNull(result);
        assertThat(result.getId(), is(expectedMovementRest.getId()));
        assertThat(result.getGeoCoordinate(), is(expectedMovementRest.getGeoCoordinate()));
    }

    // map movementrest - null
    @Test
    void givenNullMovement_whenMapToRest_thenReturnNullValue()
    {
        // given + when + then
        assertNull(MovementRestMapper.makeMovementRest(null));
    }

    // map geocoordinate - ok
    @Test
    void givenValidGeoCoordinateRest_whenMapToBizz_thenReturnGeoCoordinateObject()
    {
        // given
        final GeoCoordinate expectedGeoCoordinate = MovementTestHelper.generateGeoCoordinate(MOCK_LATITUDE1, MOCK_LONGITUDE1);
        final GeoCoordinateRest mockGeoCoordinateRest = MovementTestHelper.generateGeoCoordinateRest(MOCK_LATITUDE1, MOCK_LONGITUDE1);

        // when
        final GeoCoordinate result = MovementRestMapper.makeGeoCoordinate(mockGeoCoordinateRest);

        // then
        assertNotNull(result);
        assertThat(result.getLatitude(), is(expectedGeoCoordinate.getLatitude()));
        assertThat(result.getLongitude(), is(expectedGeoCoordinate.getLongitude()));
    }

    // map geocoordinate - null
    @Test
    void givenValidGeoCoordinateRest_whenMapToBizz_thenReturnNullValue()
    {
        // given + when + then
        assertNull(MovementRestMapper.makeGeoCoordinate(null));
    }
}
