package group.siip.userapi.user.domain;

public interface UserService {

    User get(String mobile);

    User create(User user);

    void delete(String userId);
}
