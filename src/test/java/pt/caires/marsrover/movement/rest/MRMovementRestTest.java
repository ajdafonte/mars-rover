package pt.caires.marsrover.movement.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static pt.caires.marsrover.movement.MovementTestHelper.MOCK_ID1;
import static pt.caires.marsrover.movement.MovementTestHelper.MOCK_ID2;
import static pt.caires.marsrover.movement.MovementTestHelper.MOCK_LATITUDE1;
import static pt.caires.marsrover.movement.MovementTestHelper.MOCK_LATITUDE2;
import static pt.caires.marsrover.movement.MovementTestHelper.MOCK_LONGITUDE1;
import static pt.caires.marsrover.movement.MovementTestHelper.MOCK_LONGITUDE2;

import org.junit.jupiter.api.Test;

import pt.caires.marsrover.movement.MovementTestHelper;


class MRMovementRestTest
{
    // equals ok
    @Test
    void givenTwoEqualMRMovementRest_whenCheckIfEquals_thenBothMRMovementRestMustBeEquals()
    {
        // given
        final MRMovementRest mockMRMovementRest1 = MovementTestHelper.generateMovementRest(MOCK_ID1,
            MovementTestHelper.generateGeoCoordinateRest(MOCK_LATITUDE1, MOCK_LONGITUDE1));
        final MRMovementRest mockMRMovementRest2 = MovementTestHelper.generateMovementRest(MOCK_ID1,
            MovementTestHelper.generateGeoCoordinateRest(MOCK_LATITUDE1, MOCK_LONGITUDE1));

        // when + then
        assertEquals(mockMRMovementRest1.hashCode(), mockMRMovementRest2.hashCode());
        assertEquals(mockMRMovementRest1, mockMRMovementRest2);
    }

    // equals nok
    @Test
    void givenTwoDifferentMRMovementRest_whenCheckIfEquals_thenBothMRMovementRestMustNotBeEquals()
    {
        // given
        final MRMovementRest mockMRMovementRest1 = MovementTestHelper.generateMovementRest(MOCK_ID1,
            MovementTestHelper.generateGeoCoordinateRest(MOCK_LATITUDE1, MOCK_LONGITUDE1));
        final MRMovementRest mockMRMovementRest2 = MovementTestHelper.generateMovementRest(MOCK_ID2,
            MovementTestHelper.generateGeoCoordinateRest(MOCK_LATITUDE2, MOCK_LONGITUDE2));

        // when + then
        assertNotEquals(mockMRMovementRest1.hashCode(), mockMRMovementRest2.hashCode());
        assertNotEquals(mockMRMovementRest1, mockMRMovementRest2);
    }

    // toString
    @Test
    void givenMRMovementRest_whenCallToString_thenReturnExpectedValue()
    {
        // given
        final MRMovementRest mockMRMovementRest = MovementTestHelper.generateMovementRest(MOCK_ID1,
            MovementTestHelper.generateGeoCoordinateRest(MOCK_LATITUDE1, MOCK_LONGITUDE1));
        final String expected = "MRMovementRest(" +
            "id=" + mockMRMovementRest.getId() +
            ", geoCoordinate=" + mockMRMovementRest.getGeoCoordinate() +
            ')';

        // when
        final String result = mockMRMovementRest.toString();

        // then
        assertEquals(expected, result);

    }
}
