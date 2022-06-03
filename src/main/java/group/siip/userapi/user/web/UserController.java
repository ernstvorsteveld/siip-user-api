package group.siip.userapi.user.web;

import group.siip.userapi.user.usecase.CreateUser;
import group.siip.userapi.user.usecase.DeleteUser;
import group.siip.userapi.user.usecase.GetUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/v1/user")
@Validated
public class UserController implements UserOperations {

    @Autowired
    private GetUser getUser;

    @Autowired
    private CreateUser createUser;

    @Autowired
    private DeleteUser deleteUser;

    @Override
    @Loggable("UserController, Get")
    public UserResponse get(String mobile) {
        return getUser.get(mobile);
    }

    @Override
    @Loggable("UserController, Create")
    public UserResponse create(UserRequest userRequest) {
        return createUser.create(userRequest);
    }

    @Override
    public void delete(String userId) {
        deleteUser.delete(userId);
    }
}
