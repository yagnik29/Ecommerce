package demo.example.com.ecommerce;

import android.content.Context;
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

/**
 * Created by admin on 4/24/2017.
 */

public class Cart_Adapter extends BaseAdapter {

    Context context;
    ArrayList<Cart_getset> arrayList = new ArrayList<>();
    LayoutInflater inflater;

    ImageView image;
    Button remove;
    TextView textName;
    TextView price;
    TextView desc;
    Navigational navigational;

    public Cart_Adapter(Context context, ArrayList<Cart_getset> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
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

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        final Cart_getset cart_getset = arrayList.get(i);
        //final String id = hm.get("id");
        View v = inflater.inflate(R.layout.custom_cartlist, null);

        image = (ImageView) v.findViewById(R.id.cartlist_image);
        textName = (TextView) v.findViewById(R.id.cartlist_name);
        price = (TextView) v.findViewById(R.id.cartlist_price);
        desc = (TextView) v.findViewById(R.id.cartlist_desc);
        remove = (Button) v.findViewById(R.id.removefromcart);
        textName.setText(cart_getset.getItemName());
        Picasso.with(context).load(cart_getset.getItemImage()).into(image);
        price.setText(cart_getset.getItemPrice());
        desc.setText(cart_getset.getItemDesc());
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    DBHelper dbHelper = new DBHelper(context);
                    dbHelper.deletedata(cart_getset.getID());
                    Cart.refresh();
                    Toast.makeText(context, "Removed from Cart Successfully", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Toast.makeText(context, "Error" + e, Toast.LENGTH_SHORT).show();
                }
            }
        });
        return v;
    }
}
