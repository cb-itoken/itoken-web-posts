package com.cb.itoken.web.posts.controller;

import com.cb.itoken.common.domain.TbPostsPost;
import com.cb.itoken.common.domain.TbSysUser;
import com.cb.itoken.common.dto.BaseResult;
import com.cb.itoken.common.utils.MapperUtils;
import com.cb.itoken.common.web.constants.WebConstants;
import com.cb.itoken.common.web.controller.BaseController;
import com.cb.itoken.web.posts.service.PostsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class PostsController extends BaseController<TbPostsPost, PostsService> {

    @Autowired
    private PostsService postsService;

    @ModelAttribute
    public TbPostsPost tbPostsPost(String postGuid){
        TbPostsPost tbPostsPost = null;

        if(StringUtils.isBlank(postGuid)){
            tbPostsPost = new TbPostsPost();
        } else {
            String json = postsService.get(postGuid);

            try {
                tbPostsPost = MapperUtils.json2pojo(json, TbPostsPost.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 二次确认是否为空
        if(tbPostsPost == null){
            tbPostsPost = new TbPostsPost();
        }

        return tbPostsPost;
    }

    /**
     * 跳转到首页
     * @return
     */
    @RequestMapping(value = {"", "index"}, method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    /**
     * 跳转表单页面
     * @return
     */
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form(){
        return "form";
    }

    /**
     * 保存文章
     * @param tbPostsPost
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(TbPostsPost tbPostsPost, HttpServletRequest request, RedirectAttributes redirectAttributes){
        // 初始化
        tbPostsPost.setStatus("0");

        String tbPostsPostJson = null;
        try {
            if(tbPostsPost != null){
                tbPostsPostJson = MapperUtils.obj2json(tbPostsPost);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        TbSysUser admin = (TbSysUser) request.getSession().getAttribute(WebConstants.SESSION_USER);
        String json = postsService.save(tbPostsPostJson, admin.getUserCode());
        BaseResult baseResult = null;
        try {
            baseResult = MapperUtils.json2pojo(json, BaseResult.class);

            redirectAttributes.addFlashAttribute("baseResult", baseResult);
            if(baseResult.getSuccess().endsWith("成功")){
                return "redirect:/index";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/form";
    }
}
