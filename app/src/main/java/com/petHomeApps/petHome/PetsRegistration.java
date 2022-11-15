package com.petHomeApps.petHome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.petHomeApps.petHome.Datamodels.PetModel;

import util.FireKeys;

public class PetsRegistration extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    EditText pet_name, pet_age_date, pet_age_month, pet_age_year;
    RelativeLayout submit_btn;
    AutoCompleteTextView pet_breed;

    private FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    Spinner pet_selection_spinner;
    String pet_selected_text;
    int pet_selected_index;

    RadioButton male, female;
    String gender_text;
    RelativeLayout main_lay;

    TextView loading_text;
    ProgressBar loading_bar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pets_regestration);
        mAuth = FirebaseAuth.getInstance();

        submit_btn = findViewById(R.id.sumbit_btn);
        pet_name = findViewById(R.id.pet_name);

        pet_age_date = findViewById(R.id.date);
        pet_age_month = findViewById(R.id.month);
        pet_age_year = findViewById(R.id.year);
        loading_text = findViewById(R.id.register_pet_btn_text);
        loading_bar = findViewById(R.id.pet_progress_bar);
        main_lay = findViewById(R.id.pet_reg_main_lay);

        male = findViewById(R.id.male);
        female = findViewById(R.id.female);


        submit_btn.setOnClickListener(v -> {

            show_loading();
            if (male.isChecked()) {
                gender_text = male.getText().toString();

            } else if (female.isChecked()) {
                gender_text = female.getText().toString();
            } else {
                hide_loading();
                Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
            }
            if (!pet_selected_text.isEmpty()
                    && !pet_name.getText().toString().isEmpty()
                    && !pet_breed.getText().toString().isEmpty()
                    && !pet_age_date.getText().toString().isEmpty()
                    && !pet_age_month.getText().toString().isEmpty()
                    && !pet_age_year.getText().toString().isEmpty()
                    && !gender_text.isEmpty()) {
                String user = mAuth.getCurrentUser().getUid();
                String petid = db.collection(FireKeys.User).document(user).collection(FireKeys.Pet).document().getId();
                PetModel PetModel_Object = new PetModel(
                        petid, pet_selected_text, pet_selected_index,
                        pet_name.getText().toString(),
                        pet_breed.getText().toString(),
                        pet_age_date.getText().toString(),
                        pet_age_month.getText().toString(),
                        pet_age_year.getText().toString(),
                        gender_text);
                db.collection(FireKeys.User).document(user).collection(FireKeys.Pet).document(petid).set(PetModel_Object).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Snackbar snackbar = Snackbar.make(main_lay, "Pet is Registered!", Snackbar.LENGTH_SHORT);
                        snackbar.setBackgroundTint(ContextCompat.getColor(PetsRegistration.this, R.color.success));
                        snackbar.show();
                        Intent homeIntent = new Intent(PetsRegistration.this, HomeActivity.class);
                        startActivity(homeIntent);
                        finish();
                    } else {
                        hide_loading();
                        Snackbar snackbar = Snackbar.make(main_lay, task.getException().getLocalizedMessage(), Snackbar.LENGTH_SHORT);
                        snackbar.setBackgroundTint(ContextCompat.getColor(this, R.color.error));
                        snackbar.show();
                    }
                });
            } else {
                hide_loading();
                Snackbar snackbar = Snackbar.make(main_lay, "In-valid Details", Snackbar.LENGTH_SHORT);
                snackbar.setBackgroundTint(ContextCompat.getColor(this, R.color.error));
                snackbar.show();
            }


        });


        pet_breed = findViewById(R.id.pet_breed);
        String[] pet_breeds = getResources().getStringArray(R.array.pet_breed_list);
        ArrayAdapter<String> BreedAdapter = new ArrayAdapter<String>(PetsRegistration.this, android.R.layout.simple_list_item_1, pet_breeds);
        pet_breed.setAdapter(BreedAdapter);
        pet_breed.setThreshold(1);


        pet_selection_spinner = findViewById(R.id.pet_selection_spinner);
        String[] pet_selected_data = getResources().getStringArray(R.array.pet_list);
        ArrayAdapter<String> SpinnerAdapter = new ArrayAdapter<>(PetsRegistration.this, android.R.layout.simple_list_item_1, pet_selected_data);
        pet_selection_spinner.setAdapter(SpinnerAdapter);
        pet_selection_spinner.setOnItemSelectedListener(this);


    }

    private void show_loading() {
        loading_text.setVisibility(View.GONE);
        loading_bar.setVisibility(View.VISIBLE);

    }

    private void hide_loading() {
        loading_text.setVisibility(View.VISIBLE);
        loading_bar.setVisibility(View.GONE);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        pet_selected_index = position;
        pet_selected_text = pet_selection_spinner.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}