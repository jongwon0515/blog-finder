package com.blogfinder.modules.blog.service;

import com.blogfinder.modules.blog.value.BlogSearchQueryParam;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

public interface OpenApiService {
    Mono<Map> callBlogSearchApi(HashMap<String, String> queryParam);

    String getVendorsSearchParam(HashMap<String, String> queryParam);
}
