package group.siip.util.keycloak.service;

import group.siip.util.keycloak.domain.BaseAddress;
import group.siip.util.keycloak.domain.Realm;
import group.siip.util.keycloak.service.KcUserRequestsImpl;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KcUserRequestsImplSimpleTest {

    public static final String EXPECTED_SCHEMA = "http";
    public static final String EXPECTED_FQDN = "localhost";
    public static final String EXPECTED_PORT = "8010";
    public static final String USER_PATH = "/auth/admin/realms/demo/users";

    @Test
    public void should_initialize_url() {
        KcUserRequestsImpl kcUserRequests = new KcUserRequestsImpl(
                null,
                new BaseAddress(EXPECTED_SCHEMA, EXPECTED_FQDN, EXPECTED_PORT),
                new Realm("demo"));
        assertThat(kcUserRequests.getUserApiUrl()).isEqualTo(
                EXPECTED_SCHEMA + "://" + EXPECTED_FQDN + ":" + EXPECTED_PORT + USER_PATH);
    }

}