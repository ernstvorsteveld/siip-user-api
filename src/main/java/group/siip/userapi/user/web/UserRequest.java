package group.siip.userapi.user.web;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
public class UserRequest {

    @Size(min = 8, max = 12, message
            = "Mobile number must be longer than 8 and shorter than 12 characters.")
    @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$", message = "Mobile number contains invalid characters")
    @Loggable(value = "UserRequest mobile", showData = true)
    private String mobile;

    @Size(min = 1, max = 200, message
            = "First name must have at least one character with a maximum of 200.")
    @Pattern(regexp = "^[a-zA-Z\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF]+$", message = "First name contains invalid characters")
    private String first;

    @Size(min = 1, max = 200, message
            = "Last name must have at least one character with a maximum of 200.")
    @Pattern(regexp = "^[a-zA-Z\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF]+$", message = "Last name contains invalid characters")
    private String last;

}
