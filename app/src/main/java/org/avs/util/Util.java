package org.avs.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

/**
 * Created by Administrador on 16/Jun/2015.
 */
public class Util {

    private Context context;

    private static final String TAG = "Util";

    public Util(){

    }

    public Util(Context context){

    }

    public static boolean isConnected(Context c){
        try {
            ConnectivityManager cm = (ConnectivityManager)
                    c.getSystemService(Context.CONNECTIVITY_SERVICE);
            String LogSync = null;
            String LogToUserTitle = null;
            if (cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected()) {
                LogSync += "\nConectado a Internet 3G ";
                LogToUserTitle += "Conectado a Internet 3G ";
                //handler.sendEmptyMessage(0);
                Log.d(TAG, "Status de conex�o 3G: " + cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected());
                return true;
            } else if(cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected()){
                LogSync += "\nConectado a Internet WIFI ";
                LogToUserTitle += "Conectado a Internet WIFI ";
                //handler.sendEmptyMessage(0);
                Log.d(TAG,"Status de conex�o Wifi: "+cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected());
                return true;
            } else {
                LogSync += "\nN�o possui conex�o com a internet ";
                LogToUserTitle += "N�o possui conex�o com a internet ";
                //handler.sendEmptyMessage(0);
                Log.e(TAG,"Status de conex�o Wifi: "+cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected());
                Log.e(TAG,"Status de conex�o 3G: "+cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected());
                return false;
            }
        } catch (Exception e) {
            Log.e(TAG,e.getMessage());
            return false;
        }
    }

}
