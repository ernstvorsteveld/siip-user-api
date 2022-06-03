package group.siip.userapi.user.gateway;

import group.siip.userapi.user.domain.User;

public interface UserGateway {

    User get(String mobile);

    User create(User user);

    User update(User user);

    void delete(User user);

    void delete(String id);
}
