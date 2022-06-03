package group.siip.userapi.user.domain;

import group.siip.userapi.user.gateway.mongo.UserDocumentMapper;
import group.siip.userapi.user.gateway.UserGateway;

public class UserServiceImpl implements UserService {

    private UserGateway userDbGateway;
    private UserGateway userKcGateway;
    private UserDocumentMapper userDocumentMapper;

    public UserServiceImpl(
            UserGateway userDbGateway,
            UserGateway userKcGateway) {
        this.userDbGateway = userDbGateway;
        this.userKcGateway = userKcGateway;
    }

    @Override
    public User get(String mobile) {
        return userDbGateway.get(mobile);
    }

    @Override
    public User create(User user) {
        String location = userKcGateway.create(user).getLocation();
        user.setLocation(location);
        userDbGateway.create(user);
        return get(user.getMobile());
    }

    @Override
    public void delete(String userId) {
        User user = userDbGateway.get(userId);
        userKcGateway.delete(user.getKeycloakId());
    }
}
