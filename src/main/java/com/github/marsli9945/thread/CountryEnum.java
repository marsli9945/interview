package com.github.marsli9945.thread;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum  CountryEnum
{
    ONE(0, "齐国"),
    TWE(2,"楚国"),
    THREE(3,"燕国"),
    FOUR(4,"赵国"),
    FIVE(5,"魏国"),
    SIX(1,"韩国");

    private Integer num;
    private String name;

    public static CountryEnum foreachCountryEnum(int index) {
        CountryEnum[] values = CountryEnum.values();
        for (CountryEnum elem :
                values)
        {
            if (elem.num == index) {
                return elem;
            }
        }
        return null;
    }

}
