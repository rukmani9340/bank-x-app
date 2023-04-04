package com.bankx.application.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * A domain representing Bank.
 */
@Entity
@Table(name = "bank")
public class Bank extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bank_seq")
    @GenericGenerator(name = "bank_seq", strategy = "com.bankx.application.domain.StringPrefixedSequenceIdGenerator", parameters = {
        @org.hibernate.annotations.Parameter(name = "increment_size", value = "1"), @org.hibernate.annotations.Parameter(name = "valuePrefix", value = "BANK"),
        @org.hibernate.annotations.Parameter(name = "numberFormat", value = "%05d") })
    private String id;

   private String name;

   private String address;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return Objects.equals(id, bank.id) && Objects.equals(name, bank.name) && Objects.equals(address, bank.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address);
    }
}
