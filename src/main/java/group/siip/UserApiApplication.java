package group.siip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(value = "group.siip.userapi.user.gateway")
public class UserApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApiApplication.class, args);
    }

}
