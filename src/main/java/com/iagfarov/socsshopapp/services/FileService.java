package com.iagfarov.socsshopapp.services;
import com.iagfarov.socsshopapp.models.Socks;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public interface FileService {
    boolean saveToFile(String fileName, Map<? extends Number, ? extends Object> hashMap);

    boolean cleanFile(String fileName);

    String readFile(String fileName);

    Path createTempFile(String suffix);

    Path createTxtFile(Map<Integer, Socks> storage) throws IOException;
}
