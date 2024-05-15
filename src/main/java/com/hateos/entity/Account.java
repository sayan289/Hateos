package com.hateos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Account extends RepresentationModel<Account> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public String accountNumber;
    public float balance;

    public Account() {

    }
}
