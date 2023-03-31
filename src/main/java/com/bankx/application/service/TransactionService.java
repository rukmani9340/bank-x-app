package com.bankx.application.service;

import com.bankx.application.domain.UserAccount;
import com.bankx.application.domain.Transaction;
import com.bankx.application.enums.AccountType;
import com.bankx.application.enums.Status;
import com.bankx.application.repository.UserAccountRepository;
import com.bankx.application.repository.TransactionRepository;
import com.bankx.application.service.dto.TransactionDTO;
import com.bankx.application.service.dto.UserAccountDTO;
import com.bankx.application.service.mapper.TransactionMapper;
import com.bankx.application.service.mapper.UserAccountMapper;
import com.bankx.application.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


/**
 * Service class for managing bank.
 */
@Service
@Transactional
public class TransactionService {

    private final Logger log = LoggerFactory.getLogger(TransactionService.class);

    private final TransactionRepository transactionRepository;
    private final UserAccountRepository userrAccountRepository;

    private final TransactionMapper transactionMapper;
    private final UserAccountMapper userAccountMapper;


    public TransactionService(TransactionRepository transactionRepository, TransactionMapper transactionMapper, UserAccountRepository userrAccountRepository, UserAccountMapper userAccountMapper) {
        this.transactionMapper = transactionMapper;
        this.transactionRepository = transactionRepository;
        this.userrAccountRepository = userrAccountRepository;
        this.userAccountMapper =  userAccountMapper;
    }

    public List<TransactionDTO> createTransaction(TransactionDTO transactionDTO) {
        if(transactionDTO.getAccountFrom().getId().equals(transactionDTO.getAccountTo().getId())){
            throw new BadRequestAlertException("You can not perform transaction in same accounts");
        }
        Optional<UserAccount> userAccountFromOp = userrAccountRepository.findById(transactionDTO.getAccountFrom().getId());
        Optional<UserAccount> userAccountToOp = userrAccountRepository.findById(transactionDTO.getAccountTo().getId());

        if(userAccountFromOp.isEmpty() || userAccountToOp.isEmpty()){
            throw new BadRequestAlertException("No Account present for this accunt no");
        }
        UserAccount userAccountFrom = userAccountFromOp.get();
        UserAccount userAccountTo = userAccountToOp.get();


        if (userAccountFrom.getBalance() < transactionDTO.getBalance()) {
            throw new BadRequestAlertException("You have insufficcient balance");
        }
        if (!userAccountFrom.getUser().getId().equals(userAccountTo.getUser().getId()) && userAccountFrom.getAccountType().equals(AccountType.Saving)) {

            throw new BadRequestAlertException("Saving acccount is not for making transactions.Please select your current account.");

        } else {
            Transaction transactionCredit = new Transaction();
            transactionCredit.setTransactionId(UUID.randomUUID().toString());
            transactionCredit.setBalance(transactionDTO.getBalance());
            transactionCredit.setAccountFrom(userAccountFrom);
            transactionCredit.setAccountTo(userAccountTo);
            transactionCredit.setStatus(Status.CREDIT);
            transactionCredit.setTransactionCharge(0.0);

            Transaction transactionDebit = new Transaction();
            transactionDebit.setTransactionId(UUID.randomUUID().toString());
            transactionDebit.setBalance(transactionDTO.getBalance());
            transactionDebit.setAccountFrom(userAccountFrom);
            transactionDebit.setAccountTo(userAccountTo);
            transactionDebit.setStatus(Status.DEBIT);
            if(userAccountFrom.getAccountType().equals(AccountType.Saving)){
                transactionDebit.setTransactionCharge(0.0);
            }else {
                transactionDebit.setTransactionCharge(transactionDTO.getBalance() * 0.05 / 100);
            }

            transactionRepository.save(transactionCredit);
            transactionRepository.save(transactionDebit);

            if (userAccountTo.getAccountType().equals(AccountType.Saving)) {
                userAccountTo.setBalance(userAccountTo.getBalance() + (userAccountTo.getBalance() * 0.5 / 100 + transactionDTO.getBalance()));
            } else {
                userAccountTo.setBalance(userAccountTo.getBalance() + transactionDTO.getBalance());
            }

            userAccountFrom.setBalance(userAccountFrom.getBalance() - (transactionDTO.getBalance() + transactionDTO.getBalance() * 0.05 / 100));

            userrAccountRepository.save(userAccountTo);
            userrAccountRepository.save(userAccountFrom);

            List<TransactionDTO> transactions = new ArrayList<>();
            TransactionDTO transactionCreditDTO = transactionMapper.toDto(transactionCredit);
            TransactionDTO transactionDebitDTO = transactionMapper.toDto(transactionDebit);

            String senderAccountNo = "XXXXXXXXX" + transactionCreditDTO.getAccountFrom().getAccountNo().substring(9);
            String receiverAccountNo = "XXXXXXXXX" + transactionCreditDTO.getAccountTo().getAccountNo().substring(9);

            UserAccountDTO senderAccount = userAccountMapper.toDto(transactionCreditDTO.getAccountFrom());
            senderAccount.setAccountNo(senderAccountNo);

            UserAccountDTO receiverAccount = userAccountMapper.toDto(transactionCreditDTO.getAccountTo());
            receiverAccount.setAccountNo(receiverAccountNo);

            transactionCreditDTO.setAccountFrom(userAccountMapper.toEntity(senderAccount));
            transactionCreditDTO.setAccountTo(userAccountMapper.toEntity(receiverAccount));
//            transactionCreditDTO.getAccountTo().setAccountNo("XXXXXXXXX" + transactionCreditDTO.getAccountTo().getAccountNo().substring(9));
            transactions.add(transactionCreditDTO);
            transactions.add(transactionDebitDTO);
            return transactions;

        }
    }

    @Transactional(readOnly = true)
    public Page<TransactionDTO> getAllTransactions(Pageable pageable) {
        return this.transactionRepository.findAll(pageable).map(TransactionDTO::new);
    }

    @Transactional(readOnly = true)
    public Page<TransactionDTO> getTransactionByUser(Status status, Pageable pageable) {
        return this.transactionRepository.findByStatus(status, pageable).map(TransactionDTO::new);
    }
}
