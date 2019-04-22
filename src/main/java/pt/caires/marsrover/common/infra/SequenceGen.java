package pt.caires.marsrover.common.infra;

import java.util.concurrent.atomic.AtomicLong;


public final class SequenceGen
{
    private static final AtomicLong SEQUENCE = new AtomicLong(1);

    private SequenceGen()
    {
        // NOOP
    }

    public static Long getNextValue()
    {
        return SEQUENCE.getAndIncrement();
    }
}
