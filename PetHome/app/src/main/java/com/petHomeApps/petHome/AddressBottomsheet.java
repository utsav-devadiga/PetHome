package com.petHomeApps.petHome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.petHomeApps.petHome.Datamodels.AddressModel;
import com.petHomeApps.petHome.adapters.FirestoreAddressAdapter;

import org.jetbrains.annotations.NotNull;

import util.FireKeys;

public class AddressBottomsheet extends BottomSheetDialogFragment {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference addressref=db.collection(FireKeys.address);
    RecyclerView recyclerView;
    private FirestoreAddressAdapter adapter;
    FloatingActionButton add_address;
    View view;






    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

        view =inflater.inflate(R.layout.address_dialogue,container,false);

        add_address=view.findViewById(R.id.add_address_btn);
        add_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUpRecyclerView();
                Toast.makeText(getActivity(), "Opening DialogBox", Toast.LENGTH_SHORT).show();
                openDialog();
            }


        });






        return view;

    }

    private void setUpRecyclerView() {
        Query query = addressref.orderBy("pincode", Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<AddressModel> options = new FirestoreRecyclerOptions.Builder<AddressModel>()
                .setQuery(query, AddressModel.class)
                .build();

        adapter = new FirestoreAddressAdapter(options);

        recyclerView = view.findViewById(R.id.firestore_recylerview);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm=new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);



    }

    private void openDialog() {
        AddAddressDialog addAddressDialog=new AddAddressDialog();
        addAddressDialog.show(getActivity().getSupportFragmentManager(),getTag());
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }



    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
//        super.onStop();
//        if (adapter != null) {
//            adapter.stopListening();
//        }
    }

}
