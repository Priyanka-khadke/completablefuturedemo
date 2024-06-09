package com.example.completableFutureDemo;

import com.example.completableFutureDemo.model.Employee;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class EmployeeDatabase {

    public static List<Employee> getAllEmployees() {

        ObjectMapper mapper = new ObjectMapper();
        List<Employee> employees = null;
        try {
            employees = mapper.readValue(new File("src/main/resources/employees.json"), new TypeReference<List<Employee>>() {

            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return employees;
    }
}
