package com.hengheng.dao;

import com.hengheng.domain.Permission;

import java.util.List;

public interface IPermissionDao {
    public List<Permission> findAll();
}
