package com.alonemusk.medicalapp.ui.OrderByPres;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.loader.content.CursorLoader;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

import android.provider.MediaStore;
import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.alonemusk.medicalapp.R;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
//import com.koushikdutta.ion.Ion;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;



import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

import static android.app.Activity.RESULT_OK;

public class SubmitPresDetailsFrag extends Fragment {

    private SubmitPresDetailsViewModel mViewModel;
EditText nameofpatient_edittext,mobile_of_patient_edittext,details_about_pres_edittext;
Button upload_image_btn;
Button uploadwholeform;
 Bitmap bitmap;
 ImageView imageView1;
    String  filePath;
    String imgDecodableString;
    Uri uri;
    public static SubmitPresDetailsFrag newInstance() {
        return new SubmitPresDetailsFrag();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
       View root= inflater.inflate(R.layout.submit_pres_details_fragment, container, false);
       nameofpatient_edittext=root.findViewById(R.id.name_of_patient);
       mobile_of_patient_edittext=root.findViewById(R.id.mobile_no_id);
       details_about_pres_edittext=root.findViewById(R.id.details_about_pres_id);
       upload_image_btn=root.findViewById(R.id.upload_pres_image_id);
       uploadwholeform=root.findViewById(R.id.upload_details_id);
       imageView1=root.findViewById(R.id.imageView);
       upload_image_btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
             uploadimagetonodejs();
           }
       });
       uploadwholeform.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
           int res= submitform();
           }
       });

       return root;
    }
    public int submitform(){
        String name=nameofpatient_edittext.getText().toString().trim();
        String des=details_about_pres_edittext.getText().toString().trim();
        String mobile=mobile_of_patient_edittext.getText().toString().trim();
        if(!name.isEmpty()&&!des.isEmpty()&&!mobile.isEmpty()){

          //  uploadBitmap(bitmap);
          //  String filepath1=getPathFromURI(uri);
       uploadToServer(filePath);






        }else{
            Toast.makeText(getActivity(), "please fill all data", Toast.LENGTH_SHORT).show();
        }


        return 0;
    }


    private void uploadimagetonodejs() {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);





    }
    public String getPath(Uri uri) {
        Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getActivity().getContentResolver().query(
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Uri path = data.getData();
            uri = data.getData();
           // Toast.makeText(getActivity(), "uri :    "+uri, Toast.LENGTH_LONG).show();
          filePath=getPath(data.getData());
            Toast.makeText(getActivity(), "path:    "+filePath, Toast.LENGTH_SHORT).show();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), path);
                imageView1.setImageBitmap(bitmap);
                imageView1.setVisibility(View.VISIBLE);

            } catch (IOException e) {
                e.printStackTrace();
            }

//            File f = new File(path);
//
//            Future uploading = Ion.with(getActivity())
//                    .load("http://192.168.150.1:8080/upload")
//                    .setMultipartFile("image", f)
//                    .asString()
//                    .withResponse()
//                    .setCallback(new FutureCallback<Response<String>>() {
//                        @Override
//                        public void onCompleted(Exception e, Response<String> result) {
//                            try {
//                                JSONObject jobj = new JSONObject(result.getResult());
//                                Toast.makeText(getApplicationContext(), jobj.getString("response"), Toast.LENGTH_SHORT).show();
//
//                            } catch (JSONException e1) {
//                                e1.printStackTrace();
//                            }
//
//                        }
//                    });
        }

    }




    private void uploadToServer(String filePath) {
        Retrofit retrofit = NetworkClient.getRetrofitClient(getActivity());
        UploadAPIs uploadAPIs = retrofit.create(UploadAPIs.class);
        //Create a file object using file path
        File file = new File(filePath);
        // Create a request body with file and image media type
        RequestBody fileReqBody = RequestBody.create(MediaType.parse("image/*"), file);
        // Create MultipartBody.Part using file request-body,file name and part name
        MultipartBody.Part part = MultipartBody.Part.createFormData("upload", file.getName(), fileReqBody);
        //Create request body with text description and text media type
        RequestBody description = RequestBody.create(MediaType.parse("text/plain"), "image-type");
        //
        Call call = uploadAPIs.uploadImage(part, description);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, retrofit2.Response response) {
                Toast.makeText(getActivity(), "uploaded:   "+response, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getActivity(), "not uploaded    "+t.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }
        @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SubmitPresDetailsViewModel.class);
        // TODO: Use the ViewModel

    }



        public byte[] getFileDataFromDrawable(Bitmap bitmap) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }

        private void uploadBitmap(final Bitmap bitmap) {

            //getting the tag from the edittext


            //our custom volley request
            VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, "http://ec2-3-16-216-35.us-east-2.compute.amazonaws.com:3000/cart/submit-order",
                    new Response.Listener<NetworkResponse>() {
                        @Override
                        public void onResponse(NetworkResponse response) {
                            try {
                                JSONObject obj = new JSONObject(new String(response.data));
                            Toast.makeText(getActivity(), "sucess", Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                       Toast.makeText(getActivity(),""+ error, Toast.LENGTH_SHORT).show();
                        }
                    }) {

                /*
                 * If you want to add more parameters with the image
                 * you can do it here
                 * here we have only one parameter with the image
                 * which is tags
                 * */
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();

                    return params;
                }

                /*
                 * Here we are passing image by renaming it with a unique name
                 * */
                @Override
                protected Map<String, DataPart> getByteData() {
                    Map<String, DataPart> params = new HashMap<>();
                    long imagename = System.currentTimeMillis();
                    params.put("pic", new DataPart(imagename + ".png", getFileDataFromDrawable(bitmap)));
                    return params;
                }
            };

            //adding the request to volley
            Volley.newRequestQueue(getActivity()).add(volleyMultipartRequest);
        }
    }


