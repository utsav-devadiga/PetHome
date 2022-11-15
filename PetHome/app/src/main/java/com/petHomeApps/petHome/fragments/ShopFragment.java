package com.petHomeApps.petHome.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.petHomeApps.petHome.Datamodels.ShopModel;
import com.petHomeApps.petHome.R;
import com.petHomeApps.petHome.adapters.ShopAdapter;

import java.util.ArrayList;
import java.util.List;

import util.FireKeys;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShopFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShopFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    FirebaseFirestore db;
    RecyclerView ShopCycle;
    ArrayList<ShopModel> priceList = new ArrayList<>();
    ShopAdapter shopAdapter;
    List<String> price_List;

    public ShopFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShopFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShopFragment newInstance(String param1, String param2) {
        ShopFragment fragment = new ShopFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shop, container, false);
        ShopCycle = view.findViewById(R.id.Shopcycle);
        ShopCycle.setLayoutManager(new LinearLayoutManager(getContext()));


        db = FirebaseFirestore.getInstance();
        CollectionReference medref = db.collection(FireKeys.shop);

        medref.get().addOnSuccessListener(queryDocumentSnapshots -> {

            for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots) {
                ShopModel shopModel = queryDocumentSnapshot.toObject(ShopModel.class);
                priceList.add(shopModel);

            }
            shopAdapter = new ShopAdapter(priceList, getContext(), view);
            ShopCycle.setAdapter(shopAdapter);
            shopAdapter.notifyDataSetChanged();
        });


        return view;

    }
}