package com.project.spring_project.service;

import com.project.spring_project.dao.RoleDao;
import com.project.spring_project.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public Role createNewRole(Role role){
    return roleDao.save(role);
    }
}
