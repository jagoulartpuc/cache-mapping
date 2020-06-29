package orgarq.service;

import orgarq.domain.AssCacheLine;
import orgarq.domain.CacheLine;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class CacheService {

    private FileReaderService fileReader = new FileReaderService();

    private List<String> getFormattedList() throws IOException {
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

    private Map<String, String> generateBlockMap(int wordSize, String tag, String line) {
        Map<String, String> blockMap = new HashMap<>();
        for (String key: generateKeys(wordSize)) {
            blockMap.put(key, tag + line + key);
        }
        return blockMap;
    }

    private Map<String, CacheLine> generateCacheMap(int lineSize) {
        Map<String, CacheLine> cacheMap = new HashMap<>();
        for (String key: generateKeys(lineSize)) {
            var cacheLine = new CacheLine();
            cacheMap.put(key, cacheLine);
            cacheLine.setLine(key);
        }
        return cacheMap;
    }

    private Map<String, AssCacheLine> generateAssCacheMap(int wordSize) {
        Map<String, AssCacheLine> cacheMap = new HashMap<>();
        for (String key: generateKeys(wordSize)) {
            var cacheLine = new AssCacheLine();
            cacheMap.put(key, cacheLine);
        }
        return cacheMap;
    }

    public List<String> generateKeys(int lineSize) {
        List<String> keys = new ArrayList<>();
        Random r = new Random();
        String[] poss = {"0", "1"};
        StringBuilder key = new StringBuilder();
        for (int i = 0; i < lineSize * 100; i++) {
            for (int j = 0; j < lineSize; j++) {
                key.append(poss[r.nextInt(2)]);
            }
            keys.add(key.toString());
            key = new StringBuilder();
        }
        return keys.stream()
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public double hitRate(int totalAdresses, int hitCount) {
        return (100 * hitCount)/(double)totalAdresses;

    }

    public void directMapping(int tagSize, int lineSize, int wordSize) throws IOException {
        List<String> adresses = getFormattedList();
        int hitCount = 0, missCount = 0;
        var cacheMap = generateCacheMap(lineSize);
        for (String adress: adresses) {
            String tag = adress.substring(0, tagSize);
            String line = adress.substring(tagSize, tagSize + lineSize);
            String word = adress.substring(tagSize + lineSize, adress.length() - 1);
            char s = adress.charAt(adress.length() - 1);
            CacheLine cacheLine = cacheMap.get(line);
            var blocksMap = generateBlockMap(wordSize, tag, line);
            if (cacheLine.isValid()) {
                if (cacheLine.getTag().equals(tag)) {
                    cacheLine.setWords(blocksMap);
                    String adressIndexed = cacheLine.getWords().get(word);
                    if((adressIndexed + s).equals(adress)) {
                        cacheLine.setTag(tag);
                        System.out.println("Endereço: " + adress + " = hit!");
                        hitCount++;
                    } else {
                        cacheLine.setTag(tag);
                        System.out.println("Endereço: " + adress + " = miss!");
                        missCount++;
                    }
                } else {
                    cacheLine.setWords(blocksMap);
                    cacheLine.setTag(tag);
                    System.out.println("Endereço: " + adress + " = miss!");
                    missCount++;
                }
            } else {
                cacheLine.setWords(blocksMap);
                cacheLine.setValid(true);
                cacheLine.setTag(tag);
                System.out.println("Endereço: " + adress + " = miss!");
                missCount++;
            }
        }
        System.out.println("Hits: " + hitCount);
        System.out.println("Misses: " + missCount);
        System.out.println("Hit rate: " + hitRate(adresses.size(), hitCount));
        System.out.println("Cache: " + cacheMap.toString());
    }

    public void associateMapping(int tagSize, int wordSize) throws IOException {
        List<String> adresses = getFormattedList();
        int hitCount = 0, missCount = 0;
        var cacheMap = generateAssCacheMap(wordSize);
        for (String adress: adresses) {
            String tag = adress.substring(0, tagSize);
            String word = adress.substring(tagSize, adress.length() - 1);
            String subTag = tag.substring(tag.length() - wordSize);
            char s = adress.charAt(adress.length() - 1);
            var cacheLine = cacheMap.get(subTag);
            var blocksMap = generateBlockMap(wordSize, tag, "");
            if (cacheLine.getTag() != null) {
                if (cacheLine.getTag().equals(tag)) {
                    System.out.println("Endereço: " + adress + " = hit!");
                    hitCount++;
                    cacheLine.setTag(tag);
                    cacheLine.setWords(blocksMap);
                } else {
                    cacheLine.setWords(blocksMap);
                    cacheLine.setTag(tag);
                    System.out.println("Endereço: " + adress + " = miss!");
                    missCount++;
                }
            } else {
                cacheLine.setWords(blocksMap);
                cacheLine.setTag(tag);
                System.out.println("Endereço: " + adress + " = miss!");
                missCount++;
            }
        }
        System.out.println("Hits: " + hitCount);
        System.out.println("Misses: " + missCount);
        System.out.println("Hit rate: " + hitRate(adresses.size(), hitCount));
        System.out.println("Cache: " + cacheMap.toString());

    }

}
