package com.chinaunicom.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class SampleTest1103 {
    private String packageOutPath = "com.hcj.test";//指定实体生成所在包的路径
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
            List<List<String>> attrLists = new ArrayList<>();
            JSONObject jsonObject =JSONObject.parseObject(jsonStr);
            //调用方法将json存入map
            jsonLoop(jsonObject,attrLists,"id");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //将内容以key-value的形式存入map集合
    public static void jsonLoop(Object object, List<List<String>> attrLists,String keyName) {
        //用户校验存储属性重复的问题
        Map<String, Object> map = new ConcurrentHashMap<>();
        if (object instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) object;
            for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
                map.put(entry.getKey(), entry.getValue());
            }
        }
        //校验实体类为空或者重复
        boolean checktt = checkRepeat(map, attrLists);
        if (checktt) {
            //添加
            String parse = parse(map,keyName);
            System.out.println(parse);
        }
        entity(map, attrLists,keyName);
    }
    //    //字段生成hashCode
//    public  static int toHash(List<Filed> fileds){
//        if(fileds.size()==0||fileds==null||fileds.isEmpty()){
//            return 0;
//        }
//        String filedString=null;
//        for (int i=0;i<fileds.size();i++){
//            filedString=filedString+fileds.get(i);
//        }
//        int arraySize = 11113; // 数组大小一般取质数
//        int hashCode = 0;
//        for (int i = 0; i < filedString.length(); i++) { // 从字符串的左边开始计算
//            int letterValue = filedString.charAt(i) - 96;// 将获取到的字符串转换成数字，比如a的码值是97，则97-96=1
//            // 就代表a的值，同理b=2；
//            hashCode = ((hashCode << 5) + letterValue) % arraySize;// 防止编码溢出，对每步结果都进行取模运算
//        }
//        return hashCode;
//    }



    //判断list中生成的类是否重复
    public static boolean  checkRepeat(Map<String, Object> map,List<List<String>> attrLists) {
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

    public static void entity(Map<String, Object> map, List<List<String>> attrLists,String keyName){
        List<String> list = new ArrayList<>();
        for(String s:map.keySet()){
            list.add(s);
            Object o = map.get(s);
            if(o instanceof String||o instanceof Boolean){
            }else if (o instanceof JSONArray){
                JSONArray arrays = (JSONArray) o;
                if (arrays!=null||!arrays.isEmpty()){
                    Object array = arrays.get(0);
                    jsonLoop(array, attrLists,s);
                }
            }else{
                jsonLoop(map.get(s),  attrLists,s);
            }
        }
    }
//    //判断父子类关系
//    public static void judgeRelationship(List<String> list,Map<String, Object> map){
//        List<String> sonList = new ArrayList<>();
//        for(String s:map.keySet()){
//           if(map.get(s) instanceof  JSONObject||map.get(s) )
//        }
//        int m=0;
//        for (int i=0;i<list.size();i++){
//            for (int j=0;j<sonList.size();j++){
//                if (list.get(i)==sonList.get(j)){
//
//                }
//            }
//        }
//
//    }
    /**
     * 功能：生成实体类主体代码
     * @param colnames
     * @param colTypes
     * @param colSizes
     * @return
     */
    private static String authorName = "zy";//作者名字
    //private static String tablename = "Test";//表名
    private static String javaName = "test"; //.java文件名
    private static String packagePath = "com.example.demo1.entity"; //.java文件名
    private static boolean f_util = true;
    private static boolean f_sql =false;
    private static String parse(Map<String, Object> map,String keyName) {
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

        sb.append("\r\n");
        //注释部分
        sb.append("   /**\r\n");
        sb.append("    * "+ javaName +" 实体类\r\n");
        sb.append("    * "+new Date()+" "+authorName+"\r\n");
        sb.append("    */ \r\n");
        //实体部分
        sb.append("\r\n\r\npublic class " + javaName + "{\r\n");
        processAllAttrs(sb,map,keyName);//属性
        //processAllMethod(sb,map);//get set方法
        //processAllConstructor(sb,map); //生成有参和无参构造方法
        sb.append("}\r\n");

        return sb.toString();
    }
    /**
     * 功能：生成所有属性
     * @param
     */
    private static void processAllAttrs(StringBuffer sb,Map<String, Object> map,String keyName) {
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
        //sb.append("\tprivate String "+" "+ keyName+"-parent"+ ";\r\n");

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
    /**
     * 功能：生成无参和有参构造方法
     * @param sb
     */
    private static void processAllConstructor(StringBuffer sb,Map<String, Object> map){
        //生成无参构造方法
        sb.append("\tpublic " + javaName + "(){\r\n");
        sb.append("\t\tsuper();\r\n");
        sb.append("\t}\r\n\r\n");
        //生成有参构造函数
        sb.append("\tpublic " + javaName + "(");
//        for (int i = 0; i <  attribute.size(); i++) {
//            if(i == attribute.size() - 1){
//                sb.append(sqlType2JavaType(colTypes[i].toLowerCase()) + " " + colnames[i].toLowerCase() + "){\r\n");
//            }else{
//                sb.append(sqlType2JavaType(colTypes[i].toLowerCase()) + " "  + colnames[i].toLowerCase() + ", ");
//            }
//        }
        //函数内容
        for(String s:map.keySet()){
            sb.append("\t\tthis." + s + " = " + s + ";\r\n");
        }
        sb.append("\t}");
        //换行
        sb.append("\r\n");
    }


}