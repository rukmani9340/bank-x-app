package com.bankx.application.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * A domain representing branch.
 */
@Entity
@Table(name = "branch")
public class Branch extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "branch_seq")
    @GenericGenerator(name = "branch_seq", strategy = "com.bankx.application.domain.StringPrefixedSequenceIdGenerator", parameters = {
        @org.hibernate.annotations.Parameter(name = "increment_size", value = "1"), @org.hibernate.annotations.Parameter(name = "valuePrefix", value = "BRAN"),
        @org.hibernate.annotations.Parameter(name = "numberFormat", value = "%05d") })
    private String id;

    private String name;

   private String ifscCode;

   private String address;

   @ManyToOne
   @JoinColumn(name = "bank_id")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Branch branch = (Branch) o;
        return Objects.equals(id, branch.id) && Objects.equals(name, branch.name) && Objects.equals(ifscCode, branch.ifscCode) && Objects.equals(address, branch.address) && Objects.equals(bank, branch.bank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, ifscCode, address, bank);
    }
}
