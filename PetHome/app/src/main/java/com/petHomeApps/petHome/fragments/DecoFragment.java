package com.petHomeApps.petHome.fragments;

import android.animation.ArgbEvaluator;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.petHomeApps.petHome.adapters.DecoAdapter;
import com.petHomeApps.petHome.Datamodels.DecoModel;
import com.petHomeApps.petHome.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DecoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DecoFragment extends Fragment {
    ViewPager viewPager;
    View view;
    List<DecoModel> decoModelList;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    DecoAdapter adapter;
    KonfettiView konfettiView;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DecoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DecoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DecoFragment newInstance(String param1, String param2) {
        DecoFragment fragment = new DecoFragment();
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
        view = inflater.inflate(R.layout.fragment_deco, container, false);
        decoModelList = new ArrayList<>();
        decoModelList.add(new DecoModel("Basic Plan", "₹1500", "-Decor with balloons with pet name\n-Cake \n-Pet-food for guest \n-E invitation"));
        decoModelList.add(new DecoModel("Premium Plan", "₹2500", "-Cake\n-Pet-food for guest\n-Return Gifts (cups or pet theme diary)\n-Decor with balloons pet name\n-Photo wall for everyone\n-E invitation"));
        decoModelList.add(new DecoModel("Platinum Plan", "₹4000", "-Cake\n-Pet-food for guest\n-Return Gifts (T-shirts) or custom gifts\n-Decor with balloons and 3D pet print (custom color available)\n-Pre birthday photo shoot\n-E invitation\n-Photographer / Videographer\n-Photo wall"));
        adapter = new DecoAdapter(decoModelList, getContext(),view);
        viewPager = view.findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130, 0, 130, 0);

        Integer[] colors_temp = {
                ContextCompat.getColor(getContext(), R.color.white),
                ContextCompat.getColor(getContext(), R.color.color2),
                ContextCompat.getColor(getContext(), R.color.color3)
        };
        colors = colors_temp;

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                if (position < (adapter.getCount() - 1) && position < (colors.length - 1)) {
                   /* viewPager.setBackgroundColor(

                            (Integer) argbEvaluator.evaluate(
                                    positionOffset,
                                    colors[position],
                                    colors[position + 1]
                            )
                    );*/
                } else {
                  //  viewPager.setBackgroundColor(colors[colors.length - 1]);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


         /* konfettiView = view.findViewById(R.id.viewKonfetti);

        konfettiView.build()
                .addColors(R.color.primaryLight2, Color.WHITE, R.color.primaryLight)
                .setDirection(0.0, 360.0)
                .setSpeed(1f, 4f)
                .setFadeOutEnabled(true)
                .setTimeToLive(800L)
                .addShapes(Shape.Square.INSTANCE, Shape.Circle.INSTANCE)
                .addSizes(new Size(12, 5f))
                .setPosition(0f, konfettiView.getX()+1200f, 0f, 0f)
                .streamFor(200, 4000L);
*/
        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

}