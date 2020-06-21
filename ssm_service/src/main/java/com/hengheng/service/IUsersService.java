package com.hengheng.service;

import com.hengheng.domain.Role;
import com.hengheng.domain.Users;

import java.util.List;
import java.util.Map;

public interface IUsersService {
    public Users findByUserName(String username);

    public List<Users> findAll();

    public void save(Users user);
    public Users findById(int id);
    public List<Role> findOtherRoles(int userid);

    public void addRoleToUser(Map<Integer,Integer> map);
    public void addRoleToUser(int userid,int[]ids);
}
