package demo.example.com.ecommerce;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class Description extends Fragment{

    TextView textDescription, descText, descPrice;
    ImageView descImage;
    Button addtocart, buynow;
    String name, desc , price, image;

    public Description() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_description, container, false);
        textDescription = (TextView) view.findViewById(R.id.text_description);
        descText = (TextView) view.findViewById(R.id.desctext);
        descImage = (ImageView) view.findViewById(R.id.descImage);
        descPrice = (TextView) view.findViewById(R.id.descprice);
        addtocart = (Button) view.findViewById(R.id.addtocart);
        buynow = (Button) view.findViewById(R.id.buynow);

        Bundle bundle = this.getArguments();
        if(bundle != null){
            name = bundle.getString("name");
            desc = bundle.getString("Desc");
            price= bundle.getString("Price");
            image = bundle.getString("Image");
        }

        Picasso.with(getActivity()).load(image).into(descImage);
        descText.setText(name);
        textDescription.setText(desc);
        descPrice.setText(price);


        /*textDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cart cart = new Cart();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_navigational,cart);
                ft.addToBackStack("C");
                ft.commit();
            }
        });*/

        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cart_getset cart_getset = new Cart_getset();
                cart_getset.setItemImage(image);
                cart_getset.setItemName(name);
                cart_getset.setItemPrice(price);
                cart_getset.setItemDesc(desc);
                DBHelper dbHelper = new DBHelper(getContext());
                dbHelper.insertData(cart_getset);

                Toast.makeText(getActivity(), "Added in cart", Toast.LENGTH_SHORT).show();


            }
        });

        buynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Please conform the order", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
