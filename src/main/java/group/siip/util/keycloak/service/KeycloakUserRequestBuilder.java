package group.siip.util.keycloak.service;

import group.siip.util.keycloak.domain.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class KeycloakUserRequestBuilder {

    private String phone;
    private String dateOfBirth;
    private FaceTemplate faceTemplate;
    private String siipId1;
    private String siipId2;
    private String id;
    private String appId;
    private String pinCode;
    private String dataHash;
    private String email;
    private boolean enabled;

    public KeycloakUserRequestBuilder() {
        id = UUID.randomUUID().toString();
        enabled = true;
    }

    public KeycloakUserRequestBuilder phone(Phone value) {
        phone = value.get();
        return this;
    }

    public KeycloakUserRequestBuilder dateOfBirth(LocalDate date) {
        if (date == null) {
            return null;
        }
        dateOfBirth = DateTimeFormatter.ofPattern("uuuu-MM-dd", Locale.ENGLISH).format(date);
        return this;
    }

    public KeycloakUserRequestBuilder ids(String id1, String id2) {
        siipId1 = id1;
        siipId2 = id2;
        return this;
    }

    public KeycloakUserRequestBuilder appId(String value) {
        appId = value;
        return this;
    }

    public KeycloakUserRequestBuilder pinCode(PinCode value) {
        pinCode = value.get();
        return this;
    }

    public KeycloakUserRequestBuilder withHash(DataHash value) {
        dataHash = value.get();
        return this;
    }

    public KeycloakUserRequestBuilder email(Email value) {
        email = value.get();
        return this;
    }

    public KeycloakUserRequest build() {
        KeycloakUserRequest request = new KeycloakUserRequest();
        Map<KeycloakUserRequest.Attributes,String> attributes = new HashMap<>();
        request.setAttributes(attributes);
        request.setUsername(phone);
        request.setCredentials(buildCredentials(pinCode));
        request.setId(id);
        request.setEmail(email);
        request.setEnabled(enabled);
        attributes.put(KeycloakUserRequest.Attributes.DATE_OF_BIRTH, dateOfBirth);
        attributes.put(KeycloakUserRequest.Attributes.SIIP_ID1, siipId1);
        attributes.put(KeycloakUserRequest.Attributes.SIIP_ID2, siipId2);
        attributes.put(KeycloakUserRequest.Attributes.APP_ID, appId);
        return request;
    }

    private List<KeycloakCredential> buildCredentials(String value) {
        List<KeycloakCredential> credentials = new ArrayList<>();
        if (value != null) {
            credentials.add(new KeycloakCredential("password", value, false));
        }
        return credentials;
    }

}

/*
'{"firstName":"Sergey","lastName":"Kargopolov", "email":"test@test.com", "enabled":"true", "username":"app-user"}'


 curl -v http://localhost:8080/auth/admin/realms/apiv2/users -H "Content-Type: application/json" -H "Authorization: bearer $TOKEN"
 --data '{"firstName":"xyz","lastName":"xyz", "username":"xyz123","email":"demo2@gmail.com", "enabled":"true",
 "credentials":[{"type":"password","value":"test123","temporary":false}]}'

 */