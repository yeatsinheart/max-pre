package com.max.client.utils;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by ken on 2018/3/2.
 */
public class RandomValidateCodeUtil {
    public static final String RANDOMCODEKEY = "validateCodeKey";//放到session中的key
    public static final String RANDOMCODE = "validateCode";//放到session中的key
    private static final Logger logger = LoggerFactory.getLogger(RandomValidateCodeUtil.class);
    //private String randString = "0123456789";//随机产生只有数字的字符串 private String
    //private String randString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";//随机产生只有字母的字符串
    private static String randString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";//随机产生数字与字母组合的字符串
    private static Random random = new Random();
    private int width = 95;// 图片宽
    private int height = 25;// 图片高
    private int lineSize = 40;// 干扰线数量
    private int stringNum = 4;// 随机产生字符数量

    /**
     * 转换BufferedImage 数据为byte数组
     *
     * @param bImage Image对象
     * @param format image格式字符串.如"gif","png"
     * @return byte数组
     */
    public static String imageToBytes(BufferedImage bImage, String format) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            ImageIO.write(bImage, format, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //return out.toByteArray();
        return Base64.encodeBase64String(out.toByteArray());
    }

    /**
     * 生成随机图片
     */
    public static String getRandcodeString(int len) {
        // 绘制随机字符
        String randomString = "";
        for (int i = 1; i <= len; i++) {
            String rand = String.valueOf(getRandomString(random.nextInt(randString.length())));
            randomString += rand;
        }
        logger.info(randomString);
        return randomString;
    }

    public static void main(String[] args) {
        getRandcodeString(5);
    }

    /**
     * 获取随机的字符
     */
    public static String getRandomString(int num) {
        return String.valueOf(randString.charAt(num));
    }

    /**
     * 获得字体
     */
    private Font getFont() {
        return new Font("Fixedsys", Font.CENTER_BASELINE, 18);
    }

    /**
     * 获得颜色
     */
    private Color getRandColor(int fc, int bc) {
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc - 16);
        int g = fc + random.nextInt(bc - fc - 14);
        int b = fc + random.nextInt(bc - fc - 18);
        return new Color(r, g, b);
    }

    /**
     * 生成随机图片
     */
    public HashMap<String, String> getRandcode(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        // BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();// 产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
        g.fillRect(0, 0, width, height);//图片大小
        g.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18));//字体大小
        g.setColor(getRandColor(110, 133));//字体颜色
        // 绘制干扰线
        for (int i = 0; i <= lineSize; i++) {
            drowLine(g);
        }
        // 绘制随机字符
        String randomString = "";
        for (int i = 1; i <= stringNum; i++) {
            randomString = drowString(g, randomString, i);
        }
        logger.info(randomString);
        //将生成的随机字符串保存到session中
        session.removeAttribute(RANDOMCODEKEY);
        session.setAttribute(RANDOMCODEKEY, randomString);
        g.dispose();
        HashMap<String, String> validateMap = new HashMap<String, String>();
        try {
            // 将内存中的图片通过流动形式输出到客户端
            //ImageIO.write(image, "JPEG", response.getOutputStream());
            validateMap.put(RANDOMCODEKEY, imageToBytes(image, "JPEG"));
            validateMap.put(RANDOMCODE, randomString);
        } catch (Exception e) {
            logger.error("将内存中的图片通过流动形式输出到客户端失败>>>>   ", e);
        }

        return validateMap;
    }

    /**
     * 生成小写的随机数字字母组合
     *
     * @param len
     * @return
     */
    public String getRandomLowerStr(int len) {
        return getRandcodeString(len).toLowerCase();
    }

    /**
     * 生成4位随机数字
     *
     * @return
     */
    public String getRandomNumber() {
        return (int) ((Math.random() * 9 + 1) * 1000) + "";
    }

    /**
     * 绘制字符串
     */
    private String drowString(Graphics g, String randomString, int i) {
        g.setFont(getFont());
        g.setColor(new Color(random.nextInt(101), random.nextInt(111), random.nextInt(121)));
        String rand = String.valueOf(getRandomString(random.nextInt(randString.length())));
        randomString += rand;
        g.translate(random.nextInt(3), random.nextInt(3));
        g.drawString(rand, 13 * i, 16);
        return randomString;
    }

    /**
     * 绘制干扰线
     */
    private void drowLine(Graphics g) {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = random.nextInt(13);
        int yl = random.nextInt(15);
        g.drawLine(x, y, x + xl, y + yl);
    }
}
