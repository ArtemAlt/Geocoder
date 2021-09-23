package com.example.geocoder.exceptions;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        return new BadRequestException(response.status() + "<!>"+ response.body());
    }
}