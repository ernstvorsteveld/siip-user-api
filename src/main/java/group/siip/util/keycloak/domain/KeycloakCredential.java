package group.siip.util.keycloak.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class KeycloakCredential {

    private String type;
    private String value;
    private boolean temporary;

}
