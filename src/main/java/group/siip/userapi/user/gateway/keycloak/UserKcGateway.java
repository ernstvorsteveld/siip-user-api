package group.siip.userapi.user.gateway.keycloak;

import group.siip.userapi.TimeOutSettings;
import group.siip.userapi.user.domain.User;
import group.siip.userapi.user.gateway.UserGateway;
import group.siip.util.keycloak.service.KcUserRequests;

public class UserKcGateway implements UserGateway {

    public static final String USER_KC_GATEWAY_GET_STRING_MOBILE = "UserKcGateway.get(String mobile);";
    private final TimeOutSettings timeOutSettings;
    private KcUserRequests kcUserRequests;
    private KeycloakMapper keycloakMapper;

    public UserKcGateway(
            KcUserRequests kcUserRequests,
            KeycloakMapper keycloakMapper,
            TimeOutSettings timeOutSettings) {
        this.kcUserRequests = kcUserRequests;
        this.keycloakMapper = keycloakMapper;
        this.timeOutSettings = timeOutSettings;
    }

    @Override
    public User get(String mobile) {
        throw new NotImplementedException(USER_KC_GATEWAY_GET_STRING_MOBILE);
    }

    @Override
    public User create(User user) {
        String location = kcUserRequests.create(keycloakMapper.to(user))
                .block(timeOutSettings.getDefaultTimeOut());
        return User.builder().location(location).build();
    }

    @Override
    public User update(User user) {
        kcUserRequests.update(keycloakMapper.to(user))
                .block(timeOutSettings.getDefaultTimeOut());
        return null;
    }

    @Override
    public void delete(User user) {
        kcUserRequests.delete(keycloakMapper.to(user).getId());
    }

    @Override
    public void delete(String id) {
        kcUserRequests.delete(id);
    }
}
