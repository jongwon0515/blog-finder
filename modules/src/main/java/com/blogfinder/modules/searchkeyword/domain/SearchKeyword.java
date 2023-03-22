package com.blogfinder.modules.searchkeyword.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
//@Table(name = "search_keyword")
public class SearchKeyword {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String keyword;

    @Column(nullable = false)
    private int count;

    @Builder
    public SearchKeyword(@NotNull String keyword, @NotNull int count){
        this.keyword = keyword;
        this.count = count;
    }
}
