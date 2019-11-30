package com.example.ex2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

public class ListAdapter extends RecyclerView.Adapter {
    private int[] mImages;
    private String[] mNames;
    Context context;

    public ListAdapter(Context context, String[] names, int[] images) {
        mImages = images;
        mNames = names;
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
        int picForDisplay = mImages[position];
        ((MyViewHolder)holder).mImageView.setImageResource(picForDisplay);
        String textForDisplay = mNames[position];
        ((MyViewHolder)holder).mTextView.setText(textForDisplay);
    }

    @Override
    public int getItemCount() {
        return mNames.length;
    }


}