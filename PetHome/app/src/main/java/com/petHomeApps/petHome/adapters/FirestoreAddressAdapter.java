package com.petHomeApps.petHome.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.petHomeApps.petHome.Datamodels.AddressModel;
import com.petHomeApps.petHome.R;

import org.jetbrains.annotations.NotNull;

public class FirestoreAddressAdapter extends FirestoreRecyclerAdapter<AddressModel, FirestoreAddressAdapter.FirestoreAddressHolder> {



    public FirestoreAddressAdapter(@NonNull @NotNull FirestoreRecyclerOptions<AddressModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull  FirestoreAddressHolder holder, int position, @NonNull @NotNull AddressModel model) {
        holder.pincode.setText(model.getPincode());
        holder.fulladdress.setText(model.getFull_address());
        holder.landmark.setText(model.getLandmark());
        holder.town.setText(model.getTown());
        holder.city.setText(model.getCity());
        holder.state.setText(model.getState());
        holder.country.setText(model.getCountry());
    }


    @Override
    public FirestoreAddressHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.firestore_recylerview_single_item,parent,false);
        return new FirestoreAddressHolder(v);
    }

    public class FirestoreAddressHolder extends RecyclerView.ViewHolder {

        TextView  pincode,
                 fulladdress,
                 landmark,
                 town,
                 city,
                 state,
                country;

        public FirestoreAddressHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            pincode=itemView.findViewById(R.id.fbpincode);
            fulladdress=itemView.findViewById(R.id.fbfull_address);
            landmark=itemView.findViewById(R.id.fblandmark);
            town=itemView.findViewById(R.id.fbtowntext);
            city=itemView.findViewById(R.id.fbcity);
            state=itemView.findViewById(R.id.fbstate);
            country=itemView.findViewById(R.id.fbcity);
        }
    }
}




