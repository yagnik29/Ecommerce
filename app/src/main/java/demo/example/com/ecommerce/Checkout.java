package demo.example.com.ecommerce;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Checkout extends android.app.Fragment {

    TextView textCheckout;

    public Checkout() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_checkout, container, false);

        textCheckout = (TextView) view.findViewById(R.id.text_checkout);
        textCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Payment payment = new Payment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.checkout,payment);
                ft.addToBackStack("D");
                ft.commit();
            }
        });
        return  view;
    }

}
