package com.example.demo.controller;

import com.example.demo.model.ArticleDomain;
import com.example.demo.service.ArticleService;
import com.example.demo.utils.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/")
    public String article(){
        return "home";
    }
    @GetMapping("/publish")
    public String edit(HttpServletRequest request){
        ArticleDomain article = new ArticleDomain();
        request.setAttribute("article",article);
        return "article_edit";
    }
    @GetMapping("/edit/{article_id}")
    public String editArticle(HttpServletRequest request,
                              @PathVariable("article_id") int article_id){
        ArticleDomain article = articleService.selectById(article_id);
        System.out.print(article.getTitle());
        request.setAttribute("article",article);
        return "article_edit";
    }
    @GetMapping("/newfile")
    public String newfile1(HttpServletRequest request){
        //System.out.print("helo");
        //ArticleDomain article = new ArticleDomain();
        //request.setAttribute("article",article);
        return "article_edit";
    }
    //如果传过来的文章的标题在数据库中存在，那么直接UPDATE，如果不存在，那么INSERT
    //从前端提交过来的文章在数据库中不存在，那么要调用INSERT语句插入到数据库中。
    //如果存在，则调用UPDATE语句。
/*
    @PostMapping("/publishArticle")
    public APIResponse Publish(
            @RequestParam("title") String title,
            @RequestParam("type1") String type1,
            @RequestParam("type2") String type2,
            @RequestParam("type3") String type3,
            @RequestParam("content") String content){
        System.out.print(title);
        ArticleDomain articleDomain = new ArticleDomain();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        articleDomain.setTitle(title);
        articleDomain.setType1(type1);
        articleDomain.setType2(type2);
        articleDomain.setType3(type3);
        articleDomain.setContent(content);
        articleDomain.setWrite_time(date);

        articleService.addArticle(articleDomain);
        return APIResponse.success();
    }
    */
    @PostMapping ("/newfile")
    public String Publish(
            @RequestBody ArticleDomain articleDomain
        ) {
        System.out.print("hello");

        if(articleDomain.getArticle_id() == null)  {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
            articleDomain.setWrite_time(date);
            articleService.addArticle(articleDomain);
        }
        else articleService.updateArticle(articleDomain);
        return "article_edit";

    }
    //从前端提交过来的文章在数据库中存在，那么要调用UPDATE语句跟新数据库。
    @GetMapping("/modifyArticle")
    public APIResponse modifyArticle(
            @RequestParam("article_id") int article_id,
            @RequestParam("title") String title,
            @RequestParam("type1") String type1,
            @RequestParam("type2") String type2,
            @RequestParam("type3") String type3,
            @RequestParam("content") String content){
        ArticleDomain articleDomain = new ArticleDomain();
        articleDomain.setTitle(title);
        articleDomain.setType1(type1);
        articleDomain.setType2(type2);
        articleDomain.setType3(type3);
        articleService.updateArticle(articleDomain);
        return APIResponse.success();

    }

    /*
    @GetMapping(value = "/edit/{cid}")
    public String editArticle(
            @PathVariable
                    Integer cid,
            HttpServletRequest request
    ) {
        ArticleDomain content = ArticleService.getArticleById(cid);
        request.setAttribute("contents", content);
        MetaCond metaCond = new MetaCond();
        metaCond.setType(Types.CATEGORY.getType());
        List<MetaDomain> categories = metaService.getMetas(metaCond);
        request.setAttribute("categories", categories);
        request.setAttribute("active", "article");
        return "admin/article_edit";
    }

    @RequestMapping("/addArticle")
    public void addArticle(){
        ArticleDomain articleDomain = new ArticleDomain(1,"aaaa",
                "2019-02-12",)
    }
     @RequestMapping("/deleteArticle/{cid}")
    public void deleteArticle(@PathVariable int cid){

     }
     */


}
