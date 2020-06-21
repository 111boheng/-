package com.hengheng.dao;

import com.hengheng.domain.Role;
import com.hengheng.domain.Users;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IUsersDao {
    public Users findByUserName(String username);

    public List<Users> findAll();

    public void save(Users user);
    public Users findById(int id);
    public List<Role> findOtherRoles(int userid);

    void addRoleToUser(@Param("map") Map<Integer, Integer> map);
    void addRoleToUser(@Param("userid") int userid,@Param("ids")int[] ids);
}
