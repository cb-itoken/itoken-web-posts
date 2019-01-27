package com.cb.itoken.web.posts.controller;

import com.cb.itoken.common.domain.TbPostsPost;
import com.cb.itoken.common.web.controller.BaseController;
import com.cb.itoken.web.posts.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PostsController extends BaseController<TbPostsPost, PostsService> {

    @Autowired
    private PostsService postsService;

    /**
     * 跳转到首页
     * @return
     */
    @RequestMapping(value = {"", "index"}, method = RequestMethod.GET)
    public String index(){
        return "index";
    }
}
