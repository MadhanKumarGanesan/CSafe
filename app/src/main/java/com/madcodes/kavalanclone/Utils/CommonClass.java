package com.madcodes.kavalanclone.Utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.madcodes.kavalanclone.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.LOCATION_HARDWARE;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class CommonClass {
    private static final int PERMISSION_REQUEST_CODE = 200;

    static Dialog customAlert;

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", Pattern.CASE_INSENSITIVE);


    public static boolean validateEmail(CharSequence emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public static void cusomAlertDialog(Context context, String title, String desc) {
        customAlert = new Dialog(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_alert_dialog, null);
        customAlert.setContentView(view);
        Window window = customAlert.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        Button button = view.findViewById(R.id.ok_btn);
        TextView alert_mess = view.findViewById(R.id.alert_mess);
        TextView head_mess = view.findViewById(R.id.head_mess);
        alert_mess.setText(desc);
        head_mess.setText(title);
        customAlert.show();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customAlert.dismiss();
            }
        });
    }

    public static void CreateAlert(final Activity context) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
        builder.setTitle("ATTENTION");
        builder.setMessage("Please turn on your mobile network and try again.");
        builder.setCancelable(false);

        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.cancel();
                Intent intent = new Intent(Settings.ACTION_SETTINGS);
                context.startActivity(intent);
                //context.finish();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }

    public static boolean isNetworkStatusAvialable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo netInfos = connectivityManager.getActiveNetworkInfo();
            if (netInfos != null)
                return netInfos.isConnected();
        }
        return false;
    }

    public static boolean checkPermission(Activity context) {
        int result = ContextCompat.checkSelfPermission(context, WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(context, READ_EXTERNAL_STORAGE);
        int result2 = ContextCompat.checkSelfPermission(context, CAMERA);
        int result3 = ContextCompat.checkSelfPermission(context, ACCESS_COARSE_LOCATION);
        int result4 = ContextCompat.checkSelfPermission(context, ACCESS_FINE_LOCATION);
        //  int result4 = ContextCompat.checkSelfPermission(context, LOCATION_HARDWARE);

        return result == PackageManager.PERMISSION_GRANTED &&
                result1 == PackageManager.PERMISSION_GRANTED &&
                result2 == PackageManager.PERMISSION_GRANTED &&
                result3 == PackageManager.PERMISSION_GRANTED &&
                result4 == PackageManager.PERMISSION_GRANTED;  /*&&
                result5 == PackageManager.PERMISSION_GRANTED;*/


    }

    public static void requestPermission(Activity context) {

        ActivityCompat.requestPermissions(context, new String[]
                        {
                                WRITE_EXTERNAL_STORAGE,
                                READ_EXTERNAL_STORAGE,
                                CAMERA,
                                ACCESS_COARSE_LOCATION,
                                ACCESS_FINE_LOCATION
                        },
                PERMISSION_REQUEST_CODE);


    }

}
