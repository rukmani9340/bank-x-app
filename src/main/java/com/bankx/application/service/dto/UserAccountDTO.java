package com.bankx.application.service.dto;

import com.bankx.application.domain.Branch;
import com.bankx.application.domain.UserAccount;
import com.bankx.application.domain.User;
import com.bankx.application.enums.AccountType;

import javax.persistence.OneToOne;
import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO representing a customer account.
 */
public class UserAccountDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String accountNo;

    @OneToOne
    private Branch branch;

    private User user;

    private Double balance;

    private AccountType accountType;


    private String createdBy;

    private Instant createdDate;

    private String lastModifiedBy;

    private Instant lastModifiedDate;


    public UserAccountDTO() {
        // Empty constructor needed for Jackson.
    }

    public UserAccountDTO(UserAccount userAccount) {
        this.id = userAccount.getId();
        this.accountNo = userAccount.getAccountNo();
        this.branch = userAccount.getBranch();
        this.user = userAccount.getUser();
        this.balance = userAccount.getBalance();
        this.accountType = userAccount.getAccountType();
        this.createdBy = userAccount.getCreatedBy();
        this.createdDate = userAccount.getCreatedDate();
        this.lastModifiedBy = userAccount.getLastModifiedBy();
        this.lastModifiedDate = userAccount.getLastModifiedDate();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }


    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    // prettier-ignore


    @Override
    public String toString() {
        return "UserAccountDTO{" +
            "id='" + id + '\'' +
            ", accountNo=" + accountNo +
            ", branch=" + branch +
            ", user=" + user +
            ", balance=" + balance +
            ", accountType=" + accountType +
            ", createdBy='" + createdBy + '\'' +
            ", createdDate=" + createdDate +
            ", lastModifiedBy='" + lastModifiedBy + '\'' +
            ", lastModifiedDate=" + lastModifiedDate +
            '}';
    }
}
