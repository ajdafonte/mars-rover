package pt.caires.marsrover.movement;

import java.math.BigDecimal;

import pt.caires.marsrover.movement.domain.GeoCoordinate;
import pt.caires.marsrover.movement.domain.MRMovement;
import pt.caires.marsrover.movement.rest.GeoCoordinateRest;
import pt.caires.marsrover.movement.rest.MRMovementRest;


public class MovementTestHelper
{
    public static final long MOCK_ID1 = 1L;
    public static final long MOCK_ID2 = 2L;
    public static final long MOCK_UNKNOWN_ID = 10000L;
    public static final BigDecimal MOCK_LATITUDE1 = new BigDecimal(10.5);
    public static final BigDecimal MOCK_LATITUDE2 = new BigDecimal(-20.5);
    public static final BigDecimal MOCK_INVALID_LATITUDE = new BigDecimal(-200);
    public static final BigDecimal MOCK_LONGITUDE1 = new BigDecimal(40);
    public static final BigDecimal MOCK_LONGITUDE2 = new BigDecimal(80);
    public static final BigDecimal MOCK_INVALID_LONGITUDE = new BigDecimal(400);

    public static GeoCoordinate generateGeoCoordinate(final BigDecimal latitude, final BigDecimal longitude)
    {
        return new GeoCoordinate(latitude, longitude);
    }

    public static GeoCoordinateRest generateGeoCoordinateRest(final BigDecimal latitude, final BigDecimal longitude)
    {
        final GeoCoordinateRest geoCoordinateRest = new GeoCoordinateRest();
        geoCoordinateRest.setLatitude(latitude);
        geoCoordinateRest.setLongitude(longitude);
        return geoCoordinateRest;
    }

    public static MRMovement generateMovement(final GeoCoordinate coordinate)
    {
        return new MRMovement(coordinate);
    }

    public static MRMovementRest generateMovementRest(final long id, final GeoCoordinateRest coordinateRest)
    {
        return new MRMovementRest(id, coordinateRest);
    }
}
