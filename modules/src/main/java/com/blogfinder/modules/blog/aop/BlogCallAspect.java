package com.blogfinder.modules.blog.aop;

import com.blogfinder.modules.searchkeyword.domain.SearchKeyword;
import com.blogfinder.modules.searchkeyword.repository.SearchKeywordRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Optional;

@Aspect
@Component
@RequiredArgsConstructor
public class BlogCallAspect {
    private final SearchKeywordRepository searchKeywordRepository;
    @Pointcut("execution(* com.blogfinder.modules.blog.service.*OpenApiService.callBlogSearchApi(java.util.HashMap<String, String>)) && args(queryParam)")
    public void apiCall(HashMap<String, String> queryParam) {}

    @After("apiCall(java.util.HashMap) queryParam)")
    private void insertLog(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        if (!(args != null && args.length > 0)) return;

        HashMap<String, String> parameters = (HashMap<String, String>) args[0];

        String keyword = parameters.get("query");

        Optional<SearchKeyword> searchKeywordOptional = Optional.ofNullable(searchKeywordRepository.findOneByKeyword(keyword));

        SearchKeyword searchKeyword = searchKeywordOptional
                .orElse(SearchKeyword.builder().count(0).keyword(keyword).build());

        searchKeyword.setCount(searchKeyword.getCount() + 1);

        searchKeywordRepository.save(searchKeyword);
    }
}

