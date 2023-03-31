package com.bankx.application.domain;

import com.bankx.application.enums.Status;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * An authority (a security role) used by Spring Security.
 */
@Entity
@Table(name = "transaction")
public class Transaction extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_seq")
    @GenericGenerator(name = "transaction_seq", strategy = "com.bankx.application.domain.StringPrefixedSequenceIdGenerator", parameters = {
        @org.hibernate.annotations.Parameter(name = "increment_size", value = "1"), @org.hibernate.annotations.Parameter(name = "valuePrefix", value = "TRAN"),
        @org.hibernate.annotations.Parameter(name = "numberFormat", value = "%05d") })
    private String id;

   private String transactionId;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

   @ManyToOne
   @JoinColumn(name = "account_from_no")
   private UserAccount accountFrom;

   @ManyToOne
   @JoinColumn(name = "account_to_no")
   private UserAccount accountTo;

   private Double balance;

   private Double transactionCharge = 0.0;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
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

    public Double getTransactionCharge() {
        return transactionCharge;
    }

    public void setTransactionCharge(Double transactionCharge) {
        this.transactionCharge = transactionCharge;
    }
}
