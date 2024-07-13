package com.example.controller;

import com.example.model.ApiData;
import com.example.view.FileView;
import com.example.service.ApiService;

import java.io.IOException;

public class ApiController {
    private final ApiService apiService;
    private final FileView fileView;

    public ApiController() {
        this.apiService = new ApiService();
        this.fileView = new FileView();
    }

    public void fetchDataAndSave(String url, String fileName) {
        try {
            ApiData apiData = apiService.fetchData(url);
            fileView.saveToExcel(apiData.getData(), fileName);
        } catch (IOException e) {
            System.err.println("Error fetching or saving data: " + e.getMessage());
        }
    }
}