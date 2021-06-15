package com.ishwar_arcore.pacebook.views.AddPost;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.ishwar_arcore.pacebook.R;

import org.jetbrains.annotations.NotNull;

public class SelectFeelingFragment extends DialogFragment {

    private SearchView searchView;
    private ListView listView;
    private Button select;
    private ArrayAdapter<String> arrayAdapter;

    public static interface Capture{
        void onclick(String txt);
    }
    public Capture capture;

    public SelectFeelingFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_select_feeling, container, false);
        getDialog().setTitle("search");

        searchView=view.findViewById(R.id.search);
        listView=view.findViewById(R.id.list);
        select=view.findViewById(R.id.select_btn);
        arrayAdapter=new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.feeling));
     listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             try {
                 String selected=(String)listView.getItemAtPosition(position);
                 if(capture!=null){
                     capture.onclick(selected);
                     getDialog().dismiss();
                 }
             }catch (Exception e){
                 e.printStackTrace();
                 Toast.makeText(getActivity(),"please select again",Toast.LENGTH_LONG).show();

             }

         }
     });
     searchView.setQueryHint("please select");
     searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
         @Override
         public boolean onQueryTextSubmit(String query) {
             return false;
         }

         @Override
         public boolean onQueryTextChange(String newText) {
             arrayAdapter.getFilter().filter(newText);
             return false;
         }
     });

     select.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            dismiss();
         }
     });

        return view;
    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        try {
        capture=(Capture) getActivity();
        }catch (Exception e){
            e.printStackTrace();

        }
    }
}