package com.petHomeApps.petHome.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;
import com.petHomeApps.petHome.AddressBottomsheet;
import com.petHomeApps.petHome.Datamodels.ShopModel;
import com.petHomeApps.petHome.Datamodels.SpinnerColors;
import com.petHomeApps.petHome.FeedbackDialogue;
import com.petHomeApps.petHome.R;
import com.petHomeApps.petHome.adapters.ColorSpinnerAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShopDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShopDetailFragment extends Fragment {
    View view;
    Spinner spinner_color;
    TextView nameText, category_text, price_text, description_text;
    MaterialCardView booknow;
    ImageView imageView;
    Spinner sp1;
    String[] price_array;

    ArrayList<SpinnerColors> spinList = new ArrayList<>();


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ShopDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShopDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShopDetailFragment newInstance(String param1, String param2) {
        ShopDetailFragment fragment = new ShopDetailFragment();
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
        view = inflater.inflate(R.layout.fragment_shop_detail, container, false);
        nameText = view.findViewById(R.id.product_name_text);
        price_text = view.findViewById(R.id.price_text);
        category_text = view.findViewById(R.id.category_text);
        description_text = view.findViewById(R.id.description_text);
        booknow = view.findViewById(R.id.booknow_btn);
        imageView = view.findViewById(R.id.imageCycle);
        spinner_color = view.findViewById(R.id.color_spinner_shop);
        sp1 = view.findViewById(R.id.spinner_size);


        ColorSpinnerAdapter customAdapter = new ColorSpinnerAdapter(getContext(), R.layout.color_spinner, spinList);
        spinner_color.setAdapter(customAdapter);


        booknow.setOnClickListener(v -> {
           // FeedbackDialogue feedbackDialogue = new FeedbackDialogue(getActivity());
           // feedbackDialogue.Feedback("Order Placed Successful");

            AddressBottomsheet bottomsheet =new AddressBottomsheet();
            bottomsheet.show(getActivity().getSupportFragmentManager(),"");

        });


        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                // TODO Auto-generated method stub
                price_text.setText("₹ " + price_array[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            ShopDetailFragmentArgs args = ShopDetailFragmentArgs.fromBundle(getArguments());

            nameText.setText(args.getName());
            category_text.setText(args.getCategory());
            description_text.setText(args.getDesc());
            String prices = args.getPrice();
            price_array = prices.split(",");


            ShopModel shopModel = args.getShopData();
            Glide.with(getContext()).load(shopModel.getPrd_img()).into(imageView);
            final List<String> list = new ArrayList<String>();
            for (Map.Entry<String, Integer> entry : shopModel.getColor().entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();
                spinList.add(new SpinnerColors(key));
            }

            for (Map.Entry<String, Integer> entry : shopModel.getSize().entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();
                list.add(key);
            }


            ArrayAdapter<String> adp1 = new ArrayAdapter<String>(getContext(),
                    android.R.layout.simple_list_item_1, list);
            adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp1.setAdapter(adp1);
        }
    }
}