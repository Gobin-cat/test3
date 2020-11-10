package com.example.demo.controller;

import com.example.demo.entity.ClassInfo;
import com.example.demo.service.Analyzer;
import com.example.demo.service.Interpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.IOException;
import java.util.List;
@Controller//指定当前类为控制器
@RequestMapping("/analyze")//请求路径
public class AnalyzerController {
    @Autowired
    private Analyzer analyzer;
    @Autowired(required=false)
    private Interpreter interpreter;
    @ResponseBody
    @RequestMapping("/anlyzerJson")
    public void anlyzerJson() throws IOException {
        List<ClassInfo> classInfos = analyzer.readJson("C:\\Users\\HP\\Desktop\\测试\\test2.json");
        for (int i = 0; i < classInfos.size(); i++){
            System.out.println(
            "对象"+i+"\r\n"+classInfos.get(i).getFileList()+"\r\n"
                    +classInfos.get(i).getClassname()
            );
            //调用写类的方法，传入文件内容
            interpreter.writeClass(classInfos.get(i),"C:\\Users\\HP\\Desktop\\工作\\demo\\test\\src\\main\\java\\com\\example\\demo\\entity");
        }

    }
}
