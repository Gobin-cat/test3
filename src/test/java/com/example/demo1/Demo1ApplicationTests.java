package com.example.demo1;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo1ApplicationTests {
    //static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Test
        public void getJson() {
            //System.out.println(sdf.format(new Date())+"===开始");
            String jsonStr = "";
            try {
                File file = new File("C:\\Users\\HP\\Desktop\\test.json");
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
                //return jsonStr;
            } catch (Exception e) {
                //return null;
            }
        }
//    public static void main(String[] args) {
//        System.out.println(getJson());
//        JSONObject json = JSONObject.parseObject(getJson());
//        JSONObject jsonObject = json.getJSONObject("data").getJSONArray("result").getJSONObject(1);
//        //JSONObject转string
//        String string = jsonObject.toString();
//        //转对象
//        JSONObject userJson = JSONObject.parseObject(string);
//        Result user = JSON.toJavaObject(userJson, Result.class);
//        System.out.println(user);
//    }
//    @Test
//    public void stringTest(){
//        String value ="MD=Huawei/NCE;MLSN=1;SNC=2020-06-10 02:45:46 - 47181 -wdm||MD=Huawei/NCE;MLSN=1;SNC=2020-06-10 02:45:46 - 47163 -wdm||MD=Huawei/NCE;MLSN=1;SNC=2020-06-10 02:45:46 - 47150 -wdm||MD=Huawei/NCE;MLSN=1;SNC=2020-06-05 07:47:21 - 44795 -wdm||MD=Huawei/NCE;MLSN=1;SNC=2020-06-05 07:47:21 - 44796 -wdm||MD=Huawei/NCE;MLSN=1;SNC=2020-06-05 07:47:21 - 44911 -wdm||MD=Huawei/NCE;MLSN=1;SNC=2020-07-03 01:35:12 - 60051 -wdm||MD=Huawei/NCE;MLSN=1;SNC=2020-06-10 02:45:46 - 47151 -wdm||MD=Huawei/NCE;MLSN=1;SNC=2020-06-10 02:45:46 - 47178 -wdm||MD=Huawei/NCE;MLSN=1;SNC=2020-06-05 07:47:21 - 44909 -wdm||MD=Huawei/NCE;MLSN=1;SNC=2020-06-05 07:47:21 - 44905 -wdm||MD=Huawei/NCE;MLSN=1;SNC=2020-06-10 02:45:46 - 47149 -wdm||MD=Huawei/NCE;MLSN=1;SNC=2020-06-05 07:47:21 - 44794 -wdm||MD=Huawei/NCE;MLSN=1;SNC=2020-07-03 01:35:12 - 60052 -wdm";
//        String[] split = value.split("||");
//        for (int i=0;i<split.length;i++){
//            System.out.println("这是第["+i+"]条"+split[i]);
//        }
//
//    }

}
