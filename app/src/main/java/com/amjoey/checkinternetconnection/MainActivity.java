package com.amjoey.checkinternetconnection;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.time.Instant;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(isInternetConnected()){

        }else{
            showAlertNoNet();
        }
    }
    private boolean isInternetConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isConnected()){
            return true;
        }else{
            return false;
        }
    }
    private void showAlertNoNet(){
        AlertDialog.Builder alertDlg = new AlertDialog.Builder(this);

        alertDlg.setMessage("App นี้ต้องเชื่อมต่อเน็ท กรุณาเปิด..")
                .setTitle("แจ้งการใช้งาน")
                .setIcon(R.drawable.ic_launcher_background)
                .setCancelable(false)
                .setPositiveButton("เข้าตั้งค่า เปิดเน็ท",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent gpsOptionsIntent = new Intent(android.provider.Settings.ACTION_SETTINGS);
                                startActivityForResult(gpsOptionsIntent,0);
                            }
                        }
                );

        alertDlg.setNegativeButton("เปิดไปก่อน",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }
        );

        AlertDialog  alert = alertDlg.create();
        alert.show();

    }
}
