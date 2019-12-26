package com.blue.hospital.entity;

import lombok.Data;

@Data
public class Student {
    private Integer id;
    private String name;
    private String age;

    public Student(int i, String zhangsan, String s){
        this.id = i;
        this.name = zhangsan ;
        this.age = s;
    }
}
