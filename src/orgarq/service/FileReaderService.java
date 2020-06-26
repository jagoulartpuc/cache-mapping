package orgarq.service;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReaderService {

    public List<String> readFile(String fileName) throws IOException {
        List<String> binaryAdresses = new ArrayList<>();
        Path path = Paths.get(fileName);
        try (Scanner sc = new Scanner(Files.newBufferedReader(path, StandardCharsets.UTF_8))) {
            sc.useDelimiter("[\n]");
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] adresses = line.split(", ");
                for (int i = 0; i < adresses.length; i++) {
                    if (i == adresses.length - 1) {
                        StringBuilder sb = new StringBuilder(adresses[i]);
                        sb.deleteCharAt(sb.length()-1);
                        adresses[i] = sb.toString();
                    }
                    binaryAdresses.add(hexaToBinary(adresses[i]));
                }
            }
        }

        return binaryAdresses;
    }

    private String hexaToBinary(String s) {
        return new BigInteger(s, 16).toString(2);
    }


}
