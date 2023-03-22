package com.blogfinder.modules.searchkeyword.service;

import com.blogfinder.modules.searchkeyword.repository.SearchKeywordRepository;
import com.blogfinder.modules.searchkeyword.value.SearchKeywordVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchKeywordService {
    private final SearchKeywordRepository searchKeywordRepository;

    public List<SearchKeywordVo> getPopularSearchKeyword(){
        return searchKeywordRepository.findTop10AllByOrderByCountDescKeywordAsc();
    }
}
