package com.exam.instanttest.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@Service
public class HttpService {

    public HttpResponse<String> getRequest(String url, HashMap<String, Object> queryParams)
            throws UnirestException {
        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.get(
                url)
                .fields(queryParams)
                .asString();
        return response;
    }

}
