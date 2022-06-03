package group.siip.userapi.user.gateway.keycloak;

import group.siip.userapi.user.domain.User;
import group.siip.util.keycloak.domain.KeycloakCredential;
import group.siip.util.keycloak.service.KeycloakUserRequest;
import group.siip.util.keycloak.service.KeycloakUserResponse;

import java.util.ArrayList;
import java.util.List;

public class KeycloakMapperImpl implements KeycloakMapper {

    @Override
    public KeycloakUserRequest to(User user) {
        return KeycloakUserRequest.builder()
                .username(user.getMobile())
                .credentials(createCredentails(user))
                .build();
    }

    private List<KeycloakCredential> createCredentails(User user) {
        List<KeycloakCredential> credentials = new ArrayList<>();
        credentials.add(KeycloakCredential.builder()
                .type("password")
                .value(user.getMobile())
                .build());
        return credentials;
    }

    @Override
    public User from(KeycloakUserResponse userResponse) {
        return User.builder()
                .mobile(userResponse.getUsername())
                .id(userResponse.getId())
                .build();
    }
}
