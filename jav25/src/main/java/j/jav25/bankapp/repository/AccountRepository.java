package j.jav25.bankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import j.jav25.bankapp.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

}
