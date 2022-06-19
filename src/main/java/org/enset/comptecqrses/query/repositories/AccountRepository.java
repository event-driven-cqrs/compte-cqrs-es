package org.enset.comptecqrses.query.repositories;

import org.enset.comptecqrses.query.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,String> {
}
