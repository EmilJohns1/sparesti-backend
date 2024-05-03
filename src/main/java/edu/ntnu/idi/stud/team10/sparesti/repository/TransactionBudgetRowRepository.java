package edu.ntnu.idi.stud.team10.sparesti.repository;

import edu.ntnu.idi.stud.team10.sparesti.model.BudgetRow;
import org.springframework.data.jpa.repository.JpaRepository;

import edu.ntnu.idi.stud.team10.sparesti.model.TransactionBudgetRow;

import java.util.List;

public interface TransactionBudgetRowRepository extends JpaRepository<TransactionBudgetRow, Long> {
    List<TransactionBudgetRow> findByBudgetRow(BudgetRow budgetRow);

}
