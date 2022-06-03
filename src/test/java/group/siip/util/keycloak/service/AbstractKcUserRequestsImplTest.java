package group.siip.util.keycloak.service;

import group.siip.util.keycloak.domain.Email;
import group.siip.util.keycloak.domain.Phone;
import group.siip.util.keycloak.domain.PinCode;
import group.siip.util.keycloak.service.KeycloakUserRequest;
import group.siip.util.keycloak.service.KeycloakUserRequestBuilder;

import java.time.LocalDate;
import java.util.UUID;

public abstract class AbstractKcUserRequestsImplTest {

    public static final String EXPECTED_EMAIL = "john.doe@battlefield.com";
    public static final String EXPECTED_DATE = "1980-10-10";
    public static final String EXPECTED_APP_ID = UUID.randomUUID().toString();
    public static final String EXPECTED_SIIP_ID1 = UUID.randomUUID().toString();
    public static final String EXPECTED_SIIP_ID2 = UUID.randomUUID().toString();
    public static final String EXPECTED_PHONE = "0612345678";
    public static final String EXPECTED_PIN_CODE = "12345";

    protected KeycloakUserRequest expectKeycloakUserRequest() {
        KeycloakUserRequestBuilder builder = new KeycloakUserRequestBuilder();
        builder.email(new Email(EXPECTED_EMAIL));
        builder.dateOfBirth(LocalDate.of(1980, 10, 10));
        builder.appId(EXPECTED_APP_ID);
        builder.ids(EXPECTED_SIIP_ID1, EXPECTED_SIIP_ID2);
        builder.phone(new Phone(EXPECTED_PHONE));
        builder.pinCode(new PinCode(EXPECTED_PIN_CODE));
        return builder.build();
    }

}
