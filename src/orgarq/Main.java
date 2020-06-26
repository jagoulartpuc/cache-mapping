package orgarq;

import orgarq.service.CacheService;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
	    CacheService cacheService = new CacheService();
        System.out.println(cacheService.getFormattedList());
    }
}
