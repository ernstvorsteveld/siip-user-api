package group.siip.userapi.user.usecase;

import group.siip.userapi.user.domain.User;
import group.siip.userapi.user.web.UserRequest;

public class UserRequestMapper {

    public User to(UserRequest userRequest) {
        if (userRequest == null) {
            throw new RequestPayloadMissingException();
        }
        return User.builder()
                .mobile(userRequest.getMobile())
                .first(userRequest.getFirst())
                .last(userRequest.getLast())
                .build();
    }
}
