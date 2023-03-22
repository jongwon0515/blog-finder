package com.blogfinder.modules.blog.repository;

import com.blogfinder.modules.blog.value.BlogSearchQueryParam;
import com.blogfinder.modules.searchkeyword.domain.SearchKeyword;
import com.blogfinder.modules.searchkeyword.repository.SearchKeywordRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SearchKeywordRepositoryTest {
    @Autowired
    SearchKeywordRepository searchKeywordRepository;

    @Test
    public void 데이터_삽입(){
        searchKeywordRepository.save(SearchKeyword.builder().keyword("개발").count(0).build());
        List<SearchKeyword> obj = searchKeywordRepository.findAll();
        obj.forEach(System.out::println);
    }

}