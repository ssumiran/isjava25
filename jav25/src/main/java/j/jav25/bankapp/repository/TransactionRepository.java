package j.jav25.bankapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import j.jav25.bankapp.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	List<Transaction> findByAccountIdOrderByTimestampDesc(Long accountId);
}
