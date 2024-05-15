package com.hateos.Controller;
import com.hateos.controller.AccountController;
import com.hateos.entity.Account;
import com.hateos.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
public class ControllerTest {
    @Mock
    private AccountService accountService;
    @InjectMocks
    private AccountController accountController;
    @Test
    public void testGetAllAccount() {
        // Create a list of mock accounts
        // Create a list of mock accounts
        List<Account> mockAccounts = new ArrayList<>();
        mockAccounts.add(new Account(1, "12345688", 10000));
        mockAccounts.add(new Account(2,"abc123",1000));
        // Mock behavior of accountService.listall() to return the list of mock accounts
        when(accountService.listall()).thenReturn(mockAccounts);

        // Call the controller method
        ResponseEntity<List<Account>> responseEntity = accountController.getAllAccount();

        // Assert the response status
        assert responseEntity.getStatusCode() == HttpStatus.OK;

        // Assert that the response contains the expected accounts
        List<Account> returnedAccounts = responseEntity.getBody();
        assert returnedAccounts != null;
        assert returnedAccounts.size() == mockAccounts.size();
        for (int i = 0; i < returnedAccounts.size(); i++) {
            Account returnedAccount = returnedAccounts.get(i);
            Account mockAccount = mockAccounts.get(i);
            assert returnedAccount.getId() == mockAccount.getId();
            assert returnedAccount.getAccountNumber().equals(mockAccount.getAccountNumber());
            assert returnedAccount.getBalance() == mockAccount.getBalance();
        }
    }
    @Test
    public void testGetSingleAccount()
    {
        Account mockAccount = new Account(1, "John Doe", 1000);

        // Mock behavior of accountService.getSinleAccount(id) to return the mock account
        when(accountService.getSinleAccount(anyInt())).thenReturn(mockAccount);

        // Call the controller method with a sample ID
        int sampleId = 1;
        ResponseEntity<?> responseEntity = accountController.getsingleaccount(sampleId);

        // Assert that the response body is not null
        assert responseEntity.getBody() != null;

        // Assert that the returned account matches the mock account
        Account returnedAccount = (Account) responseEntity.getBody();
        assert returnedAccount.getId() == mockAccount.getId();
        assert returnedAccount.getAccountNumber().equals(mockAccount.getAccountNumber());
        assert returnedAccount.getBalance() == mockAccount.getBalance();

    }
}
