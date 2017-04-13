package demo.example.com.ecommerce;


import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    ImageView imageView;
    TabLayout tabLayout;
    ViewPager viewPager_tab, viewPager_image;
    private static int currentPage;
    private static int NUM_Pages;

    private static final Integer[] images = {R.drawable.litchi, R.drawable.mango, R.drawable.pineapple};
    private ArrayList<Integer> imageArray = new ArrayList<Integer>();


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        imageView = (ImageView) view.findViewById(R.id.slide_image);
        tabLayout = (TabLayout) view.findViewById(R.id.tablayout);
        viewPager_tab = (ViewPager) view.findViewById(R.id.pager_tab);
        viewPager_image = (ViewPager) view.findViewById(R.id.pager_image);

        for (int i = 0; i < images.length; i++)
            imageArray.add(images[i]);

        viewPager_image.setAdapter(new SlidingImage_Adapter(getContext(), imageArray));
        CirclePageIndicator indicator = (CirclePageIndicator) view.findViewById(R.id.indicator);
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
        Tab_Adapter tab_adapter = new Tab_Adapter(getFragmentManager());
        tab_adapter.addFragment(new Electroincs(), "Electronic");
        tab_adapter.addFragment(new Appliances(), "Appliances");
        //tab_adapter.addFragment(new Clothing(), "Fashion");
        tab_adapter.addFragment(new Books(), "Books");

        viewPager_tab.setAdapter(tab_adapter);
        tabLayout.setupWithViewPager(viewPager_tab);

        return view;
    }

}
