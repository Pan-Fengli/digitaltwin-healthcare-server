package com.example.digitaltwinhealthcareserver.Service;

import com.example.digitaltwinhealthcareserver.Dao.UserDao;
import com.example.digitaltwinhealthcareserver.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {
    @Autowired
    private UserDao userDao;

    public User getUserByID(Long id){
        return userDao.findById(id).get();
    }

    public User getByName(String name){
        return userDao.findByName(name);
    }

    public Page<User> findPage(){
        Pageable pageable = PageRequest.of(0, 10);
        return userDao.findAll(pageable);
    }

    public Page<User> find(Long maxId){
        Pageable pageable = PageRequest.of(0, 10);
        return userDao.findMore(maxId,pageable);
    }

    public User save(User u){
        return userDao.save(u);
    }

    public User update(Long id,String name){
        User user = userDao.findById(id).get();
        user.setName(name+"_update");
        return userDao.save(user);
    }

    public Boolean updateById(String  name, Long id){
        return userDao.updateById(name,id)==1;
    }
}
