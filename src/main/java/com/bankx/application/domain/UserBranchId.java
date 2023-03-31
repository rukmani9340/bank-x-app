package com.bankx.application.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserBranchId implements Serializable {

    private Long userId;

    private String branchId;

    public UserBranchId(Long userId, String branchId) {
        this.userId = userId;
        this.branchId =  branchId;
    }

    public UserBranchId() {

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }


}
