package demo.example.com.ecommerce;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

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
    Button remove;
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
        final String id=hm.get("id");
        View v = inflater.inflate(R.layout.custom_cartlist,null);

        image = (ImageView) v.findViewById(R.id.cartlist_image);
        textName = (TextView) v.findViewById(R.id.cartlist_name);
        price = (TextView) v.findViewById(R.id.cartlist_price);
        desc = (TextView) v.findViewById(R.id.cartlist_desc);
        remove = (Button) v.findViewById(R.id.removefromcart);

        textName.setText(hm.get("name"));
        Picasso.with(context).load(hm.get("Image")).into(image);
        price.setText(hm.get("Price"));
        desc.setText(hm.get("Desc"));

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper = new DBHelper(context);
                int id1= Integer.parseInt(id);
                dbHelper.deletedata(id1);
                Toast.makeText(context, "Removed from Cart Successfully", Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }
}
