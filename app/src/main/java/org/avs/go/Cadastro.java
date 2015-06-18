package org.avs.go;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import org.avs.Async.CountryCodAsync;
import org.avs.usuario.Usuario;
import org.avs.util.Util;


public class Cadastro extends ActionBarActivity {

    private EditText txtName;
    private EditText txtArea_cod;
    private EditText txtPhone;
    private TextView lblCountry;
    private TextView lblCountryCod;

    private Usuario usuario;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        context = this;

        usuario = new Usuario();
        usuario = Util.getInstance().phoneInformation(this);

        this.lblCountry = (TextView) findViewById(R.id.lblCountry);
        this.lblCountryCod = (TextView) findViewById(R.id.lblCountryCod);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //buscar o codigo do pais do usuario

                CountryCodAsync countryCodAsync = new CountryCodAsync(context, usuario);
                countryCodAsync.execute();
                String cc = countryCodAsync.getJsonAnswerCountryCod();
                String c = countryCodAsync.getJsonAnswerCountry();
                if(cc!=null && c!=null) {
                    lblCountryCod.setText(cc);
                    lblCountry.setText(c);
                }else{
                    lblCountryCod.setText("");
                    lblCountry.setText("");
                }
            }
        });



        this.txtName = (EditText) findViewById(R.id.txtnome);
        this.txtArea_cod = (EditText) findViewById(R.id.txtArea_cod);
        this.txtPhone = (EditText) findViewById(R.id.txtPhone);

        this.txtPhone.setText(usuario.getPhone());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cadastro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
