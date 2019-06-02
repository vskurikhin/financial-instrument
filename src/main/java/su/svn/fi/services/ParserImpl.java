package su.svn.fi.services;

import org.springframework.stereotype.Service;
import su.svn.fi.exceptions.UnexpectedInputLineException;
import su.svn.fi.models.Instrument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Pattern;

@Service
public class ParserImpl implements Parser
{
    private final static Pattern pattern = Pattern.compile("INSTRUMENT[0-9]+");

    boolean check(String[] elements)
    {
        if (3 != elements.length) return false;
        return pattern.matcher(elements[0]).matches();
    }

    LocalDate parseDate(String dateInString)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);

        return LocalDate.parse(dateInString, formatter);
    }

    @Override
    public Instrument parse(String line, long number)
    {
        String elements[] = line.split(",");
        if ( ! check(elements)) {
            throw new UnexpectedInputLineException("Unexpected input line " + line);
        }
        LocalDate date = parseDate(elements[1]);
        double value = Double.parseDouble(elements[2]);

        return new Instrument(elements[0], number, date, value);
    }

    @Override
    public Instrument parse(String line)
    {
        return parse(line, 0);
    }
}
