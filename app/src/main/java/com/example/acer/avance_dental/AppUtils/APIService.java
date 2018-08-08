package com.example.acer.avance_dental.AppUtils;
import com.example.acer.avance_dental.Model.SignInModel;
import com.example.acer.avance_dental.Model.SignupModel;
import com.example.acer.avance_dental.Model.SocialMediaLogin;

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





    @POST("login")
    @FormUrlEncoded

    Call<SignInModel>Login(@Field("number") String number_str,
                           @Field("password") String password_str,
                           @Field("devicetoken") String devicetoken_str);


    @POST("signup_social")
    @FormUrlEncoded
    Call<SocialMediaLogin>LoginSocialMedia(@Field("name") String name,
                                           @Field("uid") String uid,
                                           @Field("email")String email,
                                           @Field("platform") String plateform,
                                           @Field("devicetoken")String devicetoken);

}