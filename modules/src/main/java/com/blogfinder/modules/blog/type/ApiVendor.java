package com.blogfinder.modules.blog.type;

import lombok.Getter;

import java.util.Map;

@Getter
public enum ApiVendor {
    KAKAO("https://dapi.kakao.com",
            Map.of("Authorization", "KakaoAK cf0010428b2be1dc030c91b4ef565584")),
    NAVER("https://openapi.naver.com",
            Map.of("X-Naver-Client-Id", "nNEYBFYgQ_70V5gOlw9D",
                    "X-Naver-Client-Secret", "RZYuuzbR5e"));

    private final String host;
    private final Map<String, String> authHeader;

    ApiVendor(String host, Map<String, String> authHeader) {
        this.host = host;
        this.authHeader = authHeader;

    }
}
