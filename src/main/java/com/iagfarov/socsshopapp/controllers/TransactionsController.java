package com.iagfarov.socsshopapp.controllers;

import com.iagfarov.socsshopapp.models.Transaction;
import com.iagfarov.socsshopapp.services.TransactionsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionsController {

    TransactionsService transactionsService;

    public TransactionsController(TransactionsService transactionsService) {
        this.transactionsService = transactionsService;
    }

    @GetMapping("/allTransactions")
    public ResponseEntity<List<Transaction>> getAllSocksList() {
        return ResponseEntity.ok().body(transactionsService.getAllTransactions());
    }


}