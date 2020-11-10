//package com.example.generate;
//
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//
//public class WriteObject {
//    private final static String directory="com.example.entity";
//    public static void  writerContent(String newFileName_dat,String s){
//        //本地创建目录并且将内容S写入文件内
//        File file=new File(directory);
//        if(!file.exists()){
//            file.mkdir();
//        }
//        File file2 = new File(directory,);
//        if(!file2.exists()){
//            try{
//                file2.createNewFile();
//            }catch(IOException e){
//                e.printStackTrace();
//            }
//        }
//        FileWriter fileWriter;
//        try{
//            fileWriter = new FileWriter(file2);
//            //向指定文件中写入内容
//            BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
//            bufferedWriter.write(s);
//            bufferedWriter.close();
//        }catch(IOException e){
//            e.printStackTrace();
//        }
//    }
//}
