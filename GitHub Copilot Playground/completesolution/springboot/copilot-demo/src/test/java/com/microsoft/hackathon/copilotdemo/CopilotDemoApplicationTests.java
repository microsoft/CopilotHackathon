package com.microsoft.hackathon.copilotdemo;

import static org.mockito.Mockito.mock;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;;


@SpringBootTest()
@AutoConfigureMockMvc 
class CopilotDemoApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
	void hello() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/hello?key=world"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().string("hello world"));
	}

	@Test
	void helloNoKey() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/hello"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().string("key not passed"));
	}

	@Test
	void diffdates() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/diffdates?date1=01-01-2021&date2=01-02-2021"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().string("difference in days: 31"));
	}

	@Test
	void diffdatesNoDate1() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/diffdates?date2=01-02-2021"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().string("date not passed"));
	}

	@Test
	void diffdatesNoDate2() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/diffdates?date1=01-01-2021"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().string("date not passed"));
	}

	@Test
	void validatephone() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/validatephone?phone=+34666666666"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().string("true"));
		
		mockMvc.perform(MockMvcRequestBuilders.get("/validatephone?phone=+34766666666"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().string("true"));

		mockMvc.perform(MockMvcRequestBuilders.get("/validatephone?phone=+34966666666"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().string("true"));
	
		mockMvc.perform(MockMvcRequestBuilders.get("/validatephone?phone=+3466666666"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().string("false"));

		mockMvc.perform(MockMvcRequestBuilders.get("/validatephone?phone=+346666666666"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().string("false"));

		mockMvc.perform(MockMvcRequestBuilders.get("/validatephone?phone=+3466666666a"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().string("false"));

		mockMvc.perform(MockMvcRequestBuilders.get("/validatephone?phone=+34866666666"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().string("false"));
	}

	@Test
	void validatephoneNoPhone() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/validatephone"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().string("false"));
	}

	@Test
	void validatedni() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/validatedni?dni=12345678A"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().string("true"));
	
		mockMvc.perform(MockMvcRequestBuilders.get("/validatedni?dni=12345678a"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().string("false"));

		mockMvc.perform(MockMvcRequestBuilders.get("/validatedni?dni=1234567A"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().string("false"));

		mockMvc.perform(MockMvcRequestBuilders.get("/validatedni?dni=123456789A"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().string("false"));

		mockMvc.perform(MockMvcRequestBuilders.get("/validatedni?dni=12345678"))	
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().string("false"));
	
	}

	@Test
	void validatedniNoDni() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/validatedni"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().string("false"));
	}

	// test for /color/{color} endpoint
	@Test
	void color() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/color/red"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().string("#FF0000"));
	}

	@Test
	void colorNotFound() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/color/purple"))
			.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	void joke() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/joke"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			// check that content is a string
			.andExpect(MockMvcResultMatchers.content().string(Matchers.any(String.class)));

	}

	@Test
	void parseUrl() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/parseurl?url=https://learn.microsoft.com/en-us/azure/aks/concepts-clusters-workloads?source=recommendations"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			// validate json fields
			.andExpect(MockMvcResultMatchers.jsonPath("$.protocol").value("https"))
			.andExpect(MockMvcResultMatchers.jsonPath("$.host").value("learn.microsoft.com"))
			.andExpect(MockMvcResultMatchers.jsonPath("$.path").value("/en-us/azure/aks/concepts-clusters-workloads"))
			.andExpect(MockMvcResultMatchers.jsonPath("$.query").value("source=recommendations"));

	}

	@Test
	void parseUrlNoUrl() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/parseurl"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().string("url not passed"));
	}

}
