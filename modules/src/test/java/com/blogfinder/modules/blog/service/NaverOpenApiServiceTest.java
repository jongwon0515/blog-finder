package com.blogfinder.modules.blog.service;

import com.blogfinder.MudoulesAppTests;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest(classes = MudoulesAppTests.class)
class NaverOpenApiServiceTest {
    @Autowired
    private OpenApiService naverOpenApiService;
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("네이버 블로그 검색 api 체크")
    void callBlogSearchApi() {
        // Given
        HashMap<String, String> params = new HashMap<>() {{
            put("query", "카뱅");
            put("sort", "accuracy");
            put("page", "1");
            put("size", "10");
        }};

        // When
        Mono<Map> resMono = naverOpenApiService.callBlogSearchApi(params);

        // Then
        StepVerifier.create(resMono)
                .expectNextMatches(response -> {
                    JsonNode actualJson = objectMapper.valueToTree(response);
                    return actualJson.has("lastBuildDate") &&
                            actualJson.has("total") &&
                            actualJson.has("start") &&
                            actualJson.has("display") &&
                            actualJson.get("items").isArray();
                })
                .expectComplete()
                .verify();
    }
}