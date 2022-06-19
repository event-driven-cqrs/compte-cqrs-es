package org.enset.comptecqrses.query.repositories;

import org.enset.comptecqrses.query.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OperationRepository extends JpaRepository<Operation,Long> {
}
