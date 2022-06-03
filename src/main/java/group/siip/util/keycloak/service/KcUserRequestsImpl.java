package group.siip.util.keycloak.service;

import group.siip.util.keycloak.domain.BaseAddress;
import group.siip.util.keycloak.domain.Mobile;
import group.siip.util.keycloak.domain.Realm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class KcUserRequestsImpl implements KcUserRequests {

    public static final String USER_CREATE_URL = "%s/auth/admin/realms/%s/users";
    public static final String USER_DELETE_URL = "%s/auth/admin/realms/%s/users/%s";
    public static final String USER_GET_URL = "%s/auth/admin/realms/%s/users?username=%s&exact=true";
    public static final String USER_GET_URL_ALL = "%s/auth/admin/realms/%s/users";
    private final String userUrl;

    private WebClient webClient;
    private BaseAddress baseAddress;
    private Realm realm;

    public KcUserRequestsImpl(WebClient webClient, BaseAddress baseAddress, Realm realm) {
        this.webClient = webClient;
        this.baseAddress = baseAddress;
        this.realm = realm;
        this.userUrl = String.format(USER_CREATE_URL, baseAddress.get(), realm.get());
    }

    @Override
    public Mono<String> create(KeycloakUserRequest user) {
        log.debug("About to create user in Keycloak {}.", user);
        return webClient.post()
                .uri(userUrl)
                .body(BodyInserters.fromValue(user))
                .header("content-type", MediaType.APPLICATION_JSON_VALUE)
                .exchangeToMono(r -> Mono.just(r.headers().header("Location").get(0)));
    }

    @Override
    public Mono<KeycloakUserResponse[]> get(Mobile mobile) {
        return webClient.get()
                .uri(String.format(USER_GET_URL, baseAddress.get(), realm.get(), mobile.get()))
                .retrieve()
                .bodyToMono(KeycloakUserResponse[].class);
    }

    @Override
    public Mono<Void> delete(Mobile mobile) {
        return webClient
                .delete()
                .uri(String.format(USER_DELETE_URL, baseAddress.get(), realm.get(), get(mobile).block()[0].getId()))
                .retrieve()
                .bodyToMono(Void.class);
    }

    @Override
    public Mono<Void> delete(String id) {
        return webClient
                .delete()
                .uri(String.format(USER_DELETE_URL, baseAddress.get(), realm.get(), id))
                .retrieve()
                .bodyToMono(Void.class);
    }

    @Override
    public Mono<KeycloakUserResponse> update(KeycloakUserRequest userRequest) {
        return null;
    }

    @Override
    public Flux<KeycloakUserResponse> get() {
        return webClient
                .get()
                .uri(String.format(USER_GET_URL_ALL, baseAddress.get(), realm.get()))
                .retrieve()
                .bodyToFlux(KeycloakUserResponse.class);
    }

    protected String getUserApiUrl() {
        return userUrl;
    }
}
