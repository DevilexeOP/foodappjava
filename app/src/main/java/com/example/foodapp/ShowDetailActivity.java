package com.example.foodapp;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.bumptech.glide.Glide;
import com.example.foodapp.Domain.TrendingDomain;
import com.example.foodapp.Helper.ManagementCart;
import org.w3c.dom.Text;

public class ShowDetailActivity extends AppCompatActivity {
    private TextView addToCartBtn;
    private TextView titleText,feeText,descriptionText,numberOfOrdersText;
    private ImageView plusBtn,minusBtn,picFood;
    private TrendingDomain object;
    int numberOfOrder = 1;
    private ManagementCart managementCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_show_detail);
        managementCart=new ManagementCart(this);
        initView();
        getBundle();
    }

    private void getBundle() {
        object=(TrendingDomain) getIntent().getSerializableExtra("object");
        int drawableResourceId = this.getResources().getIdentifier(object.getPic(),"drawable",this.getPackageName());
        Glide.with(this)
            .load(drawableResourceId)
            .into(picFood);

        titleText.setText(object.getTitle());
        feeText.setText("$"+object.getFee());
        descriptionText.setText(object.getDescription());
        numberOfOrdersText.setText(String.valueOf(numberOfOrder));

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOfOrder+=1;
                numberOfOrdersText.setText(String.valueOf(numberOfOrder));
            }
        });

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numberOfOrder>1){
                    numberOfOrder-=1;
                }
                numberOfOrdersText.setText(String.valueOf(numberOfOrder));
            }
        });

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                object.setNumberInCart(numberOfOrder);
                managementCart.insertFood(object);
            }
        });
    }

    private void initView(){
        addToCartBtn = findViewById(R.id.addToCartBtn);
        titleText =findViewById(R.id.titleText);
        feeText = findViewById(R.id.priceText);
        descriptionText = findViewById(R.id.descriptionText);
        numberOfOrdersText = findViewById(R.id.numberOfOrdersText);
        plusBtn = findViewById(R.id.plusBtn);
        minusBtn = findViewById(R.id.minusBtn);
        picFood = findViewById(R.id.picFood);
    }
}