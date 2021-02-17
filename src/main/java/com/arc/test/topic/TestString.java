package com.arc.test.topic;

import java.io.UnsupportedEncodingException;

/**
 * @author 叶超
 * @since 2019/8/20 20:08
 */
public class TestString {



    //所以：以非UTF-8编码编码出的字节数组，一旦以UTF-8进行解码，通常这是一条不归路，再尝试将解码出的字符以UTF-8进行编码，也无法还原之前的字节数组。
    //
    //相反地，其他的固定长度编码几乎都可以顺利还原。

    //这其实和UTF-8独特的编码方式有关，由于UTF-8需要对unicode字符进行编码，unicode字符集是一个几乎支持所有字符的字符集，为了表示这么庞大的字符集，UTF-8可能需要更多的二进制位来表示一个字符，同时为了不致使UTF-8编码太占存储空间，根据二八定律，UTF-8采用了一种可变长的编码方式，即将常用的字符编码成较短的序列，而不常用的字符用较长的序列表示，这样让编码占用更少存储空间的同时也保证了对庞大字符集的支持。
    //
    //正式由于UTF-8采用的这种特别的变长编码方式，这一点和其他的编码很不一样。比如GBK固定用两个字节来表示汉字，一个字节来表示英文和其他符号。
    public static void main(String[] args) throws UnsupportedEncodingException {
        String srcString = "我们是中国人";
        byte[] GbkBytes = srcString.getBytes("GBK");
        System.out.println("GbkBytes.length:" + GbkBytes.length);
        byte[] UtfBytes = srcString.getBytes("UTF-8");
        System.out.println("UtfBytes.length:" + UtfBytes.length);
        String s;
        for (int i = 0; i < srcString.length(); i++) {
            s = Character.valueOf(srcString.charAt(i)).toString();
            System.out.println(s + ":" + s.getBytes().length);
        }


//        new ThreadPoolExecutor();
    }
//    public static void main(String[] args) throws UnsupportedEncodingException {
//        String srcString = "我们是中国人";
//
//        System.out.println(new String("我们是中国人".getBytes("UTF-8"), "GBK"));
//
//
//        System.out.println(srcString);
//
//        String gbk2UtfString = new String(srcString.getBytes("GBK"), "UTF-8");
//        System.out.println("GBK转换成UTF-8：" + gbk2UtfString);
//        String gbk2Utf2GbkString = new String(gbk2UtfString.getBytes("UTF-8"), "GBK");
//        System.out.println("GBK转换成UTF-8再转成GBK：" + gbk2Utf2GbkString);
//    }
}
