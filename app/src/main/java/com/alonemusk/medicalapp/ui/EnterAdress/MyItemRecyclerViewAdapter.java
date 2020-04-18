package com.alonemusk.medicalapp.ui.EnterAdress;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alonemusk.medicalapp.R;
import com.alonemusk.medicalapp.ui.Checkout.Confirm_order_fregment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.content.ContentValues.TAG;

public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.NoteHolder> {
    private List<Note> notes=new ArrayList<>();
    onMenuClicked onMenuClicked;
    Context context;
    public  static int lastCheckedPosition = -1;
    public MyItemRecyclerViewAdapter(Context c,onMenuClicked onMenuClicked){
        this.onMenuClicked=onMenuClicked;
        this.context=c;
    }
    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.note,viewGroup,false);
        Toolbar toolbar=itemView.findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.toolbar_menu);

        return new NoteHolder(itemView,onMenuClicked);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder noteHolder, int i) {
        final Note currentnote=  notes.get(i);
        Log.d(TAG, "onBindViewHolder: 0"+ currentnote.toString());
        noteHolder.title.setText(currentnote.getAddress()+"");
        noteHolder.checkBox.setChecked(i == lastCheckedPosition);

        noteHolder.description.setText(currentnote.getCity()+"");
       noteHolder.title.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        onMenuClicked.addressSelected(currentnote.getAddress_id());
    }
});



    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(List<Note> notes){
        this.notes=notes;
        notifyDataSetChanged();

    }
     void datasetchanged(){
     notifyDataSetChanged();
    }
    public Note getNoteAt(int i){
        return notes.get(i);

    }

    class NoteHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView description;
        private RadioButton checkBox;
        private TextView priority;
        onMenuClicked onMenuClicked;



        public NoteHolder(@NonNull final View itemView, final onMenuClicked onMenuClicked) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            this.onMenuClicked=onMenuClicked;
            description=itemView.findViewById(R.id.description);
            Toolbar toolbar=itemView.findViewById(R.id.toolbar);
            checkBox=itemView.findViewById(R.id.radioButton);
            Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show();
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lastCheckedPosition = getAdapterPosition();
                    //because of this blinking problem occurs so
                    //i have a suggestion to add notifyDataSetChanged();
                    //   notifyItemRangeChanged(0, list.length);//blink list problem
                    notifyDataSetChanged();

                }
            });

            toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    Log.d("clicked","clicked clicked");
                    switch (item.getItemId()){
                        case R.id.delete:
                            onMenuClicked.onMenuClicked(getAdapterPosition(),1);
                            break;
                        case R.id.Edit:
                            onMenuClicked.onMenuClicked(getAdapterPosition(),2);
                            break;
                        case R.id.set_as_default:

                            onMenuClicked.onMenuClicked(getAdapterPosition(),3);
                            break;

                    }
                    return true;
                }
            });


        }
    }
    public interface onMenuClicked{
        void addressSelected(int address_id);
        void onMenuClicked(int i,int j);
    }

}
