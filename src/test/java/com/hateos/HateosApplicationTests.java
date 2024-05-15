package com.hateos;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hateos.Repository.AccountRepository;
import com.hateos.controller.AccountController;
import com.hateos.entity.Account;
import com.hateos.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//@SpringBootTest
@WebMvcTest(AccountController.class)
class HateosApplicationTests {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private AccountService accountService;
//	@MockBean
//	private AccountRepository repository;

//	@Test
//	public void testApiEndpoint() throws Exception {
//		mockMvc.perform(get("/account/test"))
//				.andExpect(status().isOk())
//				.andExpect(content().string("Welcome from jenkins"));
//	}
	@Test
	public void testcontroller() throws Exception {
		Account account=new Account(1,"abc123",100.00F);
		when(accountService.addDetails(account)).thenReturn(account);
		mockMvc.perform(get("/fetch").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(account)))
				.andExpect(status().isOk());

	}
}
