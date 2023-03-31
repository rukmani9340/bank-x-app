package com.bankx.application.repository;

import com.bankx.application.domain.Transaction;
import com.bankx.application.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the {@link Transaction} entity.
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {

    @Query("Select t from Transaction t where t.status=:status")
    Page<Transaction> findByStatus(@Param("status") Status status, Pageable pageable);
}
