package com.example.acer.avance_dental;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.acer.avance_dental.AppUtils.APIService;
import com.example.acer.avance_dental.AppUtils.ApiUtils;
import com.example.acer.avance_dental.AppUtils.AppConfig;
import com.example.acer.avance_dental.Model.SignupModel;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by acer on 01-08-2018.
 */

public class SignUp extends Fragment implements View.OnClickListener {
    SharedPreferences sharedPreferences;
    EditText name,number,email,password,repassword;

    Button signUp;
    ArrayList<SignupModel> list;
    View view;
    APIService mAPIService;
    String nameStr,emailStr,numberStr,confirmPasswordStr,passwordStr,tokenStr;
    CheckBox checkBox;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.signup,container,false);
        checkBox=view.findViewById(R.id.reqcheakbox);

        init();

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked()){
                    System.out.println("Checked");
                    



                }else{
                    System.out.println("Un-Checked");
                }
            }
        });
        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.signUpbtn){
            signUpButtongGetText();

            awesomeValidations();

        }

    }

    private void awesomeValidations() {

        if (numberStr.length()==0){
            number.requestFocus();
            number.setError(AppConfig.EnptyField);
        } else if(numberStr.length()<10){
            number.requestFocus();
            number.setError("enter valid number");
        }
        else if(emailStr.length()==0){
            email.requestFocus();
            email.setError(AppConfig.EnptyField);
        }
        else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(emailStr).matches()){
            email.requestFocus();
            email.setError("enter valid email");
        }
        else  if (nameStr.length() == 0) {
            name.requestFocus();
            name.setError("field cannot be empty");
        } else if (!nameStr.matches("[a-zA-Z ]+")) {
            name.requestFocus();
            name.setError("enter only alphabet character");
        } else if (passwordStr.length() == 0) {
            password.requestFocus();
            password.setError("field cannot be empty");
        } else if (!passwordStr.equals(confirmPasswordStr)) {
            repassword.requestFocus();
            repassword.setError("password does not match to confirm password");
        }
        else{
            ApiSinup();
        }


    }

    public void init(){
        mAPIService = ApiUtils.getAPIService();
        name=view.findViewById(R.id.fullname);
signUp=  view.findViewById(R.id.signUpbtn);
number=  view.findViewById(R.id.phnum);
email=  view.findViewById(R.id.email);
password=  view.findViewById(R.id.password);
repassword=  view.findViewById(R.id.confirmpassword);
signUp.setOnClickListener(this);
    }

void signUpButtongGetText(){
    sharedPreferences =getActivity(). getSharedPreferences(AppConfig.TokenPref , Context.MODE_PRIVATE);
    tokenStr =sharedPreferences.getString(AppConfig.TokenKey,"");

  nameStr=  name.getText().toString();
//passwordStr=password.getText().toString();
//confirmPasswordStr=repassword.getText().toString();
emailStr=email.getText().toString();
numberStr=number.getText().toString();
if(password.getText().toString().equals(repassword.getText().toString()))
    confirmPasswordStr=repassword.getText().toString();

}

void ApiSinup() {

    final List list1 = new ArrayList();
    list = new ArrayList();


    mAPIService.signUp(nameStr,numberStr,emailStr,confirmPasswordStr,tokenStr).enqueue(new Callback<SignupModel>() {
        @Override
        public void onResponse(Call<SignupModel> call, Response<SignupModel> response) {
            String success= response.body().getSuccess();
          int code=  response.body().getCode();
          String failure=response.body().getFailure();
          if(success==null){
              Toast.makeText(getContext(), "code"+code+"failure"+failure, Toast.LENGTH_SHORT).show();
          }else
          {
              Toast.makeText(getContext(), "success"+success, Toast.LENGTH_SHORT).show();
          }

        }

        @Override
        public void onFailure(Call<SignupModel> call, Throwable t) {
InternetConnection();
        }
    });


}
    public  void InternetConnection(){


        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo == null)
        {
            Toast.makeText(getContext(),"No internet connected",Toast.LENGTH_LONG).show();

        }
    }
    public void setTextNull(){
        name.setText("");
        email.setText("");
        password.setText("");
        number.setText("");
        repassword.setText("");
    }
}
