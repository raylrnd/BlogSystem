package com.example.demo.dao;
import com.example.demo.model.ArticleDomain;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleDAO {
    String TABLE_NAME = " Article";
    String SELECT_FIELDS = " content,writeTime,likeCount,commentCount,title,type1,type2,type3";
    String BASIC_FIELDS = " content,writeTime,title,type1,type2,type3";
    String UPDATE_FIELDS = " content,title,type1,type2,type3";
    String pp = "articleId , title, type1,type2,type3, writeTime";
    @Insert({"insert into",TABLE_NAME,"(",BASIC_FIELDS,")values (#{content},#{writeTime}" +
            ",#{title},#{type1},#{type2},#{type3})"})
    int addArticle(ArticleDomain articleDomain);


    @Select({"select ", BASIC_FIELDS, " from ", TABLE_NAME,
            " where articleId=#{articleId}"})
    ArticleDomain selectById(@Param("articleId") int article_id);

    @Select({"select ", BASIC_FIELDS, " from ", TABLE_NAME,
            " where title=#{title}"})
    int selectByTitle(@Param("title") String title);



    @Update({"update ",TABLE_NAME,"set title = #{title} , type1 = #{type1}, type2 = #{type2}," +
            "type3 = #{type3} , content = #{content} where articleId = articleId"})
    int updateArticle(ArticleDomain articleDomain);

    @Select("select * from Article")
    List<ArticleDomain> listALL();
}
