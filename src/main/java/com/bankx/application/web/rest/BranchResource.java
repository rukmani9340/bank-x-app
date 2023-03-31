package com.bankx.application.web.rest;

import com.bankx.application.domain.Branch;
import com.bankx.application.repository.BranchRepository;
import com.bankx.application.security.AuthoritiesConstants;
import com.bankx.application.service.BranchService;
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
    @RequestMapping("/api/branch")
    public class BranchResource {

        private final Logger log = LoggerFactory.getLogger(BranchResource.class);

        private BranchService branchService;

        private BranchRepository branchRepository;

        public BranchResource(BranchService branchService, BranchRepository branchRepository) {
            this.branchService = branchService;
            this.branchRepository = branchRepository;
        }

        @PostMapping("/create")
        @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
        public ResponseEntity<BranchDTO> createBranch(@Valid @RequestBody BranchDTO branchDTO) throws URISyntaxException {
            log.debug("REST request to save branch : {}", branchDTO);

            if (branchDTO.getId() != null) {
                throw new BadRequestAlertException("A new branch cannot already have an ID", "branchMangement", "idexists");
                // Lowercase the user login before comparing with database
            }
            if(branchDTO.getIfscCode() == null || branchDTO.getIfscCode().isEmpty()){
                throw new BadRequestAlertException("A branch without ifsc code can not be exist.");
            }
            Optional<Branch> branch = branchRepository.findByIfscCode(branchDTO.getIfscCode().toLowerCase());
            if(branch.isPresent()){
                throw new BadRequestAlertException("We have branch with same ifsc code. Please try with other one.");
            }
            else {
                BranchDTO newBranch = branchService.createBranch(branchDTO);
                return ResponseEntity
                    .created(new URI("/api/branch/" + newBranch.getId())).headers(HeaderUtil
                        .createEntityCreationAlert("bankXApplication", false,"Branch" , newBranch.getId().toString()))
                    .body(newBranch);
            }
        }
    }


