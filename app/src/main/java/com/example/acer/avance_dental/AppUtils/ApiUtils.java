package com.example.acer.avance_dental.AppUtils;

public class ApiUtils {
 
    private ApiUtils() {}
 
    public static final String BASE_URL = "http://52.72.120.249:1970/api/";
 
    public static APIService getAPIService() {
 
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}