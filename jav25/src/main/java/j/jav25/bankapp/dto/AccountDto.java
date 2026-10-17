package j.jav25.bankapp.dto;

import j.jav25.bankapp.entity.Account;
/*import lombok.AllArgsConstructor;
import lombok.Data;*/

/*  Using class object
@Data
@AllArgsConstructor
public class AccountDto {
    private Long id;
    private String accountHolderName;
    private double balance;
*/

//Using record from Java 16    
public record AccountDto (
    Long id,
    String accountHolderName,
    double balance
){     
	public static Account mapToAccount(AccountDto accountDto){
	    Account account = new Account(
	            accountDto.id(),
	            accountDto.accountHolderName(),
	            accountDto.balance()
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

