package group.siip.util.keycloak.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class Container {

    private String value;

    public String get() {
        return value;
    }
}
