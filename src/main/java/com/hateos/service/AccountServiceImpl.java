package com.hateos.service;

import com.hateos.Exception.CustomException;
import com.hateos.Repository.AccountRepository;
import com.hateos.entity.Account;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class AccountServiceImpl implements  AccountService{
    @Autowired
    private AccountRepository accountRepository;
    //Fetch all acount details
    public List<Account> listall()
    {
        return accountRepository.findAll();
    }
    //Fetch a single account details
    public Account getSinleAccount(int id)
    {
//        Optional<Account>byId=accountRepository.findById(id);
//        if(byId.isEmpty())
//        {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account id not found");
//        }
//        else {
//            return ResponseEntity.ok(byId.get());
//        }
         return accountRepository.findById(id).orElseThrow(()->new CustomException("Id Not present"));
    }
    //Adding account details
    public Account addDetails(Account account)
    {
        return accountRepository.save(account);
    }
    //Deposite amount
    public ResponseEntity<?> deposite(float amount,int id)
    {
        Optional<Account> byId=accountRepository.findById(id);
        if(byId.isEmpty())
        {
           return  new ResponseEntity<>("Invalid id",HttpStatus.NOT_FOUND);
        }
        else {
            Account account=byId.get();
            float balance=account.getBalance();
            float updateBalance=balance+amount;
            account.setBalance(updateBalance);
            return new ResponseEntity<>(accountRepository.save(account),HttpStatus.OK);
        }
    }
    //withdraw amount
    public ResponseEntity<?> withdraw(float amount,int id)
    {
        Optional<Account> byId=accountRepository.findById(id);
        if(byId.isEmpty())
        {
            return new ResponseEntity<>("Invalid Id",HttpStatus.NOT_FOUND);
        }
        //if amount is low than balance show Low balance
        else {
            Account account=byId.get();
            if(amount>account.getBalance())
            {
                throw new CustomException("Low Balance");
            }
            else {
                float balance=account.getBalance();
                float updateBalance=balance-amount;
                account.setBalance(updateBalance);
                return  new ResponseEntity<>(accountRepository.save(account),HttpStatus.OK);
            }
        }
    }
    //Delete account
    public ResponseEntity<?> deleteAccount(int id)
    {
        Optional<Account>byId=accountRepository.findById(id);
        if(byId.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account id not found");
        }
        else {
            accountRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Account deleted succesfully");
        }
    }
}
