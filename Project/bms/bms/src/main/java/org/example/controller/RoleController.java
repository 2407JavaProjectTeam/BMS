package org.example.controller;

import cn.dev33.satoken.util.SaResult;
import org.example.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @GetMapping("/roles")
    public SaResult queryRolesInfo(){
        return roleService.queryRolesInfo();
    }
}
