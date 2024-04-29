package com.example.landslide_prediction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class register extends AppCompatActivity implements View.OnClickListener {
    EditText e_name, e_email, e_phone, e_house, e_place, e_post, e_pin, e_pswd;
    Button b1;
    SharedPreferences sh;

    @Override
    public void onBackPressed() {
        Intent ij = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(ij);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        e_name = (EditText)findViewById(R.id.uname);
        e_email = (EditText)findViewById(R.id.editTextTextPersonName);
        e_phone = (EditText)findViewById(R.id.editTextTextPersonName2);
        e_house = (EditText)findViewById(R.id.editTextTextPersonName3);
        e_place = (EditText)findViewById(R.id.editTextTextPersonName4);
        e_post = (EditText)findViewById(R.id.editTextTextPersonName5);
        e_pin = (EditText)findViewById(R.id.editTextTextPersonName6);
        e_pswd = (EditText)findViewById(R.id.pswd);
        b1 = (Button)findViewById(R.id.button);


        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        b1.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        final String uname= e_name.getText().toString();
        final String email= e_email.getText().toString();
        final String phone= e_phone.getText().toString();
        final String house= e_house.getText().toString();
        final String place= e_place.getText().toString();
        final String post= e_post.getText().toString();
        final String pin= e_pin.getText().toString();
        final String pswd= e_pswd.getText().toString();
        if (uname.length()==0){
            e_name.setError("Missing");
        } else if (email.length()==0){
            e_email.setError("Missing");
        } else if (phone.length()==0){
            e_phone.setError("Missing");
        } else if (house.length()==0){
            e_house.setError("Missing");
        } else if (place.length()==0){
            e_place.setError("Missing");
        } else if (post.length()==0){
            e_post.setError("Missing");
        } else if (pin.length()==0){
            e_pin.setError("Missing");
        } else if (pswd.length()==0){
            e_pswd.setError("Missing");
        } else{
            SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String hu = sh.getString("url", "");
            String url = hu + "/and_register";

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                            // response
                            try {
                                JSONObject jsonObj = new JSONObject(response);
                                if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
                                    Toast.makeText(getApplicationContext(), "Registered", Toast.LENGTH_SHORT).show();
                                    Intent ij = new Intent(getApplicationContext(), login.class);
                                    startActivity(ij);

                                } else {
                                    Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
                                }

                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // error
                            Toast.makeText(getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams() {
                    SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    Map<String, String> params = new HashMap<String, String>();

                    params.put("name", uname);
                    params.put("email", email);
                    params.put("phone", phone);
                    params.put("house", house);
                    params.put("place", place);
                    params.put("post", post);
                    params.put("pin", pin);
                    params.put("pswd", pswd);

                    return params;
                }
            };

            int MY_SOCKET_TIMEOUT_MS = 100000;
            postRequest.setRetryPolicy(new DefaultRetryPolicy(
                    MY_SOCKET_TIMEOUT_MS,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            requestQueue.add(postRequest);

        }
    }
}
