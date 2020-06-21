package com.hengheng.dao;

import com.hengheng.domain.Role;

import java.util.List;

public interface IRoleDao {
    public List<Role> findAll();

    public void save(Role role);

}
