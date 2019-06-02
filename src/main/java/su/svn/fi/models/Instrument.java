package su.svn.fi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class Instrument
{
    private final String name;

    private final long number;

    private final LocalDate date;

    private final double value;
}
