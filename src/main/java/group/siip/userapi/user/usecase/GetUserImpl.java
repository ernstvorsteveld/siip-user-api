package group.siip.userapi.user.usecase;

import group.siip.userapi.user.domain.UserService;
import group.siip.userapi.user.web.UserResponse;

public class GetUserImpl implements GetUser {

    private UserResponseMapper userResponseMapper;
    private UserService userService;

    public GetUserImpl(
            UserResponseMapper userResponseMapper,
            UserService userService) {
        this.userResponseMapper = userResponseMapper;
        this.userService = userService;
    }

    @Override
    public UserResponse get(String mobile) {
        return userResponseMapper.to(userService.get(mobile));
    }
}
