package br.com.personal.budget.mapper;

import br.com.personal.budget.adapter.input.to.TransactionPatchTO;
import br.com.personal.budget.adapter.input.to.TransactionTO;
import br.com.personal.budget.adapter.output.entity.TransactionEntity;
import br.com.personal.budget.adapter.output.entity.UserEntity;
import br.com.personal.budget.core.domain.Transaction;
import br.com.personal.budget.core.domain.TransactionType;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {
    public Transaction map(TransactionTO transactionTO, Long userId) {

        return Transaction.builder()
                .userId(userId)
                .transactionDate(transactionTO.transactionDate())
                .amount(transactionTO.amount())
                .memo(transactionTO.memo())
                .transactionType(parse(transactionTO.type()))
                .build();

    }

    private TransactionType parse(String type) {
        return type == null ? null : TransactionType.valueOf(type);
    }

    public TransactionTO mapToDTO(Transaction transaction) {

        return new TransactionTO(transaction.getUserId(),
                transaction.getAmount(),
                transaction.getTransactionType().name(),
                transaction.getMemo(),
                transaction.getTransactionDate());


    }


    public Transaction map(TransactionPatchTO transactionTO) {
        return Transaction.builder()
                .id(transactionTO.transactionId())
                .transactionDate(transactionTO.transactionDate())
                .amount(transactionTO.amount())
                .memo(transactionTO.memo())
                .transactionType(parse(transactionTO.type()))
                .build();

    }

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
