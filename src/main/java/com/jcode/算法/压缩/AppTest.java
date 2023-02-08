package com.jcode.算法.压缩;

import java.io.IOException;
import java.io.StringBufferInputStream;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.zip.DataFormatException;

import org.springframework.objenesis.instantiator.basic.NewInstanceInstantiator;

public class AppTest {

    public static void main(String[] args) throws DataFormatException, IOException {
        String str = "#com.hhxx.seal.common.codec.p808.Protocol.TerminalInfo1003PO{ {\"altitude\":0,\"checksum\":-89,\"dataStatus\":1,\"direction\":0,\"flowValueL\":-1,\"flowValueM\":-1,\"header\":{\"attribute\":{\"distributed\":false,\"encrypted\":false,\"length\":74,\"version\":0},\"id\":4099,\"packetNo\":0,\"securityNo\":18,\"sequenceNo\":39582,\"terminalNo\":\"FFFFAA100734\",\"totalPackets\":0,\"totalSize\":86},\"latitude\":34795268,\"latlng\":2,\"lngitude\":113594064,\"lockStatus\":\"002222222200000000000000000000000000000000000000\",\"mainPowerStatus\":236,\"openStatus\":0,\"powerStatus\":152,\"reserveValue\":0,\"rssi\":24,\"satelliteNum\":15,\"status\":1,\"time\":1659286097276,\"timestamp\":\"220732004816\",\"velocity\":0,\"wheelAngle\":[255,255,255,255,255,255,255,255,255,255,255,255]} }";
        System.err.println(str.length());
        byte[] bs = str.getBytes("utf-8");
        System.err.println(bs.length);
        
        
        long dstartDate =  System.currentTimeMillis();
        for(int i=1;i<=1;i++) {
            byte[] cbs = bzip2Test.compress(bs);

            System.out.println("压缩后\t" + cbs.length);
            
            System.out.println("压缩后字符串\t" + new String(cbs).length());

            System.out.println(new String(cbs,"utf-8"));
            byte[] ubs =bzip2Test.uncompress(cbs);
        
     
            System.out.println("解压后\t" + ubs.length);
            System.out.println(new String(ubs,"utf-8"));
            
        }
        long dendDate =  System.currentTimeMillis();
        System.err.println(dendDate-dstartDate);
//     
////        
//        dstartDate =  System.currentTimeMillis();
//        for(int i=1;i<=1;i++) {
//            String compressStr = GZipTest.compress(str);
//            
//            System.out.println(str.length());
//            System.out.println("压缩后\t" + compressStr.length());
//            System.out.println(compressStr);
//     
//            String uncompressStr =GZipTest.uncompress(compressStr);
//            System.out.println(uncompressStr);
//     
//            System.out.println("解压后\t" + uncompressStr.length());
//        }
//        dendDate =  System.currentTimeMillis();
//        System.err.println(dendDate-dstartDate);
//        
//        
//        
//        long dstartDate =  System.currentTimeMillis();
//        for(int i=1;i<=1;i++) {
//            String compressStr = SnappyTest.compress(str);
//            
//            System.out.println(str.length());
//            System.out.println("压缩后\t" + compressStr.length());
//            System.out.println(compressStr);
//     
//            String uncompressStr =SnappyTest.uncompress(compressStr);
//            System.out.println(uncompressStr);
//     
//            System.out.println("解压后\t" + uncompressStr.length());
//        }
//        long dendDate =  System.currentTimeMillis();
//        System.err.println(dendDate-dstartDate);
    }
}
