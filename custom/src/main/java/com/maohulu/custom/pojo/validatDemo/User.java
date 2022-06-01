package com.maohulu.custom.pojo.validatDemo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.maohulu.custom.annatation.FlagValidator;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

/**
 * @author huliu
 * @date 9:42 2022/5/31
 */
@Data
public class User {
    @Size(min = 1, max = 10, message = "姓名长度必须为1到10")
    private String name;

    @NotEmpty
    private String firstName;

    @Min(value = 10, message = "年龄最小为10")
    @Max(value = 100, message = "年龄最大为100")
    private Integer age;

    //    @Future()需要是一个将来的时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") // jackon
    private Date birth;

    // 前端传入的flag值必须是1或2或3，否则校验失败
    @FlagValidator(values = "1,2,3")
    private String flag;
}