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


    private Usuario usuario;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        context = this;

        usuario = new Usuario();
        usuario = Util.getInstance().phoneInformation(this);

        this.txtName = (EditText) findViewById(R.id.txtName);
        this.txtArea_cod = (EditText) findViewById(R.id.txtArea_cod);
        this.txtPhone = (EditText) findViewById(R.id.txtPhone);

        this.txtPhone.setText(usuario.getPhone());

        //this.lblCountry = (TextView) findViewById(R.id.lblCountry);
        //this.lblCountryCod = (TextView) findViewById(R.id.lblCountryCod);


                //buscar o codigo do pais do usuario

                CountryCodAsync countryCodAsync = new CountryCodAsync(context, usuario, this );
                countryCodAsync.execute();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu);
        MenuItem m1 = menu.add(R.id.mnuAvancar);
        //m1.setIcon(R.drawable.ic_action_forward);
        //m1.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

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
