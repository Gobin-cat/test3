package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.ClassInfo;
import com.example.demo.util.*;
import org.springframework.stereotype.Service;
import java.io.*;
import java.util.*;


@Service
public class AnalyzerImpl implements Analyzer{
    @Override
    public List<ClassInfo> readJson(String path) {
        String jsonStr = "";
        try {
            File file = new File(path);
            Reader reader = new InputStreamReader(new FileInputStream(file),"Utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            reader.close();
            jsonStr = sb.toString();
            JSONObject jsonObject =JSONObject.parseObject(jsonStr);
            //调用json-map方法
            List<ClassInfo> classInfos =new ArrayList<>();
            JsonToObjectUtil.analysisJson(jsonObject,classInfos,"class1");
            return classInfos;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



}
