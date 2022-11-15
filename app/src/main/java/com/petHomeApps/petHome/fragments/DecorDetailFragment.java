package com.petHomeApps.petHome.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.petHomeApps.petHome.Datamodels.DecorModel;
import com.petHomeApps.petHome.Datamodels.UserModel;
import com.petHomeApps.petHome.FeedbackDialogue;
import com.petHomeApps.petHome.R;

import org.jetbrains.annotations.NotNull;

import util.FireKeys;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DecorDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DecorDetailFragment extends Fragment {
    View view;
    TextView name, price, desc;
    TextView order_btn;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DecorDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DecorDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DecorDetailFragment newInstance(String param1, String param2) {
        DecorDetailFragment fragment = new DecorDetailFragment();
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
        view = inflater.inflate(R.layout.fragment_decor_detail, container, false);
        name = view.findViewById(R.id.deco_name);
        desc = view.findViewById(R.id.deco_description);
        price = view.findViewById(R.id.deco_price);


        order_btn=view.findViewById(R.id.order_btn);
        order_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                booknow();

                /*DecorBottomSheetAddress bsa=new DecorBottomSheetAddress();
                bsa.show(getActivity().getSupportFragmentManager(),bsa.getTag());*/

            }
        });

        return view;


    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            DecorDetailFragmentArgs args = DecorDetailFragmentArgs.fromBundle(getArguments());

            name.setText(args.getDecoModelName());
            desc.setText(args.getDecoModelDescription());
            price.setText(args.getDecoModelPrice());
        }
    }



    public void booknow(){
        FirebaseFirestore db=FirebaseFirestore.getInstance();
        String user= FirebaseAuth.getInstance().getCurrentUser().getUid();
        String order_id=db.collection(FireKeys.decor_orders).document().getId();
        String payment_id="12345";
        boolean payment_status=true;
        long dop=System.currentTimeMillis();
        String plan_name=name.getText().toString();

        DecorModel decorModel=new DecorModel(user,order_id,plan_name,payment_status,payment_id,dop);
        db.collection(FireKeys.decor_orders).document(order_id).set(decorModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<Void> task) {
                if(task.isSuccessful()){
                    FeedbackDialogue feedbackDialogue = new FeedbackDialogue(getActivity());
                    feedbackDialogue.Feedback("Order Placed Successful");
                }
                else{
                    Toast.makeText(getContext(), "Order Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

}