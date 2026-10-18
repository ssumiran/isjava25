package j.jav25.bankapp.dto;

public record TransferFundDto(
		Long fromAccountId,
		Long toAccountId,
		double amount) {

}
