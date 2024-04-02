package com.hateos.Repository;

import com.hateos.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {
//    @Query("UPDATE Account a SET a.balance=a.balance + ?1 where a.id = ?2")
//    @Modifying
//    public void updateBalance(float amount,int id);
}
