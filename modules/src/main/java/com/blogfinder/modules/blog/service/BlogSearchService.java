package com.blogfinder.modules.blog.service;

import com.blogfinder.modules.blog.value.BlogRequestDto;

public interface BlogSearchService {
    void callBlogSearchApi(BlogRequestDto dto);
}
