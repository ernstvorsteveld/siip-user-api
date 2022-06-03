package group.siip.userapi.user.gateway.mongo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Document(collection = "user_document")
@CompoundIndexes({
        @CompoundIndex(name = "uk_mobile", def = "{'attributes.mobile' : 1}", unique = true)
})
public class UserDocument {

    public enum UniqueIndexes {
        uk_mobile
    }

    public enum UserKeys {
        mobile,
        first,
        last,
        keycloakId
    }

    private Map<UserKeys, Object> attributes;

    public UserDocument() {
        attributes = new HashMap<>();
    }

    private <T> T get(UserKeys key, Class<T> type) {
        return type.cast(attributes.get(key));
    }

    public void setMobile(String mobile) {
        attributes.put(UserKeys.mobile, mobile);
    }

    public void setFirst(String first) {
        attributes.put(UserKeys.first, first);
    }

    public void setLast(String last) {
        attributes.put(UserKeys.last, last);
    }

    public String getMobile() {
        return get(UserKeys.mobile, String.class);
    }

    public String getFirst() {
        return get(UserKeys.first, String.class);
    }

    public String getLast() {
        return get(UserKeys.last, String.class);
    }

    public void setKeycloakId(String id) {
        attributes.put(UserKeys.keycloakId, id);
    }

    public String getKeycloakId() {
        return get(UserKeys.keycloakId, String.class);
    }
}
