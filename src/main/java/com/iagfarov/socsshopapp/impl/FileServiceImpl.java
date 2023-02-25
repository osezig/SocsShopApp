package com.iagfarov.socsshopapp.impl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iagfarov.socsshopapp.models.Socks;
import com.iagfarov.socsshopapp.services.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

@Service
public class FileServiceImpl implements FileService {

    @Value("${path.to.file.folder}")
    private String filePath;

    @Override
    public boolean saveToFile(String fileName, Map<? extends Number, ?> hashMap) {
        try {

            String json = new ObjectMapper().writeValueAsString(hashMap);
            cleanFile(fileName);
            Files.writeString(Path.of(filePath, fileName), json);
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean cleanFile(String fileName) {
        try {
            Path path = Path.of(filePath, fileName);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String readFile(String fileName) {
        try {
            return Files.readString(Path.of(filePath, fileName));

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Path createTempFile(String suffix) {
        try {
            return Files.createTempFile(Path.of(filePath), "tempFile", suffix);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Path createTxtFile(Map<Integer, Socks> storage) throws IOException {
        Path storageTxt = createTempFile("Storage");

        try (Writer writer = Files.newBufferedWriter(storageTxt, StandardCharsets.UTF_8)) {
            writer.append("На складе в данный момент: \n")
                    .append("Носки\n");
            for (Socks sock : storage.values()) {
                writer
                        .append("Размер: \n")
                        .append(String.valueOf(sock.getSize()))
                        .append("\n")
                        .append("Цвет: \n")
                        .append(String.valueOf(sock.getColor()))
                        .append("\n")
                        .append("Содержание хлопка: \n")
                        .append(String.valueOf(sock.getCotton()))
                        .append("\n")
                        .append("Количество: \n")
                        .append(String.valueOf(sock.getQuantity()))
                        .append("\n");
            }
        }
        return storageTxt;
    }

}