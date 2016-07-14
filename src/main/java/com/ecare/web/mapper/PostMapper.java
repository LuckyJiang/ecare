package com.ecare.web.mapper;

import com.ecare.web.pojo.Post;
import com.ecare.web.vo.PageVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PostMapper {
    int deleteByPrimaryKey(Integer postid);

    int insert(Post record);

    int insertSelective(Post record);

    Post selectByPrimaryKey(Integer postId);

    List<Post> selectByClassId(@Param("classId") Integer classId, @Param("pageStart") Integer pageStart,@Param("pageSize")  Integer pageSize);

    int updateByPrimaryKeySelective(Post record);

    int updateByPrimaryKey(Post record);
}