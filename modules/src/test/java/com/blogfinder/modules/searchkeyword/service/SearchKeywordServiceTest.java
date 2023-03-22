package com.blogfinder.modules.searchkeyword.service;

import com.blogfinder.MudoulesAppTests;
import com.blogfinder.modules.searchkeyword.repository.SearchKeywordRepository;
import com.blogfinder.modules.searchkeyword.value.SearchKeywordVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = MudoulesAppTests.class)
@Sql("classpath:init-test-import.sql")
class SearchKeywordServiceTest {
    @Autowired
    SearchKeywordRepository searchKeywordRepository;

    @Test
    void getPopularSearchKeyword() {
        // Given
        // When
        List<SearchKeywordVo> searchKeywordVos = searchKeywordRepository.findTop10AllByOrderByCountDescKeywordAsc();
        // Then
        assertEquals(10, searchKeywordVos.size());
        assertEquals("검색어11", searchKeywordVos.get(0).getKeyword());
        assertEquals(200, searchKeywordVos.get(0).getCount());
        assertEquals("검색어2", searchKeywordVos.get(9).getKeyword());
        assertEquals(191, searchKeywordVos.get(9).getCount());
    }
}