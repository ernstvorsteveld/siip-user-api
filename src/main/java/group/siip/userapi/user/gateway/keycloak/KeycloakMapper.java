package group.siip.userapi.user.gateway.keycloak;

import group.siip.userapi.user.domain.User;
import group.siip.util.keycloak.service.KeycloakUserRequest;
import group.siip.util.keycloak.service.KeycloakUserResponse;

public interface KeycloakMapper {
    KeycloakUserRequest to(User user);

    User from(KeycloakUserResponse userResponse);
}
