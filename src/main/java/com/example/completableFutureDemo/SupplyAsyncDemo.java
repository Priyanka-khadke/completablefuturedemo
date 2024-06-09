package com.example.completableFutureDemo;

import com.example.completableFutureDemo.model.Employee;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SupplyAsyncDemo {

    public List<Employee> getAllEmployees() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        CompletableFuture<List<Employee>> listCompletableFuture = CompletableFuture
                .supplyAsync(() -> {
                    System.out.println("Thread name: "+Thread.currentThread().getName());
            return EmployeeDatabase.getAllEmployees();

        },executorService);

        return listCompletableFuture.get();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        SupplyAsyncDemo demo = new SupplyAsyncDemo();
        List<Employee> allEmployees = demo.getAllEmployees();
        allEmployees.stream().forEach(System.out::println);

    }
}
