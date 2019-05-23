package com.tensquare.search.service;

import com.tensquare.search.dao.ArticleSearchDao;
import com.tensquare.search.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * ArticleSearchService
 *
 * @Author wanggaian
 * @Date 2019/5/16 23:52
 */
@Service
public class ArticleSearchService {

    @Autowired
    private ArticleSearchDao articleSearchDao;

//    @Autowired
//    private IdWorker idWorker;

    public void save(Article article) {
//        article.setId(idWorker.nextId() + "");
        articleSearchDao.save(article);
    }

    public Page<Article> findByTitleAndContentLike(String keywords, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return articleSearchDao.findByTitleAndContentLike(keywords, keywords, pageable);
    }
}
