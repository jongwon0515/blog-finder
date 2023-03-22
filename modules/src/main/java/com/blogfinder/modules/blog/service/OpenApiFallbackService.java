package com.blogfinder.modules.blog.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class OpenApiFallbackService {
    private final List<OpenApiService> openApiServices;

    public Mono<Map> callBlogSearchApi(HashMap<String, String> queryParam) {
        AtomicInteger currentIndex = new AtomicInteger(0);
        return tryCall(currentIndex, queryParam);
    }

    public Mono<Map> tryCall(AtomicInteger currentIndex, HashMap<String, String> queryParam){
        OpenApiService currentService = openApiServices.get(currentIndex.get());

        return currentService.callBlogSearchApi(queryParam)
                .onErrorResume(e -> {
                    // 서비스 호출이 실패한 경우 다음 서비스 호출 시도
                    int nextIndex = currentIndex.incrementAndGet();
                    if (nextIndex < openApiServices.size()) {
                        return tryCall(new AtomicInteger(nextIndex), queryParam);
                    } else {
                        // 모든 서비스 호출이 실패한 경우 에러 반환
                        return Mono.error(new RuntimeException("서버 에러 입니다."));
                    }
                });
    }
}
