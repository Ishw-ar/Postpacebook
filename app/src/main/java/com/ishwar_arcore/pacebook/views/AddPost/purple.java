package com.ishwar_arcore.pacebook.views.AddPost;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.ishwar_arcore.pacebook.R;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class purple extends Fragment implements SelectFeelingFragment.Capture,ImageFreeClick{
    private EditText input;
    private FirebaseAuth mAuth;
    private FirebaseFirestore firebaseFirestore;
    private String user_id;
    private String username;
    private String userimage;
    private String selected;
    private String imageUri;
    private TextView post_textview;
    private ProgressDialog mprogress;

    @Override
    public void onImageSelected(String imageUri) {
        imageUri =imageUri;

    }


    @Override
    public void onclick(String txt) {
        if(txt!=null){
            selected=txt;
        }

    }


    public purple(){


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.purple, container, false);


            input = v.findViewById(R.id.fourthFragment);
            mAuth= FirebaseAuth.getInstance();
            firebaseFirestore= FirebaseFirestore.getInstance();
            user_id=mAuth.getCurrentUser().getUid();

        post_textview=AddPostActivity.btn;
        mprogress=new ProgressDialog(getActivity());
        post_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mprogress.setTitle("just a sec..");
                mprogress.setMessage("Uploading..");
                mprogress.setCanceledOnTouchOutside(false);
                mprogress.setCancelable(false);
                mprogress.show();
                stratUpload();

            }
        });

            firebaseFirestore.collection("Users").document(user_id).get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {
                            if (task.getResult().exists()){
                                try {
                                    String first_name=task.getResult().getString("first_name");
                                    userimage=task.getResult().getString("user_image");
                                }catch (Exception e){
                                    e.printStackTrace();
                                }

                            }else {
                                Toast.makeText(getActivity(),"Something went wrong",Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

            return v;
    }
        private void stratUpload(){
            if (input.getText()!=null){
                String text=input.getText().toString();

                Map<String,Object> map=new HashMap<>();
                map.put("text",text);
                map.put("bgc","purple");
                map.put("media_type",imageUri);

                map.put("tags","maria");
                map.put("the_text","is"+ selected);
                map.put("username",username);
                map.put("user_image",userimage);

                firebaseFirestore.collection("posts").document("allpost").set(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<Void> task) {
                        if(task.isSuccessful()){

                            mprogress.dismiss();
                            Intent intent=new Intent(getActivity(),AddPostActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                        }else {
                            mprogress.dismiss();
                            Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_LONG).show();

                        }  }
                });
            }

        }
}