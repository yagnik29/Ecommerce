package demo.example.com.ecommerce;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Cart extends Fragment {

    public static ListView cartlist;
    public static Cart_Adapter cart_adapter;
    public static List<Cart_getset> cartlist_show;
    View view;
    public static Navigational navigational;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cart, container, false);
        cartlist = (ListView) view.findViewById(R.id.cart_list);
        navigational = (Navigational) getActivity();
        refresh();
        return view;
    }

    public static void refresh() {
        DBHelper dbHelper = new DBHelper(navigational);
        cartlist_show = dbHelper.show();
        cart_adapter = new Cart_Adapter(navigational, (ArrayList<Cart_getset>) cartlist_show);
        cartlist.setAdapter(cart_adapter);
        cart_adapter.notifyDataSetChanged();
    }

   /* public static void Refreshdata() {
        DBHelper dbHelper = new DBHelper(navigational);
        cartlist_show = dbHelper.show();
        cart_adapter = new Cart_Adapter(navigational, (ArrayList<Cart_getset>) cartlist_show);
        cartlist.setAdapter(cart_adapter);
    }*/
}
