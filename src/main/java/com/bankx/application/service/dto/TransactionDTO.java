package com.bankx.application.service.dto;

import com.bankx.application.domain.UserAccount;
import com.bankx.application.domain.Transaction;
import com.bankx.application.enums.Status;

public class TransactionDTO {

    private String id;

    private String tansactionId;

    private Status status;

    private UserAccount accountFrom;

    private UserAccount accountTo;

    private Double balance;

    private Double transactionCharge;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTansactionId() {
        return tansactionId;
    }

    public void setTansactionId(String tansactionId) {
        this.tansactionId = tansactionId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public UserAccount getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(UserAccount accountFrom) {
        this.accountFrom = accountFrom;
    }

    public UserAccount getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(UserAccount accountTo) {
        this.accountTo = accountTo;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getTransactionCharge() {
        return transactionCharge;
    }

    public void setTransactionCharge(Double transactionCharge) {
        this.transactionCharge = transactionCharge;
    }

    public TransactionDTO() {
    }

    public TransactionDTO(Transaction transaction) {
        this.id = transaction.getId();
        this.tansactionId = transaction.getTransactionId();
        this.status = transaction.getStatus();
        this.accountFrom = transaction.getAccountFrom();
        this.accountTo = transaction.getAccountTo();
        this.balance = transaction.getBalance();
        this.transactionCharge = transaction.getTransactionCharge();
    }
}
