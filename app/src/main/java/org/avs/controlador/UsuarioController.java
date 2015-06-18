package org.avs.controlador;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.avs.db.DB;
import org.avs.usuario.Usuario;

/**
 * Created by Administrador on 17/Jun/2015.
 */
public class UsuarioController {
    private SQLiteDatabase db;

    public UsuarioController(Context context){
        DB auxDb = new DB(context);
        db = auxDb.getWritableDatabase();
    }

    public void inserirUsuario(Usuario usuario){
        ContentValues value = new ContentValues();
        value.put("phone", usuario.getPhone());
        value.put("nome", usuario.getNome());
        value.put("areaCod", usuario.getAreaCod());
        value.put("country", usuario.getCountry());
        value.put("imei", usuario.getImei());
        value.put("serialSim", usuario.getSerialSim());
        value.put("status", usuario.getStatus());
        db.insert("usuario", null, value);

    }

    public void updateUsuario(Usuario usuario){
        ContentValues value = new ContentValues();
        value.put("phone", usuario.getPhone());
        value.put("nome", usuario.getNome());
        value.put("areaCod", usuario.getAreaCod());
        value.put("country", usuario.getCountry());
        value.put("imei", usuario.getImei());
        value.put("serialSim", usuario.getSerialSim());
        value.put("status", usuario.getStatus());
        db.update("usuario", value, "_id = ?", new String[]{"" + usuario.getId()});

    }

    public void deleteUsuario(Usuario usuario){
        db.delete("usuario", " _id = " + usuario.getId(), null);

    }

    public Usuario searchUsuario(){
        Usuario usuario = null;
        String[] colunas = new String[]{"_id", "phone", "nome", "area_Cod", "country", "imei", "serial_Sim", "status"};
        Cursor cursor = db.query("usuario", colunas, null, null, null, null, null);

        if(cursor.getCount()>0){
            usuario = new Usuario();
            usuario.setId(cursor.getInt(cursor.getColumnIndex("_id")));
            usuario.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
            usuario.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            usuario.setAreaCod(cursor.getString(cursor.getColumnIndex("area_Cod")));
            usuario.setCountry(cursor.getString(cursor.getColumnIndex("country")));
            usuario.setImei(cursor.getString(cursor.getColumnIndex("imei")));
            usuario.setSerialSim(cursor.getString(cursor.getColumnIndex("serial_Sim")));
            usuario.setStatus(cursor.getString(cursor.getColumnIndex("status")));
        }

        cursor.close();
        return usuario;
    }
}
