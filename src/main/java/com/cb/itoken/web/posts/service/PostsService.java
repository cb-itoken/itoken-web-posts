package com.cb.itoken.web.posts.service;

import com.cb.itoken.common.web.service.BaseClientService;
import com.cb.itoken.web.posts.service.fallback.PostsServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "itoken-service-posts", fallback = PostsServiceFallback.class)
public interface PostsService extends BaseClientService {

    @RequestMapping(value = "v1/posts/page/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public String page(
            @PathVariable(required = true, value = "pageNum") int pageNum,
            @PathVariable(required = true, value = "pageSize") int pageSize,
            @RequestParam(required = false, value = "tbPostsPostJson") String tbPostsPostJson
    );
}
