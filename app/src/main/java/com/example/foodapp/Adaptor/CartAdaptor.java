package com.example.foodapp.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodapp.Domain.TrendingDomain;
import com.example.foodapp.Helper.ManagementCart;
import com.example.foodapp.Interface.ChangeNumberItemListener;
import com.example.foodapp.R;

import java.util.ArrayList;

public class CartAdaptor extends RecyclerView.Adapter<CartAdaptor.ViewHolder> {
    private ArrayList<TrendingDomain> trendingDomains;
    private ManagementCart managementCart;
    private ChangeNumberItemListener changeNumberItemListener;

    public CartAdaptor(ArrayList<TrendingDomain> trendingDomains, Context context, ChangeNumberItemListener changeNumberItemListener) {
        this.trendingDomains = trendingDomains;
        this.managementCart = new ManagementCart(context);
        this.changeNumberItemListener = changeNumberItemListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdaptor.ViewHolder holder, int position) {
    holder.titleCartTV.setText(trendingDomains.get(position).getTitle());
    holder.feeEachTV.setText(String.valueOf(trendingDomains.get(position).getFee()));
    holder.totalEachTV.setText(String.valueOf(Math.round((trendingDomains.get(position).getNumberInCart()*trendingDomains.get(position).getFee())*100)/100));
    holder.numberItemTv.setText(String.valueOf(trendingDomains.get(position).getNumberInCart()));
    int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(trendingDomains.get(position).getPic(),"drawable",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.picCartIV);

        holder.plusCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managementCart.plusNumberFood(trendingDomains, position, new ChangeNumberItemListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemListener.changed();
                    }
                });
            }
        });
        holder.minusCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managementCart.minusNumberFood(trendingDomains, position, new ChangeNumberItemListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemListener.changed();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return trendingDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView titleCartTV,feeEachTV;
        ImageView picCartIV,plusCartBtn,minusCartBtn;
        TextView totalEachTV,numberItemTv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleCartTV=itemView.findViewById(R.id.titleCartTV);
            feeEachTV = itemView.findViewById(R.id.feeEachTV);
            picCartIV = itemView.findViewById(R.id.picCartIV);
            totalEachTV =itemView.findViewById(R.id.totalEachTV);
            numberItemTv = itemView.findViewById(R.id.numberItemTV);
            minusCartBtn = itemView.findViewById(R.id.minusCartBtn);
            plusCartBtn =itemView.findViewById(R.id.plusCartBtn);

        }
    }
}
