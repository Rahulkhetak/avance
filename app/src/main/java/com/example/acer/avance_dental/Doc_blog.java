package com.example.acer.avance_dental;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Doc_blog extends Fragment {
    RecyclerView recyclerView;
    ImageView imageView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=LayoutInflater.from(container.getContext()).inflate(R.layout.docblog,container,false);
        recyclerView=view.findViewById(R.id.recid);
        imageView=view.findViewById(R.id.gallback2);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        Blog_Doc_Adapter blog_doc_adapter=new Blog_Doc_Adapter(getActivity());
        recyclerView.setAdapter( blog_doc_adapter);
        return view;
    }
}
