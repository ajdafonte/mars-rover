package pt.caires.marsrover.movement.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static pt.caires.marsrover.movement.MovementTestHelper.MOCK_LATITUDE1;
import static pt.caires.marsrover.movement.MovementTestHelper.MOCK_LATITUDE2;
import static pt.caires.marsrover.movement.MovementTestHelper.MOCK_LONGITUDE1;
import static pt.caires.marsrover.movement.MovementTestHelper.MOCK_LONGITUDE2;

import org.junit.jupiter.api.Test;

import pt.caires.marsrover.movement.MovementTestHelper;


class MRMovementTest
{
    // equals nok
    @Test
    void givenTwoMovements_whenCheckIfEquals_thenBothMovementsMustNotBeEquals()
    {
        // given
        final MRMovement mockMovement1 =
            MovementTestHelper.generateMovement(MovementTestHelper.generateGeoCoordinate(MOCK_LATITUDE1, MOCK_LONGITUDE1));
        final MRMovement mockMovement2 =
            MovementTestHelper.generateMovement(MovementTestHelper.generateGeoCoordinate(MOCK_LATITUDE2, MOCK_LONGITUDE2));

        // when + then
        assertNotEquals(mockMovement1.hashCode(), mockMovement2.hashCode());
        assertNotEquals(mockMovement1, mockMovement2);
    }

    // toString
    @Test
    void givenMovement_whenCallToString_thenReturnExpectedValue()
    {
        // given
        final MRMovement mockMovement =
            MovementTestHelper.generateMovement(MovementTestHelper.generateGeoCoordinate(MOCK_LATITUDE1, MOCK_LONGITUDE1));
        final String expected = "MRMovement(" +
            "id=" + mockMovement.getId() +
            ", geoCoordinate=" + mockMovement.getGeoCoordinate() +
            ", dateCreated=" + mockMovement.getDateCreated() +
            ')';

        // when
        final String result = mockMovement.toString();

        // then
        assertEquals(expected, result);
    }
}
