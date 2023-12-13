package com.cic.employee.util;

import com.cic.employee.dto.Post;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class ExternalAPIUtil {

    @Autowired
    private RestTemplate restTemplate;

    private final static String url ="https://jsonplaceholder.typicode.com/posts/";

    public Post getPosts(Integer postId){
        Post post = restTemplate.getForObject(url+postId, Post.class);
        if(post==null || post.getBody() == null){
            throw new ResourceNotFoundException("No post found for id "+postId);
        }
        log.info(post.getBody());
        return post;
    }
}
