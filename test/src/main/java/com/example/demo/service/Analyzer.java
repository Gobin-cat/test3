package com.example.demo.service;
import com.example.demo.entity.ClassInfo;

import java.io.IOException;
import java.util.List;
public interface Analyzer {
    //读取json文件
    List<ClassInfo> readJson(String path);

}
