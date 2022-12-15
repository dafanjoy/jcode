package com.jcode.excel.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum LandingTypeEnum {


    MOON(1, "探月"),

    SMALL_ROCKET(2, "小火箭"),

    DEEP_SKY(4, "深空"),

    SMALL_ROCKET_PUBLIC(5, "小火箭公开课"),

    MOON_PUBLIC(6, "探月公开课"),

    PYTHON(7, "Python"),

    ALL_B2C_TYPE(3, "通用")
    ;

    private final Integer id;
    private final String desc;

    public static Optional<LandingTypeEnum> of(Integer id) {
        if (id == null) {
            return Optional.empty();
        }
        return Arrays.stream(values()).filter(it -> it.id.equals(id)).findFirst();
    }

    public static Boolean isPublic(Integer landingType) {
        Assert.notNull(landingType, "landingType couldn't be null");
        return landingType.equals(MOON_PUBLIC.getId()) || landingType.equals(SMALL_ROCKET_PUBLIC.getId());
    }
}
