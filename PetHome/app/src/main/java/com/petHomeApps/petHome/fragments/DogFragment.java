package com.petHomeApps.petHome.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.petHomeApps.petHome.Datamodels.FAQ_model;
import com.petHomeApps.petHome.R;
import com.petHomeApps.petHome.adapters.FAQ_Adaptar;

import java.util.ArrayList;
import java.util.List;

import util.FireKeys;

import static util.FireKeys.faq_priority;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DogFragment extends Fragment {

    RecyclerView faq_recylerview;
    ArrayList<FAQ_model> faq_data_list;
    FirebaseFirestore db;
    FAQ_Adaptar faq_adaptar;
    View view;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DogFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DogFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DogFragment newInstance(String param1, String param2) {
        DogFragment fragment = new DogFragment();
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
        view = inflater.inflate(R.layout.fragment_dog, container, false);


        faq_recylerview = view.findViewById(R.id.faq_recylerview_dog);
        faq_recylerview.setLayoutManager(new LinearLayoutManager(getContext()));
        faq_data_list = new ArrayList<>();


        faq_adaptar = new FAQ_Adaptar(faq_data_list);
        faq_recylerview.setAdapter(faq_adaptar);

        db = FirebaseFirestore.getInstance();
        db.collection(FireKeys.dog_faq).orderBy(faq_priority).get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<DocumentSnapshot> firestore_faq_data = queryDocumentSnapshots.getDocuments();
                    for (DocumentSnapshot ds : firestore_faq_data) {
                        FAQ_model FAQ_model_obj = ds.toObject(FAQ_model.class);
                        faq_data_list.add(FAQ_model_obj);

                    }
                    //UPDATE ADAPTER
                    faq_adaptar.notifyDataSetChanged();

                });


        return view;
    }
}