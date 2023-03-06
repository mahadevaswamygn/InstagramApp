package com.Depos.Instagram.controller;

import com.Depos.Instagram.dao.Post;
import com.Depos.Instagram.dao.User;
import com.Depos.Instagram.repository.iUserRepository;
import com.Depos.Instagram.service.UserService;
import com.Depos.Instagram.service.postService;
import jakarta.annotation.Nullable;
import jakarta.persistence.PostRemove;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
public class postController {
    @Autowired
    postService postservice;
    @Autowired
    UserService usersrvice2;
    @Autowired
    iUserRepository userRepository;


    @PostMapping(value = "/post")
    public ResponseEntity<String> savepost(@RequestBody String postdata)
    {
        Post post=setpostdata(postdata);
        int PostIds=postservice.savepostd(post);
        return new ResponseEntity<String >(String.valueOf(PostIds), HttpStatus.CREATED);


    }

    private Post setpostdata(String postdata) {
        JSONObject jobj = new JSONObject(postdata);
        User user = null;
        int userid=jobj.getInt("userId");
        if (userRepository.findById(userid).isPresent()) {
            user = userRepository.findById(userid).get();
        }
        else {
            return null;
        }
        Post post = new Post();
        post.setUser(user);
        post.setPostData(jobj.getString("postData"));
        Timestamp curtime=new Timestamp(System.currentTimeMillis());
        post.setCreatedTime(curtime);

        return post;
    }
    @GetMapping(value = "/post/get")

    public ResponseEntity<String> getPosts(@Nullable @RequestParam String postid)
    {
        JSONArray jarr=postservice.findpost(postid);
        return new ResponseEntity<String>(jarr.toString(),HttpStatus.OK);
    }
    @GetMapping(value = "AllPostByUserId")

    public  ResponseEntity<String> getAllPostbyId(@RequestParam String userid)
    {
        JSONArray jarr=postservice.findAllpostById(String.valueOf(userid));
        return new ResponseEntity<String>(jarr.toString(),HttpStatus.OK);
    }
    @PutMapping(value = "/post/{postid}")

    public ResponseEntity<String> updatePost(@PathVariable String postid,@RequestBody String post)
    {
        Post newpost=setpostdata(post);

        postservice.updatePostdetails(postid,newpost);
        return new ResponseEntity<String>("updated",HttpStatus.OK);
    }


}
