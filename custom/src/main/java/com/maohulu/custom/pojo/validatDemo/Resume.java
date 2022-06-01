package com.maohulu.custom.pojo.validatDemo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;


/**
 * @author huliu
 * @date 15:54 2022/5/31
 */
@Data
public class Resume {
    @NotNull(message = "id不能为空", groups = Update.class)
    private Long id;

    @NotNull(message = "名字不能为空", groups = Default.class)
    @Length(min = 4, max = 10, message = "name 长度必须在 {min} - {max} 之间", groups = Default.class)
    private String name;

    @NotNull(message = "年龄不能为空", groups = Default.class)
    @Min(value = 18, message = "年龄不能小于18岁", groups = Default.class)
    private Integer age;

    public interface Default {
    }

    public interface Update {
    }
}