package com.bankx.application.repository;

import com.bankx.application.domain.Bank;
import com.bankx.application.domain.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data JPA repository for the {@link Bank} entity.
 */
@Repository
public interface BankRepository extends JpaRepository<Bank, String> {

    @Query("Select b from Bank b where lower(b.name) =:name")
    Optional<Bank> findByName(@Param("name") String name);
}
