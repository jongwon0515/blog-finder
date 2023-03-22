package com.blogfinder.modules.searchkeyword.repository;

import com.blogfinder.modules.searchkeyword.domain.SearchKeyword;
import com.blogfinder.modules.searchkeyword.value.SearchKeywordVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchKeywordRepository extends JpaRepository<SearchKeyword, Long> {
    SearchKeyword findOneByKeyword(String keyword);

    List<SearchKeywordVo> findTop10AllByOrderByCountDescKeywordAsc();
}