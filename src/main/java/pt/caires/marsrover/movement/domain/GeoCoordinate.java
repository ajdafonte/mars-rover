package pt.caires.marsrover.movement.domain;

import java.math.BigDecimal;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;


@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
public final class GeoCoordinate
{
    private final BigDecimal latitude;
    private final BigDecimal longitude;
}
