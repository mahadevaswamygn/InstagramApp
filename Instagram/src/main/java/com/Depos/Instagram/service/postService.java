package com.Depos.Instagram.service;

import com.Depos.Instagram.dao.Post;
import com.Depos.Instagram.dao.User;
import com.Depos.Instagram.repository.iPostRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class postService {
    @Autowired
    iPostRepository postRepository;
    public int savepostd(Post post) {
        Post p = postRepository.save(post);
        return p.getPostId();

    }

    public JSONArray findpost(String postid) {
        JSONArray jarray = new JSONArray();

        if(null != postid && postRepository.findById(Integer.valueOf(postid)).isPresent()) {
            Post post = postRepository.findById(Integer.valueOf(postid)).get();
            JSONObject jobj = setPost(post);
            jarray.put(jobj);
        }
        else {
            List<Post> listpost= postRepository.findAll();
            for (Post post:listpost) {
                JSONObject jobj = setPost(post);
                jarray.put(jobj);

            }
        }
        return jarray;

    }

    private JSONObject setPost(Post post) {
        JSONObject jobj = new JSONObject();
        jobj.put("postId",post.getPostId());
        jobj.put("postData",post.getPostData());

        User user =post.getUser();

        JSONObject uobj = new JSONObject();

        uobj.put("id",user.getId());
        uobj.put("firstname",user.getFirstname());
        uobj.put("age",user.getAge());
        jobj.put("user",uobj);
        return jobj;
    }

    public JSONArray findAllpostById(String userId) {
        JSONArray jrr=new JSONArray();
        List<Post> postList=postRepository.findAll();
        for (Post post:postList) {
            if(post.getUser().getId() == (Integer.valueOf(userId)))
            {
                JSONObject jobj=setPost(post);
                jrr.put(jobj);
            }

        }
        return jrr;
    }

    public void updatePostdetails(String postid, Post newpost) {
        if(postRepository.findById(Integer.valueOf(postid)).isPresent())
        {
            Post oldpost=postRepository.findById(Integer.valueOf(postid)).get();
            newpost.setPostId(oldpost.getPostId());
            newpost.setUser(oldpost.getUser());
            newpost.setCreatedTime(oldpost.getCreatedTime());
            Timestamp updatedtime=new Timestamp(System.currentTimeMillis());
            newpost.setUpdatedTime(updatedtime);
            postRepository.save(newpost);


        }

    }
}
