package com.paisatrack;

import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {
    private final TransactionRepository transactions;
    private final SplitBillRepository splitBills;
    private final SettlementRepository settlements;

    public ApiController(TransactionRepository transactions, SplitBillRepository splitBills, SettlementRepository settlements) {
        this.transactions = transactions;
        this.splitBills = splitBills;
        this.settlements = settlements;
    }

    @GetMapping("/health")
    public Health health() { return new Health("ok", "PaisaTrack API is running"); }

    public record Health(String status, String message) {}

    @GetMapping("/transactions")
    public List<TransactionEntity> getTransactions() { return transactions.findAll(); }

    @PutMapping("/transactions/sync")
    @Transactional
    public List<TransactionEntity> syncTransactions(@RequestBody List<TransactionEntity> items) {
        validateTransactions(items);
        transactions.deleteAll();
        transactions.flush();
        return transactions.saveAll(items);
    }

    @GetMapping("/split-bills")
    public List<SplitBillEntity> getSplitBills() { return splitBills.findAll(); }

    @PutMapping("/split-bills/sync")
    @Transactional
    public List<SplitBillEntity> syncSplitBills(@RequestBody List<SplitBillEntity> items) {
        for (SplitBillEntity b : items) {
            if (b.getId() == null || b.getTitle() == null || b.getTitle().isBlank() ||
                b.getAmount() == null || b.getAmount().signum() <= 0 || b.getPayer() == null ||
                b.getDate() == null || b.getParticipants() == null || b.getParticipants().isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Each split bill needs an id, title, positive amount, payer, date and participants.");
            }
        }
        splitBills.deleteAll();
        splitBills.flush();
        return splitBills.saveAll(items);
    }

    @GetMapping("/settlements")
    public List<SettlementEntity> getSettlements() { return settlements.findAll(); }

    @PutMapping("/settlements/sync")
    @Transactional
    public List<SettlementEntity> syncSettlements(@RequestBody List<SettlementEntity> items) {
        for (SettlementEntity s : items) {
            if (s.getId() == null || s.getFrom() == null || s.getFrom().isBlank() ||
                s.getTo() == null || s.getTo().isBlank() || s.getAmount() == null ||
                s.getAmount().signum() <= 0 || s.getDate() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Each settlement needs an id, sender, receiver, positive amount and date.");
            }
        }
        settlements.deleteAll();
        settlements.flush();
        return settlements.saveAll(items);
    }

    private void validateTransactions(List<TransactionEntity> items) {
        for (TransactionEntity t : items) {
            if (t.getId() == null || t.getTitle() == null || t.getTitle().isBlank() ||
                t.getAmount() == null || t.getAmount().signum() <= 0 ||
                t.getType() == null || !(t.getType().equals("income") || t.getType().equals("expense")) ||
                t.getDate() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Each transaction needs an id, title, positive amount, valid type and date.");
            }
        }
    }
}
