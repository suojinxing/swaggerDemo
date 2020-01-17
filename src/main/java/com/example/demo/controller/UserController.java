package com.example.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: UserController
 * Package: com.example.demo.controller
 * Desc:
 * Date: 2019/11/12 12:33 下午
 * Author: suojinxing
 */
@RestController
@Api(value = "用户模块", description = "用户模块的接口信息")
public class UserController {


    //模拟一个数据库
    public static ArrayList<User> users = new ArrayList<>();

    static {
        users.add(new User("张三", "1234"));
        users.add(new User("李四", "3456"));
    }

    //接口简单描述和详细描述
    @ApiOperation(value = "获取用户列表", notes = "获取所有用户的信息")
    @GetMapping("/users")
    public Map getUsers() {
        Map<String, List> map = new HashMap<>();
        map.put("users", users);
        return map;
    }

    //接口简单描述和详细描述
    @ApiOperation(value = "根据用户ID查询用户", notes = "查询单个用户信息")
    //参数信息
    @ApiImplicitParam(name = "id", value = "用户ID", dataType = "int", paramType = "path")
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable("id") String id) {
        int uid = Integer.parseInt(id);
        User user = users.get(uid);
        return user;
    }

    @ApiOperation(value = "添加用户")
    @ApiImplicitParam(value = "用户类", paramType = "query")
    @PostMapping("/user")
    public Object add(User user) {
        System.out.println(user);
        return users.add(user);
    }

    @ApiOperation(value = "删除用户", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", dataType = "int", paramType = "path")
    @DeleteMapping(value = "users/{id}")
    public Object delete(@PathVariable("id") int id) {
        return users.remove(id);
    }
}
