package br.com.personal.budget.core.usecase.port;

import br.com.personal.budget.core.domain.Transaction;

import java.util.Optional;

public interface TransactionPort {
    Transaction save(Transaction transaction);

    Optional<Transaction> findById(Long id);

    Transaction patch(Transaction transaction);

    void delete(Long id);
}
