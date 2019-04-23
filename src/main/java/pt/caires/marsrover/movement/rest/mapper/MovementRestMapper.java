package pt.caires.marsrover.movement.rest.mapper;

import pt.caires.marsrover.movement.domain.GeoCoordinate;
import pt.caires.marsrover.movement.domain.MRMovement;
import pt.caires.marsrover.movement.rest.GeoCoordinateRest;
import pt.caires.marsrover.movement.rest.MRMovementRest;


public class MovementRestMapper
{
    public static MRMovementRest makeMovementRest(final MRMovement movement)
    {
        if (movement != null)
        {
            return new MRMovementRest(movement.getId(), makeGeoCoordinateRest(movement.getGeoCoordinate()));
        }

        return null;
    }

    public static GeoCoordinate makeGeoCoordinate(final GeoCoordinateRest coordinateRest)
    {
        if (coordinateRest != null)
        {
            return new GeoCoordinate(coordinateRest.getLatitude(), coordinateRest.getLongitude());
        }
        return null;
    }

    private static GeoCoordinateRest makeGeoCoordinateRest(final GeoCoordinate geoCoordinate)
    {
        if (geoCoordinate != null)
        {
            final GeoCoordinateRest geoCoordinateRest = new GeoCoordinateRest();
            geoCoordinateRest.setLatitude(geoCoordinate.getLatitude());
            geoCoordinateRest.setLongitude(geoCoordinate.getLongitude());
            return geoCoordinateRest;
        }

        return null;
    }
}
