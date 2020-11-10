package com.example.demo1;
import com.alibaba.fastjson.JSONObject;
import java.io.*;
import java.util.*;
public class SampleTest {
    private String packageOutPath = "com.hcj.test";//指定实体生成所在包的路径
    //将内容以key-value的形式存入map集合
    public static void jsonLoop(Object object) {
        Map<String, Object> map = new HashMap<>();
        if (object instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) object;
            for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
                map.put(entry.getKey(), entry.getValue());
            }
        }
        //添加
        String parse = parse(map);
        System.out.println(parse);
        List<Map<String,Object>> list = new ArrayList<>();
        list.add(map);
        int m=0;
        int n=0;
        List<String> list1 = new ArrayList<>();
        for (int i=0;i<list.size();i++){
            Map<String, Object> oldMap = list.get(i);
            for (String oldKey : oldMap.keySet()) {
                    list1.add(oldKey);
            }
        }
    }
    public static void entity(Map<String, Object> map){
        for(String s:map.keySet()){
            Object o = map.get(s);
            if(o instanceof String){
                System.out.println(map.get(s));
            }else {
                jsonLoop(map.get(s));
            }
        }
    }
    //读取文件
    public static void main(String[] args){
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
            JSONObject jsonObject =JSONObject.parseObject(jsonStr);
            //调用方法将json存入map
            jsonLoop(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 功能：生成实体类主体代码
     * @param colnames
     * @param colTypes
     * @param colSizes
     * @return
     */
    private static String authorName = "zy";//作者名字
    private static String tablename = "Test";//表名
    private static String javaName = "test"; //.java文件名
    private static String packagePath = "com.example.demo1.entity"; //.java文件名
    private static boolean f_util = true;
    private static boolean f_sql =false;
    private static String parse(Map<String, Object> map) {
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
        processAllAttrs(sb,map);//属性
        processAllMethod(sb,map);//get set方法
        processAllConstructor(sb,map); //生成有参和无参构造方法
        sb.append("}\r\n");

        return sb.toString();
    }
    /**
     * 功能：生成所有属性
     * @param
     */
    private static void processAllAttrs(StringBuffer sb,Map<String, Object> map) {
        for(String s:map.keySet()){
            System.out.println("key : "+s+" value : "+map.get(s));
            StringBuffer z = sb.append("\tprivate String " + s + ";\r\n");
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