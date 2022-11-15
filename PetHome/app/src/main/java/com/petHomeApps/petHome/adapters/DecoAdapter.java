package com.petHomeApps.petHome.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.PagerAdapter;

import com.google.android.material.card.MaterialCardView;
import com.petHomeApps.petHome.Datamodels.DecoModel;
import com.petHomeApps.petHome.R;
import com.petHomeApps.petHome.fragments.DecoFragmentDirections;

import java.util.List;

public class DecoAdapter extends PagerAdapter {


    private List<DecoModel> models;
    private LayoutInflater layoutInflater;
    private Context context;
    private View view2;

    public DecoAdapter(List<DecoModel> models, Context context, View view) {
        this.models = models;
        this.context = context;
        this.view2 = view;

    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.deco_item, container, false);
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(context, R.anim.testanim);

        TextView title, desc, price;
        MaterialCardView decor_card;


        price = view.findViewById(R.id.price);
        title = view.findViewById(R.id.title);
        desc = view.findViewById(R.id.desc);
        decor_card = view.findViewById(R.id.decord_card);

        decor_card.setAnimation(hyperspaceJumpAnimation);
        title.setText(models.get(position).getName());
        desc.setText(models.get(position).getDescription());
        price.setText(models.get(position).getPrice());

        decor_card.setOnClickListener(v -> {
           /* ConfirmationAction action =
                    SpecifyAmountFragmentDirections.confirmationAction();
            action.setAmount(amount);*/
            DecoFragmentDirections.ActionDecoFragmentToDecorDetailFragment action = DecoFragmentDirections.actionDecoFragmentToDecorDetailFragment(models.get(position).getName(),
                    models.get(position).getDescription(),
                    models.get(position).getPrice());

            action.setDecoModelDescription(models.get(position).getDescription());
            action.setDecoModelName(models.get(position).getName());
            action.setDecoModelPrice(models.get(position).getPrice());


            Navigation.findNavController(view2).navigate(action);

        });


        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

}
