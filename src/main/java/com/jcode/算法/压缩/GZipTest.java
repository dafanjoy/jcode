package com.jcode.算法.压缩;
 
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.zip.DataFormatException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
 
public class GZipTest {
    public static byte[] compress(byte[] inBytes) { 
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             GZIPOutputStream gzip = new GZIPOutputStream(out)) {
            gzip.write(inBytes);
            gzip.close();
 
            return out.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return inBytes;
        }
 
    }
 
    public static byte[] uncompress(byte[] inBytes) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             ByteArrayInputStream in = new ByteArrayInputStream(inBytes)){
            GZIPInputStream ungzip = new GZIPInputStream(in);
            byte[] buffer = new byte[2048];
            int n;
            while ((n = ungzip.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
 
            return out.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return inBytes;
        }
 
    }
}