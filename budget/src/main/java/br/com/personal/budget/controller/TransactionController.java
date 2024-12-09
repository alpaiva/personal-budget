package br.com.personal.budget.controller;

import br.com.personal.budget.adapter.input.to.TransactionPatchTO;
import br.com.personal.budget.adapter.input.to.TransactionTO;
import br.com.personal.budget.auth.JwtService;
import br.com.personal.budget.auth.UserInfoService;
import br.com.personal.budget.core.domain.Transaction;
import br.com.personal.budget.core.usecase.TransactionUseCase;
import br.com.personal.budget.mapper.TransactionMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class TransactionController {

    private final TransactionUseCase transactionUseCase;

    private final TransactionMapper mapper;

    private final JwtService jwtService;
    private final UserInfoService userInfoService;

    public TransactionController(TransactionUseCase transactionUseCase,
                                 TransactionMapper mapper,
                                 JwtService jwtService,
                                 UserInfoService userInfoService) {
        this.transactionUseCase = transactionUseCase;
        this.mapper = mapper;
        this.jwtService = jwtService;
        this.userInfoService = userInfoService;
    }

    @GetMapping("/transaction/{id}")
    public ResponseEntity<TransactionTO> getTransaction(@PathVariable(value = "id") Long id) {
        Optional<Transaction> transaction = transactionUseCase.findById(id);
        if (transaction.isPresent()) {
            TransactionTO transactionDto = mapper.mapToDTO(transaction.get());
            return ResponseEntity.ok().body(transactionDto);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/transaction")
    public ResponseEntity<TransactionTO> post(HttpServletRequest request, @RequestBody(required = true) TransactionTO transactionTO) {

        String userEmail = jwtService.getUserEmailFromToken(request);

        Long userId = userInfoService.getUserId(userEmail);

        Transaction transaction = mapper.map(transactionTO, userId);

        Transaction transactionSaved = transactionUseCase.save(transaction);

        TransactionTO transactionDto = mapper.mapToDTO(transactionSaved);

        return ResponseEntity.status(HttpStatus.CREATED).body(transactionDto);
    }

    @PatchMapping("/transaction")
    public ResponseEntity<TransactionTO> patch(@RequestBody(required = true) TransactionPatchTO transactionTO) {

        Transaction transaction = mapper.map(transactionTO);

        Transaction transactionSaved = transactionUseCase.patch(transaction);

        TransactionTO transactionDto = mapper.mapToDTO(transactionSaved);

        return ResponseEntity.status(HttpStatus.CREATED).body(transactionDto);
    }

    @DeleteMapping("/transaction/{id}")
    public ResponseEntity<TransactionTO> delete(@PathVariable (name = "id") Long id) {
        transactionUseCase.delete(id);
        return ResponseEntity.ok().build();
    }
}
