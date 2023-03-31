package com.bankx.application.service.mapper;


import com.bankx.application.domain.Bank;
import com.bankx.application.service.dto.BankDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BankMapper implements EntityMapper<BankDTO, Bank> {

    @Override
    public Bank toEntity(BankDTO dto) {
        Bank bank = new Bank();
        bank.setId(dto.getId());
        bank.setName(dto.getName());
        bank.setAddress(dto.getAddress());
        return bank;
    }

    @Override
    public BankDTO toDto(Bank bank) {
        return new BankDTO(bank);
    }

    @Override
    public List<Bank> toEntity(List<BankDTO> dtoList) {
        return dtoList.stream().filter(Objects::nonNull).map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public List<BankDTO> toDto(List<Bank> entityList) {
        return entityList.stream().filter(Objects::nonNull).map(this::toDto).collect(Collectors.toList());
    }


}
