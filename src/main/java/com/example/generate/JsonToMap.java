package com.example.generate;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class JsonToMap {
    //将内容以key-value的形式存入map集合
    public static void jsonLoop(Object object, List<List<String>> attrLists) {
        int i=0;
        //用户校验存储属性重复的问题
        Map<String, Object> map = new HashMap<>();
        if (object instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) object;
            for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
                map.put(entry.getKey(), entry.getValue());
            }
        }
        String tableName=null;
        String javaName=null;
        if (i==0){
            //手动输入表名
            Scanner scanner=new Scanner(System.in);
            System.out.println("请输入表名");
            tableName=scanner.next();//输入一个单词，遇到分号则输入终止
        }
        //校验实体类为空或者重复
        boolean checktt = CheckListRepeat.checkRepeat(map, attrLists);
        if (checktt) {
            //自动生成表名
            if (i==0){
                javaName=tableName;
            }else if(false){

            }
            //添加
            String parse = GenerationClasses.parse(map);
            System.out.println(parse);
            i++;
        }
        LoopGenerationObject.entity(map, attrLists);
    }
}
