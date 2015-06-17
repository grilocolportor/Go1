package org.avs.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrador on 17/Jun/2015.
 */
public class DB extends SQLiteOpenHelper {

    private static final String NOME_BD = "go";
    private static final int VERSAO_BD = 1;


    public DB(Context context) {
        super(context, NOME_BD, null, VERSAO_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase arg){

    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2){

    }

}
