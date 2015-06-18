package org.avs.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;
import android.telephony.TelephonyManager;

import org.avs.usuario.Usuario;

/**
 * Created by Administrador on 16/Jun/2015.
 */
public class Util {

    private Context context;
    private static Util uniqueInstance  = new Util();
    private static final String TAG = "Util";


    public Util(){

    }

    public Util(Context context){

    }

    public static synchronized Util getInstance(){
        if(uniqueInstance!=null){
            uniqueInstance = new Util();
        }

        return uniqueInstance;
    }

    public  boolean isConnected(Context c){
        try {
            ConnectivityManager cm = (ConnectivityManager)
                    c.getSystemService(Context.CONNECTIVITY_SERVICE);
            String LogSync = null;
            String LogToUserTitle = null;
            if (cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected()) {
                LogSync += "\nConectado a Internet 3G ";
                LogToUserTitle += "Conectado a Internet 3G ";
                //handler.sendEmptyMessage(0);
                Log.d(TAG,"Status de conexão 3G: "+cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected());
                return true;
            } else if(cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected()){
                LogSync += "\nConectado a Internet WIFI ";
                LogToUserTitle += "Conectado a Internet WIFI ";
                //handler.sendEmptyMessage(0);
                Log.d(TAG,"Status de conexão Wifi: "+cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected());
                return true;
            } else {
                LogSync += "\nNão possui conexão com a internet ";
                LogToUserTitle += "Não possui conexão com a internet ";
                //handler.sendEmptyMessage(0);
                Log.e(TAG,"Status de conexão Wifi: "+cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected());
                Log.e(TAG,"Status de conexão 3G: "+cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected());
                return false;
            }
        } catch (Exception e) {
            Log.e(TAG,e.getMessage());
            return false;
        }
    }

    public Usuario phoneInformation(Context context) {
        Usuario usuario = new Usuario();

        TelephonyManager tMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        usuario.setPhone(tMgr.getLine1Number());
        usuario.setImei(tMgr.getDeviceId());
        usuario.setSerialSim(tMgr.getSimSerialNumber());
        usuario.setCountry(tMgr.getSimCountryIso());

        return usuario;
    }
//    public static List<Usuario> getContatos(Context context) {
//
//        Usuario contatos = new Contact();
//
//        List<Contact> listaContatos = new ArrayList<Contact>();
//
//        List<String> fone = new ArrayList<String>();
//
//        String phoneNumber = null;
//
//        Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
//        String _ID = ContactsContract.Contacts._ID;
//        String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
//        String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;
//
//        Uri PhoneCONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
//        String Phone_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
//        String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;
//        Uri EmailCONTENT_URI = ContactsContract.CommonDataKinds.Email.CONTENT_URI;
//        String EmailCONTACT_ID = ContactsContract.CommonDataKinds.Email.CONTACT_ID;
//        String DATA = ContactsContract.CommonDataKinds.Email.DATA;
//        StringBuffer output = new StringBuffer();
//        ContentResolver contentResolver = context.getContentResolver();
//        Cursor cursor = contentResolver.query(CONTENT_URI, null, null, null,
//                null);
//        // Loop for every contact in the phone
//        if (cursor.getCount() > 0) {
//            while (cursor.moveToNext()) {
//                String contact_id = cursor
//                        .getString(cursor.getColumnIndex(_ID));
//                String name = cursor.getString(cursor
//                        .getColumnIndex(DISPLAY_NAME));
//                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor
//                        .getColumnIndex(HAS_PHONE_NUMBER)));
//                if (hasPhoneNumber > 0) {
//                    output.append("\n First Name:" + name);
//
//                    // Query and loop for every phone number of the contact
//                    Cursor phoneCursor = contentResolver.query(
//                            PhoneCONTENT_URI, null, Phone_CONTACT_ID + " = ?",
//                            new String[] { contact_id }, null);
//                    while (phoneCursor.moveToNext()) {
//                        phoneNumber = phoneCursor.getString(phoneCursor
//                                .getColumnIndex(NUMBER));
//                        output.append("\n Phone number:" + phoneNumber);
//                        fone.add(phoneNumber);
//                    }
//                    phoneCursor.close();
//
//                }
//
//                contatos.setName(name);
//                contatos.setPhone(fone);
//                listaContatos.add(contatos);
//
//
//            }
//        }
//
//        return listaContatos;
//
//    }

}
