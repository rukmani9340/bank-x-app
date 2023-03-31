package com.bankx.application.web.rest;


import com.bankx.application.domain.Transaction;
import com.bankx.application.enums.Status;
import com.bankx.application.security.AuthoritiesConstants;
import com.bankx.application.service.BankService;
import com.bankx.application.service.MailService;
import com.bankx.application.service.TransactionService;
import com.bankx.application.service.dto.BankDTO;
import com.bankx.application.service.dto.TransactionDTO;
import com.bankx.application.service.mapper.TransactionMapper;
import com.bankx.application.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class TransactionResource {
    private final Logger log = LoggerFactory.getLogger(TransactionResource.class);

    private TransactionService transactionService;

    private final MailService mailService;

    private TransactionMapper transactionMapper;

    public TransactionResource(TransactionService transactionService, MailService mailService) {
        this.transactionService = transactionService;
        this.mailService = mailService;
    }

    /** Create new transaction **/
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTransaction(@Valid @RequestBody TransactionDTO transactionDTO) throws URISyntaxException {
        log.debug("REST request to save transaction : {}", transactionDTO);

        if (transactionDTO.getId() != null) {
            throw new BadRequestAlertException("A new transaction cannot already have an ID", "branchMangement", "idexists");
            // Lowercase the user login before comparing with database
        }
        else {
           List<TransactionDTO> transactions =  transactionService.createTransaction(transactionDTO);
            mailService.sendTransactionEmail(transactions);
        }
    }

    /** Get all transactions **/
    @GetMapping("/all")
    public ResponseEntity<Page<TransactionDTO>> getAllTransactions(@RequestParam(name = "search", required = false) String search,
                                                     Pageable pageable) {

        final Page<TransactionDTO> page = transactionService.getAllTransactions(pageable);
        HttpHeaders headers = PaginationUtil
            .generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page, headers, HttpStatus.OK);
    }

    /** Get transaction by status as Debit or Credit**/
    @GetMapping("/{status}")
    public ResponseEntity<Page<TransactionDTO>> getTransactionByStatus(@PathVariable String status, Pageable pageable) {
        log.debug("REST request to get transction by status : {}", status);
        return new ResponseEntity<>(transactionService.getTransactionByUser(Status.valueOf(status.toUpperCase()), pageable), HttpStatus.OK);
    }
}
