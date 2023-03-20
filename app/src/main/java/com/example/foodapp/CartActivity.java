package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.foodapp.Adaptor.CartAdaptor;
import com.example.foodapp.Helper.ManagementCart;
import com.example.foodapp.Interface.ChangeNumberItemListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_cart_activity);
        managementCart = new ManagementCart(this);
        initView();
        initList();
        CalculateCart();
        bottomNavigation();
    }

    private void bottomNavigation(){
        FloatingActionButton floatingActionButton = findViewById(R.id.cartFloatBtn);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this,CartActivity.class));
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this,MainActivity.class));
            }
        });
    }

    private void initView() {
        recyclerViewList = findViewById(R.id.recyclerView3);
        totalFeeTV = findViewById(R.id.totalFeeTV);
        taxTV = findViewById(R.id.taxTV);
        deliveryPriceTV = findViewById(R.id.deliveryPriceTV);
        deliveryServiceTV = findViewById(R.id.deliveryServiceTV);
        itemTotalTV = findViewById(R.id.itemTotalTV);
        totalPriceTV = findViewById(R.id.totalPriceTV);
        totalTV = findViewById(R.id.totalTV);
        cartEmptyTV = findViewById(R.id.cartEmptyTV);
        scrollView = findViewById(R.id.scrollView2);
        recyclerViewList=  findViewById(R.id.recyclerView3);
    }
    private void initList(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new CartAdaptor(managementCart.getListCart(), this, new ChangeNumberItemListener() {
            @Override
            public void changed() {
                CalculateCart();
            }
        });
        recyclerViewList.setAdapter(adapter);
        if(managementCart.getListCart().isEmpty()){
            cartEmptyTV.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }else{
            cartEmptyTV.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }
    private void CalculateCart(){
        double percentTax= 0.03;
        double delivery = 15;
        double taxTv = 0.55;
        taxPriceTV=Math.round((managementCart.getTotalFee()*percentTax)*100)/100;
        double total = Math.round((managementCart.getTotalFee()+taxPriceTV+delivery)*100)/100;
        double itemTotal = Math.round(managementCart.getTotalFee()*100)/100;
        totalFeeTV.setText("$"+itemTotal);
        taxTV.setText("$"+taxTv);
        deliveryPriceTV.setText("$"+delivery);
        totalTV.setText("$"+total);
    }
}