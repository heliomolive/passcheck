package hmo.passcheck.integrationtest;

import com.fasterxml.jackson.databind.ObjectMapper;
import hmo.passcheck.controller.request.VerifyPasswordRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PasscheckIntTest {

    private static final String VALIDATE_URI = "/v1/validate";
    private static final String PASS_CLASS_NAME = "NCPWR";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void validate() throws Exception {
        VerifyPasswordRequest request = new VerifyPasswordRequest("asdF@1234", PASS_CLASS_NAME);
        mockMvc.perform(post(VALIDATE_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNoContent());
    }

    @Test
    public void validateWhenPasswordIsInvalid() throws Exception {
        VerifyPasswordRequest request = new VerifyPasswordRequest("12345678", PASS_CLASS_NAME);
        mockMvc.perform(post(VALIDATE_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnprocessableEntity());
    }
}
