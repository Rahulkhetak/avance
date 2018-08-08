package com.example.acer.avance_dental.AppUtils;
import com.example.acer.avance_dental.Model.SignupModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
 
public interface APIService {
 
    @POST("register")
    @FormUrlEncoded
    Call<SignupModel> signUp(@Field("name") String name,
                               @Field("number") String number,
                               @Field("email") String email,
                             @Field("password") String password,
                             @Field("devicetoken")String devicetoken);
}