package com.example.generate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
//判断list中生成的类是否重复
public class CheckListRepeat {
    public static boolean  checkRepeat(Map<String, Object> map, List<List<String>> attrLists) {
        List<String> attributeList = new ArrayList();
        for(String s:map.keySet()){
            attributeList.add(s);
        }

        Collections.sort(attributeList);

        if(attributeList!=null&&!attributeList.isEmpty()){
            if(attrLists!=null&&!attrLists.isEmpty()){

                for(List<String> attrListsone:attrLists){
                    Collections.sort(attrListsone);

                    if (attrListsone.equals(attributeList)){
                        return false;
                    }
                }


            }

        }
        attrLists.add(attributeList);
        return true;
    }
}
