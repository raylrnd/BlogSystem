package com.example.demo.controller;

import com.example.demo.model.ArticleDomain;
import com.example.demo.model.MetaDomain;
import com.example.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/")
    public String article(){
        return "personalHome";
    }
    @GetMapping("/personalHome")
    public String article(HttpServletRequest request){
        List<ArticleDomain> articleDomains = articleService.listArticle();
        List<MetaDomain> metaDomains = articleService.listAllTypes();
        HashMap<String,Integer> YM = articleService.listWriteTime();
        request.setAttribute("article_list",articleDomains);
        request.setAttribute("categories", metaDomains);
        request.setAttribute("YM",YM);
        return "personalHome";
    }


    @GetMapping("/newfile")
    public String newfile(){
        return "article_edit";
    }

    @PostMapping ("/publish")
    @ResponseBody
    public String Publish(@RequestBody ArticleDomain articleDomain) {
        articleDomain.setSlug(articleDomain.getContent().substring(0,100));
        if(articleDomain.getArticleId() == null)  {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
            articleDomain.setWriteTime(date);
            articleService.addArticle(articleDomain);
        }
        else {
            articleService.updateArticle(articleDomain);
        }
        return "{success}";
    }

    //将数据库中的所有文章列出来
    @GetMapping("list")
    public String list(HttpServletRequest request){
        List<ArticleDomain> articleDomains = articleService.listArticle();
        request.setAttribute("article_list",articleDomains);
        return "article_list";
    }


    @GetMapping("/article/{articleId}")
    public String articleView(HttpServletRequest request,@PathVariable("articleId") int articleId){
        ArticleDomain article = articleService.selectById(articleId);
        request.setAttribute("article",article);
        return "one_article_view";
    }

    @GetMapping("/edit/{articleId}")
    public String editArticle(HttpServletRequest request, @PathVariable("articleId") int articleId){
        ArticleDomain article = articleService.selectById(articleId);
        request.setAttribute("article",article);
        return "article_edit";
    }

    @GetMapping("/time/{time}")
    public String selectByTime(HttpServletRequest request,@PathVariable String time){
        List<ArticleDomain> article_list = articleService.selectByTime(time);
        request.setAttribute("article_list",article_list);
        return "personalHome";
    }

    @GetMapping("/type/{type}")
    public String selectByType(HttpServletRequest request,@PathVariable String type){
        List<ArticleDomain> article_list = articleService.selectByType(type);
        request.setAttribute("article_list",article_list);
        return "personalHome";
    }

}
