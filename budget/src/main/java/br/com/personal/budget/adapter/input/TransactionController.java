package br.com.personal.budget.adapter.input;

import br.com.personal.budget.database.TransactionEntity;
import br.com.personal.budget.adapter.output.TransactionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class TransactionController {

    private final TransactionRepository transactionRepository;

    public TransactionController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @GetMapping("/transaction/{id}")
    public ResponseEntity<TransactionEntity> getTransaction(@PathVariable(value = "id") Long id) {
        Optional<TransactionEntity> user = transactionRepository.findById(id);
        if (user.isPresent()) {
            ResponseEntity<TransactionEntity> body = ResponseEntity.ok().body(user.get());
            return body;
        }
        return null;
    }

    @PostMapping("/transaction")
    public ResponseEntity<TransactionEntity> post(@RequestBody(required = true) TransactionEntity transactionEntity) {
        TransactionEntity save = transactionRepository.save(transactionEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(save);
    }
}
