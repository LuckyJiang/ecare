package com.ecare.web.controller;

import com.ecare.web.pojo.*;
import com.ecare.web.pojo.Class;
import com.ecare.web.service.PostService;
import com.ecare.web.util.PageUtil;
import com.ecare.web.util.ResultUtil;
import com.ecare.web.vo.Constant.Constant;
import com.ecare.web.vo.PageVo;
import com.ecare.web.vo.PostFormVo;
import com.ecare.web.vo.PostVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by L on 2016/7/13.
 */
@Controller
@RequestMapping("/Post")
public class PostController {
    @Autowired
    private PostService postService;

    @RequestMapping(value = "/findClass")
    @ResponseBody
    public Map<String, Object> findAllClass(@RequestParam(value = "page") int pageNumber) {
        if (pageNumber > -1) {
            List<Class> classList = postService.findAllClass(PageUtil.getPage(pageNumber, Constant.CLASS_PAGE_NUMBER));
            if (classList != null)
                return ResultUtil.getResult(Constant.SUCCESS, "查询成功", classList);
        }
        return ResultUtil.getResult(Constant.FAILURE, "查询失败", null);
    }

    @RequestMapping(value = "/getClass")
    @ResponseBody
    public Map<String, Object> findClassByClassId(@RequestParam(value = "classId") int classId) {
        postService.updateClassViews(classId);
        Class classVo = postService.findClassByClassId(classId);
        if (classVo != null) {

            return ResultUtil.getResult(Constant.SUCCESS, "查询成功", classVo);
        }
        return ResultUtil.getResult(Constant.FAILURE, "查询失败", null);
    }

    @RequestMapping(value = "/findPost")
    @ResponseBody
    public Map<String, Object> findPostByClassId(@RequestParam(value = "classId") int classId, @RequestParam(value = "page") int pageNumber) {

        if (pageNumber > -1) {
            List<PostFormVo> postFormVos = postService.findPostByClassId(classId, PageUtil.getPage(pageNumber, Constant.POST_PAGE_NUMBER));
            if (postFormVos.size() != 0) {
                for (int i = 0; i < postFormVos.size(); i++) {
                    postFormVos.get(i).setPhotoUrl(postService.findUrlByPostId(postFormVos.get(i).getPostId()));
                }
                return ResultUtil.getResult(Constant.SUCCESS, "查询成功", postFormVos);
            }
        }
        return ResultUtil.getResult(Constant.FAILURE, "查询失败", null);
    }

    @RequestMapping(value = "/getPost")
    @ResponseBody
    public Map<String, Object> findPostByPostId(@RequestParam(value = "postId") int postId) {
        postService.updatePostViews(postId);
        PostVo postVo = postService.findPostByPostId(postId);
        if (postVo != null) {
            postVo.setPhotoUrl(postService.findUrlByPostId(postVo.getPostId()));

            return ResultUtil.getResult(Constant.SUCCESS, "查找成功", postVo);
        }
        return ResultUtil.getResult(Constant.FAILURE, "查找失败", null);

    }

    @RequestMapping(value = "/findReply")
    @ResponseBody
    public Map<String, Object> findReplyByPostId(@RequestParam("postId") int postId, @RequestParam("page") int pageNumber) {
        if (pageNumber > -1) {
            List<Reply> replies = postService.findReplyByPostId(postId, PageUtil.getPage(pageNumber, Constant.REPLY_PAGE_NUMBER));
            if (replies.size() != 0)
                return ResultUtil.getResult(Constant.SUCCESS, "查询成功", replies);
        }
        return ResultUtil.getResult(Constant.FAILURE, "查询失败", null);
    }

    @RequestMapping(value = "/addPost", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addPost(@ModelAttribute Post post, @RequestParam("user_id") int userId, @RequestParam("url") String[] url) {
        post.setPostUserId(userId);
        int result = postService.addPost(post);
        if (result != 0) {
            for (int i = 0; i < url.length; i++) {
                int photoResult = postService.addPhotoUrl(post.getPostId(), url[i]);
                if (photoResult == 0)
                    return ResultUtil.getResult(Constant.FAILURE, "图片添加失败", null);
            }
            return ResultUtil.getResult(Constant.SUCCESS, "添加成功", null);
        } else
            return ResultUtil.getResult(Constant.FAILURE, "内容添加失败", null);
    }

    @RequestMapping(value = "/addReply", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addReply(@ModelAttribute Reply reply, @RequestParam("user_id") int userId) {
        reply.setReplyUserId(userId);
        int result = postService.addReply(reply);
        if (result != 0) {
            return ResultUtil.getResult(Constant.SUCCESS, "添加成功", reply.getReplyId());
        } else
            return ResultUtil.getResult(Constant.FAILURE, "添加失败", null);
    }

    @RequestMapping(value = "/addPostLike")
    @ResponseBody
    public Map<String, Object> addLike(@RequestParam("user_id") int userId, @RequestParam("postId") int postId) {
        Likes likes = new Likes();
        likes.setLikesUserId(userId);
        likes.setLikesType(false);
        likes.setLikesTypeId(postId);
        int result = postService.addLike(likes);
        if (result != 0)
            return ResultUtil.getResult(Constant.SUCCESS, "帖子点赞成功", null);
        return ResultUtil.getResult(Constant.FAILURE, "已经点赞", null);
    }

    @RequestMapping(value = "/addReplyLike")
    @ResponseBody
    public Map<String, Object> addReplyLike(@RequestParam("user_id") int userId, @RequestParam("replyId") int replyId) {
        Likes likes = new Likes();
        likes.setLikesUserId(userId);
        likes.setLikesType(true);
        likes.setLikesTypeId(replyId);
        int result = postService.addLike(likes);
        if (result != 0)
            return ResultUtil.getResult(Constant.SUCCESS, "回帖点赞成功", null);
        return ResultUtil.getResult(Constant.FAILURE, "已经点赞", null);
    }

    @RequestMapping(value = "/addFavorite")
    @ResponseBody
    public Map<String, Object> addFavorite(@RequestParam("user_id") int user_id, @RequestParam("postId") int postId) {
        Favorite favorite = new Favorite();
        favorite.setFavoriteUserId(user_id);
        favorite.setFavoritePostId(postId);
        int result = postService.addFavorite(favorite);
        if (result != 0)
            return ResultUtil.getResult(Constant.SUCCESS, "收藏成功", null);
        return ResultUtil.getResult(Constant.FAILURE, "已经收藏", null);
    }

    @RequestMapping(value = "/findFavorite")
    @ResponseBody
    public Map<String, Object> findFavorite(@RequestParam("user_id") int user_id, @RequestParam("page") int pageNumber) {
        if (pageNumber > -1) {
            List<PostFormVo> list = postService.findFavoriteByUserId(user_id, PageUtil.getPage(pageNumber, Constant.FAVORITE_PAGE_NUMBER));
            if (list.size() != 0)
                return ResultUtil.getResult(Constant.SUCCESS, "查询成功", list);
        }
        return ResultUtil.getResult(Constant.FAILURE, "查询失败", null);
    }
}
