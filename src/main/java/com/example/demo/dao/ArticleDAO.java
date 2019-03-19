package com.example.demo.dao;
import com.example.demo.model.ArticleDomain;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ArticleDAO {
    String TABLE_NAME = " Article";
    String SELECT_FIELDS = " content,write_time,like_count,comment_count,title,type1,type2,type3";
    String BASIC_FIELDS = " content,write_time,title,type1,type2,type3";
    String UPDATE_FIELDS = " content,title,type1,type2,type3";
    @Insert({"insert into",TABLE_NAME,"(",BASIC_FIELDS,")values (#{content},#{write_time}" +
            ",#{title},#{type1},#{type2},#{type3})"})
    int addArticle(ArticleDomain articleDomain);


    @Select({"select ", BASIC_FIELDS, " from ", TABLE_NAME,
            " where article_id=#{article_id}"})
    ArticleDomain selectById(@Param("article_id") int article_id);
    @Select({"select ", BASIC_FIELDS, " from ", TABLE_NAME,
            " where title=#{title}"})
    int selectByTitle(@Param("title") String title);



    @Update({"update ",TABLE_NAME,"set title = #{title} , type1 = #{type1}, type2 = #{type2}," +
            "type3 = #{type3} , content = #{content} where article_id = article_id"})
    int updateArticle(ArticleDomain articleDomain);
}
