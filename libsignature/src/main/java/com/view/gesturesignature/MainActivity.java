package com.view.gesturesignature;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import java.io.File;

import pub.devrel.easypermissions.EasyPermissions;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Button mStartSignature;
    private ImageView mSignatureBitmap;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mStartSignature = (Button) findViewById(R.id.btn_startSignature);
        mSignatureBitmap = (ImageView) findViewById(R.id.iv_signatureBitmap);



        EasyPermissions.requestPermissions(
                MainActivity.this,
                "申请权限",
                0,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.SYSTEM_ALERT_WINDOW,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.CAMERA);


        mStartSignature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, SignatureActivity.class), 1);
            }
        });
    }

    public static String signaturePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "signature.png";

    @Override
    protected void onResume() {
        super.onResume();
        Bitmap bitmap = BitmapFactory.decodeFile(signaturePath);
        if (bitmap != null) {
            mSignatureBitmap.setImageBitmap(bitmap);
        }
    }

    //    mMSignature = (GestureSignatureView) findViewById(R.id.gsv_signature);
//    findViewById(R.id.btn_clear).setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            mMSignature.clear();
//        }
//    });
//    findViewById(R.id.btn_save).setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            boolean b = addJpgSignatureToGallery(mMSignature.getPaintBitmap());
//            Log.d(TAG, "onClick: 保存是否成功：" + b);
//        }
//    });
//    public boolean addJpgSignatureToGallery(Bitmap signature) {
//        boolean result = false;
//        try {
//            File photo = new File(getAlbumStorageDir("SignaturePad"), String.format("Signature_%d.jpg", System.currentTimeMillis()));
//            saveBitmapToJPG(signature, photo);
//            scanMediaFile(photo);
//            result = true;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    public File getAlbumStorageDir(String albumName) {
//        // Get the directory for the user's public pictures directory.
//        File file = new File(Environment.getExternalStoragePublicDirectory(
//                Environment.DIRECTORY_PICTURES), albumName);
//        if (!file.mkdirs()) {
//            Log.e("SignaturePad", "Directory not created");
//        }
//        return file;
//    }
//
//    public void saveBitmapToJPG(Bitmap bitmap, File photo) throws IOException {
//        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        paint.setStrokeJoin(Paint.Join.ROUND);
//        paint.setStrokeWidth(10.0f);
//        paint.setStrokeCap(Paint.Cap.ROUND);
//        paint.setColor(Color.BLACK);
//        paint.setDither(true);
//        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(newBitmap);
//        canvas.drawColor(Color.WHITE);
//        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
//        canvas.drawBitmap(bitmap, 0, 0, paint);
//        OutputStream stream = new FileOutputStream(photo);
//        newBitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
//        stream.close();
//    }
//
//
//    private void scanMediaFile(File photo) {
//        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//        Uri contentUri = Uri.fromFile(photo);
//        mediaScanIntent.setData(contentUri);
//        MainActivity.this.sendBroadcast(mediaScanIntent);
//    }
}
