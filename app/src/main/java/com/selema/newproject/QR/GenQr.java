package com.selema.newproject.QR;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.selema.newproject.R;

import net.glxn.qrgen.android.QRCode;

public class GenQr extends AppCompatActivity {
    Bitmap myBitmap;
    String myEmail;
    ImageView myImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gen_qr);

        Bundle extras = getIntent().getExtras();
        if (extras != null)
            myEmail = extras.getString("email");

        myBitmap = QRCode.from("mohamed@yahoo.com").bitmap();
        myImage = (ImageView) findViewById(R.id.imageView);
        myImage.setImageBitmap(myBitmap);
    }
}
