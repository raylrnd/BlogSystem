package com.example.demo.service;
import com.example.demo.dao.ArticleDAO;
import com.example.demo.model.ArticleDomain;
import com.example.demo.model.MetaDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

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
    public List<ArticleDomain> listArticle(){
        return articleDAO.listALL();
    }
    public List<MetaDomain> listAllTypes(){
        return articleDAO.listAllType();
    }
}
