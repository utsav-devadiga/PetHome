package com.petHomeApps.petHome.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.petHomeApps.petHome.Datamodels.AddressModel;
import com.petHomeApps.petHome.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.addressviewholder>
{
    ArrayList<AddressModel> address_dataholder;

    public AddressAdapter(ArrayList<AddressModel> dataholder) {
        this.address_dataholder= dataholder;
    }

    @NonNull
    @NotNull
    @Override
    public addressviewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {


        if (viewType == 1) {
            // inflate your first item layout & return that viewHolder
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.address_single_row,parent,false);
            return new addressviewholder(view);
        } else {
            // inflate your second item layout & return that viewHolder
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.new_address_layout,parent,false);
            return new addressviewholder(view);
        }




    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull addressviewholder holder, int position) {

        holder.name.setText(address_dataholder.get(position).getId());
        holder.fulladdress.setText(address_dataholder.get(position).getFull_address());
        holder.landmark.setText(address_dataholder.get(position).getLandmark());
        holder.landmark.setText(address_dataholder.get(position).getLandmark());
        holder.city.setText(address_dataholder.get(position).getCity());
        holder.pincode.setText(address_dataholder.get(position).getPincode());
        holder.state.setText(address_dataholder.get(position).getState());
        holder.country.setText(address_dataholder.get(position).getCountry());







    }

    @Override
    public int getItemCount() {
        return address_dataholder.size();
    }


    @Override
    public int getItemViewType(int position) {
        if(position==(getItemCount()-1))return 1;
        else return 2;
    }

    class addressviewholder extends RecyclerView.ViewHolder
    {

        TextView name,fulladdress,landmark,towntext,city,state,pincode,country;


        public addressviewholder(@NonNull View itemView)
        {
            super(itemView);
            name=itemView.findViewById(R.id.username);
            fulladdress=itemView.findViewById(R.id.full_address);
            landmark=itemView.findViewById(R.id.landmark);
            towntext=itemView.findViewById(R.id.towntext);
            city=itemView.findViewById(R.id.city);
            state=itemView.findViewById(R.id.state);
            pincode=itemView.findViewById(R.id.pincode);
            country=itemView.findViewById(R.id.countrytext);


        }
    }
}