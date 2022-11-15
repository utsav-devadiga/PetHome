package com.petHomeApps.petHome.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.petHomeApps.petHome.Datamodels.PetModel;
import com.petHomeApps.petHome.R;
import com.petHomeApps.petHome.fragments.HomeFragmentDirections;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.PetsViewHolder> {

    Context context;
    View view2;
    ArrayList<PetModel> petList;





    public PetAdapter(Context context, ArrayList<PetModel> petList,View view2) {
        this.context = context;
        this.petList = petList;
        this.view2=view2;
    }




    @NonNull
    @NotNull
    @Override
    public PetsViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pet, parent, false);
        return new PetsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PetsViewHolder holder, int position) {

        String Date_string = petList.get(position).getPet_age_date() + "/" + petList.get(position).getPet_age_month() + "/" + petList.get(position).getPet_age_year();
        holder.petName.setText(petList.get(position).getPet_name());
        holder.petDate.setText(Date_string);

        if (petList.get(position).getPet_selected_category().toLowerCase().equals("cat")) {
            holder.petImage.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_cat));
        } else {
            holder.petImage.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_dog));
        }

        if (petList.get(position).getGender_text().toLowerCase().equals("male")) {
            holder.petGender.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_male_gender_sign));
        } else {
            holder.petGender.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_female_sign));
        }


        holder.pet_cardview.setOnClickListener(v -> {
            HomeFragmentDirections.ActionHomeFragmentToPetProfileFragment action = HomeFragmentDirections.actionHomeFragmentToPetProfileFragment(petList.get(position));
            action.setPetData(petList.get(position));
            Navigation.findNavController(view2).navigate(action);
            petList.clear();
        });







    }

    @Override
    public int getItemCount() {
        return petList.size();
    }

    class PetsViewHolder extends RecyclerView.ViewHolder {
        ImageView petImage, petGender;
        TextView petName;
        TextView petDate;
        MaterialCardView pet_cardview;


        public PetsViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            petName = itemView.findViewById(R.id.pet_name_item);
            petGender = itemView.findViewById(R.id.pet_gender_item);
            petImage = itemView.findViewById(R.id.pet_image_item);
            petDate = itemView.findViewById(R.id.pet_date);



            pet_cardview=itemView.findViewById(R.id.pet_cardview);







        }
    }


}
