package pt.caires.marsrover.message.domain;

import java.time.ZonedDateTime;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import pt.caires.marsrover.common.infra.SequenceGen;


@Getter
@EqualsAndHashCode
@ToString
public final class MRMessage
{
    private final long id = SequenceGen.getNextValue();
    private final String text;
    private final MRDialect dialect;
    private final ZonedDateTime dateCreated = ZonedDateTime.now();

    public MRMessage(final String text, final MRDialect dialect)
    {
        this.text = text;
        this.dialect = dialect;
    }
}
