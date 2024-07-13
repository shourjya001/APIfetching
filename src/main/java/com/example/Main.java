package com.example;

import com.example.controller.ApiController;

public class Main {
    public static void main(String[] args) {
        ApiController controller = new ApiController();
        String apiUrl = "https://jsonplaceholder.typicode.com/comments"; // Replace with your API URL
        String fileName = "output.xlsx";

        controller.fetchDataAndSave(apiUrl, fileName);
    }
}