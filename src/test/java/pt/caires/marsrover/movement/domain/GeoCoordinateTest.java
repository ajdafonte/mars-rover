package pt.caires.marsrover.movement.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static pt.caires.marsrover.movement.MovementTestHelper.MOCK_LATITUDE1;
import static pt.caires.marsrover.movement.MovementTestHelper.MOCK_LATITUDE2;
import static pt.caires.marsrover.movement.MovementTestHelper.MOCK_LONGITUDE1;
import static pt.caires.marsrover.movement.MovementTestHelper.MOCK_LONGITUDE2;

import org.junit.jupiter.api.Test;

import pt.caires.marsrover.movement.MovementTestHelper;


class GeoCoordinateTest
{
    // equals ok
    @Test
    void givenTwoEqualGeoCoordinates_whenCheckIfEquals_thenBothGeoCoordinatesMustBeEquals()
    {
        // given
        final GeoCoordinate mockGeoCoordinate1 = MovementTestHelper.generateGeoCoordinate(MOCK_LATITUDE1, MOCK_LONGITUDE1);
        final GeoCoordinate mockGeoCoordinate2 = MovementTestHelper.generateGeoCoordinate(MOCK_LATITUDE1, MOCK_LONGITUDE1);

        // when + then
        assertEquals(mockGeoCoordinate1.hashCode(), mockGeoCoordinate2.hashCode());
        assertEquals(mockGeoCoordinate1, mockGeoCoordinate2);
    }

    // equals nok
    @Test
    void givenTwoDifferentGeoCoordinates_whenCheckIfEquals_thenBothGeoCoordinatesMustNotBeEquals()
    {
        // given
        final GeoCoordinate mockGeoCoordinate1 = MovementTestHelper.generateGeoCoordinate(MOCK_LATITUDE1, MOCK_LONGITUDE1);
        final GeoCoordinate mockGeoCoordinate2 = MovementTestHelper.generateGeoCoordinate(MOCK_LATITUDE2, MOCK_LONGITUDE2);

        // when + then
        assertNotEquals(mockGeoCoordinate1.hashCode(), mockGeoCoordinate2.hashCode());
        assertNotEquals(mockGeoCoordinate1, mockGeoCoordinate2);
    }

    // toString
    @Test
    void givenGeoCoordinate_whenCallToString_thenReturnExpectedValue()
    {
        // given
        final GeoCoordinate mockGeoCoordinate = MovementTestHelper.generateGeoCoordinate(MOCK_LATITUDE1, MOCK_LONGITUDE1);
        final String expected = "GeoCoordinate(" +
            "latitude=" + mockGeoCoordinate.getLatitude() +
            ", longitude=" + mockGeoCoordinate.getLongitude() +
            ')';

        // when
        final String result = mockGeoCoordinate.toString();

        // then
        assertEquals(expected, result);

    }
}
