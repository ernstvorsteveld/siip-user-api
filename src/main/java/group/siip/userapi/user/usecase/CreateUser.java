package group.siip.userapi.user.usecase;

import group.siip.userapi.user.web.UserRequest;
import group.siip.userapi.user.web.UserResponse;

public interface CreateUser {
    UserResponse create(UserRequest userRequest);
}
