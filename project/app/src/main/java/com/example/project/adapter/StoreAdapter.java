package com.example.project.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.project.MainActivity;
import com.example.project.NearestStoreFragment;
import com.example.project.R;
import com.example.project.interfaces.OnIntentCallback;
import com.example.project.interfaces.OnMapItemClickListener;
import com.example.project.models.Category;
import com.example.project.models.Store;

import java.util.ArrayList;
import java.util.List;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.MyViewHolder> {


    ArrayList<Store> list;
    private OnMapItemClickListener  onMapItemClickListener;
    private OnIntentCallback onIntentCallback;

    public void setOnIntentCallback(OnIntentCallback onIntentCallback) {
        this.onIntentCallback = onIntentCallback;
    }

    public void setOnMapItemClickListener(OnMapItemClickListener onMapItemClickListener){
        this.onMapItemClickListener =onMapItemClickListener;
    }


    public void addItem(ArrayList<Store> stores) {
        this.list = stores;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.store, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreAdapter.MyViewHolder holder, int position) {
        Store store = list.get(position);
        holder.drawStoreList(store);
    }

    private void startActivity(Intent intent) {
        startActivity(intent);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private View view;
        private ImageView storeImage;
        private TextView storeName;
        private TextView distance;
        private TextView address;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            storeImage = itemView.findViewById(R.id.image);
            storeName = itemView.findViewById(R.id.storeName);
            distance = itemView.findViewById(R.id.distance);
            address = itemView.findViewById(R.id.address);

            view.setOnClickListener(view -> {
                if(onIntentCallback == null && onMapItemClickListener != null){
                    onMapItemClickListener.selectedItem(address.getText().toString());
                }else if(onIntentCallback != null && onMapItemClickListener == null){
                    onIntentCallback.startIntent(address.getText().toString());
                }
            });


        }

        public void drawStoreList(Store store) {
            Glide.with(storeImage.getContext())
                    .load(store.getImgurl())
                    .centerCrop()
                    .transform(new CenterCrop())
                    .into(storeImage);
            storeName.setText(store.getStoreName());
            distance.setText(store.getDistance() + "m");
            address.setText(store.getAddress());
        }


    }

}




