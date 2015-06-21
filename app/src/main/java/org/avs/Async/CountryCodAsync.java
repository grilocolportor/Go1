package org.avs.Async;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.avs.go.Cadastro;
import org.avs.go.R;
import org.avs.json.JSONParser;
import org.avs.usuario.Usuario;
import org.avs.util.Constantes;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrador on 18/Jun/2015.
 */
public class CountryCodAsync extends AsyncTask<Object, Object, String> {

    private Context context;
    private ProgressDialog progress;
    private String jsonAnswerCountry;
    private String jsonAnswerCountryCod;
    private Usuario usuario;
    private Activity activity;

    TextView lblCountry;
    TextView lblCountryCod;

    JSONParser jsonParser = new JSONParser();

    public CountryCodAsync(Context context, Usuario usuario, Activity activity){
        this.context = context;
        this.usuario = usuario;
        this.activity = activity;
    }

    @Override
    protected void onPreExecute(){
        progress = ProgressDialog.show(this.context, "Aguarde...", "", true, true);
    }

    @Override
    protected String doInBackground(Object... params) {


        int success;
        // Building Parameters
        List<NameValuePair> param= new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("country", usuario.getCountry()));

        JSONObject json = jsonParser.makeHttpRequest(Constantes.URL_SERVIDOR + Constantes.GET_COUNTRY_COD,
                "POST", param);

        // check log cat fro response
        //Log.d("Create Response", json.toString());

        try{
            if(json!=null) {
                success = json.getInt("success");
                if (success == 1) {

                   // JSONArray countryObj = json.getJSONArray("response");

                    //JSONObject country = countryObj.getJSONObject(0);

                    this.setJsonAnswerCountry(json.getString("country"));
                    this.setJsonAnswerCountryCod(json.getString("cod"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String result){
        progress.dismiss();
        this.lblCountry = (TextView) this.activity.findViewById(R.id.lblCountry);
        this.lblCountryCod = (TextView) this.activity.findViewById(R.id.lblCountryCod);
        this.lblCountry.setText(getJsonAnswerCountry());
        this.lblCountryCod.setText(getJsonAnswerCountryCod());

        usuario.setCountryCod(getJsonAnswerCountryCod());
        //if(process){
        //    Intent it = new Intent("START_SERVICE");
        //    it.putExtra("flag", 1);
        //    this.context.startService(it);
        //}
        //Cadastro.getUser(jsonAnswer);
        //Toast.makeText(context, result, Toast.LENGTH_LONG).show();

    }
    public String getJsonAnswerCountry() {
        return jsonAnswerCountry;
    }

    private void setJsonAnswerCountry(String jsonAnswerCountry) {
        this.jsonAnswerCountry = jsonAnswerCountry;
    }

    public String getJsonAnswerCountryCod() {
        return jsonAnswerCountryCod;
    }

    private void setJsonAnswerCountryCod(String jsonAnswerCountryCod) {
        this.jsonAnswerCountryCod = jsonAnswerCountryCod;
    }

}
