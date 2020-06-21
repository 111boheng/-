package com.hengheng.service.impl;

import com.hengheng.dao.IPermissionDao;
import com.hengheng.domain.Permission;
import com.hengheng.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class IPermissionServiceImpl implements IPermissionService {
    @Autowired
    private IPermissionDao permissionDao;

    @Override
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }
}
