package pt.caires.marsrover.message.bizz;

import lombok.Data;
import pt.caires.marsrover.message.domain.MRDialect;


@Data
public class CreateMessageParameter
{
    private final String text;
    private final MRDialect dialect;
}
