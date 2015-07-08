package org.avs.Async;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.avs.json.JSONParser;
import org.avs.usuario.Usuario;
import org.avs.util.Constantes;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pessoal on 22/06/2015.
 */
public class UsuarioAsync extends AsyncTask<Object, Object, String> {

    private ProgressDialog progress;

    private Context context;
    private String  usuarioStr;
    private Activity activity;

    JSONParser jsonParser = new JSONParser();

    public UsuarioAsync(Context context, String usuarioStr, Activity activity){
        this.context = context;
        this.usuarioStr = usuarioStr;
        this.activity = activity;
    }

    @Override
    protected  void onPreExecute(){
        progress = ProgressDialog.show(this.context, "Aguarde...", "", true, true);
    }

    @Override
    protected String doInBackground(Object... params) {

        List<NameValuePair> param= new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("usuario", usuarioStr));

        JSONObject json = jsonParser.makeHttpRequest(Constantes.URL_SERVIDOR + Constantes.TAG_NEW_USUARIO,
                "POST", param);



        return null;
    }

    @Override
    protected  void onPostExecute(String result){
        progress.dismiss();
    }

}
