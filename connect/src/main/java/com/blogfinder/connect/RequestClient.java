package com.blogfinder.connect;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class RequestClient{

    private final WebClient restCustomClient;

    public Mono<Map> get(String url, Map<String, String> headerMap){
        return restCustomClient.get()
                .uri(url)
                .headers(headers -> headerMap.forEach(headers::add))
                .retrieve()
                .bodyToMono(Map.class);
    }
}
