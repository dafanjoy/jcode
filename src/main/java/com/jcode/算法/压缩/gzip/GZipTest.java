package com.jcode.算法.压缩.gzip;
 
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.zip.DataFormatException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
 
public class GZipTest {
    public static String compress(String str) {
        if (str == null || str.trim().length() == 0) {
            return null;
        }
 
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             GZIPOutputStream gzip = new GZIPOutputStream(out)) {
            gzip.write(str.getBytes("utf-8"));
            gzip.close();
 
            return new String(out.toByteArray(), "iso-8859-1");
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
 
    }
 
    public static String uncompress(String str) {
        if (str == null || str.trim().length() == 0) {
            return null;
        }
 
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes("iso-8859-1"))){
            GZIPInputStream ungzip = new GZIPInputStream(in);
            byte[] buffer = new byte[2048];
            int n;
            while ((n = ungzip.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
 
            return new String(out.toByteArray(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
 
    }
}