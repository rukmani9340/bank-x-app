package com.bankx.application.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * An authority (a security role) used by Spring Security.
 */
@Entity
@Table(name = "partner_bank")
public class PartnerBank implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "partner_bank_seq")
    @GenericGenerator(name = "partner_bank_seq", strategy = "com.bankx.application.domain.StringPrefixedSequenceIdGenerator", parameters = {
        @org.hibernate.annotations.Parameter(name = "increment_size", value = "1"), @org.hibernate.annotations.Parameter(name = "valuePrefix", value = "PARB"),
        @org.hibernate.annotations.Parameter(name = "numberFormat", value = "%05d") })
    private String id;


   private String name;

   private String address;

    @ManyToOne
    @JoinColumn(name = "bank_id" , insertable = false, updatable = false)
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PartnerBank that = (PartnerBank) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(address, that.address) && Objects.equals(bank, that.bank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, bank);
    }
}
