package com.cb.itoken.web.posts.service.fallback;

import com.cb.itoken.common.hystrix.Fallback;
import com.cb.itoken.web.posts.service.PostsService;
import org.springframework.stereotype.Component;

@Component
public class PostsServiceFallback implements PostsService {
    @Override
    public String page(int pageNum, int pageSize, String tbPostsPostJson) {
        return Fallback.badGateway();
    }
}