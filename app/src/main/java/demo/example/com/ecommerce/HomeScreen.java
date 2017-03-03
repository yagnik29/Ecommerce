package demo.example.com.ecommerce;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeScreen extends AppCompatActivity {

    ImageView imageView;
    TextView textUname, textPassw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        imageView = (ImageView) findViewById(R.id.imageView);
        textUname = (TextView) findViewById(R.id.textView2);
        textPassw = (TextView) findViewById(R.id.textView3);
        Intent intent1 = getIntent();
        String uname = intent1.getStringExtra("uname");
        String passw = intent1.getStringExtra("passw");

        textUname.setText(uname);
        textPassw.setText(passw);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Description description_frag = new Description();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_homescreen,description_frag);
                ft.addToBackStack("B");
                ft.commit();

            }
        });
    }

}
