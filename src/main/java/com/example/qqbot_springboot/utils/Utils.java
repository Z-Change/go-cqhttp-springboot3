package com.example.qqbot_springboot.utils;

import net.sourceforge.pinyin4j.PinyinHelper;

public class Utils {
    /**
     * 汉字转首字母
     * @param str 语句
     * @return
     */
    public String getPinYinHeadChar(String str) {

        StringBuilder convert = new StringBuilder();
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                convert.append(pinyinArray[0].charAt(0));
            } else {
                convert.append(word);
            }
        }
        return convert.toString().toUpperCase();
    }

    /**
     * 获取当前项目所在绝对路径
     * @return
     */
    public String getpath(){
        String fs = System.getProperty("user.dir");
        if(fs.contains("\\"))  fs=fs.replaceAll("\\\\","/");
        if(fs.contains("file:/")) fs=fs.substring(6);
        //System.out.println(fs);
        return fs;
    }



}
