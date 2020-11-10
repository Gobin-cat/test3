package com.example.demo.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.ClassInfo;
import com.example.demo.entity.Filed;
import org.springframework.util.CollectionUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.*;

public class JsonToObjectUtil {
    //去重
    public static boolean checkRepeat(List<ClassInfo> classInfos, String hashCode) {
        for (int i = 0; i < classInfos.size(); i++){
            String oldHashCode = classInfos.get(i).getHashCode();
            if (oldHashCode.equals(hashCode)){
               return false;
            }
        }
        return true;
    }
    //遍历json
    public static void  analysisJson(JSONObject jsonObject,List<ClassInfo> classInfos,String className){
        Map<String, Object> map = new HashMap<>();
        //转map
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            map.put(entry.getKey(), entry.getValue());
        }
        //迭代器遍历
        Iterator<Map.Entry<String, Object>> entries = map.entrySet().iterator();
        List<Filed> fileds=new ArrayList<>();
        ClassInfo classInfo = new ClassInfo();
        while(entries.hasNext()){
            Map.Entry<String, Object> entry = entries.next();
            Filed filed = new Filed();
            String keyName = entry.getKey();
            filed.setName(keyName);
            String packageName = map.get(keyName).getClass().getTypeName();
            int i = map.get(keyName).getClass().getTypeName().split("\\.").length - 1;
            //typename
            String typeName = map.get(keyName).getClass().getTypeName().split("\\.")[i];
            filed.setPack(packageName);
            Object value = entry.getValue();
            //数据类型判断
            if (value instanceof JSONObject&&((JSONObject) value).size()!=0&&!((JSONObject) value).isEmpty()&&value!=null){
                //包含字段生成hash值并存贮
                String attributeHash = attributeHash((JSONObject) value);
                filed.setType(typeName+"$."+attributeHash);
                fileds.add(filed);
                analysisJson((JSONObject)value,classInfos,keyName);
            }else if(value instanceof JSONArray&&!((JSONArray) value).isEmpty()&&((JSONArray) value).size()!=0&&value!=null){
                Object o = ((JSONArray) value).get(0);
                String attributeHash = attributeHash((JSONObject) o);
                filed.setType(typeName+"$."+attributeHash);
                fileds.add(filed);


                    analysisJson((JSONObject)o,classInfos,keyName);
            }else {
                filed.setType(typeName);
                fileds.add(filed);
            }
        }
        //排序
        List<String> sortFileds = new ArrayList();
        for(int i=0;i<fileds.size();i++){
            sortFileds.add(fileds.get(i).getName());
        }
        Collections.sort(sortFileds);
        //生成hash值
        String hashcode = toMD5Hash(sortFileds);
        //去重
        if (checkRepeat(classInfos, hashcode)){
            //没有相同的进行添加
            classInfo.setFileList(fileds);
            classInfo.setHashCode(String.valueOf(hashcode));
            classInfo.setClassname("A"+String.valueOf(hashcode));
            //classInfo.setClassname(className);
            classInfos.add(classInfo);
        }
    }
    //所属字段生成hash值
    public static String attributeHash(JSONObject jsonObject){
        Map<String, Object> map = new HashMap<>();
        //转map
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            map.put(entry.getKey(), entry.getValue());
        }
        List<String> sortFileds = new ArrayList();
        for (String keyName:map.keySet()){
            sortFileds.add(keyName);
        }
        Collections.sort(sortFileds);
        //生成hash值
        String hashcode = toMD5Hash(sortFileds);
        return hashcode;
    }
//生成md5hash值2
    public static String toMD5Hash(List<String> sortFileds) {
        //参数校验
        if ( CollectionUtils.isEmpty(sortFileds)){
            return  null;
        }
        String input ="";
        for (int i=0;i<sortFileds.size();i++){
            input=input+sortFileds.get(i);
        }
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());
            byte[] digest = md.digest();
            BigInteger bi = new BigInteger(1, digest);
            String hashText = bi.toString(16);
            while (hashText.length() < 32) {
                hashText = "0" + hashText;
            }
            return hashText;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
