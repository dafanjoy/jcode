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
        byte[] bs = str.getBytes("utf-8");
        byte[] cbs = null;
        byte[] ubs = null;
        System.err.println("压缩前字节长度\t"+bs.length);
        
        
        long dstartDate =  System.currentTimeMillis();
        for(int i=1;i<=100000;i++) {

               
            cbs = DeflaterTest.compress(bs);
            ubs =DeflaterTest.uncompress(cbs);

            
        }
        long dendDate =  System.currentTimeMillis();
        System.out.println("耗时\t"+(dendDate-dstartDate));
        System.out.println("压缩后字节长度\t" + cbs.length);
        System.out.println("压缩率\t" + (float)cbs.length/(float)bs.length);
        System.out.println(new String(ubs,"utf-8"));
        
    }
}
