package group.siip.util.keycloak.service;

import group.siip.util.keycloak.domain.Email;
import group.siip.util.keycloak.domain.Phone;
import group.siip.util.keycloak.domain.PinCode;
import group.siip.util.keycloak.service.KeycloakUserRequest;
import group.siip.util.keycloak.service.KeycloakUserRequestBuilder;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class KeycloakRequestBuilderTest {

    public static final String EXPECTED_EMAIL = "john.doe@battlefield.com";
    public static final String EXPECTED_DATE = "1980-10-10";
    public static final String EXPECTED_APP_ID = UUID.randomUUID().toString();
    public static final String EXPECTED_SIIP_ID1 = UUID.randomUUID().toString();
    public static final String EXPECTED_SIIP_ID2 = UUID.randomUUID().toString();
    public static final String EXPECTED_PHONE = UUID.randomUUID().toString();
    public static final String EXPECTED_PIN_CODE = "12345";

    @Test
    public void should_create_request() {
        KeycloakUserRequestBuilder builder = new KeycloakUserRequestBuilder();
        builder.email(new Email(EXPECTED_EMAIL));
        builder.dateOfBirth(LocalDate.of(1980,10,10));
        builder.appId(EXPECTED_APP_ID);
        builder.ids(EXPECTED_SIIP_ID1, EXPECTED_SIIP_ID2);
        builder.phone(new Phone(EXPECTED_PHONE));
        builder.pinCode(new PinCode(EXPECTED_PIN_CODE));
        KeycloakUserRequest request = builder.build();

        assertThat(request.getEmail()).isEqualTo(EXPECTED_EMAIL);
        assertThat(request.getAttributes().get(KeycloakUserRequest.Attributes.DATE_OF_BIRTH)).isEqualTo(EXPECTED_DATE);
        assertThat(request.getAttributes().get(KeycloakUserRequest.Attributes.APP_ID)).isEqualTo(EXPECTED_APP_ID);
        assertThat(request.getAttributes().get(KeycloakUserRequest.Attributes.SIIP_ID1)).isEqualTo(EXPECTED_SIIP_ID1);
        assertThat(request.getAttributes().get(KeycloakUserRequest.Attributes.SIIP_ID2)).isEqualTo(EXPECTED_SIIP_ID2);
        assertThat(request.getUsername()).isEqualTo(EXPECTED_PHONE);
        assertThat(request.getCredentials().get(0).getValue()).isEqualTo(EXPECTED_PIN_CODE);
    }
}
