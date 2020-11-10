package com.example.demo.service;

import com.example.demo.entity.ClassInfo;

import java.io.IOException;

public interface Interpreter {
    //将类生成文件写出
    void writeClass(ClassInfo classInfo, String path) throws IOException;
}
