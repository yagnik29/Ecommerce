package demo.example.com.ecommerce;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class HomeScreen extends AppCompatActivity {

    ImageView imageView;
    TabLayout tabLayout;
    ViewPager viewPager_tab, viewPager_image;
    private static int currentPage;
    private static int NUM_Pages;

    //ViewFlipper viewFlipper;

    private static final Integer[] images = {R.drawable.litchi, R.drawable.mango, R.drawable.pineapple};
    private ArrayList<Integer> imageArray = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        imageView = (ImageView) findViewById(R.id.imageView);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager_tab= (ViewPager) findViewById(R.id.pager_tab);
        viewPager_image = (ViewPager) findViewById(R.id.pager_image);
        //viewFlipper = (ViewFlipper) findViewById(R.id.flipper);


        /*for (int i = 0; i < images.length; i++) {
            imageView = new ImageView(this);
            imageView.setImageResource(images[i]);
            viewFlipper.addView(imageView);
        }

        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);

        viewFlipper.setInAnimation(in);
        viewFlipper.setOutAnimation(out);
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
*/
        //Image Onclick
/*
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Description description_frag = new Description();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_homescreen, description_frag);
                ft.addToBackStack("B");
                ft.commit();

            }
        });*/

        for(int i = 0; i < images.length; i++)
            imageArray.add(images[i]);

        viewPager_image.setAdapter(new SlidingImage_Adapter(HomeScreen.this, imageArray));
        CirclePageIndicator indicator = (CirclePageIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(viewPager_image);

        final float density = getResources().getDisplayMetrics().density;

        //Set circle indicator radius
        indicator.setRadius(5 * density);
        NUM_Pages = images.length;

        //Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            @Override
            public void run() {
                if (currentPage == NUM_Pages) {
                    currentPage = 0;
                }
                viewPager_image.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        //Pager Listener over indicator

        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        Tab_Adapter tab_adapter = new Tab_Adapter(getSupportFragmentManager());
        tab_adapter.addFragment(new Electroincs(), "Electronic");
        tab_adapter.addFragment(new Appliances(), "Appliances");
        tab_adapter.addFragment(new Clothing(), "Fashion");
        tab_adapter.addFragment(new Books(), "Books");

        viewPager_tab.setAdapter(tab_adapter);
        tabLayout.setupWithViewPager(viewPager_tab);
    }

}
