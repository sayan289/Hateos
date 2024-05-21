package com.hateos.Controller.Repositoy;

import com.hateos.Repository.AccountRepository;
import com.hateos.entity.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RepositoryTest {
    @Autowired
    AccountRepository accountRepository;
    Account account;
    @BeforeEach
    void setup()
    {
        account=new Account(2,"12345",1000);
    }
    @Test
    void test()
    {
        accountRepository.save(account);
    }

}
