package demo.example.com.ecommerce;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    Button login;
    EditText username , password;
    TextView new_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        login = (Button) findViewById(R.id.btnLogin);
        new_register = (TextView) findViewById(R.id.newregister);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent homescreen = new Intent(Login.this,HomeScreen.class);
                String uname = username.getText().toString();
                String passw = password.getText().toString();
                homescreen.putExtra("uname",uname);
                homescreen.putExtra("passw",passw);
                startActivity(homescreen);
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

}
