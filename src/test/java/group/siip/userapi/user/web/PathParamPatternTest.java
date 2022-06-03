package group.siip.userapi.user.web;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PathParamPatternTest {

    @Test
    public void should_validate_value() {
        String pattern = "^(\\+\\d{1,3}[- ]?)?\\d{10}$";

        assertThat("ab".matches(pattern)).isFalse();
        assertThat("0611223456".matches(pattern)).isTrue();
        assertThat("+31611223456".matches(pattern)).isTrue();

        pattern = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}";
        assertThat("edd7ca95-4746-4ff3-9a00-59cd2c97398f".matches(pattern)).isTrue();
    }
}
