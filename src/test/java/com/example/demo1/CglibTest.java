package com.example.demo1;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.Result;
import org.springframework.cglib.beans.BeanGenerator;
import org.springframework.cglib.beans.BeanMap;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class CglibTest {
    public static void main(String[] args) throws JsonProcessingException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println(getJson());
        JSONObject json = JSONObject.parseObject(getJson());
        JSONObject jsonObject = json.getJSONObject("data").getJSONArray("result").getJSONObject(1);
        //JSONObject转string
        String string = jsonObject.toString();
        //转对象
        JSONObject userJson = JSONObject.parseObject(string);
        Object user = JSON.toJavaObject(userJson,Object.class);

        //Cglib动态生成对象
        //创建Bean构造器
        BeanGenerator beanGenerator = new BeanGenerator();
        //准备对象数据,用于构造类
        Map<String,Class> map = new HashMap<>();
        map.put("id",Integer.class);
        map.put("name",String.class);
        //遍历Map，添加属性
        for (Map.Entry entry : map.entrySet()){
            //当然也可以直接使用Map数据获取value的class设置进去
            //如map.put("id",1); entry.getValue().getClass();
            beanGenerator.addProperty(entry.getKey().toString(), (Class) entry.getValue());
        }
        //创建对象
        Object object = beanGenerator.create();
        //查看类名
        System.out.println(Modifier.toString(object.getClass().getModifiers())+"  "+object.getClass().getName());
        //查看构造的类的结构-->属性结构
        for (Field field : object.getClass().getDeclaredFields()){
            System.out.println(Modifier.toString(field.getModifiers()) + "  "
                    +field.getType()+"  "
                    + field.getName());
        }
        //查看构造的类的结构-->方法结构
        for (Method method : object.getClass().getDeclaredMethods()){
            System.out.println(Modifier.toString(method.getModifiers())+"  "
                    + method.getReturnType()+"  "
                    +method.getName());
        }

        //给对象赋值
        //使用Cglib相关类进行赋值
        BeanMap beanMap = BeanMap.create(object);
        beanMap.put("id", 2);
        beanMap.put("name", "xx");
        //使用JDK反射赋值
        /*Field field = object.getClass().getDeclaredField("$cglib_prop_name");
        field.setAccessible(true);
        field.set(object,"使用反射赋值");*/
        /*Method method = object.getClass().getDeclaredMethod("setName",String.class);
        method.invoke(object,"使用反射赋值");*/

        //以上几种赋值都是可以的，构造出来的类和源码写出来的类不同之处在结果截图里面已经很明显了

        //序列化输出
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(object));
    }
    public static String getJson() {
        //System.out.println(sdf.format(new Date())+"===开始");
        String jsonStr = "";
        try {
            File file = new File("C:\\Users\\HP\\Desktop\\测试\\test.json");
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
}
