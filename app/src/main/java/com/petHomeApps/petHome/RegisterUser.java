package com.petHomeApps.petHome;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.petHomeApps.petHome.Datamodels.AddressModel;
import com.petHomeApps.petHome.Datamodels.UserModel;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import util.FireKeys;
import util.Regex;

public class RegisterUser extends AppCompatActivity {

    EditText firstName, lastName, phoneNum, emailText, passwordText, full_address_text, city_edittext, town_edittext, country_edittext, state_edittext, pincode_edittext, landmark_editText;
    //here is EditText for firstName, lastName, phoneNum, emailText, passwordText,full_address_text;

    RelativeLayout register_btn;
    //here is register_btn

    TextView register_btn_text;
    //here is register_btn text

    LinearLayout mainLayout;
    //here is sign-in btn

    LinearLayout member_lay_reg;
    //here is sign-in button layout

    UserModel userModel;
    //here is object of User-model class

    ProgressBar registerLoader;
    //here is progress bar for loading

    private FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String towns, city, country, state, pincode;
    String landmark;
    ImageView visibleimg;
    boolean visible = false;

    //volley request obj
    RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firstName = findViewById(R.id.first_name_text);
        lastName = findViewById(R.id.last_name_text);
        phoneNum = findViewById(R.id.phone_num);
        emailText = findViewById(R.id.email_text);
        passwordText = findViewById(R.id.password_text);
        register_btn = findViewById(R.id.register_btn);
        mAuth = FirebaseAuth.getInstance();
        registerLoader = findViewById(R.id.registerLoader);
        register_btn_text = findViewById(R.id.register_btn_text);
        mainLayout = findViewById(R.id.mainRegLay);
        full_address_text = findViewById(R.id.address_full_text);
        member_lay_reg = findViewById(R.id.member_lay_reg);
        visibleimg = findViewById(R.id.visible_img_reg);
        city_edittext = findViewById(R.id.city_text);
        state_edittext = findViewById(R.id.state_text);
        town_edittext = findViewById(R.id.town_text);
        country_edittext = findViewById(R.id.country_text);
        pincode_edittext = findViewById(R.id.pin_code_text);
        landmark_editText = findViewById(R.id.landmark_text);
        mRequestQueue = Volley.newRequestQueue(RegisterUser.this);


        country_edittext.setFocusable(false);
        city_edittext.setFocusable(false);
        state_edittext.setFocusable(false);
        town_edittext.setFocusable(false);

