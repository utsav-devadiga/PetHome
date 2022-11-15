package com.petHomeApps.petHome;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.core.content.ContextCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.petHomeApps.petHome.Datamodels.AddressModel;
import com.petHomeApps.petHome.Datamodels.UserModel;
import com.petHomeApps.petHome.R;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import util.FireKeys;

public class AddAddressDialog extends AppCompatDialogFragment {

    RequestQueue mRequestQueue;
    View view;
    AlertDialog  alert_view;



    String towns, city, country, state, pincode;
    String landmark,fulladdress;

    private EditText epincode,
                     efull_address,
                     elandmark,
                     etown,
                     ecity,
                     estate,
                     ecountry
    ;


    @NonNull
    @NotNull
    @Override
    public Dialog onCreateDialog(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

        LayoutInflater inflater=getActivity().getLayoutInflater();
        view=inflater.inflate(R.layout.add_address,null);

        builder.setView(view)
                .setTitle("Add Address")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {



                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        pincode=epincode.getText().toString().trim();
                       fulladdress=efull_address.getText().toString().trim();
                       landmark=elandmark.getText().toString().trim();
                       towns=etown.getText().toString().trim();
                       city=ecity.getText().toString().trim();
                       state=estate.getText().toString().trim();
                       country=ecountry.getText().toString().trim();


                       if(!pincode.isEmpty()&& !fulladdress.isEmpty()&& !towns.isEmpty() && !city.isEmpty() && !country.isEmpty() && !state.isEmpty()){

                           uploadAddress();
                       }
                       else {
                           Toast.makeText(view.getContext(), "Invalid Address", Toast.LENGTH_SHORT).show();
                       }














                    }


                });





        epincode=view.findViewById(R.id.pin_code_dialog);
        efull_address=view.findViewById(R.id.address_dialog);
                elandmark=view.findViewById(R.id.landmark_dialog);
                etown=view.findViewById(R.id.town_dialog);
                ecity=view.findViewById(R.id.city_dialog);
                estate=view.findViewById(R.id.state_dialog);
                ecountry=view.findViewById(R.id.country_dialog);
        mRequestQueue = Volley.newRequestQueue(getActivity());








        epincode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if (s.toString().length() == 6) {
                    getDataFromPinCode(s.toString());
                } else {
                    ecountry.setText(null);
                    etown.setText(null);
                    ecity.setText(null);
                    estate.setText(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        alert_view=builder.create();

        return  alert_view;
    }

    private void getDataFromPinCode(String pincode) {

        // clearing our cache of request queue.
        mRequestQueue.getCache().clear();

        // below is the url from where we will be getting
        // our response in the json format.
        String url = "http://www.postalpincode.in/api/pincode/" + pincode;

        //http://www.postalpincode.in/api/pincode/400064

        // below line is use to initialize our request queue.
        RequestQueue queue = Volley.newRequestQueue(getActivity());

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
                    Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
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

                    ecountry.setText(country);
                    etown.setText(towns);
                    ecity.setText(city);
                    estate.setText(state);

                    // after getting all data we are setting this data in
                    // our text view on below line.

                }
            } catch (JSONException e) {
                // if we gets any error then it
                // will be printed in log cat.
                e.printStackTrace();
                Toast.makeText(getContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        }, error -> {
            // below method is called if we get
            // any error while fetching data from API.
            // below line is use to display an error message.
            Toast.makeText(getContext(), error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
        });
        // below line is use for adding object
        // request to our request queue.
        queue.add(objectRequest);
    }

    private void uploadAddress() {
        FirebaseFirestore db=FirebaseFirestore.getInstance();
        String user= FirebaseAuth.getInstance().getCurrentUser().getUid();
        String address_id = db.collection(FireKeys.User).document(user).collection(FireKeys.address).document().getId();

        AddressModel addressModel =new AddressModel(pincode,fulladdress,landmark,towns,city,state,country,address_id);

        db.collection(FireKeys.User).document(user).collection(FireKeys.address).document(address_id).set(addressModel).addOnCompleteListener(task -> {
         if(task.isSuccessful()){
             alert_view.dismiss();
         }
         else {


         }

        });

    }


}
