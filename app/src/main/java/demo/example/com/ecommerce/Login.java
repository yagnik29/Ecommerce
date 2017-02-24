package demo.example.com.ecommerce;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {

    Button login,new_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        */
        login = (Button) findViewById(R.id.login);
        new_register = (Button) findViewById(R.id.newregister);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homescreen = new Intent(Login.this,HomeScreen.class);
                startActivity(homescreen);
            }
        });

        new_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerscreen = new Intent(Login.this, Registration.class);
                startActivity(registerscreen);
            }
        });

    }

}
