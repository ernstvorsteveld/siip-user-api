package group.siip.util.keycloak.service;

import group.siip.util.keycloak.domain.Mobile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
@SpringBootTest(
        properties = {"application.properties"})
public class KcUserRequestsImplTest extends AbstractKcUserRequestsImplTest {

    @Autowired
    private KcUserRequests kcUserRequests;

    @MockBean
    private JwtDecoder jwtDecoder;

    @BeforeEach
    public void deleteAllUsers() {
        kcUserRequests.get().toStream().forEach(kr -> {
            kcUserRequests.delete(kr.getId()).block();
        });
    }

    @Test
    public void should_create_user() {
        kcUserRequests.create(expectKeycloakUserRequest())
                .as(StepVerifier::create)
                .assertNext(r -> {
                    assertThat(r).startsWith("http://localhost:8010/auth/admin/realms/demo/users/");
                })
                .verifyComplete();

        kcUserRequests.get(new Mobile(EXPECTED_PHONE))
                .as(StepVerifier::create)
                .assertNext(r -> {
                    assertThat(r[0].getUsername()).isEqualTo(EXPECTED_PHONE);
                    assertThat(r[0].getId()).isNotNull();
                }).verifyComplete();

        kcUserRequests.delete(new Mobile(EXPECTED_PHONE))
                .as(StepVerifier::create)
                .expectNextCount(0)
                .verifyComplete();
    }

}
