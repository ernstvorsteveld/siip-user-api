package group.siip.userapi.user.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = { "group.siip.userapi.user" })
public class UserConfiguration {
}
