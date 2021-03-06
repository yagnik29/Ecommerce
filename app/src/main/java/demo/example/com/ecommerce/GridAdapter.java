package demo.example.com.ecommerce;

import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Yash on 3/31/2017.
 */

public class GridAdapter extends BaseAdapter {

    Context context;
    private Navigational navigational;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    LayoutInflater inflater;

    public GridAdapter(Context context, ArrayList<HashMap<String, String>> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
        navigational = (Navigational) context;
        inflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class Holder {
        TextView gridtextView;
        TextView gridPrice;
        ImageView gridimageView;
    }

    @Override
    public View getView(final int i, View v, ViewGroup viewGroup) {

        final HashMap<String, String> hm = arrayList.get(i);
        Holder holder = new Holder();
        View view = inflater.inflate(R.layout.customgrid_layout,null);

        holder.gridtextView = (TextView) view.findViewById(R.id.gridText);
        holder.gridimageView = (ImageView) view.findViewById(R.id.gridImage);
        holder.gridPrice = (TextView) view.findViewById(R.id.gridPrice);
        //Log.e("in adapter==", "" + arrayList.get(i).get("Image"));

        holder.gridtextView.setText(hm.get("name"));
        holder.gridPrice.setText(hm.get("Price"));
        Picasso.with(context).load(hm.get("Image")).into(holder.gridimageView);

//        holder.gridtextView.setText(arrayList.get(i).get("Desc"));
//        holder.gridtextView.setText(arrayList.get(i).get("Price"));


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, "This is what you clicked", Toast.LENGTH_SHORT).show();
                Description description_frag = new Description();
                Bundle  bundle = new Bundle();
                bundle.putString("name",arrayList.get(i).get("name"));
                bundle.putString("Desc",arrayList.get(i).get("Desc"));
                bundle.putString("Price",arrayList.get(i).get("Price"));
                bundle.putString("Image",arrayList.get(i).get("Image"));
                description_frag.setArguments(bundle);

                navigational.getSupportFragmentManager().beginTransaction().add(R.id.content_navigational,description_frag).addToBackStack(null).commit();
                /*android.support.v4.app.FragmentTransaction ft=navigational.getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_navigational,description_frag);
                ft.addToBackStack("B");
                ft.commit();*/
                HomeFragment.tabLayout.setVisibility(View.GONE);
                HomeFragment.viewPager_tab.setVisibility(View.GONE);
                HomeFragment.viewPager_image.setVisibility(View.GONE);
            }
        });
        return view;
    }
}
