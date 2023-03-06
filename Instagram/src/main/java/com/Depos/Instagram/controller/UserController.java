package com.Depos.Instagram.controller;

import com.Depos.Instagram.dao.User;
import com.Depos.Instagram.repository.iUserRepository;
import com.Depos.Instagram.service.UserService;
import jakarta.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserService userservice;
    @Autowired
    iUserRepository iuserRep;
    @PostMapping(value = "/user")
    public ResponseEntity<String> saveUser(@RequestBody String userdata)
    {
        User user=setUser(userdata);
        int sid=userservice.saveuser(user);
        return new ResponseEntity<String>("user saved id with"+sid, HttpStatus.CREATED);

    }

    private User setUser(String userdata) {
        JSONObject json = new JSONObject(userdata);
        User user = new User();
        user.setAge(json.getInt("age"));
        user.setFirstname(json.getString("firstname"));
        user.setLastname(json.getString("lastname"));
        user.setEmail((json.getString("email")));
        user.setPhonenumber(json.getString("number"));
        return user;


    }
    @GetMapping(value = "/user")
    public ResponseEntity<String> getUser(@Nullable @RequestParam String userId)
    {
        JSONArray userarray=userservice.getUser(userId);
        return new ResponseEntity(userarray.toString(),HttpStatus.OK);

    }
    @PutMapping(value = "/user/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable String userId,@RequestBody String userdata)
    {
        User user=setUser(userdata);
        userservice.updateuser(userId,user);
        return new ResponseEntity<>("user updated",HttpStatus.OK);

    }
    @DeleteMapping(value = "userid/{userid}")

    public ResponseEntity<String> deleteUser(@PathVariable String userid)
    {
        userservice.deleteUsers(userid);
       return new ResponseEntity<String>("deleted",HttpStatus.OK);

    }


}
