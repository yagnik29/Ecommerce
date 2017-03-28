package demo.example.com.ecommerce;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
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

    public SlidingImage_Adapter(Context context, ArrayList<Integer> images) {
        this.context = context;
        this.images = images;
        inflater = LayoutInflater.from(context);
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
    public Object instantiateItem(ViewGroup view, int position) {

        View imageLayout = inflater.inflate(R.layout.sliding_image, view, false);
        assert imageLayout != null;
        final ImageView imageView = (ImageView) imageLayout.findViewById(R.id.slide_image);

        imageView.setImageResource(images.get(position));

        view.addView(imageLayout, 0);

        return imageLayout;

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}
