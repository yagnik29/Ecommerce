package demo.example.com.ecommerce;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Login extends AppCompatActivity {

    Button login;
    EditText username , password;
    TextView new_register;
    String url;
    String EMAIL_ID = "email_id";
    String PASSWORD = "password";
    String sUsername;
    String sPassword;
    String email,pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        login = (Button) findViewById(R.id.btnLogin);
        new_register = (TextView) findViewById(R.id.newregister);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sUsername = username.getText().toString();
                sPassword = password.getText().toString();
                try {
                    url = "http://yagnik.890m.com/webservices/jsonphp.php?" + "email_id=" + sUsername + "&password=" + sPassword;
                    new GetData().execute();
                } catch (Exception e) {

                }
            }
        });

        new_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register registration_frag = new Register();
                android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_login,registration_frag);
                ft.addToBackStack("A");
                ft.commit();
            }
        });

    }
    public class GetData extends AsyncTask< Void, Void, Void> {

        ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(Login.this);
            progressDialog.setMessage("Please Wait");
            progressDialog.show();
        }



        @Override
        protected Void doInBackground(Void... voids) {

            HttpServiceHandler servicedata = new HttpServiceHandler();
            String result = servicedata.getHttpdata(url);
//            Log.e("Result" , result);

            try {
                JSONArray jsonArray = new JSONArray(result);

                for (int i=0; i < jsonArray.length(); i++){
                    JSONObject object = jsonArray.getJSONObject(i);
                     email = object.getString(EMAIL_ID);
                    pass= object.getString(PASSWORD);


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try {
                if(email.equals(sUsername) || pass.equals(sPassword)){
                    progressDialog.dismiss();
                    Intent homescreen = new Intent(Login.this,Navigational.class);
                    startActivity(homescreen);
                }
            }catch (Exception e){
                Toast.makeText(Login.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
