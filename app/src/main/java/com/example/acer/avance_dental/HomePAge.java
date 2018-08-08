package com.example.acer.avance_dental;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.*;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.acer.avance_dental.AppUtils.AppConfig;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

@RequiresApi(api = Build.VERSION_CODES.M)
public class HomePAge extends AppCompatActivity implements View.OnScrollChangeListener,NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {
    ScrollView scrollView;
    CircleIndicator indicator;
    RelativeLayout relativeLayout;
DrawerLayout drawerLayout;
    ImageView open_drawer;
    SharedPreferences sharedPreferences;
    public static final int RequestSignInCode = 7;

    // Firebase Auth Object.
    public GoogleApiClient googleApiClient;

    private FirebaseAuth mAuth;

ImageView about,apoiment,availibty,blog,contact,gallryy,rache,service,testmoinal;
    android.support.v4.view.ViewPager mPager;
    private static int currentPage = 0;
    private static final Integer[] XMEN = {R.drawable.docc, R.drawable.dooc};
    private ArrayList<Integer> XMENArray = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        mAuth = FirebaseAuth.getInstance();

        drawerLayout=findViewById(R.id.drawer_layout);
        mPager = findViewById(R.id.pager);
        indicator = findViewById(R.id.indicator);
        about = findViewById(R.id.ic_about_us);
        apoiment = findViewById(R.id.ic_appointment);
        availibty = findViewById(R.id.ic_availibility);
        blog = findViewById(R.id.ic_blog);
        contact = findViewById(R.id.ic_contatc_us);
        gallryy = findViewById(R.id.ic_gallery);
        rache = findViewById(R.id.ic_reach_us);
        service = findViewById(R.id.ic_services);
        testmoinal = findViewById(R.id.ic_testimonials);
        relativeLayout=findViewById(R.id.relative);
        scrollView=findViewById(R.id.scroll);
        open_drawer=findViewById(R.id.open_drawer);
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
//
        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                    }
                } /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();

        open_drawer.setOnClickListener(this);
//        drawerLayout.findViewById(R.id.drawer);
//        drawerLayout.openDrawer(Gravity.START);



        scrollView.setOnScrollChangeListener(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer,  R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);






        init();
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction transaction=fragmentManager.beginTransaction();
                transaction.replace(R.id.homefrag,new Frag_About_Dental_Care());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        gallryy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction transaction=fragmentManager.beginTransaction();
                transaction.replace(R.id.homefrag,new Frag_Gallery());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        availibty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction transaction=fragmentManager.beginTransaction();
                transaction.replace(R.id.homefrag,new Frag_Availability());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction transaction=fragmentManager.beginTransaction();
                transaction.replace(R.id.homefrag,new MainActivity());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        rache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePAge.this, Google_Map_Ap.class);
                startActivity(intent);
            }
        });

        blog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction transaction=fragmentManager.beginTransaction();
                transaction.replace(R.id.homefrag,new Doc_blog());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

    }

    private void init() {


        for (int i = 0; i < XMEN.length; i++)
            XMENArray.add(XMEN[i]);


        mPager.setAdapter(new MyAdapter2(HomePAge.this, XMENArray));

        indicator.setViewPager(mPager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == XMEN.length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, false);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2500, 3500);
    }

    @Override
    public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        relativeLayout.setBackgroundColor(Color.parseColor("#60F2F2F2"));

         Log.d(String.valueOf(scrollView.getScrollY()),"scroll");
          if(scrollY>=0&&scrollY<=100){
             relativeLayout.setBackgroundColor(Color.parseColor("#00ffffff"));
         }


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.hommee) {
            // Handle the camera action
        } else if (id == R.id.contact_uss) {
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.homefrag,new MainActivity()).addToBackStack(null).commit();

        } else if (id == R.id.tetss) {

        } else if (id == R.id.share_app) {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = "Here is the share content body";
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, "Share via"));

        } else if (id == R.id.rate_us) {

        } else if (id == R.id.about_us) {
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.homefrag,new Frag_About_Dental_Care()).addToBackStack(null).commit();
        }
        else if (id == R.id.Log_out) {

logout();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logout() {

        mAuth.signOut();

        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        sharedPreferences = getSharedPreferences(AppConfig.SharedPref , Context.MODE_PRIVATE);

                        sharedPreferences.edit().clear().commit();
sharedPreferences.edit().remove(AppConfig.Keyemail).commit();
sharedPreferences.edit().remove(AppConfig.Keytoken).commit();
sharedPreferences.edit().remove(AppConfig.Keyuid).commit();
// Write down your any code here which you want to execute After Sign Out.

// Printing Logout toast message on screen.
                        finish();
                        Toast.makeText(HomePAge.this, "Logout Successfully", Toast.LENGTH_LONG).show();
Intent intent=new Intent(HomePAge.this,ViewPager.class);
startActivity(intent);
                    }
                });


    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.open_drawer){
            drawerLayout.openDrawer(Gravity.START);
        }
    }


    // Slider class inner class
    public class MyAdapter2 extends PagerAdapter {
        private ArrayList<Integer> images;
        private LayoutInflater inflater;
        private Context context;

        public MyAdapter2(HomePAge mainActivity, ArrayList<Integer> xmenArray) {
            this.context = mainActivity;
            this.images = xmenArray;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            View myImageLayout = inflater.inflate(R.layout.slide, view, false);
            ImageView myImage = (ImageView) myImageLayout.findViewById(R.id.image);
            myImage.setImageResource(images.get(position));
            view.addView(myImageLayout, 0);


            return myImageLayout;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view.equals(object);
        }
    }
}