package com.iagfarov.socsshopapp.services;
import com.iagfarov.socsshopapp.enums.Color;
import com.iagfarov.socsshopapp.enums.Size;
import com.iagfarov.socsshopapp.exceptions.QuantityException;
import com.iagfarov.socsshopapp.models.Socks;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;


public interface StorageService {
    @PostConstruct
    void init();
    void addSocksInStorage(Socks socks) throws QuantityException;

    abstract List<Socks> obtainAllSocks();

    Map<Integer, Socks> obtainMapAllSocks();

    int getFromStock(Socks socks) throws QuantityException;

    int deleteFromStock(Socks socks) throws QuantityException;

    int availabilityCheck(Color color,
                          Size size,
                          int cottonMin,
                          int cottonMax);

}
