package group.siip.util.keycloak;

import group.siip.util.keycloak.domain.BaseAddress;
import group.siip.util.keycloak.domain.Realm;
import group.siip.util.keycloak.service.KcUserRequests;
import group.siip.util.keycloak.service.KcUserRequestsImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableWebSecurity
public class KeycloakClientConfiguration {

    @Value("${spring.security.oauth2.client.registration.kc_demo_app}")
    private String registrationId;

    @Bean
    public KcUserRequests kcUserRequests(
            WebClient webclient,
            @Value("${siip.keycloak.schema}") String schema,
            @Value("${siip.keycloak.fqdn}") String fqdn,
            @Value("${siip.keycloak.port}") String port,
            @Value("${siip.keycloak.realm}") String realm) {
        return new KcUserRequestsImpl(webclient, new BaseAddress(schema, fqdn, port), new Realm(realm));
    }

    @Bean
    public WebClient webclient(
            WebClient.Builder webClientBuilder,
            ClientRegistrationRepository clientRegistrations,
            OAuth2AuthorizedClientRepository authorizedClients) {
        ServletOAuth2AuthorizedClientExchangeFilterFunction oauth =
                new ServletOAuth2AuthorizedClientExchangeFilterFunction(clientRegistrations, authorizedClients);
        oauth.setDefaultClientRegistrationId(registrationId);
        return webClientBuilder.filter(oauth).build();
    }

}
