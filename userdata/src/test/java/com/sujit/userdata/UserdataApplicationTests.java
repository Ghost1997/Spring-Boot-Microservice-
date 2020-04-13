package com.sujit.userdata;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import com.sujit.userdata.controller.AccountController;
import com.sujit.userdata.controller.UserController;

@Nested
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)

class UserdataApplicationTests {
	@InjectMocks
	UserController user;
	
	@InjectMocks
	AccountController account;

	@Autowired
	MockMvc mockMvc;

	@Autowired
	WebApplicationContext webApplicationContext;

	@Test
	@Order(1)
	void createUser() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/info/createUser").contentType(MediaType.APPLICATION_JSON)
				.content("{\n" + "	\"id\":2000,\n" + "    \"createdBy\": \"1\",\n"
						+ "    \"email\": \"mailatsujit@gmail.com\",\n" + "    \"firstName\": \"Sujit\",\n"
						+ "    \"lastModifiedBy\": \"1\",\n" + "    \"lastName\": \"Kumar\",\n"
						+ "    \"status\": \"active\",\n" + "    \"username\": \"Alpha\"\n" + "  \n" + "}")
				.accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));

	}

	@Test
	@Order(2)
	void findUser() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/info/getUser/2000/mailatsujit@gmail.com"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}
	@Test
	@Order(3)
	void updateUser() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.put("/info/updateUser/2000").contentType(MediaType.APPLICATION_JSON).
				content("{\n" + 
						"	\"id\":2000,\n" + 
						"    \"firstName\": \"Gourav\"\n" + 
						"  \n" + 
						"}").accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	@Order(4)
	void deleteUser() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/info/deleteUser/2000"))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}
	
	@Test
	@Order(5)
	void createAccount() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/info/createAccount").contentType(MediaType.APPLICATION_JSON)
				.content("{\n" + 
						"    \"lastModifiedBy\": \"1\",\n" + 
						"    \"accountName\": \"SBI\",\n" + 
						"    \"createdBy\": \"1\",\n" + 
						"    \"owner\":\"2\",\n" + 
						"    \"id\" : \"1001\"\n" + 
						"}")
				.accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));

	}

	@Test
	@Order(6)
	void findAccount() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/info/getAccount/1001/2"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}
	@Test
	@Order(7)
	void updateAccount() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.put("/info/updateAccount/1001").contentType(MediaType.APPLICATION_JSON).
				content("{\n" + 
						"    \"lastModifiedBy\": \"2\",\n" + 
						"    \"accountName\": \"PNB\",\n" + 
						"    \"createdBy\": \"2\",\n" + 
						"    \"owner\":\"2\",\n" + 
						"    \"id\" : \"1001\"\n" + 
						"}").accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	@Order(8)
	void deleteAccount() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/info/deleteAccount/1001"))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

}
