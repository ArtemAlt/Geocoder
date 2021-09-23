package com.example.geocoder.controllers;

import com.example.geocoder.entitys.Graphhopper;
import com.example.geocoder.entitys.GraphhopperResponse;
import com.example.geocoder.exceptions.BadRequestException;
import com.example.geocoder.proxy.GeoCoderProxy;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Geocoder controller", tags = {"Geocoder"})
@SwaggerDefinition(tags = {@Tag(name = "Geocoder controller", description = "Основанной контроллер для геокодирования")})
@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
@Slf4j
public class GeocoderController {
    private final GeoCoderProxy proxy;


    @ApiOperation(value = "Get address by coordinates or vice versa",
            httpMethod = "GET",
            notes = "Получение адреса по координатам или наоборот",
            response = GraphhopperResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved response"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping(value = "/get_address")
    @Cacheable(value = "GraphhopperResponse", key = "{#q,#point,#key}")
    public ResponseEntity<Graphhopper> getData(@RequestParam(required = false, name = "q") String q,
                                               @RequestParam(required = false, name = "locale", defaultValue = "en") String locale,
                                               @RequestParam(required = false, name = "limit", defaultValue = "10") int limit,
                                               @RequestParam(required = false, name = "reverse", defaultValue = "false") boolean reverse,
                                               @RequestParam(required = false, name = "debug", defaultValue = "false") boolean debug,
                                               @RequestParam(required = false, name = "point") String point,
                                               @RequestParam(required = false, name = "provider", defaultValue = "default") String provider,
                                               @RequestParam(name = "key") String key) {
        log.debug("Request for REST controller");
        ResponseEntity<Graphhopper> response;
        try {
            response = proxy.getCoordinatesByAddress(q, locale, limit, reverse, debug, point, provider, key);
            log.debug("Response status 200;");
            return response;
        }catch (BadRequestException e){
            log.debug("Response error - "+e.getMessage());
            String [] errorAnswer = e.getMessage().split("<!>");
            return  new ResponseEntity(errorAnswer[1], HttpStatus.valueOf(Integer.parseInt(errorAnswer[0])));
        }


    }
}
