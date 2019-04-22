package pt.caires.marsrover.movement.domain;

import java.time.ZonedDateTime;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import pt.caires.marsrover.common.infra.SequenceGen;


@Getter
@EqualsAndHashCode
@ToString
public final class MRMovement
{
    private final long id = SequenceGen.getNextValue();
    private final GeoCoordinate geoCoordinate;
    private final ZonedDateTime dateCreated = ZonedDateTime.now();

    public MRMovement(final GeoCoordinate geoCoordinate)
    {
        this.geoCoordinate = geoCoordinate;
    }
}
