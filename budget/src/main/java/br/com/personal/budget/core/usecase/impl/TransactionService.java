package br.com.personal.budget.core.usecase.impl;

import br.com.personal.budget.core.domain.Transaction;
import br.com.personal.budget.core.usecase.TransactionUseCase;
import br.com.personal.budget.core.usecase.port.TransactionPort;
import br.com.personal.budget.core.usecase.port.UserPort;
import jakarta.inject.Named;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.Optional;

@Named
@ApplicationScope
public class TransactionService implements TransactionUseCase {

    private final TransactionPort transactionPort;

    private final UserPort userPort;

    public TransactionService(TransactionPort transactionPort, UserPort userPort) {
        this.transactionPort = transactionPort;
        this.userPort = userPort;
    }

    @Override
    public Transaction save(Transaction transaction) {
        return transactionPort.save(transaction);
    }

    @Override
    public Optional<Transaction> findById(Long id) {
        return transactionPort.findById(id);
    }

    @Override
    public Transaction patch(Transaction transaction) {

        return transactionPort.patch(transaction);
    }

    @Override
    public void delete(Long id) {
        transactionPort.delete(id);
    }
}
