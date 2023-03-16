package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.foodapp.Helper.ManagementCart;

public class CartActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManagementCart managementCart;
    TextView itemTotalTV,totalPriceTV,deliveryServiceTV,deliveryPriceTV,taxTV,totalTV,totalFeeTV,cartEmptyTV;
    private double taxPriceTV;
    private ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_activity);
        managementCart = new ManagementCart(this);
        initView();
    }

    private void initView() {
        recyclerViewList = findViewById(R.id.recyclerView);
        totalFeeTV = findViewById(R.id.totalFeeTV);
        taxTV = findViewById(R.id.taxTV);
        deliveryPriceTV = findViewById(R.id.deliveryPriceTV);
        deliveryServiceTV = findViewById(R.id.deliveryServiceTV);
        itemTotalTV = findViewById(R.id.itemTotalTV);
        totalPriceTV = findViewById(R.id.totalPriceTV);
        totalTV = findViewById(R.id.totalTV);
        cartEmptyTV = findViewById(R.id.cartEmptyTV);
        scrollView = findViewById(R.id.scrollView2);
    }
    private void initList(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter.Cart
    }
}