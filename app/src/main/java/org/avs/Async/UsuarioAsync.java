package org.avs.Async;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.avs.usuario.Usuario;

/**
 * Created by pessoal on 22/06/2015.
 */
public class UsuarioAsync extends AsyncTask<Object, Object, String> {

    private ProgressDialog progress;

    private Context context;
    private Usuario usuario;
    private Activity activity;

    public UsuarioAsync(Context context, Usuario usuario, Activity activity){
        this.context = context;
        this.usuario = usuario;
        this.activity = activity;
    }

    @Override
    protected  void onPreExecute(){
        progress = ProgressDialog.show(this.context, "Aguarde...", "", true, true);
    }

    @Override
    protected String doInBackground(Object... params) {



        return null;
    }

    @Override
    protected  void onPostExecute(String result){
        progress.dismiss();
    }

}
