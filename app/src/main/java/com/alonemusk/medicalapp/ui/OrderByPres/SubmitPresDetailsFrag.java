
package com.alonemusk.medicalapp.ui.OrderByPres;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.alonemusk.medicalapp.R;
import com.alonemusk.medicalapp.ui.EnterAdress.MyItemRecyclerViewAdapter;
import com.alonemusk.medicalapp.ui.EnterAdress.Note;
import com.alonemusk.medicalapp.ui.EnterAdress.NoteViewmodel;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.app.Activity.RESULT_OK;

//import com.koushikdutta.ion.Ion;

public class SubmitPresDetailsFrag extends Fragment {
    private SubmitPresDetailsViewModel mViewModel;
    private static final int PICK_IMAGE_REQUEST = 1;
    EditText name, address, mobileno, pincode;
    ImageButton uploadimagebtn;
    Button submitorderbtn;
    ProgressBar uploadProgressBar;
    ImageView imageView;
    ArrayList<Uri> images = new ArrayList<>();
    private Uri mImageUri;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    List<Note> noted;
    private StorageTask mUploadTask;
    TextView textview_address_directorder;
    EditText directorderDescription_edittext;

    public static SubmitPresDetailsFrag newInstance() {
        return new SubmitPresDetailsFrag();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.submit_pres_details_fragment, container, false);
        //  name=root.findViewById(R.id.editText_name);
        Button btn = root.findViewById(R.id.change_address_btn);
        imageView=root.findViewById(R.id.upload_prescription);
        // textview_address_directorder = root.findViewById(R.id.textview_address_directorder);
        //directorderDescription_edittext = root.findViewById(R.id.directorderDescription_edittext);
        NoteViewmodel noteViewmodel = ViewModelProviders.of(this).get(NoteViewmodel.class);
        noteViewmodel.getAllnote().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                Log.d("notes notes notes", notes.toString());
                noted = notes;

                // myItemRecyclerViewAdapter.setNotes(notes);
                if (MyItemRecyclerViewAdapter.lastCheckedPosition != -1)
                    textview_address_directorder.setText((noted.get(MyItemRecyclerViewAdapter.lastCheckedPosition).getLandmark()));
            }
        });


        final NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_submitPresDetailsFrag_to_navigation_adress);

            }
        });
        address = root.findViewById(R.id.address);
      //  mobileno = root.findViewById(R.id.editText_mobile);
      //  pincode = root.findViewById(R.id.editText_pincode);
        uploadimagebtn = root.findViewById(R.id.imageButton);
        submitorderbtn = root.findViewById(R.id.submit_order);
        //imageView = root.findViewById(R.id.imageView2);

        //uploadProgressBar = root.findViewById(R.id.progressBar);
        mStorageRef = FirebaseStorage.getInstance().getReference().child("ImageFolder");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("/direct_order");
        uploadimagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }

        });
        submitorderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mUploadTask != null && mUploadTask.isInProgress()) {
                    Toast.makeText(getActivity(), "An Upload is Still in Progress", Toast.LENGTH_SHORT).show();
                } else {
                    uploadFile();
                }
            }
        });
        return root;
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getActivity().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    ArrayList<String> urlImage = new ArrayList<>();
    int i = 0;

    private void uploadFile() {
        if (mImageUri != null) {
            final StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                    + "." + getFileExtension(mImageUri));

//            uploadProgressBar.setVisibility(View.VISIBLE);
//            uploadProgressBar.setIndeterminate(true);

            mUploadTask = fileReference.putFile(mImageUri)

                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {

                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    //  Toast.makeText(getActivity(), "image uploaded", Toast.LENGTH_SHORT).show();
                                    urlImage.add(String.valueOf(uri));

         //                           uploadProgressBar.setVisibility(View.GONE);
                                    upload();


                                }
                            });

                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
//                                    uploadProgressBar.setVisibility(View.VISIBLE);
  //                                  uploadProgressBar.setIndeterminate(false);
    //                                uploadProgressBar.setProgress(0);
                                }
                            }, 500);


                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                      //      uploadProgressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
//                            uploadProgressBar.setProgress((int) progress);
                        }
                    });

        } else {
            Toast.makeText(getActivity(), "You haven't Selected Any file selected", Toast.LENGTH_SHORT).show();
        }

    }


    void upload() {
        String uploadId = mDatabaseRef.push().getKey();
        HashMap<String, String> hashMap = new HashMap<>();

        hashMap.put("description", "YO");
        hashMap.put("address_id", "" + "noted.get(MyItemRecyclerViewAdapter.lastCheckedPosition).getAddress_id())");
        hashMap.put("status_code", "" + 0);
        hashMap.put("tracking_id", "not confirmed");
        hashMap.put("status_massage", "not confirmed");
        hashMap.put("delivery_date", "12/01/1999");
        hashMap.put("amount", "nan");
        //  String uploadId3 = mDatabaseRef.push().getKey();
        // mDatabaseRef.child(uploadId3).setValue(1);

        mDatabaseRef.child(uploadId).setValue(hashMap);
        String uploadId2 = mDatabaseRef.push().getKey();
        HashMap<String, String> hashMap2 = new HashMap<>();
        for (int i = 0; i < 1; i++)
            hashMap2.put("" + i, urlImage.get(i));
        mDatabaseRef.child(uploadId).child(uploadId2).setValue(hashMap2);
  //      uploadProgressBar.setVisibility(View.INVISIBLE);
        upload_to_node(uploadId);
        Toast.makeText(getActivity(), "Teacher  Upload successful", Toast.LENGTH_LONG).show();


    }

    public void upload_to_node(String reference) {
        RequestQueue queue = Volley.newRequestQueue(getActivity());

        // JSONObject urlf = new JSONObject(data);
        JSONObject data2 = new JSONObject();

        try {
            data2.put("user_id", "Mahendra");
            data2.put("address_id", 1);
            data2.put("imagefile_refernce", reference);


        } catch (Exception e) {

        }


        final JsonObjectRequest putRequest = new JsonObjectRequest(Request.Method.POST
                , "http://ec2-13-235-73-199.ap-south-1.compute.amazonaws.com:3000/post-direct-order", data2,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getActivity(), "SUCESS" + response, Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getContext(), DirectOrderSuccess.class));

                        getActivity().finish();
                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("hello world",error.toString());
                        Toast.makeText(getActivity(), "order is not submitted please check your internet or try again   " + error.getMessage(), Toast.LENGTH_LONG).show();

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

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (SubmitPresDetailsViewModel.images.size() != 0) {
            Picasso.with(getActivity()).load(SubmitPresDetailsViewModel.images.get(0)).into(imageView);
        }

    }

    @Override
    public void onResume() {

        super.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();
            images.add(mImageUri);
            SubmitPresDetailsViewModel.images = images;
            Picasso.with(getActivity()).load(mImageUri).into(imageView);
        }
    }


}


