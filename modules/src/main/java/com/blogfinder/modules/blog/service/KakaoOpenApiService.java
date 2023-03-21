package com.blogfinder.modules.blog.service;

import com.blogfinder.connect.RequestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

import static com.blogfinder.modules.blog.type.ApiVendor.KAKAO;

@Service
@RequiredArgsConstructor
public class KakaoOpenApiService implements OpenApiService {
    private final RequestClient requestClient;
    private final String BLOG_SEARCH_PATH = "/v2/search/blog";

    @Override
    public Mono<Map> callBlogSearchApi(HashMap<String, String> queryParam){
        String url = KAKAO.getHost() + BLOG_SEARCH_PATH + "?" + getVendorsSearchParam(queryParam);

        return requestClient.get(url, KAKAO.getAuthHeader());
    }

    @Override
    public String getVendorsSearchParam(HashMap<String, String> queryParam) {

        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, String> entry : queryParam.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }

            sb.append(entry.getKey());
            sb.append("=");
            sb.append(entry.getValue());
        }

        return sb.toString();
    }
}
