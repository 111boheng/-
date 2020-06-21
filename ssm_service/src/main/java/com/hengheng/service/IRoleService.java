package com.hengheng.service;

import com.hengheng.domain.Role;

import java.util.List;

public interface IRoleService {
    public List<Role> findAll();

    public void save(Role role);
}
