package com.example.acer.avance_dental;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
       // FragmentManager fragmentManager=activity.getFragmentManager();
//               FragmentTransaction transaction=fragmentManager.beginTransaction();
//               transaction.replace(R.id.avail_frame,new Frag_About_Dental_Care());
//               transaction.addToBackStack(null);
//               transaction.commit();

    FragmentManager manager=activity.getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.replace(R.id.fragdoc,new Doc_Read());
        transaction.addToBackStack(null);
        transaction.commit();


    }
});
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
