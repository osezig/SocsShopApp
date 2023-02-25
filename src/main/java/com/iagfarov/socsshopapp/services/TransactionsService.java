package com.iagfarov.socsshopapp.services;
import com.iagfarov.socsshopapp.enums.Color;
import com.iagfarov.socsshopapp.enums.Size;
import com.iagfarov.socsshopapp.enums.TypeTransaction;
import com.iagfarov.socsshopapp.models.Transaction;

import javax.annotation.PostConstruct;
import java.util.List;

public interface TransactionsService {
    @PostConstruct
    void init();

    void addTransactions(TypeTransaction typeTransaction,
                         int socksQuantity,
                         Size size,
                         int cotton,
                         Color color);

    List<Transaction> getAllTransactions();
}
