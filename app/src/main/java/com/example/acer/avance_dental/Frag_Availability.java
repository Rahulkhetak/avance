package com.example.acer.avance_dental;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by acer on 02-08-2018.
 */

public class Frag_Availability extends Fragment {
    Button nexthasdbity;
    ImageView imageView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frag_availability,container,false);
        nexthasdbity=view.findViewById(R.id.nexthasdbity);
        nexthasdbity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//               FragmentManager fragmentManager=getFragmentManager();
//               FragmentTransaction transaction=fragmentManager.beginTransaction();
//               transaction.replace(R.id.avail_frame,new Frag_About_Dental_Care());
//               transaction.addToBackStack(null);
//               transaction.commit();
                          }
        });
        imageView=view.findViewById(R.id.avail_backpress);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        return view;
    }
}
