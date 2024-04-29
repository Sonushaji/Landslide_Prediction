package com.example.landslide_prediction;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

public class Prediction3 extends AppCompatActivity {
    EditText q20, q21, q22, q23, q24, q25, q27, q28;
    TextView tv;
    SharedPreferences sh;

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prediction3);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        button = findViewById(R.id.button11);
        q20 = findViewById(R.id.editTextText21);
        q21 = findViewById(R.id.editTextText22);
        q22 = findViewById(R.id.editTextText23);
        q23 = findViewById(R.id.editTextText24);
        q24 = findViewById(R.id.editTextText25);
        q25 = findViewById(R.id.editTextText26);
        q27 = findViewById(R.id.editTextText28);
        q28 = findViewById(R.id.editTextText29);
        tv = findViewById(R.id.textView16);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = sh.getString("url", "")+"/and_input";
                String a20 = q20.getText().toString();
                String a21 = q21.getText().toString();
                String a22 = q22.getText().toString();
                String a23 = q23.getText().toString();
                String a24 = q24.getText().toString();
                String a25 = q25.getText().toString();
                String a27 = q27.getText().toString();
                String a28 = q28.getText().toString();

                if (a20.isEmpty()){
                    q20.setError("*");
                }
                if (a21.isEmpty()){
                    q21.setError("*");
                }
                if (a22.isEmpty()){
                    q22.setError("*");
                }
                if (a23.isEmpty()){
                    q23.setError("*");
                }
                if (a24.isEmpty()){
                    q24.setError("*");
                }
                if (a25.isEmpty()){
                    q25.setError("*");
                }
                if (a27.isEmpty()){
                    q27.setError("*");
                }
                if (a28.isEmpty()){
                    q28.setError("*");
                }
                else {

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
                                            String result = jsonObj.getString("result");
                                            tv.setText("As per your provided values, "+result);
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Wait for Administrator to Authenticate!", Toast.LENGTH_LONG).show();
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

                            params.put("lid", sh.getString("lid", ""));
                            params.put("a1", sh.getString("a1", ""));
                            params.put("a2", sh.getString("a2", ""));
                            params.put("a3", sh.getString("a3", ""));
                            params.put("a4", sh.getString("a4", ""));
                            params.put("a5", sh.getString("a5", ""));
                            params.put("a6", sh.getString("a6", ""));
                            params.put("a7", sh.getString("a7", ""));
                            params.put("a8", sh.getString("a8", ""));
                            params.put("a9", sh.getString("a9", ""));
                            params.put("a10", sh.getString("a10", ""));
                            params.put("a11", sh.getString("a11", ""));
                            params.put("a12", sh.getString("a12", ""));
                            params.put("a13", sh.getString("a13", ""));
                            params.put("a14", sh.getString("a14", ""));
                            params.put("a15", sh.getString("a15", ""));
                            params.put("a16", sh.getString("a16", ""));
                            params.put("a17", sh.getString("a17", ""));
                            params.put("a18", sh.getString("a18", ""));
                            params.put("a19", sh.getString("a19", ""));
                            params.put("a20", a20);
                            params.put("a21", a21);
                            params.put("a22", a22);
                            params.put("a23", a23);
                            params.put("a24", a24);
                            params.put("a25", a25);
                            params.put("a27", a27);
                            params.put("a28", a28);
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
        });
    }
}