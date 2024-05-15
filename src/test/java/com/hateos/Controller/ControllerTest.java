package com.hateos.Controller;
import com.hateos.controller.AccountController;
import com.hateos.entity.Account;
import com.hateos.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(AccountController.class)
public class ControllerTest {
    @MockBean
    private AccountService accountService;
    @Autowired
    private MockMvc mockMvc;
    private Account account;

    @BeforeEach
    void setUp() {
        account=new Account(10,"huihds-90809-jjj",678);
    }

    @Test
    public void testGetSingleAccount() throws Exception {
        when(accountService.getSinleAccount(10))
                .thenReturn(account);
        ResultActions perform = mockMvc.perform(get("/account/fetch/10")
                .contentType(MediaType.APPLICATION_JSON));
        perform.andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
    @Test
    public void testGetSingleAccounts() throws Exception {
        when(accountService.getSinleAccount(10))
                .thenReturn(account);
        ResultActions perform = mockMvc.perform(get("/account/fetch/10")
                .contentType(MediaType.APPLICATION_JSON));
        perform.andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(10));

    }

}
