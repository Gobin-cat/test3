package com.example.generate;

import com.google.gson.JsonArray;
import lombok.Data;

import java.util.Date;
import java.util.Map;

public class GenerationClasses {
    /**
     * 功能：生成实体类主体代码
     * @param colnames
     * @param colTypes
     * @param colSizes
     * @return
     */
    private static String authorName = "zy";//作者名字
    private static String javaName = "test"; //.java文件名
    private static String packagePath = "com.example.demo1.entity"; //.java文件名
    private static boolean f_util = true;
    private static boolean f_sql =false;
    public static String parse(Map<String, Object> map) {
        StringBuffer sb = new StringBuffer();
        //导入包名
        sb.append("package " + packagePath + ";\r\n");
        //判断是否导入工具包
        if(f_util){
            sb.append("import java.util.Date;\r\n");
        }
        if(f_sql){
            sb.append("import java.sql.*;\r\n");
        }
        sb.append("import lombok.Data;\r\n");
        sb.append("\r\n");
        //注释部分
        sb.append("   /**\r\n");
        sb.append("    * "+ javaName +" 实体类\r\n");
        sb.append("    * "+new Date()+" "+authorName+"\r\n");
        sb.append("    */ \r\n");
        //实体部分
        sb.append("\r\n\r\n@Data{\r\n");
        sb.append("\r\n\r\npublic class " + javaName + "{\r\n");
        processAllAttrs(sb,map);//属性
        processAllMethod(sb,map);//get set方法
       // processAllConstructor(sb,map); //生成有参和无参构造方法
        sb.append("}\r\n");

        return sb.toString();
    }
    /**
     * 功能：生成所有属性
     * @param
     */
    private static void processAllAttrs(StringBuffer sb,Map<String, Object> map) {
        for(String s:map.keySet()){
            int i = map.get(s).getClass().getTypeName().split("\\.").length - 1;
            String typeName = map.get(s).getClass().getTypeName().split("\\.")[i];
            if (typeName.equals("JSONArray")||typeName.equals("JSONObject")){
                sb.append("\tprivate String "+" "+ s + ";\r\n");
            }else {
                if(!typeName.equals("Boolean")){
                    sb.append("\tprivate String "+" "+ s + ";\r\n");
                }else{
                    sb.append("\tprivate "+typeName+" "+ s + ";\r\n");
                }
            }


        }
        //换行
        sb.append("\r\n");
    }
    /**
     * 功能：生成所有方法
     * @param sb
     */
    private static void processAllMethod(StringBuffer sb,Map<String, Object> map) {
        for(String s:map.keySet()){
            //生成set方法
            sb.append("\tpublic void set" +s+ "(" + "String " +
                    s+ "){\r\n");
            sb.append("\t\tthis." +s+ "=" +s+ ";\r\n");
            sb.append("\t}\r\n\r\n");
            //生成get方法
            sb.append("\tpublic String get" +s+ "(){\r\n");
            sb.append("\t\treturn " +  s + ";\r\n");
            sb.append("\t}\r\n\r\n");
        }

    }
//    /**
//     * 功能：生成无参和有参构造方法
//     * @param sb
//     */
//    private static void processAllConstructor(StringBuffer sb,Map<String, Object> map){
//        //生成无参构造方法
//        sb.append("\tpublic " + javaName + "(){\r\n");
//        sb.append("\t\tsuper();\r\n");
//        sb.append("\t}\r\n\r\n");
//        //生成有参构造函数
//        sb.append("\tpublic " + javaName + "(");
//        //函数内容
//        for(String s:map.keySet()){
//            sb.append("\t\tthis." + s + " = " + s + ";\r\n");
//        }
//        sb.append("\t}");
//        //换行
//        sb.append("\r\n");
//    }
}
