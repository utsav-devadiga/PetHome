package com.petHomeApps.petHome.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.petHomeApps.petHome.Datamodels.FAQ_model;
import com.petHomeApps.petHome.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class FAQ_Adaptar extends RecyclerView.Adapter<FAQ_Adaptar.faq_classholder> {

    ArrayList<FAQ_model> FAQ_datalist;

    public FAQ_Adaptar(ArrayList<FAQ_model> FAQ_datalist) {
        this.FAQ_datalist = FAQ_datalist;
    }

    @NonNull
    @NotNull
    @Override
    public faq_classholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.faq_singlerow, parent, false);
        return new faq_classholder(view);


    }


    @Override
    public void onBindViewHolder(@NonNull @NotNull faq_classholder holder, int position) {
        holder.faq_question.setText(FAQ_datalist.get(position).getFaq_que());
        holder.faq_answer.setText(FAQ_datalist.get(position).getFaq_ans());

        holder.setIsRecyclable(false);
        holder.cardView.setOnClickListener(v -> {
            if (FAQ_datalist.get(position).isExpanded()) {
                holder.faq_answer.setVisibility(View.GONE);
                FAQ_datalist.get(position).setExpanded(false);
            } else {
                holder.faq_answer.setVisibility(View.VISIBLE);
                FAQ_datalist.get(position).setExpanded(true);
            }
        });


    }

    @Override
    public int getItemCount() {
        return FAQ_datalist.size();
    }

    class faq_classholder extends RecyclerView.ViewHolder {

        TextView faq_question, faq_answer;
        MaterialCardView cardView;

        public faq_classholder(@NonNull View itemView) {
            super(itemView);
            faq_question = itemView.findViewById(R.id.faq_question);
            faq_answer = itemView.findViewById(R.id.faq_answer);
            cardView = itemView.findViewById(R.id.item_card);
        }
    }
}
