package com.bankx.application.service.dto;

import com.bankx.application.domain.Bank;
import com.bankx.application.domain.Branch;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO representing a branch.
 */
public class BranchDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String ifscCode;

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

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
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

    public BranchDTO() {
    }

    public BranchDTO(Branch branch) {
        this.id = branch.getId();
        this.name = branch.getName();
        this.ifscCode = branch.getIfscCode();
        this.address = branch.getAddress();
        this.bank = branch.getBank();
    }

    @Override
    public String toString() {
        return "BranchDTO{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", ifscCode='" + ifscCode + '\'' +
            ", address='" + address + '\'' +
            '}';
    }
}
