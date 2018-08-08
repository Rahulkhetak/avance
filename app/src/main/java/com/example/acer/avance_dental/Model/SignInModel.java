package com.example.acer.avance_dental.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignInModel {

@SerializedName("code")
@Expose
private Integer code;
@SerializedName("msg")
@Expose
private String success;

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

}
