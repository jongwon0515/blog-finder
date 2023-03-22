package com.blogfinder.api.searchkeyword;

import com.blogfinder.MudoulesAppTests;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = MudoulesAppTests.class)
@AutoConfigureMockMvc
@Sql("classpath:init-test-import.sql")
class SearchKeywardApiTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    WebApplicationContext ctx;
    private final String API = "/v1/search-keyword";

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .build();
    }

    @Test
    @DisplayName("인기 검색어 목록 조회")
    void getPopularSearchKeywords() throws Exception {
        ResultActions result = mockMvc.perform(get(API))
                .andExpect(status().isOk())
                .andDo(print());

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(10)))
                .andExpect(jsonPath("$[0].count", equalTo(200)))
                .andExpect(jsonPath("$[0].keyword", equalTo("검색어11")))
                .andExpect(jsonPath("$[9].count", equalTo(191)))
                .andExpect(jsonPath("$[9].keyword", equalTo("검색어2")));

    }
}