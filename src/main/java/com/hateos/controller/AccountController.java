package com.hateos.controller;

import com.hateos.Repository.AccountRepository;
import com.hateos.entity.Account;
import com.hateos.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @GetMapping("/fetch")
    public ResponseEntity<List<Account>> getAllAccount()
    {
        List<Account> allaccount = accountService.listall();
        allaccount.forEach(account -> {
            //Adding SelfLink
            Link selfLink = WebMvcLinkBuilder.linkTo(AccountController.class).slash("fetch").slash(account.getId()).withSelfRel();
            account.add(selfLink);
            //Adding DepositeLink
            Link depositLink = linkTo(methodOn(AccountController.class).deposite(0,account.getId())).withRel("deposit [amount must be in params]");
            depositLink=depositLink.withHref(depositLink.getHref() + "?amount={amount}");
            account.add(depositLink);
            //Adding withdraw Link
            Link withdrawLink = linkTo(methodOn(AccountController.class).withdraw(0, account.getId())).withRel("withdraw [amount must be in params]");
            withdrawLink=withdrawLink.withHref(withdrawLink.getHref() + "?amount={amount}");
            account.add(withdrawLink);
            //Add delete
            Link deleteLink=linkTo(methodOn(AccountController.class).deleteAccount(account.getId())).withRel("Deleted");
            account.add(deleteLink);
        });
        return new ResponseEntity<>(accountService.listall(), HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Account account)
    {
        Account addAccount = accountService.addDetails(account);
        Link selfLink = linkTo(AccountController.class).slash("fetch").slash(addAccount.getId()).withSelfRel();
        account.add(selfLink);
        //Adding Deposite
        Link depositLink = linkTo(methodOn(AccountController.class).deposite(0,addAccount.getId())).withRel("deposit [amount must be in params]");
        depositLink=depositLink.withHref(depositLink.getHref() + "?amount={amount}");
        account.add(depositLink);
        //Adding Withdraw link
        Link withdrawLink = linkTo(methodOn(AccountController.class).withdraw(0, addAccount.getId())).withRel("withdraw [amount must be in params]");
        withdrawLink=withdrawLink.withHref(withdrawLink.getHref() + "?amount={amount}");
        account.add(withdrawLink);
        //Add delete
        Link deleteLink=linkTo(methodOn(AccountController.class).deleteAccount(addAccount.getId())).withRel("Deleted");
        account.add(deleteLink);
        return ResponseEntity.ok(account);
    }
    //Use Account ServiceImpl method
    @GetMapping("/fetch/{id}")
    public ResponseEntity<?> getsingleaccount(@PathVariable int id) {
        Account account= accountService.getSinleAccount(id);
       Link selfLink = linkTo(AccountController.class).slash("fetch").slash(account.getId()).withSelfRel();
       account.add(selfLink);
       //Adding Deposite
        Link depositLink = linkTo(methodOn(AccountController.class).deposite(0,account.getId())).withRel("deposit [amount must be in params]");
        depositLink=depositLink.withHref(depositLink.getHref() + "?amount={amount}");
        account.add(depositLink);
        //Adding Withdraw link
        Link withdrawLink = linkTo(methodOn(AccountController.class).withdraw(0, account.getId())).withRel("withdraw [amount must be in params]");
        withdrawLink=withdrawLink.withHref(withdrawLink.getHref() + "?amount={amount}");
        account.add(withdrawLink);
        Link deleteLink=linkTo(methodOn(AccountController.class).deleteAccount(account.getId())).withRel("Deleted");
        account.add(deleteLink);
        return new ResponseEntity<>(account,HttpStatus.OK);

    }
    @PutMapping("/deposite/{id}")
    public ResponseEntity<?> deposite(@Param("amount") float amount, @PathVariable int id)
    {
        return accountService.deposite(amount,id);
    }
    @PutMapping("/withdraw/{id}")
    public ResponseEntity<?> withdraw(@Param("amount") float amount, @PathVariable int id)
    {
        return accountService.withdraw(amount,id);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>deleteAccount(@PathVariable int id)
    {
        return accountService.deleteAccount(id);
    }
    @GetMapping("/test")
    public String test()
    {
        return "Hello world ";
    }
}
