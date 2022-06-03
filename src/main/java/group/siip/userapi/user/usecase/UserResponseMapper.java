package group.siip.userapi.user.usecase;

import group.siip.userapi.user.domain.User;
import group.siip.userapi.user.web.UserResponse;

public class UserResponseMapper {

    public UserResponse to(User user) {
        if(user == null) {
            throw new UserNotFoundException();
        }
        return UserResponse.builder()
                .mobile(user.getMobile())
                .first(user.getFirst())
                .last(user.getLast())
                .build();
    }
}
