package com.fancz.demo;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.Iterator;

public class JsonTest {
    public static String getJson() {
        //System.out.println(sdf.format(new Date())+"===开始");
        String jsonStr = "";
        try {
            File file = new File("C:\\Users\\HP\\Desktop\\测试\\test2.json");
            FileReader fileReader = new FileReader(file);
            Reader reader = new InputStreamReader(new FileInputStream(file),"Utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            //System.out.println(sdf.format(new Date())+"===结束");
            return jsonStr;
        } catch (Exception e) {
            return null;
        }
    }
    public static void main(String[] args) {
        System.out.println(getJson());
    }


}
