package com.jcode.excel.domin;

import lombok.Data;

/**
 * @author : mpy
 * @date : 2021/11/4 5:03 下午
 * @description: 每日获取趋势
 *  横向填充Excel
 */
@Data
public class AttendTrendBo {

    //日期 yyyy/MM/dd
    private String date;

    //全国
    private Long total;

    //东区
    private Long east;

    //南区
    private Long south;

    //西区
    private Long west;

    //北区
    private Long north;
}
