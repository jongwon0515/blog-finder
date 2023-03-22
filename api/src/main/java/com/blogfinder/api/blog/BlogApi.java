package com.blogfinder.api.blog;

import com.blogfinder.modules.blog.service.OpenApiFallbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/v1/search")
@RestController
@RequiredArgsConstructor
public class BlogApi {
    private final OpenApiFallbackService openApiFallbackService;

    @GetMapping("/blog")
    public Mono<Map> searchBlog(@RequestParam(value = "query", required = true, defaultValue = "") String query,
                                @RequestParam(value = "sort", required = false, defaultValue = "accuracy") String sort,
                                @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                @RequestParam(value = "size", required = false, defaultValue = "10") Integer size){
        return openApiFallbackService.callBlogSearchApi(new HashMap<>() {{
            put("query", query);
            put("sort", sort);
            put("page", String.valueOf(page));
            put("size", String.valueOf(size));
        }});
    }
}
