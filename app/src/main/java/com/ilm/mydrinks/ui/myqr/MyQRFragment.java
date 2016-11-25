package com.ilm.mydrinks.ui.myqr;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.ilm.mydrinks.R;
import com.ilm.mydrinks.utility.SessionManager;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ilm.mydrinks.ui.myqr.MyQRActivity.WIDTH;

/**
 * Created by ERD on 11/11/2016.
 */

public class MyQRFragment extends Fragment {
    @BindView(R.id.img_qr_code)ImageView imageQRCode;

    private static final String TAG = "HomeFragment";
    private SessionManager sessionManager;
    private String QRcode;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_my_qr, container, false);
        ButterKnife.bind(this, view);

        sessionManager = new SessionManager(getActivity().getApplicationContext());
        sessionManager.checkLogin();

        if (!sessionManager.isLoggedIn()) {
            sessionManager.setLogin(false);
            sessionManager.logoutUser();
        }

        final HashMap<String, String> user = sessionManager.getUserDetails();
        QRcode = user.get(SessionManager.KEY_CUSTOMER_CODE);

        final ProgressDialog dialog = ProgressDialog.show(getActivity(), "", "loading...");
        dialog.setCancelable(false);

        // create thread to avoid ANR Exception
        Thread t = new Thread(new Runnable() {
            public void run() {
//                QRcode = username;                // this is the msg which will be encode in QRcode
                try {
                    synchronized (this) {
                        wait(2000);
                        // runOnUiThread method used to do UI task in main thread.
                        getActivity().runOnUiThread(new Runnable() {
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

        return view;
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
