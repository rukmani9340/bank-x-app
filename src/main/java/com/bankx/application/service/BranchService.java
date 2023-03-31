package com.bankx.application.service;

import com.bankx.application.domain.Bank;
import com.bankx.application.domain.Branch;
import com.bankx.application.repository.BankRepository;
import com.bankx.application.repository.BranchRepository;
import com.bankx.application.service.dto.BranchDTO;
import com.bankx.application.service.mapper.BranchMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


/**
 * Service class for managing branch.
 */
@Service
@Transactional
public class BranchService {

    private final Logger log = LoggerFactory.getLogger(BranchService.class);

    private final BranchRepository branchRepository;

    private final BranchMapper branchMapper;

    private final BankRepository bankRepository;


    public BranchService(BranchRepository branchRepository, BranchMapper branchMapper, BankRepository bankRepository) {
        this.branchRepository = branchRepository;
        this.branchMapper = branchMapper;
        this.bankRepository = bankRepository;
    }

    public BranchDTO createBranch(BranchDTO branchDTO) {
        Optional<Bank> bank = bankRepository.findById(branchDTO.getBank().getId());
        if(bank.isPresent()){
            branchDTO.setBank(bank.get());
        }
        Branch branch = branchMapper.toEntity(branchDTO);
        return branchMapper.toDto(branchRepository.save(branch));

    }
}
