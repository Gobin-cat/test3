package com.example.generate;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadJson {
    //读取文件
    public static void main(String[] args){
        final String jsonPath = "C:\\Users\\HP\\Desktop\\测试\\test2.json";//指定实体生成所在包的路径
        String jsonStr = "";
        try {
            File file = new File(jsonPath);
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
            List<List<String>> attrLists = new ArrayList<List<String>>();
            JSONObject jsonObject =JSONObject.parseObject(jsonStr);
            //调用方法将json存入map
            JsonToMap.jsonLoop(jsonObject,attrLists);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
