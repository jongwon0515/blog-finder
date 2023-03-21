package com.blogfinder.modules.blog.service;

import com.blogfinder.MudoulesApp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = MudoulesApp.class)
class KakaoOpenApiServiceTest {
    @Autowired
    private OpenApiService kakaoOpenApiService;
    @Test
    void callBlogSearchApi() {
//        kakaoOpenApiService.callBlogSearchApi();

    }
}