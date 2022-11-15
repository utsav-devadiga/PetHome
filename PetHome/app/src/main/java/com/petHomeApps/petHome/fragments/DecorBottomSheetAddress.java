package com.petHomeApps.petHome.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.petHomeApps.petHome.Datamodels.AddressModel;
import com.petHomeApps.petHome.R;
import com.petHomeApps.petHome.adapters.AddressAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import util.FireKeys;

public class DecorBottomSheetAddress extends BottomSheetDialogFragment {

    AddressAdapter addressAdapter;
    RecyclerView decor_address_recyerview;
    ArrayList<AddressModel> decor_address_dataholder=new ArrayList<>();



    public DecorBottomSheetAddress() {
        // Required empty public constructor
    }


    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {


        View view=inflater.inflate(R.layout.decor_bottom_address, container, false);
        decor_address_recyerview=view.findViewById(R.id.decor_address_recylerview);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        decor_address_recyerview.setLayoutManager(linearLayoutManager);





        addressAdapter=new AddressAdapter(decor_address_dataholder);
        decor_address_recyerview.setAdapter(addressAdapter);



        getdata();



        return view;






    }

    private void getdata() {
        FirebaseFirestore db=FirebaseFirestore.getInstance();
        db.collection(FireKeys.User).document(FirebaseAuth.getInstance().getCurrentUser().getUid()).collection(FireKeys.address).get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                for(QueryDocumentSnapshot snapshot:task.getResult()){
                    AddressModel am=snapshot.toObject(AddressModel.class);
                    decor_address_dataholder.add(am);
                }
                addressAdapter.notifyDataSetChanged();
            }
        });


    }
}



