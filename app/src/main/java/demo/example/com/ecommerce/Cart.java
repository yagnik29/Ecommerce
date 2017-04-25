package demo.example.com.ecommerce;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Cart extends Fragment{

    TextView textCheckout;
    ListView cartlist;
    Cart_Adapter cart_adapter;
    List<Cart_getset> cartlist_show;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();

    public Cart() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_cart, container, false);

        cartlist = (ListView) view.findViewById(R.id.cart_list);

        /*HashMap<String, String> hm = new HashMap<>();
        hm.put("name","Yagnik Prajapati");
        arrayList.add(hm);*/

        DBHelper dbHelper = new DBHelper(getContext());
        cartlist_show =dbHelper.show();

        for( Cart_getset cart_getset : cartlist_show){
            HashMap<String, String> hm = new HashMap<>();
            hm.put("Image", cart_getset.getItemImage());
            hm.put("name", cart_getset.getItemName());
            hm.put("Price", cart_getset.getItemPrice());
            hm.put("Desc", cart_getset.getItemDesc());

            arrayList.add(hm);

        }

        Log.e("R+++++", String.valueOf(arrayList));
        cart_adapter = new Cart_Adapter(getActivity(),arrayList);
        cartlist.setAdapter(cart_adapter);

        /*textCheckout = (TextView) view.findViewById(R.id.text_checkout);
        textCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Payment payment = new Payment();
                android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.cart,payment);
                ft.addToBackStack("D");
                ft.commit();
            }
        });*/
        return  view;
    }


}
