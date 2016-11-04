package com.ilm.mydrinks.ui.myqr;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ilm.mydrinks.R;
import com.ilm.mydrinks.ui.main.MainActivity;
import com.ilm.mydrinks.utility.SessionManager;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyQRActivity extends AppCompatActivity {
    @BindView(R.id.img_qr_code)ImageView imageQRCode;
    @BindView(R.id.txt_name)TextView txtName;
    @BindView(R.id.txt_points)TextView txtPoint;

    public String QRcode, firstName, lastName, point;
    public final static int WIDTH = 500;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_qr);
        ButterKnife.bind(this);

        sessionManager = new SessionManager(getApplicationContext());
        sessionManager.checkLogin();

        if (!sessionManager.isLoggedIn()) {
            sessionManager.setLogin(false);
            sessionManager.logoutUser();
            finish();
        }

        final HashMap<String, String> user = sessionManager.getUserDetails();
        firstName = user.get(SessionManager.KEY_FIRST_NAME);
        lastName = user.get(SessionManager.KEY_LAST_NAME);
        QRcode = user.get(SessionManager.KEY_CUSTOMER_CODE);
        point = user.get(SessionManager.KEY_POINT_BALANCE);


        txtName.setText(firstName + " " + lastName);
        txtPoint.setText(point + " pts");

        final ProgressDialog dialog = ProgressDialog.show(MyQRActivity.this, "", "loading...");
        dialog.setCancelable(false);

        // create thread to avoid ANR Exception
        Thread t = new Thread(new Runnable() {
            public void run() {
//                QRcode = username;                // this is the msg which will be encode in QRcode
                try {
                    synchronized (this) {
                        wait(2000);
                        // runOnUiThread method used to do UI task in main thread.
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    dialog.dismiss();

                                    Bitmap bitmap = null;
                                    bitmap = encodeAsBitmap(QRcode);

                                    imageQRCode.setImageBitmap(bitmap);
                                } catch (WriterException e) {
                                    dialog.dismiss();
                                    e.printStackTrace();
                                } // end of catch block
                            } // end of run method
                        });
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

    @OnClick(R.id.btn_my_qr_close)
    public void closeToMain(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    // this is method call from on create and return bitmap image of QRCode.
    Bitmap encodeAsBitmap(String str) throws WriterException {
        BitMatrix result;
        try {
            result = new MultiFormatWriter().encode(str,
                    BarcodeFormat.QR_CODE, WIDTH, WIDTH, null);
        } catch (IllegalArgumentException iae) {
            // Unsupported format
            return null;
        }
        int w = result.getWidth();
        int h = result.getHeight();
        int[] pixels = new int[w * h];
        for (int y = 0; y < h; y++) {
            int offset = y * w;
            for (int x = 0; x < w; x++) {
                pixels[offset + x] = result.get(x, y) ? getResources().getColor(R.color.black):getResources().getColor(R.color.white);
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, 500, 0, 0, w, h);
        return bitmap;
    } /// end of this method

}
