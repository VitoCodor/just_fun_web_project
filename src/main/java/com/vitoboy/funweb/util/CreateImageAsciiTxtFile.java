package com.vitoboy.funweb.util;

import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

/**
 * 这个工具类, 是用来实现将一个图片转换成一个ASCII码图(即txt文本)
 * 这个ASCII码图是这个图片的字符表示
 * 即这个ASCII码图整体上会表示为这个图片的大体样子
 *
 * @Author: vito
 * @Date: 2020/10/23 22:55
 * @Version: 1.0
 */
public class CreateImageAsciiTxtFile {

    /**
     * 默认像素与字符替换列表(自己设置的, 可以使用自己的)
     *
     * 设置的原则是, 占用一个字符空间越满的下标越小
     *
     * 默认替换列表不会有空, 即图片里的空白, 在转化为ascii文本后, 不会显示空, 而是"·"
     */
    private static final String[] defaultChars
            = new String[]{"@", "#", "$", "&", "B", "8", "R", "S",
            "W", "N", "A", "a", "e", "s", "t", "x",
            "=","+", "1", "?", "!", ";", ":", "-", "·"};

    /**
     * 可选像素与字符替换列表
     *
     * 图片里的空白, 在转化为ascii文本后, 显示为" "
     */
    private static final String[] emptyBackgroundChars
            = new String[]{"@", "#", "$", "&", "B", "8", "R", "S",
            "W", "A", "a", "e", "s", "t", "x",
            "=","+", "1", "?", "!", ";", ":", "-", "·", " "};

    /**
     * 可选像素与字符替换列表
     *
     * 1024节日专用
     */
    private static final String[] programmingChars
            = new String[]{"4","4","2","2","0","0","x","x","=","="
            ,"+","+","1","1","?","!","!", ";",";", ":", ":", "-", "·","·", " "};

    /**
     * 宽度像素的计算增量, 经过测试发现, 默认值为2更好(360*360的图片下)
     */
    private static final int widthGrow = 2;
    /**
     *  高度像素的计算增量, 经过测试发现, 默认值为4更好(360*360的图片下)
     *  因为图片转文字的原因, 高度的像素计算增量大体相当于宽度像素计算增量的两倍(测试看下来的结果)
     */
    private static final int heightGrow = 4;


    /**
     * 将图片文件转化为ascii文本, 并保存到同图片文件目录下
     * 即: /path/image.png  --生成--> /path/image.txt
     *
     * @param imagePath         图片文件路径
     * @param changeStrings     图片里的像素替换字符列表, 不传会有默认值
     * @param empty             是否将白像素转化为" "
     * @param time              图片放大的倍数, 正数为放大, 负数为缩小
     * @throws Exception        异常
     */
    public static void turnImageToAsciiFile(String imagePath, String[] changeStrings, boolean empty, int time) throws Exception {
        if (imagePath == null || imagePath.isEmpty()) throw new RuntimeException("文件路径不能为空!");
        if (changeStrings == null || changeStrings.length == 0) {
            if (empty) {
                changeStrings = emptyBackgroundChars;
            } else {
                changeStrings = defaultChars;
            }
        }
        // 设置图片与文字文件的放大比例
        int widGrow = widthGrow;
        int heigGrow = heightGrow;
        if (time > 0) {
            widGrow = widthGrow / time;
            heigGrow = heightGrow / time;
        } else if (time < 0){
            widGrow = widthGrow * (-time);
            heigGrow = heightGrow * (-time);
        }
        if (widGrow == 0) widGrow = 1;
        if (heigGrow == 0) heigGrow = 1;
        File imageFile = new File(imagePath);
        BufferedImage bi = null;
        File asciiImageTxt = null;
        FileOutputStream fileOutputStream = null;
        OutputStreamWriter writeBuffer = null;
        try {
            bi = ImageIO.read(imageFile);
            // 获得源图片的目录
            String dirImg = imageFile.getParent();
            System.out.println("new file directory is : " + dirImg);

            // 获得源图片的文件名
            String nameImg = imageFile.getName().substring(0, imageFile.getName().lastIndexOf('.'));
            System.out.println("new file name is : " + nameImg);

            String newFileName = dirImg + File.separator + nameImg + ".txt";
            System.out.println("new full file name is : " + newFileName);

            // 生成新文件的File对象
            asciiImageTxt = new File(newFileName);
            fileOutputStream = new FileOutputStream(asciiImageTxt);
            writeBuffer = new OutputStreamWriter(fileOutputStream);


            int width = bi.getWidth();      // 图片的宽
            int height = bi.getHeight();    // 图片的高
            System.out.println("file name is : " +imagePath+ "\nwidth is : ["+ width +"], height is : ["+ height+"]");

            // 计算每个点的像素权重, 转化成int值, 并定位到特定字符, 同时输出到文件里去
            for (int i = 0; i < height; i+=heigGrow) {
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < width; j+=widGrow) {
                    int sum = 0;
                    int count = 0;
                    for (int k = i; k < i+heigGrow && k < height; k++) {
                        for (int l = j; l < j+widGrow && l < width; l++) {
                            int pixel = bi.getRGB(l, k);
                            count ++;
                            // red int : (pixel & 0xff0000) >> 16
                            // green int : (pixel & 0xff00) >> 8
                            // blue int : (pixel & 0xff)
                            sum += (((pixel & 0xff0000) >> 16) + ((pixel & 0xff00) >> 8) + (pixel & 0xff));
                        }
                    }
                    int index = 0;
                    // 计算像素权重对应的字符数组下标
                    if (sum != 0) index = (int)(sum*1.0/(count*30)) - 1;
                    if (index > 0) {
                        builder.append(changeStrings[index]);
                    } else {
                        builder.append(changeStrings[0]);
                    }
                }
                // 将字符信息写入文件
                writeBuffer.write(builder.toString() + "\n");
            }
            // 清空流
            writeBuffer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {

//        turnImageToAsciiFile("/Users/vito/Pictures/namae.jpeg", null, true, -4);
        turnImageToAsciiFile("/Users/vito/Pictures/20201024.jpeg",
                new String[]{"4","4","2","2","0","0","x","x","=","="
                ,"+","+","1","1","?","!","!", ";",";", ":", ":", "-", "·","·", " "},
                true, -3);
    }

}
