package com.example.demo.dao;
import com.example.demo.model.ArticleDomain;
import com.example.demo.model.MetaDomain;
import com.example.demo.model.WriteTimeDomain;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleDAO {
    String TABLE_NAME = " Article";
    String SELECT_FIELDS = " articleId, content,writeTime,commentCount,title,type1,type2,type3";
    String BASIC_FIELDS = "  content,writeTime,title,type1,type2,type3,slug";
    String UPDATE_FIELDS = " articleId, content,title,type1,type2,type3";

    @Insert({"insert into",TABLE_NAME,"(",BASIC_FIELDS,")values (#{content},#{writeTime}" +
            ",#{title},#{type1},#{type2},#{type3},#{slug})"})
    int addArticle(ArticleDomain articleDomain);


    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME,
            " where articleId=#{articleId}"})
    ArticleDomain selectById(@Param("articleId") int articleId);



    @Update({"update ",TABLE_NAME,"set title = #{title} , type1 = #{type1}, type2 = #{type2}," +
            "type3 = #{type3} , content = #{content}, slug = #{slug} where articleId = #{articleId}"})
    int updateArticle(ArticleDomain articleDomain);

    @Select("select * from Article")
    List<ArticleDomain> listALL();

    @Select({"select type1 as type ,count(type1) as count from (select type1 from article " +
            "union all select type2 from article union all select type3 from article) tt group by type1"})
    List<MetaDomain> listAllType();

    @Select({"select writeTime from" ,TABLE_NAME})
    List<WriteTimeDomain> listWriteTime();
//select * from article where  left(writeTime,7)  = '2019-03';
    @Select({"select * from ", TABLE_NAME,"where left(writeTime,7)  = #{time}"})
    List<ArticleDomain> selectByTime(String time);

    @Select({"select * from",TABLE_NAME,"where type1 = #{type} or type2 = #{type} or type3 = #{type}"})
    List<ArticleDomain> selectByType(String type);
}
