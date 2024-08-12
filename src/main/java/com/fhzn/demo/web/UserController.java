package com.fhzn.demo.web;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fhzn.commons.toolkit.entity.PageInfo;
import com.fhzn.commons.toolkit.entity.PageRequest;
import com.fhzn.commons.webapi.entity.WebResponse;
//import com.fhzn.commons.wups.client.UserClient;

import com.fhzn.demo.entity.User;
import com.fhzn.demo.service.UserService;
import com.fhzn.demo.web.converter.Converters;
import com.fhzn.demo.web.converter.UserMapper;
import com.fhzn.demo.web.interceptor.RequestContext;
import com.fhzn.demo.web.request.IdRequest;
import com.fhzn.demo.web.request.UserRequest;
import com.fhzn.demo.web.vo.UserVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Objects;

//import com.fhzn.demo.mapper.UserMapper;
@RestController
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@RequestMapping("user")
@Tag(name = "user")
public class UserController {
    private final UserService userService;
//    private final UserClient userClient;

    @GetMapping("")
    @Operation(description = "用户列表/用户查询/登录验证")
    public WebResponse<PageInfo<UserVO>> list(@ParameterObject PageRequest request,
                                              @Parameter(name = "name", description = "目标用户名称") @RequestBody(required = false) User user) {
        // TODO 区分and和or的查询
        QueryWrapper<User> wrapper = Wrappers.query();


        if (!checkObjAllFieldsIsNull(user)) {
            if(!Objects.isNull(user.getId())){
                wrapper.eq("id", user.getId());
            }
            if(!Objects.isNull(user.getName())){
                wrapper.eq("name", user.getName());
            }
            if(!Objects.isNull(user.getAccount())){
                wrapper.eq("account", user.getAccount());
            }
            if(!Objects.isNull(user.getPassword())){
                wrapper.eq("password", user.getPassword());
            }
            if(!Objects.isNull(user.getType())){
                wrapper.eq("type", user.getType());
            }
            if(!Objects.isNull(user.getStatus())){
                wrapper.eq("status", user.getStatus());
            }
            if(!Objects.isNull(user.getPhonenumber())){
                wrapper.eq("phonenumber", user.getPhonenumber());
            }
        }
        Page<User> page = userService.page(Page.of(request.getPage(), request.getPageSize()), wrapper);
        PageInfo<UserVO> ret = Converters.convert2page(page, UserMapper::toApplicationVO);
        return WebResponse.success(ret);
    }

    @PostMapping("")
    @Operation(description = "新增用户")
    public WebResponse<Long> save(@Validated @RequestBody UserRequest request) {
        User user = UserMapper.fromRequest(request);
        QueryWrapper<User> wrapper = Wrappers.query();
        wrapper.eq("account", user.getAccount());
        user.setCreator(RequestContext.getRequestData().getNickname());
        user.setModifier(RequestContext.getRequestData().getNickname());
        user.setCreatedTime(new Date());
        user.setUpdatedTime(new Date());
        if(userService.getOne(wrapper,false)!=null){
            return WebResponse.error("账号已存在");
        }
        user.setId(null);
        userService.saveOrUpdate(user);
        return WebResponse.success(user.getId());
    }

    @PutMapping("")
    @Operation(description = "修改用户")
    public WebResponse<Long> Update(@Validated @RequestBody UserRequest request) {
        User user = UserMapper.fromRequest(request);
        QueryWrapper<User> wrapper = Wrappers.query();
        user.setModifier(RequestContext.getRequestData().getNickname());
        wrapper.eq("account", user.getAccount());
        if(userService.getOne(wrapper,false)==null){
            return WebResponse.error("账号不存在");
        }
        user.setId(null);
        userService.saveOrUpdate(user);
        return WebResponse.success(user.getId());
    }

    @DeleteMapping("")
    @Operation(description = "删除用户/恢复用户")
    public WebResponse<Long> deleteOrRestore(@Validated @RequestBody UserRequest request) {
        User user = UserMapper.fromRequest(request);
        user.setModifier(RequestContext.getRequestData().getNickname());

        QueryWrapper<User> wrapper = Wrappers.query();
        wrapper.eq("account", user.getAccount());
        if(userService.getOne(wrapper,false)==null){
            return WebResponse.error("账号不存在");
        }
        if(user.getStatus().equals("1")){
            user.setStatus("0");
        }else if (user.getStatus().equals("0")){
            user.setStatus("1");
        }

        userService.saveOrUpdate(user);
        return WebResponse.success(user.getId());
    }


//    @GetMapping("/auth-query")
//    public Object auth() {
//        return userClient.authQuery(null);
//    }

    private boolean checkObjAllFieldsIsNull(Object object) {
        // 如果对象为null直接返回true
        if (null == object) {
            return true;
        }
        try {
            // 挨个获取对象属性值
            for (Field f : object.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                // 如果属性名不为serialVersionUID，有一个属性值不为null，且值不是空字符串，就返回false
                if (!"serialVersionUID".equals(f.getName()) &&
                    f.get(object) != null && StringUtils.isNotBlank(f.get(object).toString())) {
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

}
