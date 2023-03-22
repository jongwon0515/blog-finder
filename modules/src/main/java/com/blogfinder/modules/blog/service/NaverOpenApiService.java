package com.blogfinder.modules.blog.service;

import com.blogfinder.connect.RequestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

import static com.blogfinder.modules.blog.type.ApiVendor.NAVER;

@Order(2)
@Service
@RequiredArgsConstructor
public class NaverOpenApiService implements OpenApiService {
    private final RequestClient requestClient;
    private final String BLOG_SEARCH_PATH = "/v1/search/blog.jsond";

    @Override
    public Mono<Map> callBlogSearchApi(HashMap<String, String> queryParam){
        String url = NAVER.getHost() + BLOG_SEARCH_PATH + "?" + getVendorsSearchParam(queryParam);

        return requestClient.get(url, NAVER.getAuthHeader());
    }

    @Override
    public String getVendorsSearchParam(HashMap<String, String> queryParam) {

        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, String> entry : queryParam.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }

            switch (entry.getKey()){
                case "page":
                    sb.append("start");
                    break;
                case "size":
                    sb.append("display");
                    break;
                default:
                    sb.append(entry.getKey());
                    break;
            }

            sb.append("=");

            if(entry.getKey().equals("sort")){
                if (entry.getValue().equals("recency")) {
                    sb.append("date");
                } else {
                    sb.append("sim");
                }
            }else {
                sb.append(entry.getValue());
            }
        }

        return sb.toString();
    }
}
