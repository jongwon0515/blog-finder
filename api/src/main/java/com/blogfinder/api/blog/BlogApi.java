package com.blogfinder.api.blog;

import com.blogfinder.modules.blog.service.BlogSearchService;
import com.blogfinder.modules.blog.value.BlogRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v1/")
@RestController
@RequiredArgsConstructor
public class BlogApi {
    private final BlogSearchService blogSearchService;

    @GetMapping("/search/blog")
    public void searchBlog(@RequestParam(value = "query", required = true, defaultValue = "") String query,
                           @RequestParam(value = "sort", required = false, defaultValue = "accuracy") String sort,
                           @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                           @RequestParam(value = "size", required = false, defaultValue = "10") Integer size){

        blogSearchService.callBlogSearchApi(BlogRequestDto.builder().build());

    }
}
