package com.example.ex2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter {
    private List<Personality> mData;
    Context context;

    public ListAdapter(Context context, List<Personality> Doctors) {
        this.mData = Doctors;
        this.context = context;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mTextView;

        public MyViewHolder(View view) {
            super(view);
            mImageView = (ImageView)view.findViewById(R.id.image);
            mTextView = (TextView)view.findViewById(R.id.name);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        MyViewHolder holder = new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        int picForDisplay = mData.get(position).image;
        ((MyViewHolder)holder).mImageView.setImageResource(picForDisplay);
        String textForDisplay = mData.get(position).name;
        ((MyViewHolder)holder).mTextView.setText(textForDisplay);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


}