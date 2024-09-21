package com.example.stream.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Employee {

    private int id;
    private String name;
    private String dept;
    private double salary;
    private int age;

}