package demo.example.com.ecommerce;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Electroincs extends Fragment {

    GridView gridElectronics;
    Context context;
    ArrayList itemName;

    public static String[] itemNameList={"TV", "AC","Laptop"};
    public static int[] itemImages = {R.drawable.yagnik,R.drawable.dhruv,R.drawable.laptop};


    public Electroincs() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_electroincs, container, false);
        gridElectronics = (GridView) view.findViewById(R.id.grid);
        gridElectronics.setAdapter(new GridAdapter(getContext(),itemNameList,itemImages));
        return view;

    }

}
