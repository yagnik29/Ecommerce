package demo.example.com.ecommerce;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class Electroincs extends Fragment {

    GridView gridElectronics;
    Context context;
    ArrayList itemName;
    String URL, NAME, DESCRIPTION, PRICE, IMAGE;
    ProgressDialog progressDialog;
    String name = "Name";
    String description = "Description";
    String price = "price";
    String image = "image";
    String url = "http://yagnik.890m.com/webservices/fetchimageEle.php";

    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();

/*
    public static String[] itemNameList={"TV", "AC","Laptop"};
    public static int[] itemImages = {R.drawable.yagnik,R.drawable.dhruv,R.drawable.laptop};
*/


    public Electroincs() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_electroincs, container, false);
        gridElectronics = (GridView) view.findViewById(R.id.grid);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        URL = "http://yagnik.890m.com/webservices/fetchimage.php";
        new GetElectronicsdata().execute();


        return view;

    }

    public class GetElectronicsdata extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Please Wait");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            HttpServiceHandler servicedata = new HttpServiceHandler();
            String result = servicedata.getHttpdata(url);
            /*Log.e("Result", result);*/

            if (result != null) {

                try {
                    JSONArray jsonArray = new JSONArray(result);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        NAME = jsonObject.getString(name);
                        DESCRIPTION = jsonObject.getString(description);
                        PRICE = jsonObject.getString(price);
                        IMAGE = jsonObject.getString(image);

                        HashMap<String, String> hm = new HashMap<>();
                        hm.put("name", NAME);
                        hm.put("Desc", DESCRIPTION);
                        hm.put("Price", PRICE);
                        hm.put("Image", IMAGE);
                        arrayList.add(hm);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();

            GridAdapter adapter = new GridAdapter(getActivity(), arrayList);
            gridElectronics.setAdapter(adapter);

        }
    }
}
