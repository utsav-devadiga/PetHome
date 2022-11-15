package com.petHomeApps.petHome;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;


public class FeedbackDialogue {
    private Activity activity;
    private AlertDialog view;
    Button Done_btn;
    TextView DescriptionText;

    public FeedbackDialogue(Activity myActivity) {
        activity = myActivity;
    }

    public void Feedback(String Description) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.feedback_dialogue, null));
        builder.setCancelable(true);
        view = builder.create();
        view.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        view.requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams wmlp = view.getWindow().getAttributes();
        wmlp.gravity = Gravity.BOTTOM;
        wmlp.verticalMargin = 0.02F;
       wmlp.windowAnimations = R.style.Animation_Design_BottomSheetDialog;

        view.show();


        Done_btn = view.findViewById(R.id.feedback_done);

        DescriptionText = view.findViewById(R.id.feedback_description);

        if (Description.isEmpty()) {
            Description = "Successful";
        }
        DescriptionText.setText(Description);

        Done_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeFeedBack();
            }
        });



    }

    public void closeFeedBack() {
        view.dismiss();
    }

}
