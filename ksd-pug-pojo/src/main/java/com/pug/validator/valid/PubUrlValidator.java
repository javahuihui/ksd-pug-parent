package com.pug.validator.valid;

import com.pug.validator.anno.PugURL;
import com.pug.validator.utils.ValidatorUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 飞哥B站地址：https://space.bilibili.com/490711252
 * 记得关注和三连哦！
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2022/2/20$ 21:33$
 */
public class PubUrlValidator implements ConstraintValidator<PugURL, String> {
    @Override
    public boolean isValid(String url, ConstraintValidatorContext constraintValidatorContext) {
        // 1: 如果用户没输入直接返回不校验，因为空的判断交给@NotEmpty去做就行了
        if (null == url || "".equals(url) ) {
            return true;
        }

        // 2：如果校验通过就返回true,否则返回false;
        return ValidatorUtil.isURL(url);
    }

    @Override
    public void initialize(PugURL constraintAnnotation) {
    }
}
