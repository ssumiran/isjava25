package j.jav25.bankapp.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import j.jav25.bankapp.dto.AccountDto;
import j.jav25.bankapp.dto.TransactionDto;
import j.jav25.bankapp.dto.TransferFundDto;
import j.jav25.bankapp.entity.Account;
import j.jav25.bankapp.entity.Transaction;
import j.jav25.bankapp.repository.AccountRepository;
import j.jav25.bankapp.repository.TransactionRepository;
import j.jav25.bankapp.service.AccountService;
import j.jav25.exception.AccountException;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;
    
    private static final String TRANSACTION_TYPE_DEPOSIT = "DEPOSIT";
    private static final String TRANSACTION_TYPE_WITHDRAW = "WITHDRAW";
    private static final String TRANSACTION_TYPE_TRANSFER = "TRANSFER";

    public AccountServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountDto.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountDto.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {

        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountException("Account does not exists"));
        return AccountDto.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {

        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountException("Account does not exists"));

        double total = account.getBalance() + amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        
        Transaction transaction = new Transaction();
        transaction.setAccountId(id);
        transaction.setAmount(amount);
        transaction.setTransactionType(TRANSACTION_TYPE_DEPOSIT);
        transaction.setTimestamp(LocalDateTime.now());
        
        transactionRepository.save(transaction);
        
        return AccountDto.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {

        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountException("Account does not exists"));

        if(account.getBalance() < amount){
            throw new AccountException("Insufficient amount");
        }

        double total = account.getBalance() - amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setAccountId(id);
        transaction.setAmount(amount);
        transaction.setTransactionType(TRANSACTION_TYPE_WITHDRAW);
        transaction.setTimestamp(LocalDateTime.now());
        
        transactionRepository.save(transaction);
        
        return AccountDto.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map((account) -> AccountDto.mapToAccountDto(account))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {

        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountException("Account does not exists"));

        accountRepository.deleteById(id);
    }

	@Override
	public void transferFunds(TransferFundDto transferFundDto) {
		//Retrieve the account from 
		Account fromAccount = accountRepository.findById(transferFundDto.fromAccountId())
			.orElseThrow( ()-> new AccountException("Account doesn't exist") );  //orElsethrow( Supplier function )
		
		//get the account to information 
		Account toAccount = accountRepository.findById(transferFundDto.toAccountId())
			.orElseThrow( ()-> new AccountException("Account doesn't exist") );  //orElsethrow( Supplier function )
		
		//Debit the amount fromAccount 
		fromAccount.setBalance(fromAccount.getBalance() - transferFundDto.amount());
		
		//Credit add the amount toAccount 
		toAccount.setBalance(toAccount.getBalance() + transferFundDto.amount());
		
		//Save the accounts
		accountRepository.save(fromAccount);
		accountRepository.save(toAccount);
		
		Transaction transaction = new Transaction();
        transaction.setAccountId(transferFundDto.fromAccountId());
        transaction.setAmount(transferFundDto.amount());
        transaction.setTransactionType(TRANSACTION_TYPE_TRANSFER);
        transaction.setTimestamp(LocalDateTime.now());
        
        transactionRepository.save(transaction);
	}

	@Override
	public List<TransactionDto> getAccountTransactions(Long accountId) {
		List<Transaction> transactions = transactionRepository
				.findByAccountIdOrderByTimestampDesc(accountId);
		
		return transactions.stream()
			.map( t -> convertEntityToDto(t))
			.collect( Collectors.toList());
	}	
	
	private TransactionDto convertEntityToDto(Transaction t) {
		return new TransactionDto(
				t.getId(),
				t.getAccountId(),
				t.getAmount(),
				t.getTransactionType(),
				t.getTimestamp());
	}
}
