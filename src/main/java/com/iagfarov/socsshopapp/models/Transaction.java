package com.iagfarov.socsshopapp.models;
import com.iagfarov.socsshopapp.enums.Color;
import com.iagfarov.socsshopapp.enums.Size;
import com.iagfarov.socsshopapp.enums.TypeTransaction;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
public class Transaction {
    TypeTransaction typeTransaction;

    String createTime;

    int socksQuantity;

    Size size;

    int cotton;

    Color color;

    int iD;

    public Transaction(TransactionBuilder transactionBuilder) {
        if (transactionBuilder == null) {
            throw new IllegalArgumentException("Please provide Transaction builder to build Transaction object.");
        }
        if (transactionBuilder.socksQuantity <= 0) {
            throw new IllegalArgumentException("Please provide valid socks number.");
        }
        this.typeTransaction = transactionBuilder.typeTransaction;
        this.iD = transactionBuilder.iD;
        this.createTime = String.valueOf(transactionBuilder.createTime);
        this.size = transactionBuilder.size;
        this.socksQuantity = transactionBuilder.socksQuantity;
        this.color = transactionBuilder.color;
    }

    public static class TransactionBuilder {
        final static DateTimeFormatter FORMAT_DATE = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        private TypeTransaction typeTransaction;

        private String createTime;

        private int socksQuantity;

        private Size size;

        private int cotton;

        private Color color;

        private int iD;

        public TransactionBuilder() {
            super();
        }

        public TransactionBuilder typeTransaction(TypeTransaction typeTransaction) {
            this.typeTransaction = typeTransaction;
            return this;
        }

        public TransactionBuilder createTime(LocalDateTime createTime) {
            this.createTime = createTime.format(FORMAT_DATE);
            return this;
        }

        public TransactionBuilder socksQuantity(int socksQuantity) {
            this.socksQuantity = socksQuantity;
            return this;
        }

        public TransactionBuilder iD(int iD) {
            this.iD = iD;
            return this;
        }

        public TransactionBuilder size(Size size) {
            this.size = size;
            return this;
        }

        public TransactionBuilder cotton(int cotton) {
            this.cotton = cotton;
            return this;
        }

        public TransactionBuilder color(Color color) {
            this.color = color;
            return this;
        }

        public Transaction build() {
            return new Transaction(this);
        }
    }
}