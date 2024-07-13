package com.example.model;

import java.util.List;
import java.util.Map;

public class ApiData {
    private List<Map<String, String>> data;

    public ApiData(List<Map<String, String>> data) {
        this.data = data;
    }

    public List<Map<String, String>> getData() {
        return data;
    }

    public void setData(List<Map<String, String>> data) {
        this.data = data;
    }
}