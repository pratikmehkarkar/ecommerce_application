package com.jayesh.jayesh.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jayesh.jayesh.MainActivity;
import com.jayesh.jayesh.R;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import com.jayesh.jayesh.app.AppConfig;
import com.jayesh.jayesh.app.AppController;
import com.jayesh.jayesh.helper.SQLiteHandler;
import com.jayesh.jayesh.helper.SessionManager;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = RegisterActivity.class.getSimpleName();
    ImageButton btnreg;
    //Button btnLinkToLogin;
    private EditText inputFullName;
    private EditText inputEmail;
    private EditText inputPassword;
    private EditText mob;
    private ProgressDialog pDialog;
    private SessionManager session;
    private SQLiteHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inputFullName = (EditText) findViewById(R.id.namen);
        inputEmail = (EditText) findViewById(R.id.emailid);
        inputPassword = (EditText) findViewById(R.id.passw);
        mob = (EditText)findViewById(R.id.mobileno);
        btnreg = (ImageButton) findViewById(R.id.sign);
        //btnLinkToLogin = (Button) findViewById(R.id.btnLinkToLoginScreen);
        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        session = new SessionManager(getApplicationContext());

        // SQLite database handler
        db = new SQLiteHandler(getApplicationContext());
        // Check if user is already logged in or not
        if (session.isLoggedIn())
        {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(intent);
            finish();// Register Button Click event
            // Link to Login Screen
           /* btnLinkToLogin.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {
                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(i);
                    finish();
                }
            });*/
        }
        btnreg.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {

                //Toast.makeText(getApplicationContext(), "check", Toast.LENGTH_LONG).show();
                String name = inputFullName.getText().toString().trim();
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                String mobil = mob.getText().toString().trim();

                if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty() && !mobil.isEmpty())
                {
                    registerUser(name, email, password,mobil);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Please enter your details!", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
    private void registerUser(final String name, final String email,
                              final String password,final String mobil) {
        // Tag used to cancel the request
        String tag_string_req = "req_register";

        pDialog.setMessage("Registering ...");
        showDialog();

        StringRequest strReq = new StringRequest(Method.POST,
                AppConfig.URL_REGISTER, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        // User successfully stored in MySQL
                        // Now store the user in sqlite
                        String uid = jObj.getString("uid");

                        JSONObject user = jObj.getJSONObject("user");
                        String name = user.getString("name");
                        String email = user.getString("email");
                        String created_at = user.getString("created_at");
                        String mobil = user.getString("mobile");

                        // Inserting row in users table
                        db.addUser(name, email, uid, created_at,mobil);

                        Toast.makeText(getApplicationContext(), "User successfully registered. Try login now!", Toast.LENGTH_LONG).show();

                        // Launch login activity
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } else {

                        // Error occurred in registration. Get the error
                        // message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Registration Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", name);
                params.put("email", email);
                params.put("password", password);
                params.put("mobile",mobil);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}