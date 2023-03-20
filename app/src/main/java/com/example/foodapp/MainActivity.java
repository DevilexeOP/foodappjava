package com.example.foodapp;

import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.foodapp.Adaptor.CategoryAdaptor;
import com.example.foodapp.Adaptor.TrendingAdaptor;
import com.example.foodapp.Domain.CategoryDomain;
import com.example.foodapp.Domain.TrendingDomain;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter,adapter2;
    private RecyclerView recyclerViewCategoryList,recyclerViewTrendingList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        recyclerViewCategoryList();
        recyclerViewTrending();
        bottomNavigation();
    }

    private void bottomNavigation(){
        FloatingActionButton floatingActionButton = findViewById(R.id.cartFloatBtn);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CartActivity.class));
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MainActivity.class));
            }
        });
    }
    private void recyclerViewCategoryList(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewCategoryList = findViewById(R.id.recyclerView3);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> category = new ArrayList<>();
        category.add(new CategoryDomain("Pizza","cat_1"));
        category.add(new CategoryDomain("Burger","cat_2"));
        category.add(new CategoryDomain("Hot dog","cat_3"));
        category.add(new CategoryDomain("Drink","cat_4"));
        category.add(new CategoryDomain("Donut","cat_5"));

        adapter = new CategoryAdaptor(category);
        recyclerViewCategoryList.setAdapter(adapter);

    }

    private void recyclerViewTrending(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewTrendingList=findViewById(R.id.recyclerView2);
        recyclerViewTrendingList.setLayoutManager(linearLayoutManager);

        ArrayList<TrendingDomain> foodList = new ArrayList<>();
        foodList.add(new TrendingDomain("Pepperoni Pizza", "pizza","slices pepperoni,mozzerella cheese, fresh oregano,ground black pepper,pizza sauce",10.5));
        foodList.add(new TrendingDomain("Cheese Burger", "pop_2","beef,Gouda Cheese,Special Sauce,tomato",12.5));
        foodList.add(new TrendingDomain("Vegetable Pizza", "pop_3","olive oil , vegetable oil,pitted kalamata,cherry tomatoes",14.5));

        adapter2=new TrendingAdaptor(foodList);
        recyclerViewTrendingList.setAdapter(adapter2);
    }
}