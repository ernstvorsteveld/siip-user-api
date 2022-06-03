package group.siip.userapi.user.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.util.StringUtils;

@Getter
@Setter
@Builder
@Slf4j
public class User {

    @Id
    private String id;

    private String mobile;
    private String first;
    private String last;

    private String keycloakId;

    private String location;

    public void setLocation(String location) {
        this.location = location;
        if(StringUtils.hasLength(location)) {
            try {
                this.keycloakId = location.substring(location.lastIndexOf("/") + 1);
            } catch (Exception ignore) {
                log.error("User error: could not retrieve id from location string.");
            }
        }
    }
}
