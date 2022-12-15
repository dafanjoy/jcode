package com.jcode.excel.domin;

import lombok.Data;

/**
 * @author : mpy
 * @date : 2021/11/4 4:45 下午
 * @description: BD统计数据汇总
 */
@Data
public class BDGatherBo {

    //月份
    private String month;

    /**
     * 累计进线
     */
    private Long totalSign;
    private Long sumType2Sign;
    private Long sumType1Sign;
    private Long sumType7Sign;

    /**
     * 待激活量级
     */
    private Long totalRes;
    private Long sumType2Res;
    private Long sumType1Res;
    private Long sumType7Res;

    /**
     * 今日新增获客
     */
    private Long totalAttend;
    private Long sumType2Attend;
    private Long sumType1Attend;
    private Long sumType7Attend;


}
