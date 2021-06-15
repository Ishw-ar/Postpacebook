package com.ishwar_arcore.pacebook.views.AddPost;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.ishwar_arcore.pacebook.R;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;
import id.zelory.compressor.Compressor;

public class AddPostActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int LOAD_IMAGE = 100;
    private TextView userName;
    private CircleImageView userImage;
    private FirebaseAuth mAuth;
    public static TextView btn;
    private Button addImageBtn;
    private Button addFeelingBtn;
    private ImageView backBtn;
    private FirebaseFirestore firebaseFirestore;
    private StorageReference storageReference;
    private File newImageFile;
    private Bitmap compressImageFile;
    private Uri imageUri;
    private ImageFreeClick imageFreeClick;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        btn = findViewById(R.id.etPost);
        initWidget();

        ViewPager pager = (ViewPager) findViewById(R.id.postviewpager);
        pager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager()));

    }

    private void initWidget() {
        backBtn=findViewById(R.id.btnbackpost);
        addFeelingBtn=findViewById(R.id.btnTwo);
        addImageBtn=findViewById(R.id.btnOne);
        addImageBtn.setOnClickListener(this);
        addFeelingBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);

        mAuth =FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnbackpost:
                finish();
                break;
            case R.id.btnTwo:

                openFeelingFragment();
                break;

            case R.id.btnOne:
                picImage();
                break;
        }

    }

    private void openFeelingFragment() {

        android.app.FragmentManager fragmentManager=getFragmentManager();
        SelectFeelingFragment selectFeelingFragment=new SelectFeelingFragment();
        selectFeelingFragment.show(fragmentManager,"feeling");
    }

    private void picImage() {
        Intent intent = new Intent();
        intent.setType("image/+");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,LOAD_IMAGE);

    }
    private void finalizeTask() {
        newImageFile = new File(imageUri.getPath());

        final  String randome= UUID.randomUUID().toString();
        try {
            ByteArrayOutputStream boas=new ByteArrayOutputStream();
            compressImageFile.compress(Bitmap.CompressFormat.JPEG,100,boas);
            byte[] imagedata=boas.toByteArray();
            final StorageReference ref = storageReference.child("post_images").child(randome +",jpeg");
            UploadTask uploadTask=ref.putBytes(imagedata);
            uploadTask.continueWith(new Continuation<UploadTask.TaskSnapshot, Object>() {


                @Override
                public Object then(@NonNull @NotNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if(!task.isSuccessful()){
                        throw  task.getException();
                    }
                    return ref.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Object>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<Object> task) {
                    String uri=task.getResult().toString();
                    if(uri !=null){
                        if(imageFreeClick!=null){
                            imageFreeClick.onImageSelected(uri);

                        }
                    }
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class MyFragmentPagerAdapter extends FragmentPagerAdapter{
        public MyFragmentPagerAdapter(FragmentManager fm){
            super(fm);

        }


        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0: return new Default();
                case 1: return new purple();
                case 2: return new Orange();
                case 3: return new Pink();
                case 4: return new Yellow();
                case 5:return new green();
                case 6: return new Red();
                default: return new Default();
            }
        }

        @Override
        public int getCount() {
            return 7;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==LOAD_IMAGE){
            if(resultCode==RESULT_OK){
                imageUri=data.getData();
            }else if(resultCode==RESULT_CANCELED) {
                Toast.makeText(AddPostActivity.this, "Cancelled",Toast.LENGTH_SHORT).show();


            }
        }
    }
}