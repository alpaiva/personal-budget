package br.com.personal.budget.adapter.output.mapper;

import br.com.personal.budget.adapter.output.entity.TransactionEntity;
import br.com.personal.budget.adapter.output.entity.UserEntity;
import br.com.personal.budget.core.domain.Transaction;
import br.com.personal.budget.core.domain.TransactionType;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {


    public TransactionEntity mapToEntity(Transaction transaction, UserEntity userEntity) {

        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setTransactionDate(transaction.getTransactionDate());
        transactionEntity.setAmount(transaction.getAmount());
        transactionEntity.setMemo(transaction.getMemo());
        transactionEntity.setType(transaction.getTransactionType().name());
        transactionEntity.setUser(userEntity);

        return transactionEntity;


    }

    public Transaction map(TransactionEntity entity) {

        return Transaction.builder()
                .userId(entity.getUser().getId())
                .memo(entity.getMemo())
                .amount(entity.getAmount())
                .transactionDate(entity.getTransactionDate())
                .transactionType(TransactionType.valueOf(entity.getType()))
                .build();

    }

    public TransactionEntity mapToEntity(TransactionEntity transactionEntity, Transaction transaction) {

        transactionEntity.setMemo(transaction.getMemo());
        transactionEntity.setTransactionDate(transaction.getTransactionDate());
        transactionEntity.setAmount(transaction.getAmount());
        transactionEntity.setType(transactionEntity.getType());

        return transactionEntity;
    }
}
