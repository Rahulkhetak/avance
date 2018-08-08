package com.example.acer.avance_dental;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.acer.avance_dental.AppUtils.APIService;
import com.example.acer.avance_dental.AppUtils.ApiUtils;
import com.example.acer.avance_dental.AppUtils.AppConfig;
import com.example.acer.avance_dental.Model.SignInModel;
import com.example.acer.avance_dental.Model.SignupModel;
import com.example.acer.avance_dental.Model.SocialMediaLogin;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by acer on 01-08-2018.
 */

public class SignIn extends Fragment {

    APIService mAPIService;
    ArrayList<SignupModel> list;
    SharedPreferences sharedPreferences,sharedPreferences1;
    EditText number, password;
    String numberStr,passwordStr,tokenStr;
    Button availability, singin, googleloginbutton;
    public static final int RequestSignInCode = 7;
    // Firebase Auth Object.
    public GoogleApiClient googleApiClient;
    ImageView facelogin;
    CallbackManager callbackManager;
 String uid, email,name;
    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.signin, container, false);
        mAuth = FirebaseAuth.getInstance();

        availability = view.findViewById(R.id.availability);
        googleloginbutton = view.findViewById(R.id.googlelogin);
        facelogin = view.findViewById(R.id.faclogin);
        callbackManager = CallbackManager.Factory.create();
        singin=view.findViewById(R.id.singin);
        number=view.findViewById(R.id.number);
        password=view.findViewById(R.id.password);
        singin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberStr= number.getText().toString();
                passwordStr=password.getText().toString();
                sharedPreferences =getActivity(). getSharedPreferences(AppConfig.TokenPref , Context.MODE_PRIVATE);

                tokenStr =sharedPreferences.getString(AppConfig.TokenKey,"");
                ApiSinup();

            }
        });
          faceLogin();
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
//
        googleApiClient = new GoogleApiClient.Builder(getContext())
                .enableAutoManage(getActivity(), new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                    }
                } /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();


        availability.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewPager.viewpager.setCurrentItem(1);


            }
        });

   //     singin = view.findViewById(R.id.singin);

//        singin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), HomePAge.class);
//                startActivity(intent);
//            }
//        });

        googleloginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                UserSignInMethod();
            }
        });

        return view;
    }

    private void ApiSinup() {



        mAPIService = ApiUtils.getAPIService();


        final List list1 = new ArrayList();
        list = new ArrayList();


        mAPIService.Login(numberStr,passwordStr,tokenStr).enqueue(new Callback<SignInModel>() {
            @Override
            public void onResponse(Call<SignInModel> call, Response<SignInModel> response) {

            int code= response.code();
            if(code==200){
//                sharedPreferences =getActivity(). getSharedPreferences(AppConfig.SharedPref , Context.MODE_PRIVATE);

//                sharedPreferences1.edit().putString(AppConfig.Keyemail,numberStr).putString(AppConfig.TokenKey,tokenStr).putString(AppConfig.Keyuid,passwordStr).apply();

                Intent intent=new Intent(getActivity(),HomePAge.class);
                startActivity(intent);
            }else if(code==404){

                Toast.makeText(getContext(), "user doesnot exists", Toast.LENGTH_SHORT).show();
            }

            }


            @Override
            public void onFailure(Call<SignInModel> call, Throwable t) {
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




    private void UserSignInMethod() {

        Intent AuthIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);

        startActivityForResult(AuthIntent, RequestSignInCode);


    }


    private void FirebaseUserAuth(GoogleSignInAccount googleSignInAccount) {
        AuthCredential authCredential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);

//        Toast.makeText(MainActivity.this,""+ authCredential.getProvider(),Toast.LENGTH_LONG).show();

        mAuth.signInWithCredential(authCredential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> AuthResultTask) {

                        if (AuthResultTask.isSuccessful()) {
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                             uid=firebaseUser.getUid();
                             email=firebaseUser.getEmail();
                             name=firebaseUser.getDisplayName();
                            Log.d("uid",uid+email+name);
                            SocialMediaLoginApi(name,uid,email,"gmail",tokenStr);

                        } else {
                            Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    public void hideKeyBoardMethod(View view) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) getActivity().getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                getActivity().getCurrentFocus().getWindowToken(), 0);
    }


    public void faceLogin() {
        facelogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        LoginManager.getInstance().logInWithReadPermissions(getActivity(), Arrays.asList("email"));
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        getUserDetails(loginResult);
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException error) {

                    }


                });
            }
        });
    }


    @Override
    public void onActivityResult(final int requestCode, final int resultCode,
                                 final Intent data) {
        //facebook result
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RequestSignInCode) {

            GoogleSignInResult googleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            if (googleSignInResult.isSuccess()) {

                GoogleSignInAccount googleSignInAccount = googleSignInResult.getSignInAccount();

                FirebaseUserAuth(googleSignInAccount);
            }

        }
    }


    protected void getUserDetails(LoginResult loginResult) {

        GraphRequest data_request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        LoginManager.getInstance().logOut();
                        Intent intent = new Intent(getActivity(),
                                HomePAge.class);
                        intent.putExtra("userProfile", object.toString());
                        startActivity(intent);

                    }

                });
        Bundle permission_param = new Bundle();
        permission_param.putString("fields", "id,name,email,picture.width(120).height(120)");
        data_request.setParameters(permission_param);
        data_request.executeAsync();
    }

//    protected void onResume() {
//        super.onResume();
//        // Logs 'install' and 'app activate' App Events.
//        AppEventsLogger.activateApp(getActivity());
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        // Logs 'app deactivate' App Event.
//        AppEventsLogger.deactivateApp(getActivity());
//    }

public void SocialMediaLoginApi(String name, final String uid, final String email, String plateform, String deviceToken){
    mAPIService = ApiUtils.getAPIService();


    mAPIService.LoginSocialMedia(name,uid,email,plateform,deviceToken).enqueue(new Callback<SocialMediaLogin>() {
        @Override
        public void onResponse(Call<SocialMediaLogin> call, Response<SocialMediaLogin> response) {
            String success= response.body().getSuccess();

            int code=  response.body().getCode();
            String failure=response.body().getFailure();
            sharedPreferences1=getActivity(). getSharedPreferences(AppConfig.SharedPref , Context.MODE_PRIVATE);

            sharedPreferences1.edit().putString(AppConfig.Keyemail,email).putString(AppConfig.TokenKey,tokenStr).putString(AppConfig.Keyuid,uid).apply();

                Toast.makeText(getContext(),"successs" , Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), HomePAge.class);
                startActivity(intent);

            }




        @Override
        public void onFailure(Call<SocialMediaLogin> call, Throwable t) {
            InternetConnection();
        }
    });


}


}
