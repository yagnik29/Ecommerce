package demo.example.com.ecommerce;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeScreen extends AppCompatActivity {

    ImageView imageView;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        imageView = (ImageView) findViewById(R.id.imageView);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.pager);


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

        Tab_Adapter tab_adapter = new Tab_Adapter(getSupportFragmentManager());
        tab_adapter.addFragment(new Electroincs(),"Electronic");
        tab_adapter.addFragment(new Appliances(),"Appliances");
        tab_adapter.addFragment(new Clothing(),"Cloths");
        tab_adapter.addFragment(new Books(),"Books");

        viewPager.setAdapter(tab_adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

}
