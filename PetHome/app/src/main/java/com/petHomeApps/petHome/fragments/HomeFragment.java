
package com.petHomeApps.petHome.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.petHomeApps.petHome.Datamodels.PetModel;
import com.petHomeApps.petHome.PetsRegistration;
import com.petHomeApps.petHome.R;
import com.petHomeApps.petHome.adapters.PetAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import util.FireKeys;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    RecyclerView petCycle;
    PetAdapter petAdapter;
    FirebaseFirestore db;
    FirebaseAuth mAuth;
    ArrayList<PetModel> PetList = new ArrayList<>();
    RelativeLayout faq_button, shop_button, deco_button, profile_btn;
    FloatingActionButton fab;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        petCycle = view.findViewById(R.id.owned_pet_cycle);
        fab = view.findViewById(R.id.fab);
        faq_button = view.findViewById(R.id.faq_btn);
        deco_button = view.findViewById(R.id.deco_btn);
        shop_button = view.findViewById(R.id.shop);
        profile_btn = view.findViewById(R.id.profile_btn);


        profile_btn.setOnClickListener(v -> {

            Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.action_homeFragment_to_profileFragment);
        });

        faq_button.setOnClickListener(v -> {

            Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.action_homeFragment_to_faqFragment);
        });

        deco_button.setOnClickListener(v -> {

            Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.action_homeFragment_to_decoFragment);
        });

        shop_button.setOnClickListener(v -> {

            Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.action_homeFragment_to_shopFragment);

        });
        fab.setOnClickListener(v -> {

            Intent petintent = new Intent(getContext(), PetsRegistration.class);
            getActivity().startActivity(petintent);
        });

        LinearLayoutManager linearLayoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        petCycle.setLayoutManager(linearLayoutManager);
        petAdapter = new PetAdapter(getContext(), PetList,view);
        petCycle.setAdapter(petAdapter);
        getData();


        return view;
    }

    private void getData() {
        if (PetList.isEmpty()) {
            CollectionReference petRef = db.collection(FireKeys.User).document(mAuth.getCurrentUser().getUid()).collection(FireKeys.Pet);

            petRef.get().addOnCompleteListener(task -> {

                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot snapshot : task.getResult()) {
                        PetModel uploadFileModel = snapshot.toObject(PetModel.class);
                        PetList.add(uploadFileModel);

                    }
                    petAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "Something went Wrong!\n" + task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


}