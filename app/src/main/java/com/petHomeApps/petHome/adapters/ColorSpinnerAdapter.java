package com.petHomeApps.petHome.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.card.MaterialCardView;
import com.petHomeApps.petHome.Datamodels.SpinnerColors;
import com.petHomeApps.petHome.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ColorSpinnerAdapter extends ArrayAdapter {

    LayoutInflater layoutInflater;

    public ColorSpinnerAdapter(@NonNull Context context, int resource, @NonNull List<SpinnerColors> colors) {
        super(context, resource, colors);
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View rowView = layoutInflater.inflate(R.layout.color_spinner, null, true);
        SpinnerColors spinnerColors = (SpinnerColors) getItem(position);

        MaterialCardView cardView = rowView.findViewById(R.id.item_color);
        TextView textView = rowView.findViewById(R.id.color_text_item);


        cardView.setCardBackgroundColor(Color.parseColor(spinnerColors.getColorCode()));
        textView.setText(spinnerColors.getColorCode());

        return rowView;
    }

    @Override
    public View getDropDownView(int position, @Nullable @org.jetbrains.annotations.Nullable View convertView, @NonNull @NotNull ViewGroup parent) {
        if (convertView == null)
            convertView = layoutInflater.inflate(R.layout.color_spinner, parent, false);
        SpinnerColors spinnerColors = (SpinnerColors) getItem(position);

        MaterialCardView cardView = convertView.findViewById(R.id.item_color);
        TextView textView = convertView.findViewById(R.id.color_text_item);


        cardView.setCardBackgroundColor(Color.parseColor(spinnerColors.getColorCode()));
        textView.setText(spinnerColors.getColorCode());

        return convertView;
    }
}
