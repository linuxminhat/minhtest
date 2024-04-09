package com.midterm.rvimageintent;

import static android.provider.MediaStore.ACTION_IMAGE_CAPTURE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView myimageview;
    ImageButton imageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myimageview = findViewById(R.id.myimageview);
        imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Khai bao Intent an cua chuong trinh
                //Dat mot ten bien la cameraIntent
                Intent cameraIntent = new Intent(ACTION_IMAGE_CAPTURE);
                //Viet ham dung de yeu cau quyen truy cap cua nguoi dung
                if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CAMERA}, 1);
                    return;
                }
                startActivityForResult(cameraIntent, 2009);
            }
        });
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 2009 && resultCode == RESULT_OK){
            //resultCode la code ma do ung dung chup hinh tra ve
            //Key mac dinh ma camera tra ve la key data, chung ta khong the sua duoc
            Bitmap photo = (Bitmap) data.getExtras().get("data");

            myimageview.setImageBitmap((android.graphics.Bitmap) data.getExtras().get("data"));
        }
    }
}