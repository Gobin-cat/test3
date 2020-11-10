package com.example.demo.util;

import com.example.demo.entity.ClassInfo;
import com.example.demo.entity.Filed;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class WriteClassUtil {
//    public static void writeFile(ClassInfo classInfo, String path) throws IOException {
//        File dir = new File(path);
//        // 一、检查放置文件的文件夹路径是否存在，不存在则创建
//        if (!dir.exists()) {
//            dir.mkdirs();// mkdirs创建多级目录
//        }
//        File checkFile = new File(path + "//"+classInfo.getClassname()+".java");
//        FileWriter writer = null;
//        try {
//            // 二、检查目标文件是否存在，不存在则创建
//            if (!checkFile.exists()) {
//                checkFile.createNewFile();// 创建目标文件
//            }
//            // 三、向目标文件中s写入内容
//            // FileWriter(File file, boolean append)，append为true时为追加模式，false或缺省则为覆盖模式
//            writer = new FileWriter(checkFile, true);
//            String parse = parse(classInfo);
//            writer.append(parse);
//            writer.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (null != writer)
//                writer.close();
//        }
//    }
    //生成文件
    /**
     * 功能：生成实体类主体代码
     * @param colnames
     * @param colTypes
     * @param colSizes
     * @return
     */
    private static String authorName = "zy";//作者名字
    private static String packagePath ="com.example.demo.entity";
    public static String parse(ClassInfo classInfo) {
        StringBuffer sb = new StringBuffer();
        //导入包名
        sb.append("package " + packagePath + ";\r\n");
        //判断是否导入工具包
        sb.append("import java.util.*;\r\n");
        sb.append("import lombok.Data;\r\n");
        sb.append("import com.gitee.sunchenbin.mybatis.actable.annotation.Column;\r\n");
        sb.append("import com.gitee.sunchenbin.mybatis.actable.annotation.Table;\r\n");
        sb.append("import com.gitee.sunchenbin.mybatis.actable.command.BaseModel;\r\n");
        sb.append("import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;\r\n");
        sb.append("\r\n");
        //注释部分
        sb.append("   /**\r\n");
        sb.append("    * "+ classInfo.getClassname() +" 实体类\r\n");
        sb.append("    * "+new Date()+" "+authorName+"\r\n");
        sb.append("    */ \r\n");
        //实体部分
        sb.append("\r\n\r\n@Data\r\n");
        sb.append("\r\n\r\n@Table(name = "+"\""+classInfo.getClassname()+"\""+")\r\n");
        sb.append("\r\n\r\npublic class " + classInfo.getClassname() + " extends BaseModel{\r\n");
        processAllAttrs(sb,classInfo);//属性
        sb.append("}\r\n");

        return sb.toString();
    }
    /**
     * 功能：生成所有属性
     * @param
     */
    private static void processAllAttrs(StringBuffer sb,ClassInfo classInfo) {
        for(Filed filed:classInfo.getFileList()){
            int i = filed.getType().getClass().getTypeName().split("\\.").length - 1;
            String typeName = filed.getType().getClass().getTypeName().split("\\.")[i];
            if (typeName.equals("JSONArray")||typeName.equals("JSONObject")){
                sb.append("@Column(name = "+"\""+filed.getName()+"\""+",type = MySqlTypeConstant.VARCHAR,length = 600)\r\n");
                sb.append("\tprivate String "+" "+ filed.getName() + ";\r\n");
            }else {
                sb.append("@Column(name = "+"\""+filed.getName()+"\""+",type = MySqlTypeConstant.VARCHAR,length = 255)\r\n");
                sb.append("\tprivate "+typeName+" "+ filed.getName() + ";\r\n");
                }
            }
        //换行
        sb.append("\r\n");
    }
}
