package mx.edu.ito.estres.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import mx.edu.ito.estres.infrastructure.adapters.in.web.dto.StudentRequestDTO;
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
public class StudentIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldRegisterStudentSuccessfully() throws Exception {

        StudentRequestDTO dto = new StudentRequestDTO(
                "test@itoaxaca.edu.mx",
                "1234",
                2,
                "Local",
                6,
                true
        );

        mockMvc.perform(post("/api/v1/students/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }
    @Test
    void shouldFailWhenSemesterInvalid() throws Exception {

        StudentRequestDTO dto = new StudentRequestDTO(
                "test2@itoaxaca.edu.mx",
                "1234",
                10,
                "Local",
                6,
                true
        );

        mockMvc.perform(post("/api/v1/students/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }
}