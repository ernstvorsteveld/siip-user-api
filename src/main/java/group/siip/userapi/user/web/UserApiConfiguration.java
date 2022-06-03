package group.siip.userapi.user.web;

import group.siip.userapi.TimeOutSettings;
import group.siip.userapi.user.domain.UserService;
import group.siip.userapi.user.domain.UserServiceImpl;
import group.siip.userapi.user.gateway.keycloak.KeycloakMapper;
import group.siip.userapi.user.gateway.keycloak.KeycloakMapperImpl;
import group.siip.userapi.user.gateway.keycloak.UserKcGateway;
import group.siip.userapi.user.gateway.mongo.UserDocumentMapper;
import group.siip.userapi.user.gateway.UserGateway;
import group.siip.userapi.user.gateway.mongo.UserGatewayMongoDb;
import group.siip.userapi.user.gateway.mongo.UserRepository;
import group.siip.userapi.user.usecase.*;
import group.siip.util.keycloak.service.KcUserRequests;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.time.Duration;

@Configuration
public class UserApiConfiguration {

    @Bean
    public GetUser getUser(
            UserResponseMapper userResponseMapper,
            UserService userService) {
        return new GetUserImpl(
                userResponseMapper,
                userService);
    }

    @Bean
    public CreateUser createUser(
            UserRequestMapper userRequestMapper,
            UserResponseMapper userResponseMapper,
            UserService userService) {
        return new CreateUserImpl(
                userRequestMapper,
                userResponseMapper,
                userService);
    }

    @Bean
    public DeleteUser deleteUser(UserService userService) {
        return new DeleteUserImpl(userService);
    }

    @Bean
    public UserResponseMapper userResponseMapper() {
        return new UserResponseMapper();
    }

    @Bean
    public UserRequestMapper userRequestMapper() {
        return new UserRequestMapper();
    }

    @Bean
    public UserDocumentMapper userDocumentMapper() {
        return new UserDocumentMapper();
    }

    @Bean
    public UserService userService(
            UserGateway userDbGateway,
            UserGateway userKcGateway) {
        return new UserServiceImpl(userDbGateway, userKcGateway);
    }

    @Bean
    public UserGateway userDbGateway(
            UserRepository userRepository,
            UserDocumentMapper userDocumentMapper) {
        return new UserGatewayMongoDb(userRepository, userDocumentMapper);
    }

    @Bean
    public KeycloakMapper keycloakMapper() {
        return new KeycloakMapperImpl();
    }

    @Bean
    public UserGateway userKcGateway(
            KcUserRequests kcUserRequests,
            KeycloakMapper keycloakMapper,
            TimeOutSettings timeOutSettings) {
        return new UserKcGateway(kcUserRequests, keycloakMapper, timeOutSettings);
    }

    @Bean
    public TimeOutSettings timeOutSettings(
            @Value("${sipp.http.timeout.default}") String defaultTimeOut) {
        return TimeOutSettings.builder()
                .defaultTimeOut(Duration.parse(defaultTimeOut))
                .build();
    }
}