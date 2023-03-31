package com.bankx.application.service.dto;

import com.bankx.application.domain.Bank;
import com.bankx.application.domain.User;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO representing a bank.
 */
public class BankDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String address;

    public BankDTO() {
    }

    public BankDTO(Bank bank) {
        this.id = bank.getId();
        this.name = bank.getName();
        this.address = bank.getAddress();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    @Override
    public String toString() {
        return "BankDTO{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", address='" + address + '\'' +
            '}';
    }
}
