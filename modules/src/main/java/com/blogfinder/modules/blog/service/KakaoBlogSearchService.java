package com.blogfinder.modules.blog.service;

import com.blogfinder.connect.RequestClient;
import com.blogfinder.modules.blog.value.BlogRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KakaoBlogSearchService implements BlogSearchService{
    private final RequestClient requestClient;
    private final String HOST_URL = "https://dapi.kakao.com";
    private final String BLOG_SEARCH_PATH = "/v2/search/blog";

    @Override
    public void callBlogSearchApi(BlogRequestDto dto){
        requestClient.call(HttpMethod.GET, HOST_URL + BLOG_SEARCH_PATH, null);
    }
}
