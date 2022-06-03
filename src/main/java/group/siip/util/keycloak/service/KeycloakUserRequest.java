package group.siip.util.keycloak.service;

import group.siip.util.keycloak.domain.KeycloakCredential;
import lombok.*;

import java.util.List;
import java.util.Map;

@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KeycloakUserRequest {

//     "disableableCredentialTypes", "enabled", "emailVerified", "origin", "self", "applicationRoles", "createdTimestamp", "clientRoles", "groups", "username", "totp", "id", "email", "federationLink", "serviceAccountClientId", "lastName", "clientConsents", "socialLinks", "realmRoles", "notBefore", "attributes", "firstName", "credentials", "requiredActions", "federatedIdentities", "access"])

    private String username;
    private String email;
    private List<KeycloakCredential> credentials;
    private boolean enabled;
    private String id;

    private Map<Attributes,String> attributes;

    public enum Attributes {
        DATE_OF_BIRTH,
        SIIP_ID1,
        SIIP_ID2,
        APP_ID
    }
}