package group.siip.userapi.user.web;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@RequestMapping("/default")
public interface UserOperations {

    @GetMapping(value = "/{mobile}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    UserResponse get(@PathVariable
                     @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$", message = "Mobile number is invalid")
                     @NotBlank @Size(max = 12, message = "Maximum length of a mobile number is 12") String mobile);

    @PostMapping(value = "/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    UserResponse create(@Valid @RequestBody UserRequest userRequest);

    @DeleteMapping(value = "/{userId}")
    void delete(@PathVariable
                @Pattern(regexp = "^[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$", message = "User id is invalid")
}
