package com.example.geocoder.proxy;

import com.example.geocoder.config.FeignConfiguration;
import com.example.geocoder.entitys.Graphhopper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient( value = "${app.feign.config.name}",url = "${app.feign.config.url}",configuration = FeignConfiguration.class)

public interface GeoCoderProxy {

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<Graphhopper> getCoordinatesByAddress(@RequestParam(required = false, name = "q") String q,
                                                        @RequestParam(required = false, name = "locale", defaultValue = "en") String locale,
                                                        @RequestParam(required = false, name = "limit", defaultValue = "10") int limit,
                                                        @RequestParam(required = false, name = "reverse", defaultValue = "false") boolean reverse,
                                                        @RequestParam(required = false, name = "debug", defaultValue = "false") boolean debug,
                                                        @RequestParam(required = false, name = "point") String point,
                                                        @RequestParam(required = false, name = "provider", defaultValue = "default") String provider,
                                                        @RequestParam(name = "key") String key
    );



}
