package br.com.personal.budget.adapter.output;

import br.com.personal.budget.adapter.output.entity.TransactionEntity;
import br.com.personal.budget.adapter.output.entity.UserEntity;
import br.com.personal.budget.adapter.output.mapper.TransactionMapper;
import br.com.personal.budget.core.domain.Transaction;
import br.com.personal.budget.core.usecase.exception.TransactionException;
import br.com.personal.budget.core.usecase.port.TransactionPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TransactionAdapter implements TransactionPort {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    private final UserRepository userRepository;

    public TransactionAdapter(TransactionRepository transactionRepository,
                              TransactionMapper transactionMapper, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
        this.userRepository = userRepository;
    }

    @Override
    public Transaction save(Transaction transaction) {
        Optional<UserEntity> userEntity = userRepository.findById(transaction.getUserId());
        if (userEntity.isEmpty()) {
            throw new TransactionException("User not found.");
        }

        TransactionEntity transactionEntity = transactionMapper.mapToEntity(transaction, userEntity.get());

        TransactionEntity saved = transactionRepository.save(transactionEntity);

        return transactionMapper.map(saved);


    }

    @Override
    public Optional<Transaction> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Transaction patch(Transaction transaction) {

        Optional<TransactionEntity> transactionEntity = transactionRepository.findById(transaction.getId());
        if (transactionEntity.isEmpty()) {
            throw new TransactionException("Transaction not found");
        }

        TransactionEntity transactionUpdate = transactionMapper.mapToEntity(transactionEntity.get(), transaction);

        TransactionEntity saved = transactionRepository.save(transactionUpdate);

        return transactionMapper.map(saved);

    }

    @Override
    public void delete(Long id) {
        transactionRepository.deleteById(id);
    }
}
