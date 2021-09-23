package com.example.geocoder;

import com.example.geocoder.controllers.GeocoderController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GeocoderApplicationTests {

    @Autowired
    GeocoderController controller;

    @Test
    public void testProxyCorrectStatusResponseByCoordinates(){
        String q = "";
        String locale = "";
        int limit = 1;
        String point = "52.55703,13.38885";
        String provider = "default";
        String key = "3048cc9a-b274-4045-9a96-8fd342e8a86f";
        assertEquals(HttpStatus.OK,controller.getData(q, locale, limit, true, false, point, provider, key).getStatusCode());
    }

    @Test
    public void testProxyCorrectStatusResponseByAddress() {
        String q = "moscow";
        String locale = "";
        int limit = 1;
        String point = "";
        String provider = "default";
        String key = "3048cc9a-b274-4045-9a96-8fd342e8a86f";
        assertEquals(HttpStatus.OK,controller.getData(q, locale, limit, false, false, point, provider, key).getStatusCode());

    }

    @Test
    public void testProxyWhenWrongAuthKeyResultUNAUTHORIZED(){
        String q = "moscow";
        String locale = "";
        int limit = 1;
        String point = "";
        String provider = "default";
        String key = "3048cc9a-b274-4045-9a96-8fd342e8a87f";
        assertEquals(HttpStatus.UNAUTHORIZED,controller.getData(q, locale, limit, false, false, point, provider, key).getStatusCode());
    }

    @Test
    public void testProxyWhenAddressContainSpace(){
        String q = "moscow lenina";
        String locale = "";
        int limit = 1;
        String point = "";
        String provider = "default";
        String key = "3048cc9a-b274-4045-9a96-8fd342e8a86f";
        assertEquals(HttpStatus.OK,controller.getData(q, locale, limit, false, false, point, provider, key).getStatusCode());
    }



    @Test
    public void testProxyBadResponseWhenInputParametersWrong(){
        String q = "";
        String locale = "";
        int limit = 1;
        String point = "52.55703,13.38885";
        String provider = "default";
        String key = "3048cc9a-b274-4045-9a96-8fd342e8a86f";
        assertEquals(HttpStatus.BAD_REQUEST,controller.getData(q, locale, limit, false, false, point, provider, key).getStatusCode());
    }

}
