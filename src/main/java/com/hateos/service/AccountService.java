package com.hateos.service;

import com.hateos.entity.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {
    public List<Account> listall();
    public Account getSinleAccount(int id);
    public Account addDetails(Account account);
    public ResponseEntity<?> deposite(float amount,int id);
    public ResponseEntity<?> withdraw(float amount,int id);
    public ResponseEntity<?> deleteAccount(int id);
}
