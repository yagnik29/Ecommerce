package demo.example.com.ecommerce;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by admin on 4/24/2017.
 */

public class Cart_Adapter extends BaseAdapter{

    Context context;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    LayoutInflater inflater;

    ImageView image;
    TextView textName;
    TextView price;
    TextView desc;


    public Cart_Adapter(Context context, ArrayList<HashMap<String, String>> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        inflater = LayoutInflater.from(this.context);

    }

    @Override
    public int getCount()
    {
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

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        final HashMap<String, String> hm = arrayList.get(i);

        View v = inflater.inflate(R.layout.custom_cartlist,null);

        image = (ImageView) v.findViewById(R.id.cartlist_image);
        textName = (TextView) v.findViewById(R.id.cartlist_name);
        price = (TextView) v.findViewById(R.id.cartlist_price);
        desc = (TextView) v.findViewById(R.id.cartlist_desc);

        textName.setText(hm.get("name"));

        return v;
    }
}
