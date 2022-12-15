package com.jcode.excel.test;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.enums.WriteDirectionEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.jcode.excel.domin.AttendTrendBo;
import com.jcode.excel.domin.BDGatherBo;
import com.jcode.excel.domin.BDStatisticsBo;
import com.jcode.excel.domin.WriteData1;
import com.jcode.excel.utils.TestFileUtil;

public class WriteApp {


	 public static void main( String[] args ) {
		 complexWithTable();
		 complexFillWithTable();
	 }

    
    public static void complexWithTable() {
        List<WriteData1> list = Lists.newArrayList();
        String templateFileName = "D:/excelTemp/Excel_demo "+ System.currentTimeMillis() + ".xlsx";
        for(int i=0;i<=10;i++) {
        	WriteData1 wData1 = new WriteData1();
        	wData1.setArea("111");
        	wData1.setBd("111");
        	list.add(wData1);
        }
        EasyExcel.write(templateFileName,WriteData1.class).
                sheet("模板").
                doWrite(list);
    }
   
    public static void complexFillWithTable() {
        // 模板注意 用{} 来表示你要用的变量 如果本来就有"{","}" 特殊字符 用"\{","\}"代替
        // {} 代表普通变量 {.} 代表是list的变量
        // 这里模板 删除了list以后的数据，也就是统计的这一行
        String templateFileName =  "D:/excelTemp/Excel_模板.xlsx";
        String fileName = "D:/excelTemp/complexFillWithTable" + System.currentTimeMillis() + ".xlsx";
        ExcelWriter excelWriter = EasyExcel.write(fileName).withTemplate(templateFileName).build();
        WriteSheet writeSheet = EasyExcel.writerSheet(0).build();
        // 直接写入数据
        List<BDStatisticsBo> list = getBDStatisticsBo();
        // 这里注意 入参用了forceNewRow 代表在写入list的时候不管list下面有没有空行 都会创建一行，然后下面的数据往后移动。默认 是false，会直接使用下一行，如果没有则创建。
        // forceNewRow 如果设置了true,有个缺点 就是他会把所有的数据都放到内存了，所以慎用
        // 简单的说 如果你的模板有list,且list不是最后一行，下面还有数据需要填充 就必须设置 forceNewRow=true 但是这个就会把所有数据放到内存 会很耗内存
        // 如果数据量大 list不是最后一行 参照下一个
        FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
        excelWriter.fill(list, fillConfig, writeSheet);
        // 写入list之前的数据
        BDGatherBo bo = getBDGatherBo();
        Map map = JSONObject.parseObject(JSONObject.toJSONString(bo), Map.class);
        excelWriter.fill(map, writeSheet);

        WriteSheet writeSheet2 = EasyExcel.writerSheet(1).build();
        FillConfig fillConfig2 = FillConfig.builder().direction(WriteDirectionEnum.HORIZONTAL).build();
        List<AttendTrendBo> list2 = getAttendTrendBo();
        excelWriter.fill(list2, fillConfig2, writeSheet2);
        excelWriter.finish();
    }

    private static List<AttendTrendBo> getAttendTrendBo() {
        List<AttendTrendBo> list2 = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            AttendTrendBo item = new AttendTrendBo();
            item.setDate("2021/11/" + i);
            item.setTotal(i + 10L);
            item.setEast(i + 1L);
            item.setSouth(i + 2L);
            item.setWest(i + 3L);
            item.setNorth(i + 4L);
            list2.add(item);
        }
        return list2;
    }

    private static BDGatherBo getBDGatherBo() {
        BDGatherBo bo = new BDGatherBo();
        bo.setMonth("11");
        bo.setTotalSign(100L);
        bo.setSumType2Sign(40L);
        bo.setSumType1Sign(30L);
        bo.setSumType7Sign(30L);

        bo.setTotalRes(150L);
        bo.setSumType2Res(50L);
        bo.setSumType1Res(30L);
        bo.setSumType7Res(70L);

        bo.setTotalAttend(200L);
        bo.setSumType2Attend(10L);
        bo.setSumType1Attend(50L);
        bo.setSumType7Attend(50L);
        return bo;
    }

    private static List<BDStatisticsBo> getBDStatisticsBo() {
        List<BDStatisticsBo> list = Lists.newArrayList();
        for (int i = 0; i < 3; i++) {
            BDStatisticsBo writeData2 = new BDStatisticsBo();
            writeData2.setIndex(i);
            writeData2.setArea("南区" + i);
            writeData2.setBd("BD" + i);

            writeData2.setSumSign(i + 10L);
            writeData2.setSumRes(i + 10L);
            writeData2.setSumAttend(i + 10L);

            writeData2.setType1Sign(i + 1L);
            writeData2.setType1Res(i + 1L);
            writeData2.setType1Attend(i + 1L);

            writeData2.setType2Sign(i + 2L);
            writeData2.setType2Res(i + 2L);
            writeData2.setType2Attend(i + 2L);

            writeData2.setType7Sign(i + 3L);
            writeData2.setType7Res(i + 3L);
            writeData2.setType7Attend(i + 3L);

            list.add(writeData2);
        }
        return list;
    }
}
