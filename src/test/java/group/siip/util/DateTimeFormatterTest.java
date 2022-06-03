package group.siip.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

public class DateTimeFormatterTest {

    public static final String EXPECTED = "1988-05-05";

    @Test
    public void should_format() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd", Locale.ENGLISH);
        LocalDate localDate = LocalDate.of(1988, 5, 5);
        String output = dateTimeFormatter.format(localDate);
        assertThat(output).isEqualTo(EXPECTED);
        LocalDate parse = LocalDate.parse(EXPECTED);
        assertThat(parse.getYear()).isEqualTo(1988);
        assertThat(parse.getMonthValue()).isEqualTo(5);
        assertThat(parse.getDayOfMonth()).isEqualTo(5);
    }
}
