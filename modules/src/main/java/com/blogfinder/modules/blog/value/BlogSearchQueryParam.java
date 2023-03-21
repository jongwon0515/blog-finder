package com.blogfinder.modules.blog.value;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class BlogSearchQueryParam {
    private String query;
    private String sort;
    private Integer page;
    private Integer size;
}
