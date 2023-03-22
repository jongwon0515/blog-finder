package com.blogfinder.api.searchkeyword;

import com.blogfinder.modules.searchkeyword.service.SearchKeywordService;
import com.blogfinder.modules.searchkeyword.value.SearchKeywordVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/v1/search-keyword")
@RestController
@RequiredArgsConstructor
public class SearchKeywardApi {
    private final SearchKeywordService searchKeywordService;
    @GetMapping("/")
    public List<SearchKeywordVo> getPopularSearchKeywords(){
        return searchKeywordService.getPopularSearchKeyword();
    }
}