//      // nameofpatient_edittext=root.findViewById(R.id.name_of_patient);
////       mobile_of_patient_edittext=root.findViewById(R.id.mobile_no_id);
////       details_about_pres_edittext=root.findViewById(R.id.details_about_pres_id);
//      // upload_image_btn=root.findViewById(R.id.upload_pres_image_id);
////       uploadwholeform=root.findViewById(R.id.upload_details_id);
////       imageView1=root.findViewById(R.id.imageView);
//        Prescription_image_recyclerview=root.findViewById(R.id.prescription_image_recyclerview);
//        progressDialog = new ProgressDialog(getActivity());
//        progressDialog.setMessage("Uploading ..........");
//        //textView = findViewById(R.id.text);
//        choose = root.findViewById(R.id.choose);
//        send = root.findViewById(R.id.upload);
//        choose.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View view) {
//
//            //we will pick images
//            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//            intent.setType("image/*");
//            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
//            startActivityForResult(intent, PICK_IMG);
//
//
//    }
//});
//send.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View view) {
//
//        databaseReference = FirebaseDatabase.getInstance().getReference("/direct_order_prescription").child("mahendra");
//       // textView.setText("Please Wait ... If Uploading takes Too much time please the button again ");
//       // progressDialog.show();
//        final StorageReference ImageFolder =  FirebaseStorage.getInstance().getReference().child("ImageFolder");
//        for (uploads=0; uploads < ImageList.size(); uploads++) {
//            Toast.makeText(getActivity(), "image uploaded   "+uploads, Toast.LENGTH_SHORT).show();
//            Uri Image  = ImageList.get(uploads);
//            final StorageReference imagename = ImageFolder.child("image/"+Image.getLastPathSegment());
//
//            imagename.putFile(ImageList.get(uploads)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//
//                @Override
//                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                    imagename.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                        @Override
//                        public void onSuccess(Uri uri) {
//                            Toast.makeText(getActivity(), "image uploaded", Toast.LENGTH_SHORT).show();
//                            String url = String.valueOf(uri);
//                              if(uploads<=ImageList.size()-1)
//                                SendLink(url ,0);
//                              else
//                                  SendLink(url ,1);
//                        }
//                    });
//
//                }
//
//
//            });
//
//
//        }
//
//    }
//});
//
//       return root;
//    }
//
//    private void SendLink(String url, final int last) {
//        HashMap<String, String> hashMap = new HashMap<>();
//        hashMap.put("link", url);
//
//        databaseReference.push().setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//
//               // progressDialog.dismiss();
//               // textView.setText("Image Uploaded Successfully");
////                send.setVisibility(View.GONE);
////                ImageList.clear();
//
//
//            }
//
//        });
//
//
//    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == PICK_IMG) {
//            if (resultCode == RESULT_OK) {
//                if (data.getClipData() != null) {
//                    int count = data.getClipData().getItemCount();
//                    Toast.makeText(getActivity(), "count  "+count, Toast.LENGTH_SHORT).show();
//                    int CurrentImageSelect = 0;
//
//                    while (CurrentImageSelect < count) {
//                        Uri imageuri = data.getClipData().getItemAt(CurrentImageSelect).getUri();
//                        ImageList.add(imageuri);
//                        CurrentImageSelect = CurrentImageSelect + 1;
//                    }
////                    textView.setVisibility(View.VISIBLE);
////                    textView.setText("You Have Selected "+ ImageList.size() +" Pictures" );
//                    choose.setVisibility(View.GONE);
//                }
//
//            }
//
//        }
//    }
//
//    public int submitform(){
////        String name=nameofpatient_edittext.getText().toString().trim();
////        String des=details_about_pres_edittext.getText().toString().trim();
////        String mobile=mobile_of_patient_edittext.getText().toString().trim();
////        if(!name.isEmpty()&&!des.isEmpty()&&!mobile.isEmpty()){
////
////          //  uploadBitmap(bitmap);
////          //  String filepath1=getPathFromURI(uri);
////
////
////
////
////
////
////
////        }else{
////            Toast.makeText(getActivity(), "please fill all data", Toast.LENGTH_SHORT).show();
////        }
////
//
//        return 0;
//    }
//
//
//    }


