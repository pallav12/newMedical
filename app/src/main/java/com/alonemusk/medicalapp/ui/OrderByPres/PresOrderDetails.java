package com.alonemusk.medicalapp.ui.OrderByPres;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.alonemusk.medicalapp.R;
import com.baoyachi.stepview.VerticalStepView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class PresOrderDetails extends Fragment {

    EditText orderId;
    EditText status;
    EditText name;
    EditText amount;

    VerticalStepView mSetpview0;
    List<String> source = new ArrayList<>();
    private PresOrderDetailsViewModel mViewModel;

    OrderModel orderModel;

    Integer position1 = 3;
    private DatabaseReference mDatabaseRef;

    public static PresOrderDetails newInstance() {
        return new PresOrderDetails();
    }

    TextView textView2;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.pres_order_details_fragment, container, false);
        final int position = getArguments().getInt("position");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("/direct_order/" + AllData.orderModels.get(position).getImagefile_refernce());
        orderId = v.findViewById(R.id.orderID);
        status = v.findViewById(R.id.status);
        name = v.findViewById(R.id.name);
        amount = v.findViewById(R.id.amount);
        orderModel = AllData.orderModels.get(position);
        fillView();
        mDatabaseRef.child("status_code").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                int p = getint(dataSnapshot.getValue().toString().trim());
                Toast.makeText(getActivity(), "" + dataSnapshot, Toast.LENGTH_SHORT).show();
                makestepview(p);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                int p = getint(dataSnapshot.getValue().toString().trim());
                Toast.makeText(getActivity(), "int " + dataSnapshot, Toast.LENGTH_SHORT).show();
                makestepview(p);

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        // textView=v.findViewById(R.id.textView5);
        mSetpview0 = v.findViewById(R.id.stepView0);
        source.add("order Placed");
        source.add("Address Validated");
        source.add("Prescription varified");
        source.add("billing");
        source.add("order Confirmed");
        source.add("dispached");
        source.add("shipped");
        source.add("delevired");


//
//
//        mSetpview0.setStepViewTexts(source)
//        .setStepsViewIndicatorComplectingPosition(position1)//设置完成的步数
//              .reverseDraw(false)//default is true
//                //总步骤
//         .setLinePaddingProportion(0.85f)//设置indicator线与线间距的比例系数
//                .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(getActivity(), android.R.color.white))//设置StepsViewIndicator完成线的颜色
//                .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(getActivity(),android.R.color.darker_gray))//设置StepsViewIndicator未完成线的颜色
//                .setStepViewComplectedTextColor(ContextCompat.getColor(getActivity(), android.R.color.darker_gray))//设置StepsView text完成线的颜色
//                .setStepViewUnComplectedTextColor(ContextCompat.getColor(getActivity(), R.color.uncompleted_text_color))//设置StepsView text未完成线的颜色
//                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(getActivity(),R.color.colorgreen))//设置StepsViewIndicator CompleteIcon
//                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(getActivity(), R.drawable.default_icon))//设置StepsViewIndicator DefaultIcon
//                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(getActivity(), R.drawable.attention));//设置StepsViewIndicator AttentionIcon
//
//
//
//
//
//
//


        // textView.setText(""+ AllData.orderModels.get(position).getOrder_id());
        return v;
    }

    private void fillView() {
        status.setText(orderModel.getStatus());
        name.setText(orderModel.getUser_id());
    }

    public void makestepview(int position) {


        mSetpview0.setStepViewTexts(source)
                .setStepsViewIndicatorComplectingPosition(position)//设置完成的步数
                .reverseDraw(false)//default is true
                //总步骤
                .setLinePaddingProportion(0.85f)//设置indicator线与线间距的比例系数
                .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(getActivity(), android.R.color.white))//设置StepsViewIndicator完成线的颜色
                .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(getActivity(), android.R.color.darker_gray))//设置StepsViewIndicator未完成线的颜色
                .setStepViewComplectedTextColor(ContextCompat.getColor(getActivity(), android.R.color.darker_gray))//设置StepsView text完成线的颜色
                .setStepViewUnComplectedTextColor(ContextCompat.getColor(getActivity(), R.color.uncompleted_text_color))//设置StepsView text未完成线的颜色
                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(getActivity(), R.color.colorgreen))//设置StepsViewIndicator CompleteIcon
                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(getActivity(), R.drawable.default_icon))//设置StepsViewIndicator DefaultIcon
                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(getActivity(), R.drawable.attention));//设置StepsViewIndicator AttentionIcon


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PresOrderDetailsViewModel.class);
        // TODO: Use the ViewModel
    }

    public int getint(String s) {
        if (s == "-1")
            return -1;
        if (s.equals("0"))
            return 0;
        if (s.equals("1"))
            return 1;
        if (s.equals("2"))
            return 2;
        if (s.equals("3"))
            return 3;
        if (s.equals("4"))
            return 4;
        if (s.equals("5"))
            return 5;
        if (s.equals("6"))
            return 6;
        if (s.equals("7"))
            return 7;
        if (s.equals("8"))
            return 8;
        if (s.equals("9"))
            return 9;

        return 0;
    }

}
