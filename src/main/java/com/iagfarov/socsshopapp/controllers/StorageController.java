package com.iagfarov.socsshopapp.controllers;
import com.iagfarov.socsshopapp.enums.Color;
import com.iagfarov.socsshopapp.enums.Size;
import com.iagfarov.socsshopapp.exceptions.QuantityException;
import com.iagfarov.socsshopapp.models.Socks;
import com.iagfarov.socsshopapp.services.StorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/socks")
@Tag(name = "Склад носков", description = "CRUD-операции и другие эндпоинты для работы с поставками носков")
public class StorageController {

    private final StorageService storageService;

    @PostMapping()
    @Operation (summary = "добавление носков на склад")
    public ResponseEntity<Socks[]> addSocksInStorage(@Valid @RequestBody Socks...socks) throws QuantityException {
        for (Socks sock : socks) {
            storageService.addSocksInStorage(sock);
        }
        return ResponseEntity.ok(socks);
    }
    @PutMapping
    @Operation (summary = "Отгрузка со склада")
    public ResponseEntity<Object> getFromStock(@Valid @RequestBody Socks socks) {
        int a = 0;
        try {
            a = storageService.getFromStock(socks);
        } catch (QuantityException e) {
            ResponseEntity.badRequest().body(e);
        }
        return ResponseEntity.ok().body("На складе осталось " + a);
    }

    @GetMapping
    @Operation(summary = "Остаток носков на складе")

    public ResponseEntity<Integer> obtainQuantity(Color color,
                                                  Size size,
                                                  int cottonMin,
                                                  int cottonMax) {

        int quantity = storageService.availabilityCheck(color, size, cottonMin, cottonMax);

        return ResponseEntity.ok(quantity);
    }

    @DeleteMapping
    @Operation(summary = "Списание бракованных носков")
    public ResponseEntity<Object> deleteFromStock(@Valid @RequestBody Socks socks) {
        int a = 0;
        try {
            a = storageService.deleteFromStock(socks);
        } catch (QuantityException e) {
            ResponseEntity.badRequest().body(e);
        }
        return ResponseEntity.ok().body("На складе осталось " + a);
    }
    @GetMapping("/allSocks")
    @Operation(summary = "Получение всех видов носков", description = "Входные данные не нужны")
    public ResponseEntity<List<Socks>> getAllSocksList() {
        return ResponseEntity.ok(storageService.obtainAllSocks());
    }
}
