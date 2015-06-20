package org.avs.go;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import org.avs.Async.CountryCodAsync;
import org.avs.usuario.Usuario;
import org.avs.util.Constantes;
import org.avs.util.CustomToast;
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
        /* Inflate the menu; this adds items to the action bar if it is present. */
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.mnuAvancar:

                CustomToast ct = new CustomToast();
                if(this.txtName.length()==0){
                    ct.showToast(Constantes.EMPTY_FIELD + "nome", this, CustomToast.TYPE_TOAST_ALERT);
                    this.txtName.requestFocus();
                }else if(this.txtArea_cod.length()==0){
                    ct.showToast(Constantes.EMPTY_FIELD + "codigo de area", this, CustomToast.TYPE_TOAST_ALERT);
                    this.txtArea_cod.requestFocus();
                }else if(this.txtPhone.length()==0){
                    ct.showToast(Constantes.EMPTY_FIELD + "numero do telefone", this, CustomToast.TYPE_TOAST_ALERT);
                    this.txtPhone.requestFocus();
                }else{
//				user = new User();
//				user.setCountry(phone.getCountry());
//				user.setPhone(txtAreaCod.getText().toString()+txtPhoneNumber.getText().toString());
//				String phone = GeneralFunctions.getFormatedPhoneNumber(txtAreaCod.getText().toString()+txtPhoneNumber.getText().toString(), user);
//				User findUser = new User();
//				findUser.setPhone(phone);
//				GetUser getUser = new GetUser(this, findUser, Constantes.TASK_MATCH_FRIEND);
//				getUser.execute();
//				List<User> listUser = new ArrayList<User>();
//				listUser = getUser.getListUser();
//				if(listUser.isEmpty()){

                    usuario.setAreaCod(this.txtArea_cod.toString());
                    usuario.setPhone(this.txtPhone.toString());
                    usuario.setNome(this.txtName.toString());


                    Intent intent = new Intent(this, Photo.class);
                    //intent.putExtra("areaCode", txtAreaCod.getText().toString());
                    //intent.putExtra("phoneNumber", txtPhoneNumber.getText().toString());
                    intent.putExtra("usuario", usuario);
                    startActivity(intent);
//				}else{
//					Intent intent = new Intent(Cadastro.this, MainActivity.class);
//					startActivity(intent);
//				}
                    Cadastro.this.finish();
                }
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);

    }
}
