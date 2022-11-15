package com.petHomeApps.petHome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.petHomeApps.petHome.fragments.HomeFragment;
import com.petHomeApps.petHome.fragments.ProfileFragment;

import org.jetbrains.annotations.NotNull;

import util.MyPasswordTransformationMethod;

public class MainActivity extends AppCompatActivity {

    RelativeLayout mainLayout;
    LinearLayout member_lay;
    private FirebaseAuth mAuth;
    ProgressBar loginLoader;
    TextView login_btn_text;
    RelativeLayout login_btn;
    EditText email_text, password_text;
    ImageView visibleimg;
    boolean visible = false;
    TextView forgot_text;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        mainLayout = findViewById(R.id.main_layout);
        member_lay = findViewById(R.id.member_lay);
        loginLoader = findViewById(R.id.Loader_login);
        login_btn_text = findViewById(R.id.btn_login_text);
        login_btn = findViewById(R.id.login_btn);
        email_text = findViewById(R.id.email_text_login);
        password_text = findViewById(R.id.password_text_login);
        visibleimg = findViewById(R.id.visible_img);
        forgot_text = findViewById(R.id.forgot_text);

        forgot_text.setOnClickListener(v -> {
            Intent forgetIntent = new Intent(MainActivity.this,ForgetPassword.class);
            startActivity(forgetIntent);
        });





        visibleimg.setOnClickListener(v -> {
            visible = !visible;
            if (visible) {
                visibleimg.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_visible_off));
                password_text.setTransformationMethod(new HideReturnsTransformationMethod());

            } else {
                visibleimg.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_visible));
                password_text.setTransformationMethod(new PasswordTransformationMethod());
            }
        });


        member_lay.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterUser.class);
            startActivity(intent);
            finish();
        });

        login_btn.setOnClickListener(v -> {
            show_loading();
            if (!email_text.getText().toString().trim().isEmpty() && !password_text.getText().toString().trim().isEmpty()) {
                user_sign_in(email_text.getText().toString().trim(), password_text.getText().toString().trim());
            } else {
                hide_loading();
                Snackbar snackbar = Snackbar.make(mainLayout, "Invalid Input", Snackbar.LENGTH_SHORT);
                snackbar.setBackgroundTint(ContextCompat.getColor(MainActivity.this, R.color.error));
                snackbar.show();
            }
        });


    }

    private void show_loading() {
        loginLoader.setVisibility(View.VISIBLE);
        login_btn_text.setVisibility(View.GONE);
    }

    private void hide_loading() {
        loginLoader.setVisibility(View.GONE);
        login_btn_text.setVisibility(View.VISIBLE);
    }

    private void user_sign_in(String email, String password) {
        show_loading();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information

                        FirebaseUser user = mAuth.getCurrentUser();
                        Snackbar snackbar = Snackbar.make(mainLayout, "Success! welcome " + user.getUid(), Snackbar.LENGTH_SHORT);
                        snackbar.setBackgroundTint(ContextCompat.getColor(MainActivity.this, R.color.success));
                        snackbar.show();
                        Intent homeIntent = new Intent(this, HomeActivity.class);
                        startActivity(homeIntent);
                        finish();

                    } else {
                        // If sign in fails, display a message to the user.
                        Snackbar snackbar = Snackbar.make(mainLayout, task.getException().getLocalizedMessage(), Snackbar.LENGTH_SHORT);
                        snackbar.setBackgroundTint(ContextCompat.getColor(MainActivity.this, R.color.error));
                        snackbar.show();
                        hide_loading();

                    }
                });
    }
}