package com.example.acer.avance_dental;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Blog_Doc_Adapter extends RecyclerView.Adapter <Blog_Doc_Adapter.Myholder>{
    FragmentActivity activity;
    public Blog_Doc_Adapter(FragmentActivity activity) {
        this.activity=activity;
    }

    @Override
    public Myholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.doc_blog_adapter,parent,false);
        Myholder myholder=new Myholder(view);

        return myholder;
    }

    @Override
    public void onBindViewHolder(Myholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public  class Myholder extends RecyclerView.ViewHolder {
        public Myholder(View view) {
            super(view);

        }
    }
}
