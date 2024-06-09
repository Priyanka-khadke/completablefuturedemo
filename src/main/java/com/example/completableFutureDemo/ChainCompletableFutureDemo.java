package com.example.completableFutureDemo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class ChainCompletableFutureDemo {

    /*
    1.getAllEmployees
    2.filter employees who are new joinees and traning is pending
    3.get mailid of employees
    4.sendemail to them
     */
    public void employeeReminder() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> completableFuture = CompletableFuture
                .supplyAsync(EmployeeDatabase::getAllEmployees)
        .thenApply((allEmployees) -> {
                    System.out.println("size of all employees: "+allEmployees.size());
            return allEmployees.stream()
                    .filter(e -> ("TRUE".equals(e.getNewJoiner()) && "TRUE".equals(e.getLearningPending())))
                    .collect(Collectors.toList());
        }).thenApply((filteredEmployees) -> {
            return filteredEmployees.stream()
                    .map(e -> (e.getEmail()))
                    .collect(Collectors.toList());
        }).thenAccept((emails) -> {
            System.out.println("mail id list size: " + emails.size());
            emails.stream()
                    .forEach(ChainCompletableFutureDemo::sendEmails);
        });
        completableFuture.get();


    }

    public static void sendEmails(String mailId) {

        System.out.println("sending mail to : "+mailId);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ChainCompletableFutureDemo demo = new ChainCompletableFutureDemo();
        demo.employeeReminder();
    }
}
