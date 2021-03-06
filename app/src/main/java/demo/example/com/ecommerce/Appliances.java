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
public class Appliances extends Fragment {

    GridView gridAppliancs;
    Context context;
    ArrayList itemName;
    String URL, NAME, DESCRIPTION, PRICE, IMAGE;
    ProgressDialog progressDialog;
    String name = "Name";
    String description = "Description";
    String price = "price";
    String image = "image";
    String url = "http://yagnik.890m.com/webservices/fetchimageAppl.php";

    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();

    public Appliances() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_appliances, container,false);
        gridAppliancs = (GridView) view.findViewById(R.id.gridAppl);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        new GetAppliancesdata().execute();

        return view;
    }

    public class GetAppliancesdata extends AsyncTask<Void, Void, Void>{

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
            //Log.e("Result", result);

            try {

                JSONArray jsonArray = new JSONArray(result);
                arrayList.clear();
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
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();

            GridAdapter adapter = new GridAdapter(getActivity(), arrayList);
            gridAppliancs.setAdapter(adapter);
        }
    }
}
