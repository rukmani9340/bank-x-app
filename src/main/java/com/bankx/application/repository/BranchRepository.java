package com.bankx.application.repository;

import com.bankx.application.domain.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data JPA repository for the {@link Branch} entity.
 */
@Repository
public interface BranchRepository extends JpaRepository<Branch, String> {

    @Query("Select b from Branch b where lower(b.ifscCode) =:ifscCode")
    Optional<Branch> findByIfscCode(@Param("ifscCode") String ifscCode);
}
