package com.microsoft.hackathon.copilotdemo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest()
@AutoConfigureMockMvc 
@DisplayName("CopilotDemo Application Tests")
class CopilotDemoApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    // ============ Existing Test ============
    @Test
    @DisplayName("GET /hello with key=world should return 'hello world'")
    void hello() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/hello?key=world"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().string("hello world"));
    }

    // ============ New Test Cases for DemoController ============

    @Test
    @DisplayName("GET /hello without key parameter should return 'key not passed'")
    void helloWithoutKeyParameter() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/hello"))
            .andExpect(status().isOk())
            .andExpect(content().string("key not passed"));
    }

    @Test
    @DisplayName("GET /hello with empty key parameter should return 'key not passed'")
    void helloWithEmptyKeyParameter() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/hello?key="))
            .andExpect(status().isOk())
            .andExpect(content().string("key not passed"));
    }

    @Test
    @DisplayName("GET /hello with key containing spaces should return greeting with encoded spaces")
    void helloWithSpacesInKey() throws Exception {
        // Note: MockMvc passes URL parameters as-is; %20 is not auto-decoded
        mockMvc.perform(MockMvcRequestBuilders.get("/hello?key=john%20doe"))
            .andExpect(status().isOk())
            .andExpect(content().string("hello john%20doe"));
    }

    @Test
    @DisplayName("GET /hello with numeric key should return numeric greeting")
    void helloWithNumericKey() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/hello?key=123"))
            .andExpect(status().isOk())
            .andExpect(content().string("hello 123"));
    }

    @Test
    @DisplayName("GET /hello with special characters in key should return greeting with special chars")
    void helloWithSpecialCharacters() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/hello?key=test@123"))
            .andExpect(status().isOk())
            .andExpect(content().string("hello test@123"));
    }

    @Test
    @DisplayName("GET /hello with multiple key parameters should concatenate with comma")
    void helloWithMultipleKeyParameters() throws Exception {
        // Note: When multiple values provided for same parameter, Spring concatenates with comma
        mockMvc.perform(MockMvcRequestBuilders.get("/hello?key=first&key=second"))
            .andExpect(status().isOk())
            .andExpect(content().string("hello first,second"));
    }

    @Test
    @DisplayName("GET /hello with long key should return full greeting")
    void helloWithLongKey() throws Exception {
        String longKey = "thisisaverylongkeyvalue";
        mockMvc.perform(MockMvcRequestBuilders.get("/hello?key=" + longKey))
            .andExpect(status().isOk())
            .andExpect(content().string("hello " + longKey));
    }

    @Test
    @DisplayName("GET /hello endpoint exists and is accessible")
    void helloEndpointIsAccessible() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/hello?key=test"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("text/plain;charset=UTF-8"));
    }

    @Test
    @DisplayName("GET /hello returns plain text response")
    void helloReturnsPlainText() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/hello?key=content-type-test"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("text/plain;charset=UTF-8"));
    }

    // ============ Application Startup Tests ============

    @Test
    @DisplayName("Application context loads successfully")
    void contextLoads() {
        // Test verifies Spring Boot application context loads without errors
        // Implicit assertion: if context doesn't load, test fails
    }

}