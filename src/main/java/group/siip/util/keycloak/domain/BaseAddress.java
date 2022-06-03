package group.siip.util.keycloak.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BaseAddress {
    private String schema;
    private String fqdn;
    private String port;

    public String get() {
        return schema + "://" + fqdn + ":" + port;
    }
}
