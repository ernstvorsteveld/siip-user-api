package group.siip.util.keycloak.service;

import group.siip.util.keycloak.domain.Mobile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface KcUserRequests {

    Mono<String> create(KeycloakUserRequest user);

    Mono<KeycloakUserResponse[]> get(Mobile mobile);

    Mono<Void> delete(Mobile mobile);

    Mono<Void> delete(String id);

    Mono<KeycloakUserResponse> update(KeycloakUserRequest userRequest);

    Flux<KeycloakUserResponse> get();
}
