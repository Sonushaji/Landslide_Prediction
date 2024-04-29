package com.example.landslide_prediction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class ChangePassword extends AppCompatActivity {
    EditText current, newpass, confirm;
    Button change;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        current = findViewById(R.id.editTextTextPassword);
        newpass = findViewById(R.id.editTextTextPassword2);
        confirm = findViewById(R.id.editTextText);
        change = findViewById(R.id.button4);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String curr = current.getText().toString();
                String newp = newpass.getText().toString();
                String conf = confirm.getText().toString();
                if (curr.isEmpty()){
                    current.setError("*");
                }
                if (newp.isEmpty()){
                    newpass.setError("*");
                }
                if (conf.isEmpty()){
                    confirm.setError("*");
                }
                else {
                    if (curr.equalsIgnoreCase(newp)){
                        Toast.makeText(ChangePassword.this, "Old and new Password cant be same", Toast.LENGTH_SHORT).show();
                        newpass.setText("");
                        return;
                    }
                    if (!newp.equalsIgnoreCase(conf)){
                        Toast.makeText(ChangePassword.this, "Passwords are missmatching", Toast.LENGTH_SHORT).show();
                        confirm.setText("");
                        return;
                    }
                    change_password(curr, newp);
                }
            }
        });

    }

    private void change_password(String current, String newp) {
        String url = sh.getString("url", "")+"/and_change_password";
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
                                Toast.makeText(ChangePassword.this, "Password Changed Successfully", Toast.LENGTH_SHORT).show();
                                finish();

                            } else {
                                Toast.makeText(ChangePassword.this, "Not found", Toast.LENGTH_SHORT).show();
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
                params.put("current", current);
                params.put("new", newp);
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