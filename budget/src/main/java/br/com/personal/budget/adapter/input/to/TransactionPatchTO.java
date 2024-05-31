package br.com.personal.budget.adapter.input.to;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionPatchTO(Long transactionId, BigDecimal amount, String type, String memo,
                                 LocalDate transactionDate) {
}
