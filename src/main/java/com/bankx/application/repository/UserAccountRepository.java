package com.bankx.application.repository;

import com.bankx.application.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * Spring Data JPA repository for the {@link UserAccount} entity.
 */
@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, String> {

    @Query(value = "select nextval ('user_account_seq')", nativeQuery = true)
    BigDecimal getNextValMySequence();

}
