package com.example.geocoder;

import com.example.geocoder.proxy.GeoCoderProxy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FeignTests {
    @Autowired
    GeoCoderProxy feign;
    @Test
    public void testFeignCorrectStatusResponseByAddress() {
        String q = "moscow";
        String locale = "";
        int limit = 1;
        String point = "";
        String provider = "default";
        String key = "3048cc9a-b274-4045-9a96-8fd342e8a86f";
        assertEquals(HttpStatus.OK,feign.getCoordinatesByAddress(q, locale, limit, false, false, point, provider, key).getStatusCode());

    }


    @Test
    public void testFeignWhenAddressContainSpace(){
        String q = "moscow lenina";
        String locale = "";
        int limit = 1;
        String point = "";
        String provider = "default";
        String key = "3048cc9a-b274-4045-9a96-8fd342e8a86f";
        assertEquals(HttpStatus.OK,feign.getCoordinatesByAddress(q, locale, limit, false, false, point, provider, key).getStatusCode());
    }

    @Test
    public void testFeignCorrectStatusResponseByCoordinates(){
        String q = "";
        String locale = "";
        int limit = 1;
        String point = "52.55703,13.38885";
        String provider = "default";
        String key = "3048cc9a-b274-4045-9a96-8fd342e8a86f";
        assertEquals(HttpStatus.OK,feign.getCoordinatesByAddress(q, locale, limit, true, false, point, provider, key).getStatusCode());
    }

}
