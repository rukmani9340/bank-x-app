package com.bankx.application.domain;

import com.bankx.application.enums.AccountType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="user_account")
public class UserAccount extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private  String accountNo;

    private String id;

    @OneToOne
    private Branch branch;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Double balance = 0.0;

    @Column(name = "account_type")
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    public UserAccount() {
        super();
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAccount that = (UserAccount) o;
        return Objects.equals(accountNo, that.accountNo) && Objects.equals(branch, that.branch) && Objects.equals(user, that.user) && Objects.equals(balance, that.balance) && accountType == that.accountType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountNo, branch, user, balance, accountType);
    }
}
