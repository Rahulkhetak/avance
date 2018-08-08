package com.example.acer.avance_dental.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SocialMediaLogin {

@SerializedName("code")
@Expose
private Integer code;
@SerializedName("success")
@Expose
private String success;
@SerializedName("user")
@Expose
private Integer user;
    @SerializedName("failed")
    @Expose
    private String failure;


    public String getFailure() {

        return failure;
    }

    public void setFailure(String failure) {
        this.failure = failure;
    }
public Integer getCode() {
return code;
}

public void setCode(Integer code) {
this.code = code;
}

public String getSuccess() {
return success;
}

public void setSuccess(String success) {
this.success = success;
}

public Integer getUser() {
return user;
}

public void setUser(Integer user) {
this.user = user;
}

}

