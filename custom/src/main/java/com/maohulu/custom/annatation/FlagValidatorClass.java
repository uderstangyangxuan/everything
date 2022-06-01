package com.maohulu.custom.annatation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @author huliu
 * @date 10:19 2022/5/31
 */
public class FlagValidatorClass implements ConstraintValidator<FlagValidator, Object> {
    /**
     * FlagValidator注解规定的那些有效值
     */
    private String values;

    @Override
    public void initialize(FlagValidator flagValidator) {
        this.values = flagValidator.values();
    }

    /**
     * 用户输入的值，必须是FlagValidator注解规定的那些值其中之一。
     * 否则，校验不通过。
     *
     * @param value 用户输入的值，如从前端传入的某个值
     */
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        // 切割获取值
        String[] valueArray = values.split(",");
        boolean isFlag = false;

        for (String s : valueArray) {
            // 存在一致就跳出循环
            if (s.equals(value)) {
                isFlag = true;
                break;
            }
        }
        return isFlag;
    }
}