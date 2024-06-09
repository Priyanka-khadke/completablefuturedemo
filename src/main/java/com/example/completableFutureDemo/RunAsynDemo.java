package com.example.completableFutureDemo;

import com.example.completableFutureDemo.model.Employee;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RunAsynDemo {




    public Void getAllEmployees() throws ExecutionException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() ->{
            try {
                List<Employee> employees = mapper.readValue(new File("src/main/resources/employees.json"), new TypeReference<List<Employee>>() {

                });
                System.out.println(employees.size());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
        return completableFuture.get();




    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        RunAsynDemo runasyn = new RunAsynDemo();
        runasyn.getAllEmployees();
    }


}
