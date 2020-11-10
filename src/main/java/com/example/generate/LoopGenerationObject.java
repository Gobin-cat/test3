package com.example.generate;

import com.alibaba.fastjson.JSONArray;

import java.util.List;
import java.util.Map;

public class LoopGenerationObject {
    public static void entity(Map<String, Object> map, List<List<String>> attrLists){
        for(String s:map.keySet()){
            Object o = map.get(s);
            if(o instanceof String||o instanceof Boolean){
            }else if (o instanceof JSONArray){
                JSONArray arrays = (JSONArray) o;
                if (arrays!=null||!arrays.isEmpty()){
                    Object array = arrays.get(0);
                    JsonToMap.jsonLoop(array,  attrLists);
                }
            }else{
                JsonToMap.jsonLoop(map.get(s),  attrLists);
            }
        }
    }
}
