package com.hengheng.service.impl;

import com.hengheng.dao.IUsersDao;
import com.hengheng.domain.Role;
import com.hengheng.domain.Users;
import com.hengheng.service.IUsersService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UsersServiceImpl implements IUsersService {
    @Autowired
    private IUsersDao usersDao;
    @Override
    public Users findByUserName(String username) {

        return usersDao.findByUserName(username);
    }

    @Override
    public List<Users> findAll() {
        return usersDao.findAll();
    }

    @Override
    public void save(Users user) {
        SimpleHash simpleHash = new SimpleHash("md5",user.getPassword(),user.getUsername(),1);
        user.setPassword(simpleHash.toString());
        usersDao.save(user);
    }

    @Override
    public Users findById(int id) {
        return usersDao.findById(id);
    }

    @Override
    public List<Role> findOtherRoles(int userid) {
        return usersDao.findOtherRoles(userid);
    }



    @Override
    public void addRoleToUser(Map<Integer, Integer> map) {
        usersDao.addRoleToUser(map);
    }

    @Override
    public void addRoleToUser(int userid, int[] ids) {
        usersDao.addRoleToUser(userid,ids);
    }
}
