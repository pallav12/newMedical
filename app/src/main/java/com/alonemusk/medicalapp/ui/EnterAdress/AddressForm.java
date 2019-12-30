package com.alonemusk.medicalapp.ui.EnterAdress;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alonemusk.medicalapp.R;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONObject;

import java.util.HashMap;

import static android.content.ContentValues.TAG;


 public class AddressForm extends Fragment {

    private AddressFormViewModel mViewModel;
    String address;
    String Landmark;
    String City;
    String state;
Button submit;
       public static AddressForm newInstance() {
        return new AddressForm();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.address_form_fragment, container, false);



submit=v.findViewById(R.id.submit);
        final EditText editText =v.findViewById(R.id.line1);
        final EditText editText2 =v.findViewById(R.id.line2);
        final EditText editText5 =v.findViewById(R.id.line5);

        final EditText editTex3 =v.findViewById(R.id.line3);

        final EditText editTex4 =v.findViewById(R.id.line4);

submit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        InsertAddressToServer(10001,editText.getText().toString(),editText2.getText().toString(),editTex3.getText().toString(),editTex4.getText().toString(),editText5.getText().toString());
    }
});







        return v;
    }
     public  void InsertAddressToServer(int user_id,String address,String landmark,String city,String state,String mobile_no){




           Toast.makeText(getActivity(), "in vooly", Toast.LENGTH_SHORT).show();
           RequestQueue queue = Volley.newRequestQueue(getActivity());

           // JSONObject urlf = new JSONObject(data);
           JSONObject data2 = new JSONObject();

           try{
               data2.put("address",address);
               data2.put("user_id",user_id);
               data2.put("landmark",landmark);
               data2.put("mobile_no",mobile_no);
               data2.put("city",city);


           }catch(Exception e){

           }


           final JsonObjectRequest putRequest = new JsonObjectRequest(Request.Method.POST
                   , "http://ec2-3-16-216-35.us-east-2.compute.amazonaws.com:3000/user/insert-address", data2,
                   new Response.Listener<JSONObject>() {
                       @Override
                       public void onResponse(JSONObject response) {
                           Toast.makeText(getActivity(), ""+response, Toast.LENGTH_SHORT).show();
                       }


                   },
                   new Response.ErrorListener() {
                       @Override
                       public void onErrorResponse(VolleyError error) {
                           Toast.makeText(getActivity(), "volly error   " + error, Toast.LENGTH_SHORT).show();

                       }
                   }
           ) {

               @Override
               public HashMap<String, String> getHeaders() throws AuthFailureError {
                   HashMap<String, String> params = new HashMap<>();
                   //  params.put("Content-Type", " text/javascript");
                   params.put("Content-Type", "application/json");

                   return params;



               }


           };


           queue.add(putRequest);








       }

       @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AddressFormViewModel.class);




               }






}
