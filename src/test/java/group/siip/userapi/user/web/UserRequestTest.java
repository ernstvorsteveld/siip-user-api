package group.siip.userapi.user.web;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class UserRequestTest {

    private static Validator validator;

    @BeforeAll
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @ParameterizedTest
    @CsvSource({
            "John,true",
            "$$,false",
            "διακριτικός,true",
            "10,false",
            "<img>,false",
            "U+003C,false"
    })
    void should_validate_user_request(String first, String result) {
        UserRequest userRequest = new UserRequest();
        userRequest.setFirst(first);
        Set<ConstraintViolation<UserRequest>> violations = validator.validate(userRequest);

        switch (result) {
            case "true": {
                assertThat(violations.isEmpty()).isTrue();
                break;
            }
            case "false": {
                assertThat(violations.isEmpty()).isFalse();
                break;
            }
        }
    }
}
