package com.blogfinder.connect;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class RequestClient{

    private final WebClient restCustomClient;

    public Object call(HttpMethod method, String url, Object body){


        return null;
    }
}
