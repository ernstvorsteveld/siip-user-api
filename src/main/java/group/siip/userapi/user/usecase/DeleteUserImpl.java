package group.siip.userapi.user.usecase;

import group.siip.userapi.user.domain.UserService;

public class DeleteUserImpl implements DeleteUser {

    private UserService userService;

    public DeleteUserImpl(
            UserService userService) {
        this.userService = userService;
    }

    @Override
    public void delete(String userId) {
        userService.delete(userId);
    }
}
