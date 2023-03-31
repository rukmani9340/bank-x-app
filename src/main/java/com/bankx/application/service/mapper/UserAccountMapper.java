package com.bankx.application.service.mapper;


import com.bankx.application.domain.UserAccount;
import com.bankx.application.service.dto.UserAccountDTO;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserAccountMapper implements EntityMapper<UserAccountDTO, UserAccount> {

    @Override
    public UserAccount toEntity(UserAccountDTO dto) {
        UserAccount userAccount = new UserAccount();
        userAccount.setAccountNo(dto.getAccountNo());
        userAccount.setUser(dto.getUser());
        userAccount.setBalance(dto.getBalance());
        userAccount.setBranch(dto.getBranch());
        userAccount.setAccountType(dto.getAccountType());
        return userAccount;
    }

    @Override
    public UserAccountDTO toDto(UserAccount userAccount) {
        return new UserAccountDTO(userAccount);
    }

    @Override
    public List<UserAccount> toEntity(List<UserAccountDTO> dtoList) {
        return dtoList.stream().filter(Objects::nonNull).map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public List<UserAccountDTO> toDto(List<UserAccount> entityList) {
        return entityList.stream().filter(Objects::nonNull).map(this::toDto).collect(Collectors.toList());
    }


}
