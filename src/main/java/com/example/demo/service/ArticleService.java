package com.example.demo.service;
import com.example.demo.dao.ArticleDAO;
import com.example.demo.model.ArticleDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    @Autowired
    ArticleDAO articleDAO;

    public int addArticle(ArticleDomain articleDomain){
        return articleDAO.addArticle(articleDomain);
    }

    public ArticleDomain selectById(int article_id){
        return articleDAO.selectById(article_id);
    }
    public int updateArticle(ArticleDomain articleDomain){
        return articleDAO.updateArticle(articleDomain);
    }
}
