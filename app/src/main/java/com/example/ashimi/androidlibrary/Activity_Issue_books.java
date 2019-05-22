package com.example.ashimi.androidlibrary;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ashimi.androidlibrary.models.Borrowing;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.Date;

public class Activity_Issue_books extends AppCompatActivity {

    Borrowing newBrw;
    SurfaceView cameraField;
    CameraSource cameraSource;
    TextView textView;
    BarcodeDetector barcodeDetector;
    BarcodeDetector barcodeDetector1;
    private static final int REQUEST_CAMERA = 0;

    DatabaseReference ref;
    long maxID;
    Date today;
    boolean done = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_books);

        newBrw = new Borrowing();
        today = new Date();

        cameraField = (SurfaceView) findViewById(R.id.qrcamerapreview);
        textView = findViewById(R.id.qrTextView);

        barcodeDetector = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.QR_CODE).build();

        cameraSource = new CameraSource.Builder(this, barcodeDetector).setAutoFocusEnabled(true)
                .setRequestedPreviewSize(640, 480).build();

        cameraField.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {

                try {
                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        Log.d("camError","no permission");
                        if (ActivityCompat.shouldShowRequestPermissionRationale(Activity_Issue_books.this,
                                Manifest.permission.CAMERA)) {

                            Log.d("camError","no permission rationale");

                            // Show an explanation to the user *asynchronously* -- don't block
                            // this thread waiting for the user's response! After the user
                            // sees the explanation, try again to request the permission.

                        } else {
                            ActivityCompat.requestPermissions(Activity_Issue_books.this,
                                    new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
                        }
                    }
                    cameraSource.start(holder);

                    Log.d("camError","has permission");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> qrCodes = detections.getDetectedItems();

                if(qrCodes.size()!=0){

                    ref = FirebaseDatabase.getInstance().getReference().child("Borrowing");

                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.exists()){
                                maxID = (dataSnapshot.getChildrenCount());
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    //for now
                    newBrw.setBorrowerID(qrCodes.valueAt(0).displayValue);
                    newBrw.setISBN("test");
                    newBrw.setOutdate(today.toString());

                    try{
                        ref.child(String.valueOf(maxID + 1)).setValue(newBrw);
                        finish();
                        Toast.makeText(Activity_Issue_books.this, "Data Inserted Successfully", Toast.LENGTH_LONG).show();
                    }
                    catch (Exception e){
                        Log.e("Error", e.getLocalizedMessage());
                        Toast.makeText(Activity_Issue_books.this,"error"+ e, Toast.LENGTH_LONG).show();
                    }

//                    Intent intent = new Intent();
//                    intent.putExtra("qrValue",qrCodes.valueAt(0).displayValue);
//                    setResult(RESULT_OK,intent);finish();
//                    textView.post(new Runnable() {
////                        @Override
////                        public void run() {
////                            Vibrator vibrator = (Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
////                            vibrator.vibrate(1000);
////
////                            textView.setText(qrCodes.valueAt(0).displayValue);
////
////
////                        }
////                    });



                }

            }
        });


        // add to database
        if(done){
            Log.d("done","true");
            Log.d("value",newBrw.getBorrowerID());


        }


    }
}
