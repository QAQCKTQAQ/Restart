//package com.fhzn.demo.web;
//
//    import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//    import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//    import com.baomidou.mybatisplus.core.toolkit.Wrappers;
//    import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//    import com.fhzn.commons.toolkit.entity.PageInfo;
//    import com.fhzn.commons.toolkit.entity.PageRequest;
//    import com.fhzn.commons.webapi.entity.WebResponse;
//    import com.fhzn.demo.entity.Test;
//    import com.fhzn.demo.remote.wups.TestClient;
//    import com.fhzn.demo.service.TestService;
//    import com.fhzn.demo.web.converter.TestMapper;
//    import com.fhzn.demo.web.converter.Converters;
//    import com.fhzn.demo.web.interceptor.RequestContext;
//    import com.fhzn.demo.web.request.TestRequest;
//    import com.fhzn.demo.web.request.IdRequest;
//    import com.fhzn.demo.web.vo.TestVO;
//    import io.swagger.v3.oas.annotations.Operation;
//    import io.swagger.v3.oas.annotations.Parameter;
//    import io.swagger.v3.oas.annotations.tags.Tag;
//    import lombok.AccessLevel;
//    import lombok.RequiredArgsConstructor;
//    import org.apache.commons.lang3.StringUtils;
//    import org.springdoc.api.annotations.ParameterObject;
//    import org.springframework.validation.annotation.Validated;
//    import org.springframework.web.bind.annotation.*;
//    import java.lang.reflect.Field;
//    import java.util.Date;
//
//@RestController
//@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
//@RequestMapping("tests")
//@Tag(name = "test")
//
//public class TestController {
//
//    private final TestService testService;
//    private final TestClient testClient;
//
//    @GetMapping("")
//    @Operation(description = "应用列表")
//    public WebResponse<PageInfo<TestVO>> list(@ParameterObject PageRequest request,
//                                              @Parameter(name = "name", description = "目标用户名称") @RequestBody(required = false) Test test) {
//        QueryWrapper<Test> wrapper = Wrappers.query();
//        if (!checkObjAllFieldsIsNull(test)) {
//
//            wrapper.eq("name", test.getName());
//            wrapper.or();
//            wrapper.eq("id", test.getId());
////            wrapper.eq("if_deleted", test.getId());
//        //修改
//        }
//
//
//        Page<Test> page = testService.page(Page.of(request.getPage(), request.getPageSize()), wrapper);
//        PageInfo<TestVO> ret = Converters.convert2page(page, TestMapper::toApplicationVO);
//        return WebResponse.success(ret);
//    }
//
//    @PostMapping("")
//    @Operation(description = "新增应用")
//    public WebResponse<Long> save(@Validated @RequestBody TestRequest request) {
//        Test test = TestMapper.fromRequest(request);
//            test.setCreator(RequestContext.getRequestData().getNickname());
//            test.setModifier(RequestContext.getRequestData().getNickname());
//            test.setCreatedTime(new Date());
//            test.setUpdatedTime(new Date());
//
//        testService.saveOrUpdate(test);
//        return WebResponse.success(test.getId());
//    }
//
//    @PutMapping("")
//    @Operation(description = "修改应用")
//    public WebResponse<Long> Update(@Validated @RequestBody TestRequest request) {
//        Test test = TestMapper.fromRequest(request);
//        test.setModifier(RequestContext.getRequestData().getNickname());
//        testService.saveOrUpdate(test);
//        return WebResponse.success(test.getId());
//    }
//
//    @DeleteMapping("")
//    @Operation(description = "删除应用")
//    public WebResponse<Void> delete(@Validated @RequestBody IdRequest request) {
//        testService.removeById(request.getId());
//        return WebResponse.success();
//    }
//
//
//    @GetMapping("/auth-query")
//    public Object auth() {
//        return testClient.authQuery(null);
//    }
//
//    public boolean checkObjAllFieldsIsNull(Object object) {
//        // 如果对象为null直接返回true
//        if (null == object) {
//            return true;
//        }
//        try {
//            // 挨个获取对象属性值
//            for (Field f : object.getClass().getDeclaredFields()) {
//                f.setAccessible(true);
//                // 如果属性名不为serialVersionUID，有一个属性值不为null，且值不是空字符串，就返回false
//                if (!"serialVersionUID".equals(f.getName()) &&
//                    f.get(object) != null && StringUtils.isNotBlank(f.get(object).toString())) {
//                    return false;
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return true;
//    }
//
//
//}
