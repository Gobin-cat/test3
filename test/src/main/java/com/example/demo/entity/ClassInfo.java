package com.example.demo.entity;

import lombok.Data;

import java.util.List;

@Data
public class ClassInfo {
    private List<Filed> fileList;
    private String classname;
    private String hashCode;
}
