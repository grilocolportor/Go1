package org.avs.util;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.os.Environment;
import android.util.Log;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import org.avs.usuario.Usuario;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

    public String getDataToString(String formato){
        SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
        // OU
       // SimpleDateFormat dateFormat_hora = new SimpleDateFormat("HH:mm:ss");

        Date data = new Date();

        Calendar  cal = Calendar.getInstance();
        cal.setTime(data);
        Date data_atual = cal.getTime();

        String data_completa = dateFormat.format(data_atual);

        //String hora_atual = dateFormat_hora.format(data_atual);

        return data_completa;
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

    public File createFolder(String filePath, String fileName, Context context){

        ContextWrapper contextWrapper = new ContextWrapper(context.getApplicationContext());
        File directory = contextWrapper.getDir(filePath, Context.MODE_PRIVATE);
        File internalFile = new File(directory, fileName);

        return internalFile;

    }

    public void saveFile(File file,  Bitmap bitmap){
        try {
            FileOutputStream fos = new FileOutputStream(file);
            //fos.write(bitmap);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int uploadFile(Usuario usuario) {

        String sourceFileUri = usuario.getPhoto();
        String fileName = sourceFileUri;

        HttpURLConnection conn = null;
        DataOutputStream dos = null;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
       // File sourceFile = new File(sourceFileUri);

        int serverResponseCode = 0;

            try {

                // open a URL connection to the Servlet
                FileInputStream fileInputStream = new FileInputStream(sourceFileUri);
                URL url = new URL(Constantes.UPLOAD_FILE_PATH);

                // Open a HTTP  connection to  the URL
                conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true); // Allow Inputs
                conn.setDoOutput(true); // Allow Outputs
                conn.setUseCaches(false); // Don't use a Cached Copy
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Connection", "Keep-Alive");
                conn.setRequestProperty("ENCTYPE", "multipart/form-data");
                conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                conn.setRequestProperty("uploaded_file", fileName);

                dos = new DataOutputStream(conn.getOutputStream());

                dos.writeBytes(twoHyphens + boundary + lineEnd);
                dos.writeBytes("Content-Disposition: form-data; name=" + usuario.getPhoto() + ";filename="
                                + fileName + "" + lineEnd);

                        dos.writeBytes(lineEnd);

                // create a buffer of  maximum size
                bytesAvailable = fileInputStream.available();

                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                buffer = new byte[bufferSize];

                // read file and write it into form...
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                while (bytesRead > 0) {

                    dos.write(buffer, 0, bufferSize);
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                }

                // send multipart form data necesssary after file data...
                dos.writeBytes(lineEnd);
                dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                // Responses from the server (code and message)
                serverResponseCode = conn.getResponseCode();
                String serverResponseMessage = conn.getResponseMessage();

                Log.i("uploadFile", "HTTP Response is : "
                        + serverResponseMessage + ": " + serverResponseCode);

                if(serverResponseCode == 200){

                   // runOnUiThread(new Runnable() {
                   //     public void run() {

                   //         String msg = "File Upload Completed.\n\n See uploaded file here : \n\n"
                   //                 +" http://www.androidexample.com/media/uploads/"
                   //                 +uploadFileName;

                   //         messageText.setText(msg);
                    //        Toast.makeText(UploadToServer.this, "File Upload Complete.",
                    //                Toast.LENGTH_SHORT).show();
                    //    }
                   // });
                }

                //close the streams //
                fileInputStream.close();
                dos.flush();
                dos.close();

            } catch (MalformedURLException ex) {


                ex.printStackTrace();


                Log.e("Upload file to server", "error: " + ex.getMessage(), ex);
            } catch (Exception e) {
                e.printStackTrace();

                // End else block
            }
        return serverResponseCode;
    }

    public  boolean createFolder(String path){

        boolean retorno = true;

        File file = new File(Environment.getExternalStorageDirectory(), path);
        if(!file.exists()){
            if(!file.mkdir()){
                retorno = false;
            }
        }

        return retorno;

    }

    public File copyFile(File src, File dst) throws IOException {
        InputStream in = new FileInputStream(src);
        OutputStream out = new FileOutputStream(dst);

        // Transfer bytes from in to out
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        in.close();
        out.close();

        File file = new File(dst, "");

        return file;
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
