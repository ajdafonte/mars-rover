//package pt.caires.marsrover.movement.bizz;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotEquals;
//import static pt.caires.marsrover.movement.MovementTestHelper.MOCK_LATITUDE1;
//import static pt.caires.marsrover.movement.MovementTestHelper.MOCK_LATITUDE2;
//import static pt.caires.marsrover.movement.MovementTestHelper.MOCK_LONGITUDE1;
//import static pt.caires.marsrover.movement.MovementTestHelper.MOCK_LONGITUDE2;
//
//import org.junit.jupiter.api.Test;
//
//import pt.caires.marsrover.movement.MovementTestHelper;
//
//
//class CreateMessageParameterTest
//{
//    // equals ok
//    @Test
//    void givenTwoEqualCreateMovementParameters_whenCheckIfEquals_thenBothCreateMovementParametersMustBeEquals()
//    {
//        // given
//        final CreateMovementParameter mockCreateMovementParameter1 =
//            MovementTestHelper.generateCreateMovementParameter(MovementTestHelper.generateGeoCoordinate(MOCK_LATITUDE1, MOCK_LONGITUDE1));
//        final CreateMovementParameter mockCreateMovementParameter2 =
//            MovementTestHelper.generateCreateMovementParameter(MovementTestHelper.generateGeoCoordinate(MOCK_LATITUDE1, MOCK_LONGITUDE1));
//
//        // when + then
//        assertEquals(mockCreateMovementParameter1.hashCode(), mockCreateMovementParameter2.hashCode());
//        assertEquals(mockCreateMovementParameter1, mockCreateMovementParameter2);
//    }
//
//    // equals nok
//    @Test
//    void givenTwoDifferentCreateMovementParameters_whenCheckIfEquals_thenBothCreateMovementParametersMustNotBeEquals()
//    {
//        // given
//        final CreateMovementParameter mockCreateMovementParameter1 =
//            MovementTestHelper.generateCreateMovementParameter(MovementTestHelper.generateGeoCoordinate(MOCK_LATITUDE1, MOCK_LONGITUDE1));
//        final CreateMovementParameter mockCreateMovementParameter2 =
//            MovementTestHelper.generateCreateMovementParameter(MovementTestHelper.generateGeoCoordinate(MOCK_LATITUDE2, MOCK_LONGITUDE2));
//
//        // when + then
//        assertNotEquals(mockCreateMovementParameter1.hashCode(), mockCreateMovementParameter2.hashCode());
//        assertNotEquals(mockCreateMovementParameter1, mockCreateMovementParameter2);
//    }
//
//    // toString
//    @Test
//    void givenCreateMovementParameter_whenCallToString_thenReturnExpectedValue()
//    {
//        // given
//        final CreateMovementParameter mockCreateMovementParameter =
//            MovementTestHelper.generateCreateMovementParameter(MovementTestHelper.generateGeoCoordinate(MOCK_LATITUDE1, MOCK_LONGITUDE1));
//        final String expected = "CreateMovementParameter(" +
//            "geoCoordinate=" + mockCreateMovementParameter.getGeoCoordinate() +
//            ')';
//
//        // when
//        final String result = mockCreateMovementParameter.toString();
//
//        // then
//        assertEquals(expected, result);
//
//    }
//}
