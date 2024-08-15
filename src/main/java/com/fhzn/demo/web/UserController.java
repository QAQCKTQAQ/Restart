package com.fhzn.demo.web;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fhzn.commons.toolkit.entity.PageInfo;
import com.fhzn.commons.toolkit.entity.PageRequest;
import com.fhzn.commons.webapi.entity.WebResponse;
import com.fhzn.demo.entity.User;
import com.fhzn.demo.service.UserService;
import com.fhzn.demo.util.RSAUtils;
import com.fhzn.demo.web.converter.Converters;
import com.fhzn.demo.web.converter.UserMapper;
import com.fhzn.demo.web.interceptor.RequestContext;
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

@RestController
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@RequestMapping("")
@Tag(name = "user")
public class UserController {
    private final UserService userService;
    @GetMapping("/auth-service/user/query")
    @Operation(description = "用户列表/用户查询/登录验证")
//    public WebResponse<PageInfo<UserVO>> list(@ParameterObject PageRequest request,
////                                              @Parameter(name = "name", description = "目标用户名称") @RequestBody(required = false) User user)
    public WebResponse<PageInfo<UserVO>> list(@ParameterObject PageRequest request, @Parameter(name = "name", description = "目标用户名称")
        @RequestParam(required = false) Integer id,
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String username,
        @RequestParam(required = false) String password,
        @RequestParam(required = false) String type,
        @RequestParam(required = false) Integer status,
        @RequestParam(required = false) String phonenumber
    )
    {
        QueryWrapper<User> wrapper = Wrappers.query();
            if(!Objects.isNull(id)){
                wrapper.eq("id",id);
            }
            if(!Objects.isNull(name)){
                wrapper.eq("name", name);
            }
            if(!Objects.isNull(username)){
                wrapper.eq("username", username);
            }
            if(!Objects.isNull(password)){
                wrapper.eq("password", password);
            }
            if(!Objects.isNull(type)){
                wrapper.eq("type", type);
            }
            if(!Objects.isNull(status)){
                wrapper.eq("status", status);
            }
            if(!Objects.isNull(phonenumber)){
                wrapper.eq("phonenumber", phonenumber);
            }
        Page<User> page = userService.page(Page.of(request.getPage(), request.getPageSize()), wrapper);
        PageInfo<UserVO> ret = Converters.convert2page(page, UserMapper::toApplicationVO);
        return WebResponse.success(ret);
    }

    @PostMapping("/auth-service/gateWay/user/passwordCheck")
    @Operation(description = "登录验证")
    public WebResponse<PageInfo<UserVO>> login(@ParameterObject PageRequest request,
                                              @Parameter(name = "name", description = "目标用户名称") @RequestBody(required = false) User user) {
        QueryWrapper<User> wrapper = Wrappers.query();
        wrapper.eq("username", user.getUsername());
        String realpw=null;
        try {
            String encryptedData = user.getPassword(); // 替换为实际的加密数据
            realpw = RSAUtils.decrypt(encryptedData);

        } catch (Exception e) {
            e.printStackTrace();
        }
        wrapper.eq("password", realpw);
        if(userService.getOne(wrapper,false)==null){
            return WebResponse.error("账号不存在01");
        }else{
            return WebResponse.success(null);
        }

    }

    @PostMapping("/auth-service/v2/user/add")
    @Operation(description = "新增用户")
    public WebResponse<Long> save(@Validated @RequestBody UserRequest request) {
        User user = UserMapper.fromRequest(request);
        QueryWrapper<User> wrapper = Wrappers.query();
        if (!checkObjAllFieldsIsNull(user)) {
            if(!Objects.isNull(user.getId())){
                wrapper.eq("id", user.getId());
            }
            if(!Objects.isNull(user.getName())){
                wrapper.eq("name", user.getName());
            }
            if(!Objects.isNull(user.getUsername())){
                wrapper.eq("username", user.getUsername());
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
        user.setCreator("ckt");
        user.setModifier("ckt");
        user.setCreatedTime(new Date());
        user.setUpdatedTime(new Date());
        if(userService.getOne(wrapper,false)!=null){
            return WebResponse.error("账号已存在");
        }
        user.setId(null);
        userService.saveOrUpdate(user);
        return WebResponse.success(user.getId());
    }

    @PostMapping("/auth-service/v2/user/update")
    @Operation(description = "修改用户")
    public WebResponse<Long> Update(@Validated @RequestBody UserRequest request) {
        User user = UserMapper.fromRequest(request);
        user.setUpdatedTime(new Date());
        QueryWrapper<User> wrapper = Wrappers.query();
        user.setModifier("ckt");
        wrapper.eq("username", user.getUsername());
        if(userService.getOne(wrapper,false)==null){
            return WebResponse.error("账号不存在");
        }
        user.setId(userService.getOne(wrapper,false).getId());
        userService.saveOrUpdate(user);
        return WebResponse.success(user.getId());
    }

    @DeleteMapping("")
    @Operation(description = "删除用户/恢复用户")
    public WebResponse<Long> deleteOrRestore(@Validated @RequestBody UserRequest request) {
        User user = UserMapper.fromRequest(request);
        user.setModifier(RequestContext.getRequestData().getNickname());
        QueryWrapper<User> wrapper = Wrappers.query();
        wrapper.eq("username", user.getUsername());
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

    @GetMapping("/auth-service/auth/query")
    public Object auth() {
        return WebResponse.success();
//        return testClient.authQuery(null);
    }

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
