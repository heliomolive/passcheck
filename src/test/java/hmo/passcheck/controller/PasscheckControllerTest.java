package hmo.passcheck.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hmo.passcheck.controller.request.VerifyPasswordRequest;
import hmo.passcheck.domain.enums.PassClassName;
import hmo.passcheck.domain.exception.UnprocessableEntityException;
import hmo.passcheck.service.PassCheckService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@Import(ControllerTestConfig.class)
public class PasscheckControllerTest {

    private static final String VALIDATE_URI = "/v1/validate";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PassCheckService passCheckService;


    @Test
    public void validate() throws Exception {
        VerifyPasswordRequest request = new VerifyPasswordRequest("asdF@1234", PassClassName.NCPWR.toString());
        mockMvc.perform(post(VALIDATE_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNoContent());
    }

    @Test
    public void validateWhenPasswordIsInvalid() throws Exception {
        VerifyPasswordRequest request = new VerifyPasswordRequest("asdF@1234", PassClassName.NCPWR.toString());
        String errorMessage = "Formato de senha inválido";

        doThrow(
                UnprocessableEntityException.builder().userMessage(errorMessage).build()
        ).when(passCheckService).validate(anyString(), anyString());

        mockMvc.perform(post(VALIDATE_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.userMessage").value(errorMessage));
    }

}
