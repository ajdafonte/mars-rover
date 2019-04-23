package pt.caires.marsrover.multimedia.domain;

import java.time.ZonedDateTime;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pt.caires.marsrover.common.infra.SequenceGen;


@Getter
@Setter
@EqualsAndHashCode
@ToString
public final class MRMultimedia
{
    private long id = SequenceGen.getNextValue();
    private MRMultimediaType type;
    private String title;
    private ZonedDateTime dateCreated = ZonedDateTime.now();

    public MRMultimedia(final MRMultimediaType type, final String title)
    {
        this.type = type;
        this.title = title;
    }

    public MRMultimedia(final long id, final MRMultimediaType type, final String title, final ZonedDateTime dateCreated)
    {
        this(type, title);
        this.id = id;
        this.dateCreated = dateCreated;
    }
}
