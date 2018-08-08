package com.example.acer.avance_dental;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Testmoinal_Frag extends Fragment {
ImageView back;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=LayoutInflater.from(container.getContext()).inflate(R.layout.testmonial,container,false);

back=view.findViewById(R.id.gallback33);

back.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        getActivity().onBackPressed();
    }
});


        return view;
    }
}
