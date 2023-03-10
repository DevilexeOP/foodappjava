package com.example.foodapp.Helper;

import android.content.Context;
import android.widget.Toast;
import com.example.foodapp.Domain.TrendingDomain;

import java.util.ArrayList;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;

    public ManagementCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }
    public void insertFood(TrendingDomain item){
        ArrayList<TrendingDomain>listFood=getListCart();
        boolean existAlready =false;
        int n =0;
        for(int i = 0;i < listFood.size();i++){
            if(listFood.get(i).getTitle().equals(item.getTitle())){
                existAlready =true;
                n=i;
                break;
            }
        }
        if(existAlready)
            listFood.get(n).setNumberInCart(item.getNumberInCart());
        else
            listFood.add(item);
        tinyDB.putListObject("CardList",listFood);
        Toast.makeText(context, "Added to cart", Toast.LENGTH_SHORT).show();
    }
    public ArrayList<TrendingDomain> getListCart(){
        return tinyDB.getListObject("CartList");
    }
}
