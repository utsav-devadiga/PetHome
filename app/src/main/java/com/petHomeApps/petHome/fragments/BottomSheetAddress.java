package com.petHomeApps.petHome.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.petHomeApps.petHome.Datamodels.AddressModel;
import com.petHomeApps.petHome.Datamodels.FAQ_model;
import com.petHomeApps.petHome.R;
import com.petHomeApps.petHome.adapters.AddressAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import util.FireKeys;

public class BottomSheetAddress extends BottomSheetDialogFragment {

    AddressAdapter addressAdapter;
    RecyclerView address_recyerview;
    ArrayList<AddressModel> address_dataholder=new ArrayList<>();



    public BottomSheetAddress() {
        // Required empty public constructor
    }


    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {


        View view=inflater.inflate(R.layout.bottom_address, container, false);
        address_recyerview=view.findViewById(R.id.address_recylerview);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        address_recyerview.setLayoutManager(linearLayoutManager);





        addressAdapter=new AddressAdapter(address_dataholder);
        address_recyerview.setAdapter(addressAdapter);



        getdata();



        return view;






    }

    private void getdata() {
        FirebaseFirestore db=FirebaseFirestore.getInstance();
        db.collection(FireKeys.User).document(FirebaseAuth.getInstance().getCurrentUser().getUid()).collection(FireKeys.address).get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                for(QueryDocumentSnapshot snapshot:task.getResult()){
                    AddressModel am=snapshot.toObject(AddressModel.class);
                    address_dataholder.add(am);
                }
                addressAdapter.notifyDataSetChanged();
            }
        });


    }
}
