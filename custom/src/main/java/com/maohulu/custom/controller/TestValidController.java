package com.maohulu.custom.controller;

import com.maohulu.custom.pojo.validatDemo.Resume;
import com.maohulu.custom.pojo.validatDemo.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huliu
 * @since 2022/4/14 10:43
 */
@RestController
@Validated // 控制台打印参数校验失败的错误日志，返回前台的是 500
@Valid // @Size @Max 不生效
public class TestValidController {

    /**
     * PathVariable校验
     * 用法是：路径变量:正则表达式。
     * 当请求URI不满足正则表达式时，客户端将收到 404 错误码。不方便的地方是，不能通过捕获异常的方式，向前端返回统一的、自定义格式的响应参数。
     */
    @GetMapping("/path/{group:[0-9]+}/{userid}")
    @ResponseBody
    public String path(@PathVariable("group") String group, @PathVariable("userid") Integer userid) {
        return group + ":" + userid;
    }


    @GetMapping("/validate1")
    @ResponseBody
    public String validate1(
            @Size(min = 1, max = 10, message = "姓名长度必须为1到10") @RequestParam("name") String name,
            @Min(value = 10, message = "年龄最小为10") @Max(value = 100, message = "年龄最大为100") @RequestParam("age") Integer age) {
        return "validate1";
    }

    @PostMapping("/validate2")
    @ResponseBody
    public User validate2(@Valid @RequestBody User user) {
        return user;
    }

    /**
     * 使用 Default 分组进行验证
     */
    @PostMapping("/validate5")
    public String addUser(@Validated(value = Resume.Default.class) @RequestBody Resume resume) {
        return "validate5";
    }

    /**
     * 使用Default、Update分组进行验证
     */
    @PutMapping("/validate6")
    public String updateUser(@Validated(value = {Resume.Update.class, Resume.Default.class}) @RequestBody Resume resume) {
        return "validate6";
    }
}
