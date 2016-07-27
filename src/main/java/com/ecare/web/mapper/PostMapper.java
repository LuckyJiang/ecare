package com.ecare.web.mapper;

import com.ecare.web.pojo.Post;
import com.ecare.web.vo.PostFormVo;
import com.ecare.web.vo.PostVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PostMapper {
//    int deleteByPrimaryKey(Integer postId);

//    int insert(Post record);

    int insertSelective(Post record);

    PostVo selectByPrimaryKey( Integer postId);

    PostFormVo selectFormByPrimaryKey(Integer postId);

    List<PostFormVo> selectByClassId(@Param("classId") Integer classId, @Param("pageStart") Integer pageStart, @Param("pageSize")  Integer pageSize);

    List<PostFormVo> selectHomeTop(Integer homeTopNumber);
    List<PostFormVo> selectClassTop(@Param("classId") Integer classId,@Param("classTopNumber") Integer ClassTopNumber);

    int updatePostLikes(Integer postId);
    int updatePostViews(Integer postId);
    int updatePostActiveNumber(Integer postId);
    int initPostActiveNumber(Integer postId);
    int initAllPostActiveNumber();


//    int updateByPrimaryKeySelective(Post record);
//
//    int updateByPrimaryKeyWithBLOBs(Post record);
//
//    int updateByPrimaryKey(Post record);
}