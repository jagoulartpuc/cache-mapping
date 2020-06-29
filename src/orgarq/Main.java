package orgarq;

import orgarq.service.CacheService;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
	    CacheService cacheService = new CacheService();
        //cacheService.directMapping(9, 3, 3);
        //cacheService.directMapping(9, 4, 2);
        //cacheService.associateMapping(13, 2);
        cacheService.associateMapping(12, 3);
    }
}
