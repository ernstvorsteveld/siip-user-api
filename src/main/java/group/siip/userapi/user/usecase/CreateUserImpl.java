package group.siip.userapi.user.usecase;

import group.siip.userapi.user.domain.UserService;
import group.siip.userapi.user.web.UserRequest;
import group.siip.userapi.user.web.UserResponse;

public class CreateUserImpl implements CreateUser {

    private UserRequestMapper userRequestMapper;
    private UserResponseMapper userResponseMapper;
    private UserService userService;

    public CreateUserImpl(
            UserRequestMapper userRequestMapper,
            UserResponseMapper userResponseMapper,
            UserService userService) {
        this.userRequestMapper = userRequestMapper;
        this.userResponseMapper = userResponseMapper;
        this.userService = userService;
    }

    @Override
    public UserResponse create(UserRequest userRequest) {
        return userResponseMapper.to(userService.create(userRequestMapper.to(userRequest)));
    }
}
