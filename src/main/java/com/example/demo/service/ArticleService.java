package com.example.demo.service;
import com.example.demo.dao.ArticleDAO;
import com.example.demo.model.ArticleDomain;
import com.example.demo.model.MetaDomain;
import com.example.demo.model.WriteTimeDomain;
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
    public HashMap<String,Integer> listWriteTime(){
        HashMap<String,Integer> YM = new HashMap<>();
        List<WriteTimeDomain> writeTimeDomain =  articleDAO.listWriteTime();
        for(WriteTimeDomain writeTime : writeTimeDomain){
            String yearAndMounth = writeTime.getWriteTime().substring(0,7);
            if(YM.containsKey(yearAndMounth))
                YM.put(yearAndMounth,YM.get(yearAndMounth)+1);
            else
                YM.put(yearAndMounth,1);
        }
        return YM;
    }

    public List<ArticleDomain> selectByTime(String time) {
        return  articleDAO.selectByTime(time);
    }

    public List<ArticleDomain> selectByType(String type) {
        return articleDAO.selectByType(type);
    }
}
