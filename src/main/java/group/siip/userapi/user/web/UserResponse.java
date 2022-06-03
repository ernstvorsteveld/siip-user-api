package group.siip.userapi.user.web;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserResponse {

    private String mobile;
    private String first;
    private String last;

}
