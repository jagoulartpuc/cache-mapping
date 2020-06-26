package orgarq.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CacheService {

    private FileReaderService fileReader = new FileReaderService();

    public List<String> getFormattedList() throws IOException {
        return fileReader.readFile("data.txt")
                .stream()
                .map(adr -> formatBitsOfAdress(adr, 16))
                .collect(Collectors.toList());
    }

    private String formatBitsOfAdress(String bits, int n) {
        StringBuilder sb = new StringBuilder();
        int size = 0;
        while (size < n) {
            sb.append("0");
            size = bits.length() + sb.toString().length();
        }
        return sb.toString() + bits;
    }

    public String directMapping(int tag, int line, int word, int s) {
        return "";
    }
}
