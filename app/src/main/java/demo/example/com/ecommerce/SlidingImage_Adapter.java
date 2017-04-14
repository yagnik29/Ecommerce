package demo.example.com.ecommerce;

import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Yash on 3/28/2017.
 */

public class SlidingImage_Adapter extends PagerAdapter {

    private ArrayList<Integer> images;
    private Context context;
    private LayoutInflater inflater;
    private Navigational navigational;

    public SlidingImage_Adapter(Context context, ArrayList<Integer> images) {

        this.context=context;
        this.images=images;
        navigational = (Navigational) context;
        inflater=LayoutInflater.from(this.context);

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, final int position) {

        View imageLayout = inflater.inflate(R.layout.sliding_image, view, false);
        assert imageLayout != null;
        final ImageView imageView = (ImageView) imageLayout.findViewById(R.id.slide_image);

        imageView.setImageResource(images.get(position));
        view.addView(imageLayout);
        imageLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //this will log the page number that was click
                Log.i("TAG", "This page was clicked: " + (position+1));
                Description description_frag = new Description();
                android.support.v4.app.FragmentTransaction ft = navigational.getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_navigational, description_frag);
                ft.addToBackStack("C");
                ft.commit();
            }
        });

        return imageLayout;

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}
