package j.jav25.bankapp.dto;

import j.jav25.bankapp.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountDto {
    private Long id;
    private String accountHolderName;
    private double balance;
    
    public static Account mapToAccount(AccountDto accountDto){
        Account account = new Account(
                accountDto.getId(),
                accountDto.getAccountHolderName(),
                accountDto.getBalance()
        );

        return account;
    }

    public static AccountDto mapToAccountDto(Account account){
        AccountDto accountDto = new AccountDto(
          account.getId(),
          account.getAccountHolderName(),
          account.getBalance()
        );
        return accountDto;
    }
}
