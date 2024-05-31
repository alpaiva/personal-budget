package br.com.personal.budget.adapter.output;

import br.com.personal.budget.adapter.output.entity.TransactionEntity;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<TransactionEntity, Long> {
}
