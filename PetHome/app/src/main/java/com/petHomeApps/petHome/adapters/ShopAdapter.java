package com.petHomeApps.petHome.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.petHomeApps.petHome.Datamodels.PetModel;
import com.petHomeApps.petHome.Datamodels.ShopModel;
import com.petHomeApps.petHome.R;
import com.petHomeApps.petHome.fragments.ShopFragmentDirections;
import com.petHomeApps.petHome.fragments.DecoFragmentDirections;

import org.jetbrains.annotations.NotNull;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import util.FireKeys;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ShopViewHolder> {

    List<ShopModel> shopItems;
    Context context;
    View view2;
    FirebaseFirestore db;

    public ShopAdapter(List<ShopModel> shopItems, Context context, View view) {
        this.shopItems = shopItems;
        this.context = context;
        this.view2 = view;

    }

    @Override
    public ShopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_item, parent, false);
        return new ShopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShopViewHolder holder, int position) {

        Glide.with(context).load(shopItems.get(position).getPrd_img()).into(holder.product_img);
        holder.product_name.setText(shopItems.get(position).getName());
        holder.product_desc.setText(shopItems.get(position).getDescription());

        String prices = shopItems.get(position).getPrice();
        String[] pirce_array = prices.split(",");


        holder.product_price.setText("₹ " + pirce_array[0]);


        holder.shopCard.setOnClickListener(v -> {
            ShopFragmentDirections.ActionShopFragmentToShopDetailFragment action = ShopFragmentDirections.actionShopFragmentToShopDetailFragment(shopItems.get(position).getName(),
                    shopItems.get(position).getDescription(),
                    shopItems.get(position).getPrice(),
                    shopItems.get(position).getCategory(), shopItems.get(position));

            action.setName(shopItems.get(position).getName());
            action.setDesc(shopItems.get(position).getDescription());
            action.setPrice(shopItems.get(position).getPrice());
            action.setCategory(shopItems.get(position).getCategory());
            action.setShopData(shopItems.get(position));


            Navigation.findNavController(view2).navigate(action);
        });

    }

    @Override
    public int getItemCount() {
        return shopItems.size();
    }

    class ShopViewHolder extends RecyclerView.ViewHolder {

        ShapeableImageView product_img;
        TextView product_name,
                product_desc,
                product_price,
                stockinfo,
                rating;

        MaterialCardView shopCard;


        public ShopViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            product_img = itemView.findViewById(R.id.product_img);
            product_name = itemView.findViewById(R.id.product_name);
            product_desc = itemView.findViewById(R.id.product_desc);
            product_price = itemView.findViewById(R.id.product_price);
            shopCard = itemView.findViewById(R.id.card_item_shop);
        }
    }
}
