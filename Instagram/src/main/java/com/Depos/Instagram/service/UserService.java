package com.Depos.Instagram.service;

import com.Depos.Instagram.dao.User;
import com.Depos.Instagram.repository.iUserRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    iUserRepository userRep;
    public int saveuser(User user) {
       User user1= userRep.save(user);
       return user1.getId();
    }

    public JSONArray getUser(String userId) {
        JSONArray userarr = new JSONArray();
        if(null != userId)
        {
            User user=userRep.findById(Integer.valueOf(userId)).get();
            if(user!=null) {
                JSONObject uobject = setUser(user);
                userarr.put(uobject);
            }
        }
        else {
            List<User> userList = userRep.findAll();
            for (User user:userList)
            {
                JSONObject uobj = setUser(user);
                userarr.put((uobj));

            }
        }
        return userarr;
    }

    private JSONObject setUser(User user) {
        JSONObject uobj= new JSONObject();
        uobj.put("id",user.getId());
        uobj.put("firstname",user.getFirstname());
        uobj.put("lastname",user.getLastname());
        uobj.put("age",user.getAge());
        uobj.put("email",user.getEmail());
        uobj.put("phonenumber",user.getPhonenumber());
        return uobj;
    }

    public void updateuser(String userId, User newuser) {
        if(userRep.findById(Integer.valueOf(userId)).isPresent())
        {
            User user = userRep.findById(Integer.valueOf(userId)).get();
            newuser.setId(user.getId());
            userRep.save(newuser);


        }
    }
    public void deleteUsers(String userid)
    {
        User user = userRep.findById(Integer.valueOf(userid)).get();
        userRep.delete(user);
    }
}
