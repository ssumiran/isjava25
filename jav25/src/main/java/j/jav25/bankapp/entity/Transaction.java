package j.jav25.bankapp.entity;

import java.time.LocalDateTime;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "transaction")
public class Transaction {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long accountId;
	private double amount;
	private String transactionType;  //DEPOSIT, WITHDRAW, TRANSFER
	private LocalDateTime timestamp;
	
}
