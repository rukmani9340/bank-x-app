package com.bankx.application.service.mapper;


import com.bankx.application.domain.Bank;
import com.bankx.application.domain.Transaction;
import com.bankx.application.service.dto.BankDTO;
import com.bankx.application.service.dto.TransactionDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TransactionMapper implements EntityMapper<TransactionDTO, Transaction> {

    @Override
    public Transaction toEntity(TransactionDTO dto) {
        Transaction transaction = new Transaction();
        transaction.setId(dto.getId());
        transaction.setAccountFrom(dto.getAccountFrom());
        transaction.setAccountTo(dto.getAccountTo());
        transaction.setTransactionId(dto.getTansactionId());
        transaction.setBalance(dto.getBalance());
        transaction.setStatus(dto.getStatus());
        transaction.setTransactionCharge(dto.getTransactionCharge());
        return transaction;
    }

    @Override
    public TransactionDTO toDto(Transaction transaction) {
        return new TransactionDTO(transaction);
    }

    @Override
    public List<Transaction> toEntity(List<TransactionDTO> dtoList) {
        return dtoList.stream().filter(Objects::nonNull).map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> toDto(List<Transaction> entityList) {
        return entityList.stream().filter(Objects::nonNull).map(this::toDto).collect(Collectors.toList());
    }


}
