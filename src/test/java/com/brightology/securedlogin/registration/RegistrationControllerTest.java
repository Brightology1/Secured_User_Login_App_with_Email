package com.brightology.securedlogin.registration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {RegistrationController.class})
@ExtendWith(SpringExtension.class)
class RegistrationControllerTest {
    @Autowired
    private RegistrationController registrationController;

    @MockBean
    private RegistrationService registrationService;

    @Test
    void testRegister() throws Exception {
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.get("/")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult.content(
                objectMapper.writeValueAsString(new RegistrationRequest("Jane", "Doe", "jane.doe@example.org", "iloveyou")));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.registrationController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}

