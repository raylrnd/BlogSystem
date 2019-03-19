package com.example.demo.controller;

import com.example.demo.model.ArticleDomain;
import com.example.demo.model.CommentDomain;
import com.example.demo.service.ArticleService;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;
    @GetMapping("/")
    public String article(){
        return "home";
    }



    @GetMapping("/newfile")
    public String newfile(){
        return "article_edit";
    }

    @PostMapping ("/publish")
    @ResponseBody
    public String Publish(@RequestBody ArticleDomain articleDomain) {
        if(articleDomain.getArticleId() == null)  {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
            articleDomain.setWriteTime(date);
            articleDomain.setSlug(articleDomain.getContent().substring(0,100));
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
        return "home_article_view";
    }

    @GetMapping("/edit/{articleId}")
    public String editArticle(HttpServletRequest request, @PathVariable("articleId") int articleId){
        ArticleDomain article = articleService.selectById(articleId);
        request.setAttribute("article",article);
        return "article_edit";
    }

}
