package com.quanmin.paresfile.util;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletInputStream;
import java.io.*;

/**
 * IO操作帮助类
 *
 * @author quanmin
 * @since 2020-07-10 8:37
 */
@Slf4j
public class IOUtils {
    /**
     * 通过输入流获取流中的字符串
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static String getStrByInputStream(ServletInputStream inputStream) throws IOException {
        log.info("正在通过流获取数据！");
        StringBuilder str = new StringBuilder();
        byte[] buff = new byte[1000];
        int len;
        while ((len = inputStream.read(buff)) != -1) {
            str.append(new String(buff, 0, len));
        }
        log.info("通过流获取数据结束！");
        return str.toString();
    }

    /**
     * base64转pdf
     *
     * @param base64Content base64字符串
     * @param filePath      输出文件路径
     */
    public static void base64StringToPdf(String base64Content, String filePath) {
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;

        try {
            byte[] bytes = Base64.decode(base64Content);//base64编码内容转换为字节数组
            ByteArrayInputStream byteInputStream = new ByteArrayInputStream(bytes);
            bis = new BufferedInputStream(byteInputStream);
            File file = new File(filePath);
            File path = file.getParentFile();
            if (!path.exists()) {
                path.mkdirs();
            }
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);

            byte[] buffer = new byte[1024];
            int length = bis.read(buffer);
            while (length != -1) {
                bos.write(buffer, 0, length);
                length = bis.read(buffer);
            }
            bos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (fos != null) {
                    fos.close();
                }
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
