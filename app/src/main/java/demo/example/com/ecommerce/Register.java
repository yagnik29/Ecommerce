package demo.example.com.ecommerce;


import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class Register extends android.app.Fragment {

    Button register;
    EditText name, email, phone, address, city, password, pincode;


    public Register() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_register, container, false);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        register = (Button) v.findViewById(R.id.register);
        name = (EditText) v.findViewById(R.id.name);
        email = (EditText) v.findViewById(R.id.email);
        phone = (EditText) v.findViewById(R.id.phone);
        address = (EditText) v.findViewById(R.id.address);
        city = (EditText) v.findViewById(R.id.city);
        password = (EditText) v.findViewById(R.id.password);
        pincode = (EditText) v.findViewById(R.id.pincode);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptRegistrtion();
            }
        });
        return v;
    }

    private void attemptRegistrtion() {

        String sName = name.getText().toString();
        String sEmail = email.getText().toString();
        String sPhone = phone.getText().toString();
        String sAddress = address.getText().toString();
        String sCity = city.getText().toString();
        String sPincode = pincode.getText().toString();
        String sPassword = password.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(sName)) {
            name.setError("Please Enter Name");
            focusView = name;
            cancel = true;
        }
        if (TextUtils.isEmpty(sEmail)) {
            email.setError("This field is required");
            focusView = email;
            cancel = true;
        } else if (!isEmailValid(sEmail)) {
            email.setError("This email address is invalid");
            focusView = email;
            cancel = true;
        }
        if (sPhone.length() < 10) {
            phone.setError("Please Enter valid Phone Number");
            focusView = phone;
            cancel = true;
        }
        if (sAddress.length() == 0) {
            address.setError("Please Enter your address");
            focusView = address;
            cancel = true;
        }
        if (sCity.length() == 0) {
            city.setError("Please Enter your curent city name");
            focusView = city;
            cancel = true;
        }

        if (sPincode.length() < 6) {
            pincode.setError("Please enter valid PIN code");
            focusView = pincode;
            cancel = true;
        }

        if (sPassword.length() <= 8) {
            password.setError("Password must be 8 characters long");
            focusView = password;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            try {
                String url = "http://yagnik.890m.com/webservices/registration.php?uname=" + sName +
                        "&email_id=" + sEmail + "&phone=" + sPhone + "&address1=" + sAddress + "&city=" + sCity + "&pincode=" + sPincode + "&password=" + sPassword;

                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder().url(url).build();
                Response response = client.newCall(request).execute();

                Toast.makeText(getActivity(), response.body().toString(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), "Success", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
            }
        }
    }

    private boolean isEmailValid(String email) {
        return email.contains("@gmail.com");
    }

}
