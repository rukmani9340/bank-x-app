package com.bankx.application.web.rest;


import com.bankx.application.domain.Bank;
import com.bankx.application.repository.BankRepository;
import com.bankx.application.security.AuthoritiesConstants;
import com.bankx.application.service.BankService;
import com.bankx.application.service.BranchService;
import com.bankx.application.service.dto.BankDTO;
import com.bankx.application.service.dto.BranchDTO;
import com.bankx.application.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.jhipster.web.util.HeaderUtil;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/api/bank")
public class BankResource {
    private final Logger log = LoggerFactory.getLogger(BankResource.class);

    private BankService bankSevice;

    private BankRepository bankRepository;

    public BankResource(BankService bankSevice, BankRepository bankRepository) {
        this.bankSevice = bankSevice;
        this.bankRepository = bankRepository;
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<BankDTO> createBank(@Valid @RequestBody BankDTO bankDTO) throws URISyntaxException {
        log.debug("REST request to save branch : {}", bankDTO);

        if (bankDTO.getId() != null) {
            throw new BadRequestAlertException("A new branch cannot already have an ID", "branchMangement", "idexists");
            // Lowercase the user login before comparing with database
        }
        if(bankDTO.getName() == null || bankDTO.getName().isEmpty()){
            throw new BadRequestAlertException("Bank name can not be empty.");
        }
        Optional<Bank> bank = bankRepository.findByName(bankDTO.getName().toLowerCase());
        if(bank.isPresent()){
            throw new BadRequestAlertException("We have a bank with same name. Please try with other name.");
        }
        else {
            BankDTO newBank = bankSevice.createBank(bankDTO);
            return ResponseEntity
                .created(new URI("/api/bank/" + newBank.getId())).headers(HeaderUtil
                    .createEntityCreationAlert("bankXApplication", false,"Bank" , newBank.getId().toString()))
                .body(newBank);
        }
    }
}
