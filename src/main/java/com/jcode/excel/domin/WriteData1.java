package com.jcode.excel.domin;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;

import lombok.Data;

/**
 * @author : mpy
 * @date : 2021/11/4 2:10 下午
 * @description: 复杂表头
 */
@Data
public class WriteData1 {

	@ColumnWidth(20) // 定义列宽
	@ExcelProperty(value = {"列1"}, index = 0)
    private Integer index;

	@ColumnWidth(20) // 定义列宽
	@ExcelProperty(value = {"列2"}, index = 1)
    private String area;

    @ExcelProperty("BD")
    private String bd;

    @ExcelProperty("总计")
    private Long sumSign;

    @ExcelProperty("小火箭 ")
    private Long type2Sign;

    @ExcelProperty("探月")
    private Long type1Sign;

    @ExcelProperty("python")
    private Long type7Sign;


    @ExcelProperty("总计")
    private Long sumRes;

    @ExcelProperty("小火箭 ")
    private Long type2Res;

    @ExcelProperty("探月")
    private Long type1Res;

    @ExcelProperty("python")
    private Long type7Res;

    @ExcelProperty("总计")
    private Long sumAttend;

    @ExcelProperty("小火箭 ")
    private Long type2Attend;

    @ExcelProperty("探月")
    private Long type1Attend;

    @ExcelProperty("python")
    private Long type7Attend;
}
