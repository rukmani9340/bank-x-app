package com.bankx.application.service.dto;

import com.bankx.application.domain.Bank;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO representing a partner bank.
 */
public class PartnerBankDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String address;

    private Bank bank;

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

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    @Override
    public String toString() {
        return "PartnerBankDTO{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", address='" + address + '\'' +
            ", bank=" + bank +
            '}';
    }
}
