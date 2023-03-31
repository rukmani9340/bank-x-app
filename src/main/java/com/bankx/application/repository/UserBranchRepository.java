package com.bankx.application.repository;

import com.bankx.application.domain.Branch;
import com.bankx.application.domain.UserBranch;
import com.bankx.application.domain.UserBranchId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the {@link UserBranch} entity.
 */
@Repository
public interface UserBranchRepository extends JpaRepository<UserBranch, UserBranchId> {

}
