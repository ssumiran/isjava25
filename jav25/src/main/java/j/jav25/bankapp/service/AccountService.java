package j.jav25.bankapp.service;

import java.util.List;

import j.jav25.bankapp.dto.AccountDto;
import j.jav25.bankapp.dto.TransferFundDto;

public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(Long id);

    AccountDto deposit(Long id, double amount);

    AccountDto withdraw(Long id, double amount);

    List<AccountDto> getAllAccounts();

    void deleteAccount(Long id);
    
    void transferFunds(TransferFundDto transferFundDto);
}