        pincode_edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if (s.toString().length() == 6) {
                    getDataFromPinCode(s.toString());
                } else {
                    country_edittext.setText(null);
                    town_edittext.setText(null);
                    city_edittext.setText(null);
                    state_edittext.setText(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        visibleimg.setOnClickListener(v -> {
            visible = !visible;
            if (visible) {
                visibleimg.setImageDrawable(ContextCompat.getDrawable(RegisterUser.this, R.drawable.ic_visible_off));
                passwordText.setTransformationMethod(new HideReturnsTransformationMethod());

            } else {
                visibleimg.setImageDrawable(ContextCompat.getDrawable(RegisterUser.this, R.drawable.ic_visible));
                passwordText.setTransformationMethod(new PasswordTransformationMethod());
            }
        });

        member_lay_reg.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterUser.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        register_btn.setOnClickListener(v -> {
            showLoading();

            String fullName = firstName.getText().toString().trim() +
                    " " + lastName.getText().toString().trim();

            pincode = pincode_edittext.getText().toString().trim();
            landmark = landmark_editText.getText().toString().trim();

            UserModel userModel = new UserModel(fullName,
                    phoneNum.getText().toString().trim(),
                    emailText.getText().toString().trim(),
                    full_address_text.getText().toString().trim(), city, towns, landmark, pincode, state, country, System.currentTimeMillis());

            if (verifyUser(userModel)) {
                registerUser(userModel.getEmail(), passwordText.getText().toString().trim(), userModel);
            }
        });
    }

    private void registerUser(String email, String password, UserModel model) {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {

                        // Sign in success, update UI with the signed-in user's information
                        FirebaseUser user = mAuth.getCurrentUser();
                        db.collection(FireKeys.User).document(user.getUid()).set(model).addOnSuccessListener(task1 -> {
                            //random id for addresses (primary)
                            String address_id = db.collection(FireKeys.User).document(user.getUid()).collection(FireKeys.address).document().getId();
                            //model class set up for addresses
                            AddressModel addressModel = new AddressModel(model.getPincode(), model.getFull_address(), model.getLandmark(), model.getTown_text(), model.getCity_text(), model.getState(), model.getCountry(), address_id);

                            //storing address in address collection
                            db.collection(FireKeys.User).document(user.getUid()).collection(FireKeys.address).document(address_id).set(addressModel).addOnCompleteListener(task2 -> {
                                Snackbar snackbar = Snackbar.make(mainLayout, "Data Saved!", Snackbar.LENGTH_SHORT);
                                snackbar.setBackgroundTint(ContextCompat.getColor(RegisterUser.this, R.color.success));
                                TextView tv = (TextView) (snackbar.getView()).findViewById(com.google.android.material.R.id.snackbar_text);
                                Typeface font = ResourcesCompat.getFont(RegisterUser.this, R.font.futura_round);
                                tv.setTypeface(font);
                                snackbar.show();

                                //pet page
                                Intent petregIntent = new Intent(RegisterUser.this, PetsRegistration.class);
                                startActivity(petregIntent);
                                finish();
                            });

                        }).addOnFailureListener(task3 -> {
                            Snackbar snackbar = Snackbar.make(mainLayout, task3.getLocalizedMessage(), Snackbar.LENGTH_SHORT);
                            snackbar.setBackgroundTint(ContextCompat.getColor(this, R.color.error));
                            snackbar.show();
                            hideloading();
                        });
                        hideloading();
                    } else {
                        hideloading();
                        // If sign in fails, display a message to the user.
                        Toast.makeText(RegisterUser.this, task.getException().getLocalizedMessage(),
                                Toast.LENGTH_SHORT).show();

                    }

                    // ...
                });

    }

    private boolean verifyUser(UserModel userModel) {


        if (userModel.getEmail().isEmpty() || !Regex.MY_EMAIL.matcher(userModel.getEmail()).matches()) {
            Snackbar snackbar = Snackbar.make(mainLayout, "Invalid email!", Snackbar.LENGTH_SHORT);
            snackbar.setBackgroundTint(ContextCompat.getColor(this, R.color.error));

            TextView tv = (TextView) (snackbar.getView()).findViewById(com.google.android.material.R.id.snackbar_text);
            Typeface font = ResourcesCompat.getFont(this, R.font.futura_round);
            tv.setTypeface(font);

            snackbar.show();
            hideloading();
            return false;
        }
        if (firstName.getText().toString().trim().isEmpty() || !Regex.USERNAME_PATTERN.matcher(firstName.getText().toString().trim()).matches()) {
            Snackbar snackbar = Snackbar.make(mainLayout, "Invalid first name", Snackbar.LENGTH_SHORT);
            snackbar.setBackgroundTint(ContextCompat.getColor(this, R.color.error));
            TextView tv = (TextView) (snackbar.getView()).findViewById(com.google.android.material.R.id.snackbar_text);
            Typeface font = ResourcesCompat.getFont(this, R.font.futura_round);
            tv.setTypeface(font);
            snackbar.show();
            hideloading();
            return false;
        }
        if (lastName.getText().toString().trim().isEmpty() || !Regex.USERNAME_PATTERN.matcher(lastName.getText().toString().trim()).matches()) {
            Snackbar snackbar = Snackbar.make(mainLayout, "Invalid last name", Snackbar.LENGTH_SHORT);
            snackbar.setBackgroundTint(ContextCompat.getColor(this, R.color.error));
            TextView tv = (TextView) (snackbar.getView()).findViewById(com.google.android.material.R.id.snackbar_text);
            Typeface font = ResourcesCompat.getFont(this, R.font.futura_round);
            tv.setTypeface(font);
            snackbar.show();
            hideloading();
            return false;

        }
        if (userModel.getPhone_num().isEmpty() || !Regex.NUMBER_PATTERN.matcher(userModel.getPhone_num()).matches()) {
            Snackbar snackbar = Snackbar.make(mainLayout, "Invalid phone number", Snackbar.LENGTH_SHORT);
            snackbar.setBackgroundTint(ContextCompat.getColor(this, R.color.error));
            TextView tv = (TextView) (snackbar.getView()).findViewById(com.google.android.material.R.id.snackbar_text);
            Typeface font = ResourcesCompat.getFont(this, R.font.futura_round);
            tv.setTypeface(font);
            snackbar.show();
            hideloading();
            return false;
        }
        if (passwordText.getText().toString().trim().length() <= 5) {
            Snackbar snackbar = Snackbar.make(mainLayout, "Password invalid need at-least 6 character", Snackbar.LENGTH_SHORT);
            snackbar.setBackgroundTint(ContextCompat.getColor(this, R.color.error));
            TextView tv = (TextView) (snackbar.getView()).findViewById(com.google.android.material.R.id.snackbar_text);
            Typeface font = ResourcesCompat.getFont(this, R.font.futura_round);
            tv.setTypeface(font);
            snackbar.show();
            hideloading();
            return false;
        }

        if (passwordText.getText().toString().trim().isEmpty() || !Regex.PASSWORD_LOWER_PATTERN.matcher(passwordText.getText().toString().trim()).matches()) {
            Snackbar snackbar = Snackbar.make(mainLayout, "Password invalid need at-least one lowercase character", Snackbar.LENGTH_SHORT);
            snackbar.setBackgroundTint(ContextCompat.getColor(this, R.color.error));
            TextView tv = (TextView) (snackbar.getView()).findViewById(com.google.android.material.R.id.snackbar_text);
            Typeface font = ResourcesCompat.getFont(this, R.font.futura_round);
            tv.setTypeface(font);
            snackbar.show();
            hideloading();
            return false;
        }
        if (passwordText.getText().toString().trim().isEmpty() || !Regex.PASSWORD_UPPERCASE_PATTERN.matcher(passwordText.getText().toString().trim()).matches()) {
            Snackbar snackbar = Snackbar.make(mainLayout, "Password invalid need at-least one uppercase character", Snackbar.LENGTH_SHORT);
            snackbar.setBackgroundTint(ContextCompat.getColor(this, R.color.error));
            TextView tv = (TextView) (snackbar.getView()).findViewById(com.google.android.material.R.id.snackbar_text);
            Typeface font = ResourcesCompat.getFont(this, R.font.futura_round);
            tv.setTypeface(font);
            snackbar.show();
            hideloading();
            return false;
        }
        if (userModel.getFull_address().isEmpty()) {
            Snackbar snackbar = Snackbar.make(mainLayout, "Invalid Address!", Snackbar.LENGTH_SHORT);
            snackbar.setBackgroundTint(ContextCompat.getColor(this, R.color.error));
            TextView tv = (TextView) (snackbar.getView()).findViewById(com.google.android.material.R.id.snackbar_text);
            Typeface font = ResourcesCompat.getFont(this, R.font.futura_round);
            tv.setTypeface(font);
            snackbar.show();
            hideloading();
            return false;
        }
        if (userModel.getTown_text().isEmpty()) {
            Snackbar snackbar = Snackbar.make(mainLayout, "Invalid town!", Snackbar.LENGTH_SHORT);
            snackbar.setBackgroundTint(ContextCompat.getColor(this, R.color.error));
            TextView tv = (TextView) (snackbar.getView()).findViewById(com.google.android.material.R.id.snackbar_text);
            Typeface font = ResourcesCompat.getFont(this, R.font.futura_round);
            tv.setTypeface(font);
            snackbar.show();
            hideloading();
            return false;
        }
        if (userModel.getCity_text().isEmpty()) {
            Snackbar snackbar = Snackbar.make(mainLayout, "Invalid City!", Snackbar.LENGTH_SHORT);
            snackbar.setBackgroundTint(ContextCompat.getColor(this, R.color.error));
            TextView tv = (TextView) (snackbar.getView()).findViewById(com.google.android.material.R.id.snackbar_text);
            Typeface font = ResourcesCompat.getFont(this, R.font.futura_round);
            tv.setTypeface(font);
            snackbar.show();
            hideloading();
            return false;
        }
        if (userModel.getPincode().isEmpty()) {
            Snackbar snackbar = Snackbar.make(mainLayout, "Invalid Pin-code!", Snackbar.LENGTH_SHORT);
            snackbar.setBackgroundTint(ContextCompat.getColor(this, R.color.error));
            TextView tv = (TextView) (snackbar.getView()).findViewById(com.google.android.material.R.id.snackbar_text);
            Typeface font = ResourcesCompat.getFont(this, R.font.futura_round);
            tv.setTypeface(font);
            snackbar.show();
            hideloading();
            return false;
        } else {

            return true;
        }
    }

    private void showLoading() {
        registerLoader.setVisibility(View.VISIBLE);
        register_btn_text.setVisibility(View.GONE);
    }

    private void hideloading() {
        registerLoader.setVisibility(View.GONE);
        register_btn_text.setVisibility(View.VISIBLE);
    }


    private void getDataFromPinCode(String pinCode) {

        // clearing our cache of request queue.
        mRequestQueue.getCache().clear();

        // below is the url from where we will be getting
        // our response in the json format.
        String url = "http://www.postalpincode.in/api/pincode/" + pinCode;

        //http://www.postalpincode.in/api/pincode/400064

        // below line is use to initialize our request queue.
        RequestQueue queue = Volley.newRequestQueue(RegisterUser.this);

        // in below line we are creating a
        // object request using volley.
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            // inside this method we will get two methods
            // such as on response method
            // inside on response method we are extracting
            // data from the json format.
            try {
                // we are getting data of post office
                // in the form of JSON file.
                JSONArray postOfficeArray = response.getJSONArray("PostOffice");

                if (response.getString("Status").equals("Error")) {
                    // validating if the response status is success or failure.
                    // in this method the response status is having error and
                    // we are setting text to TextView as invalid pincode.
                    Snackbar snackbar = Snackbar.make(mainLayout, "Invalid City!", Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(ContextCompat.getColor(getApplicationContext(), R.color.error));
                    snackbar.show();
                } else {
                    // if the status is success we are calling this method
                    // in which we are getting data from post office object
                    // here we are calling first object of our json array.
                    JSONObject obj = postOfficeArray.getJSONObject(0);

                    // inside our json array we are getting district name,
                    // state and country from our data.
                    String district_l = obj.getString("District");
                    String state_l = obj.getString("State");
                    String country_l = obj.getString("Country");
                    String town_l = obj.getString("Taluk");
                    String city_l = obj.getString("Region");


                    state = state_l;
                    country = country_l;
                    towns = town_l;
                    city = city_l;

                    country_edittext.setText(country);
                    town_edittext.setText(towns);
                    city_edittext.setText(city);
                    state_edittext.setText(state);

                    // after getting all data we are setting this data in
                    // our text view on below line.

                }
            } catch (JSONException e) {
                // if we gets any error then it
                // will be printed in log cat.
                e.printStackTrace();
                Toast.makeText(RegisterUser.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        }, error -> {
            // below method is called if we get
            // any error while fetching data from API.
            // below line is use to display an error message.
            Toast.makeText(RegisterUser.this, error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
        });
        // below line is use for adding object
        // request to our request queue.
        queue.add(objectRequest);
    }


}