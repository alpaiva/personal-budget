package br.com.personal.budget.core.usecase;

import br.com.personal.budget.core.domain.Transaction;

import java.util.Optional;

public interface TransactionUseCase {
    Transaction save(Transaction transaction);

    Optional<Transaction> findById(Long id);

    Transaction patch(Transaction transaction);

    void delete(Long id);
}
