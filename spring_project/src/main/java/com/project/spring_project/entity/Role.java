package com.project.spring_project.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Role {

    @Id
    private String roleName;
    private String roleDescription;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

}

/*

JSON format :
{
    "roleName": "admin",
    "roleDescription": "admin role for the admin of application"
}
-----------------
{
    "roleName":"user",
    "roleDescription":"default role for the user of application"
}
 */