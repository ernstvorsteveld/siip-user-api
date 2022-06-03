package group.siip.userapi.user.usecase;

import group.siip.userapi.user.web.UserResponse;

public interface GetUser {

    UserResponse get(String mobile);
}
