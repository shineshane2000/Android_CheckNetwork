package com.nayuu.checknetwork;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.util.Log;

/**
 * Created by Akatsuki on 2019/5/28 11:10.
 */
class CheckNetwork {
    private static final CheckNetwork ourInstance = new CheckNetwork();
    private final String TAG = this.getClass().getSimpleName();

    static CheckNetwork getInstance() {
        return ourInstance;
    }

    private CheckNetwork() {
    }


    public void checkType1(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null) {
            //網路是否已連線(true or false)
            Boolean isConnect = networkInfo.isConnected();
            Log.d(TAG, "isConnected = " + isConnect);

            //網路連線方式名稱(WIFI or mobile)
            String typeName = networkInfo.getTypeName();
            Log.d(TAG, "typeName = " + typeName);

            //網路連線狀態
            NetworkInfo.State state = networkInfo.getState();
            Log.d(TAG, "networkInfo state = " + state);

            //網路是否可使用
            Boolean isAvailable = networkInfo.isAvailable();
            Log.d(TAG, "isAvailable = " + isAvailable);

            //網路是否已連接or連線中
            Boolean isConnectedOrConnecting = networkInfo.isConnectedOrConnecting();
            Log.d(TAG, "isConnectedOrConnecting = " + isConnectedOrConnecting);

            //網路是否故障有問題
            Boolean isFailover = networkInfo.isFailover();
            Log.d(TAG, "isFailover = " + isFailover);

            //網路是否在漫遊模式
            Boolean isRoaming = networkInfo.isRoaming();
            Log.d(TAG, "isRoaming = " + isRoaming);


        }
    }


    //API21使用
    public void checkType2(Context context){
        ConnectivityManager connectivityManager;
        if ((connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)) != null) {
            NetworkRequest.Builder builder = new NetworkRequest.Builder();
            connectivityManager.registerNetworkCallback(builder.build(), new ConnectivityManager.NetworkCallback() {

                @Override
                public void onAvailable(Network network) {
                    //有網路
                    Log.d(TAG, "isConnected = true");

                }

                @Override
                public void onLost(Network network) {
                    //未連網
                    Log.d(TAG, "isConnected = false");
                }
            });
        }
    }



}
