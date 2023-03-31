package com.bankx.application.service;

import com.bankx.application.domain.Bank;
import com.bankx.application.domain.Branch;
import com.bankx.application.repository.BankRepository;
import com.bankx.application.repository.BranchRepository;
import com.bankx.application.service.dto.BankDTO;
import com.bankx.application.service.dto.BranchDTO;
import com.bankx.application.service.mapper.BankMapper;
import com.bankx.application.service.mapper.BranchMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service class for managing bank.
 */
@Service
@Transactional
public class BankService {

    private final Logger log = LoggerFactory.getLogger(BankService.class);

    private final BankRepository bankRepository;

    private final BankMapper bankMapper;


    public BankService(BankRepository bankRepository, BankMapper bankMapper) {
        this.bankMapper = bankMapper;
        this.bankRepository = bankRepository;
    }

    public BankDTO createBank(BankDTO bankDTO) {
        Bank bank = bankMapper.toEntity(bankDTO);
        bank = bankRepository.save(bank);
        return bankMapper.toDto(bank);

    }
}
