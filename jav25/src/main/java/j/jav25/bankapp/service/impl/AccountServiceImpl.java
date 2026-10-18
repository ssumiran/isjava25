package j.jav25.bankapp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import j.jav25.bankapp.dto.AccountDto;
import j.jav25.bankapp.entity.Account;
import j.jav25.bankapp.repository.AccountRepository;
import j.jav25.bankapp.service.AccountService;
import j.jav25.exception.AccountException;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
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
}
