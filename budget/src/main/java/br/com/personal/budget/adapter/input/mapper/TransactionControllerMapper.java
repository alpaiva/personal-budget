package br.com.personal.budget.adapter.input.mapper;

import br.com.personal.budget.adapter.input.to.TransactionPatchTO;
import br.com.personal.budget.adapter.input.to.TransactionTO;
import br.com.personal.budget.core.domain.Transaction;
import br.com.personal.budget.core.domain.TransactionType;
import org.springframework.stereotype.Component;

@Component
public class TransactionControllerMapper {
    public Transaction map(TransactionTO transactionTO) {

        return Transaction.builder()
                .userId(transactionTO.userId())
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
}
