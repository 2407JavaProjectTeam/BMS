package org.example.controller;

import cn.dev33.satoken.util.SaResult;
import org.example.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    //http://localhost:8088/api/permisssion/admin
    @GetMapping("/permisssion/{userName}")
    public SaResult queryPermissionInfo(@PathVariable("userName") String userName){
        return permissionService.queryPermissions(userName);
    }
}
