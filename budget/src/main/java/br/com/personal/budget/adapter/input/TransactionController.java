package br.com.personal.budget.adapter.input;

import br.com.personal.budget.adapter.input.mapper.TransactionControllerMapper;
import br.com.personal.budget.adapter.input.to.TransactionPatchTO;
import br.com.personal.budget.adapter.input.to.TransactionTO;
import br.com.personal.budget.auth.JwtService;
import br.com.personal.budget.auth.UserInfoService;
import br.com.personal.budget.core.domain.Transaction;
import br.com.personal.budget.core.usecase.TransactionUseCase;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class TransactionController {

    private final TransactionUseCase transactionUseCase;

    private final TransactionControllerMapper transactionControllerMapper;

    private final JwtService jwtService;
    private final UserInfoService userInfoService;

    public TransactionController(TransactionUseCase transactionUseCase,
                                 TransactionControllerMapper transactionControllerMapper,
                                 JwtService jwtService,
                                 UserInfoService userInfoService) {
        this.transactionUseCase = transactionUseCase;
        this.transactionControllerMapper = transactionControllerMapper;
        this.jwtService = jwtService;
        this.userInfoService = userInfoService;
    }

    @GetMapping("/transaction/{id}")
    public ResponseEntity<TransactionTO> getTransaction(@PathVariable(value = "id") Long id) {
        Optional<Transaction> transaction = transactionUseCase.findById(id);
        if (transaction.isPresent()) {
            TransactionTO transactionDto = transactionControllerMapper.mapToDTO(transaction.get());
            return ResponseEntity.ok().body(transactionDto);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/transaction")
    public ResponseEntity<TransactionTO> post(HttpServletRequest request, @RequestBody(required = true) TransactionTO transactionTO) {

        String userEmail = jwtService.getUserEmailFromToken(request);

        Long userId = userInfoService.getUserId(userEmail);

        Transaction transaction = transactionControllerMapper.map(transactionTO, userId);

        Transaction transactionSaved = transactionUseCase.save(transaction);

        TransactionTO transactionDto = transactionControllerMapper.mapToDTO(transactionSaved);

        return ResponseEntity.status(HttpStatus.CREATED).body(transactionDto);
    }

    @PatchMapping("/transaction")
    public ResponseEntity<TransactionTO> patch(@RequestBody(required = true) TransactionPatchTO transactionTO) {

        Transaction transaction = transactionControllerMapper.map(transactionTO);

        Transaction transactionSaved = transactionUseCase.patch(transaction);

        TransactionTO transactionDto = transactionControllerMapper.mapToDTO(transactionSaved);

        return ResponseEntity.status(HttpStatus.CREATED).body(transactionDto);
    }

    @DeleteMapping("/transaction/{id}")
    public ResponseEntity<TransactionTO> delete(@PathVariable (name = "id") Long id) {
        transactionUseCase.delete(id);
        return ResponseEntity.ok().build();
    }
}
