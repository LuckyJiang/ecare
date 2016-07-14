package com.ecare.web.mapper;

import com.ecare.web.pojo.Reply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReplyMapper {
    int deleteByPrimaryKey(Integer replyid);

    int insert(Reply record);

    int insertSelective(Reply record);

    Reply selectByPrimaryKey(Integer replyid);

    List<Reply> selectByPostId(@Param("postId") Integer postId, @Param("pageStart") Integer pageStart,@Param("pageSize")  Integer pageSize);

    int updateByPrimaryKeySelective(Reply record);

    int updateByPrimaryKey(Reply record);
}