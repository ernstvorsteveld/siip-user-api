package group.siip.userapi.user.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(
        controllers = {UserController.class},
        properties = {"spring.data.mongodb.port=23017"})
@AutoConfigureDataMongo
@Disabled
public class UserControllerITest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtDecoder jwtDecoder;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    @WithMockUser(username = "john", roles = {"ADMIN"})
    public void should_create_user_and_get_user_on_mobile() throws Exception {
        UserRequest userRequest = new UserRequest();
        userRequest.setFirst("john");
        userRequest.setMobile("0611111111");
        this.mockMvc
                .perform(post("/users/")
                        .content(mapper.writeValueAsBytes(userRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

        this.mockMvc
                .perform(get("/users/0611111111"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.first").value("john"));
    }

    @Test
    @WithMockUser(username = "john", roles = {"ADMIN"})
    public void should_not_allow_create_of_existing_mobile() throws Exception {
        UserRequest userRequest = new UserRequest();
        userRequest.setFirst("barak");
        userRequest.setMobile("0611111112");
        this.mockMvc
                .perform(post("/users/")
                        .content(mapper.writeValueAsBytes(userRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

        this.mockMvc
                .perform(post("/users/")
                        .content(mapper.writeValueAsBytes(userRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is4xxClientError())
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$.error")
                                .value("Mobile number not unique"));
    }

}
