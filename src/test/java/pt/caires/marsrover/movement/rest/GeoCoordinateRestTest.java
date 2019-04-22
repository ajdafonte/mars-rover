package pt.caires.marsrover.movement.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static pt.caires.marsrover.movement.MovementTestHelper.MOCK_LATITUDE1;
import static pt.caires.marsrover.movement.MovementTestHelper.MOCK_LATITUDE2;
import static pt.caires.marsrover.movement.MovementTestHelper.MOCK_LONGITUDE1;
import static pt.caires.marsrover.movement.MovementTestHelper.MOCK_LONGITUDE2;

import org.junit.jupiter.api.Test;

import pt.caires.marsrover.movement.MovementTestHelper;


class GeoCoordinateRestTest
{
    // equals ok
    @Test
    void givenTwoEqualGeoCoordinateRest_whenCheckIfEquals_thenBothGeoCoordinateRestMustBeEquals()
    {
        // given
        final GeoCoordinateRest mockGeoCoordinateRest1 = MovementTestHelper.generateGeoCoordinateRest(MOCK_LATITUDE1, MOCK_LONGITUDE1);
        final GeoCoordinateRest mockGeoCoordinateRest2 = MovementTestHelper.generateGeoCoordinateRest(MOCK_LATITUDE1, MOCK_LONGITUDE1);

        // when + then
        assertEquals(mockGeoCoordinateRest1.hashCode(), mockGeoCoordinateRest2.hashCode());
        assertEquals(mockGeoCoordinateRest1, mockGeoCoordinateRest2);
    }

    // equals nok
    @Test
    void givenTwoDifferentGeoCoordinateRest_whenCheckIfEquals_thenBothGeoCoordinateRestMustNotBeEquals()
    {
        // given
        final GeoCoordinateRest mockGeoCoordinateRest1 = MovementTestHelper.generateGeoCoordinateRest(MOCK_LATITUDE1, MOCK_LONGITUDE1);
        final GeoCoordinateRest mockGeoCoordinateRest2 = MovementTestHelper.generateGeoCoordinateRest(MOCK_LATITUDE2, MOCK_LONGITUDE2);

        // when + then
        assertNotEquals(mockGeoCoordinateRest1.hashCode(), mockGeoCoordinateRest2.hashCode());
        assertNotEquals(mockGeoCoordinateRest1, mockGeoCoordinateRest2);
    }

    // toString
    @Test
    void givenGeoCoordinateRest_whenCallToString_thenReturnExpectedValue()
    {
        // given
        final GeoCoordinateRest mockGeoCoordinateRest = MovementTestHelper.generateGeoCoordinateRest(MOCK_LATITUDE1, MOCK_LONGITUDE1);
        final String expected = "GeoCoordinateRest(" +
            "latitude=" + mockGeoCoordinateRest.getLatitude() +
            ", longitude=" + mockGeoCoordinateRest.getLongitude() +
            ')';

        // when
        final String result = mockGeoCoordinateRest.toString();

        // then
        assertEquals(expected, result);

    }
}
