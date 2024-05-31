package br.com.personal.budget.core.domain;

import br.com.personal.budget.core.usecase.exception.TransactionException;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
public class Transaction {

    private final Long id;
    private final Long userId;
    private final String memo;
    private final BigDecimal amount;
    private final TransactionType transactionType;
    private final LocalDate transactionDate;
    private final LocalDateTime creationDate = LocalDateTime.now();


    @Builder
    public Transaction(Long id,
                       Long userId,
                       String memo,
                       @NonNull BigDecimal amount,
                       @NonNull TransactionType transactionType,
                       @NonNull LocalDate transactionDate) {

        if (BigDecimal.ZERO.compareTo(amount) > 0) {
            throw new TransactionException("Amount must be greater than Zero.");
        }
        this.id = id;
        this.userId = userId;
        this.amount = amount;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
        this.memo = memo;
    }

}
