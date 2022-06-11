package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.project.Define.CategoryType;
import com.example.project.adapter.FoodAdapter;
import com.example.project.databinding.FragmentChild1Binding;
import com.example.project.databinding.FragmentChild2Binding;
import com.example.project.databinding.FragmentChild3Binding;
import com.example.project.databinding.FragmentChild4Binding;
import com.example.project.databinding.FragmentChild5Binding;
import com.example.project.databinding.FragmentChild6Binding;
import com.example.project.databinding.FragmentChild7Binding;
import com.example.project.databinding.FragmentChild8Binding;
import com.example.project.interfaces.OnFoodItemClickListener;
import com.example.project.models.Food;
import com.example.project.models.Store;
import com.example.project.service.Service;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ChildFragment8 extends Fragment implements OnFoodItemClickListener {

    private static final String TAG = "TAG";
    private FragmentChild8Binding fragmentChild8Binding;
    private FoodAdapter foodAdapter;
    private Service service;
    ArrayList<Food> foods;
    CategoryType categoryType;

    private OnFoodItemClickListener onFoodItemClickListener;

    public ChildFragment8() {
        // Required empty public constructor
    }

    public static ChildFragment8 newInstance() {
        ChildFragment8 fragment = new ChildFragment8();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        service = Service.retrofit.create(Service.class);
        foods = new ArrayList<Food>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentChild8Binding = FragmentChild8Binding.inflate(inflater, container, false);
        setupRecyclerView(foods);

        return fragmentChild8Binding.getRoot();
    }

    private void setupRecyclerView(ArrayList<Food> foods) {
        foodAdapter = new FoodAdapter();
        foodAdapter.addItem(foods);
        foodAdapter.setOnFoodItemClickListener(this);

        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
        fragmentChild8Binding.recyclerView2.setAdapter(foodAdapter);
        fragmentChild8Binding.recyclerView2.setLayoutManager(manager);
        fragmentChild8Binding.recyclerView2.hasFixedSize();

        requestFoodsData();
    }

    private void requestFoodsData() {
        service.getCategoryFoods("OVERSEA").enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {

                if (response.isSuccessful()) {
                    foods = new ArrayList<Food>();

                    for (Food food : response.body()) {
                        Food f = new Food();
                        f.setFoodName(food.getFoodName());
                        f.setUrl(food.getUrl());
                        foods.add(f);
                    }
                    foodAdapter.addItem(foods);
                }
                Log.d(TAG, foods.toString());
            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {

            }


        });
    }

    @Override
    public void onItemClicked(Food food) {
        Intent intent = new Intent(getContext(), CategoryDetailActivity.class);
        startActivity(intent);
    }
}