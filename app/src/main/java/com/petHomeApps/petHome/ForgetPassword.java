package com.petHomeApps.petHome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

import util.Regex;

public class ForgetPassword extends AppCompatActivity {
    Button forgot_btn;
    String email;
    EditText emailtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        forgot_btn = findViewById(R.id.forgot_btn);
        emailtext = findViewById(R.id.email_text_login_forgot);


        forgot_btn.setOnClickListener(v -> {
            email = emailtext.getText().toString().trim();
            if (!email.isEmpty() && Regex.MY_EMAIL.matcher(email).matches()) {
                FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FeedbackDialogue feedbackDialogue = new FeedbackDialogue(this);
                        feedbackDialogue.Feedback("Sent Link to email");
                    } else {
                        Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(this, "In-valid Email!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}