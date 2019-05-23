package com.tensquare.search.dao;

import com.tensquare.search.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * ArticleSearchDao
 *
 * @Author wanggaian
 * @Date 2019/5/16 23:47
 */
public interface ArticleSearchDao extends ElasticsearchRepository<Article, String> {
    Page<Article> findByTitleAndContentLike(String title, String content, Pageable pageable);
}
