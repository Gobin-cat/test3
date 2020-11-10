package com.example.demo.service;

import com.example.demo.entity.ClassInfo;
import com.example.demo.util.WriteClassUtil;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
@Service
public class InterpreterImpl implements Interpreter{
    @Override
    public void writeClass(ClassInfo classInfo,String path) throws IOException {
        File dir = new File(path);
        // 一、检查放置文件的文件夹路径是否存在，不存在则创建
        if (!dir.exists()) {
            dir.mkdirs();// mkdirs创建多级目录
        }
        File checkFile = new File(path + "//"+classInfo.getClassname()+".java");
        FileWriter writer = null;
        try {
            // 二、检查目标文件是否存在，不存在则创建
            if (!checkFile.exists()) {
                checkFile.createNewFile();// 创建目标文件
            }
            // 三、向目标文件中s写入内容
            // FileWriter(File file, boolean append)，append为true时为追加模式，false或缺省则为覆盖模式
            writer = new FileWriter(checkFile, true);
            String parse = WriteClassUtil.parse(classInfo);
            writer.append(parse);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != writer) writer.close();
        }
    }
}
