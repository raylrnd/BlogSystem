package com.example.demo;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by nowcoder on 2016/6/26.
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String home(){
        return "myblog";
    }
    @RequestMapping("/publish")
    public String edit_file(){
        return "article_edit";
    }

}
