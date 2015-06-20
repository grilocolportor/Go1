package org.avs.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrador on 17/Jun/2015.
 */
public class DB extends SQLiteOpenHelper {

    private static final String NOME_BD = "go";
    private static final int VERSAO_BD = 6;

    private static final String CREATE_DATABASE = "CREATE TABLE USUARIO(_ID BIGINT AUTO_INCREMENT PRIMARY KEY," +
                                                    " PHONE VARCHAR(50) NOT NULL, " +
                                                    " NOME VARCHAR(50) NOT NULL," +
                                                    " AREA_COD VARCHAR(4) NOT NULL," +
                                                    " COUNTRY VARCHAR(3) NOT NULL," +
                                                    " IMEI VARCHAR(50) NOT NULL," +
                                                    " SERIAL_SIM VARCHAR(50) NOT NULL," +
                                                    " STATUS INT NOT NULL, " +
                                                    " PHOTO VARCHAR(50) NOT NULL, " +
                                                    " BORN VARCHAR(10) NOT NULL)";


    public DB(Context context) {
        super(context, NOME_BD, null, VERSAO_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase bd){
        bd.execSQL(CREATE_DATABASE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int arg1, int arg2){
        bd.execSQL("DROP TABLE USUARIO");
        onCreate(bd);
    }

    public void closeDB(SQLiteDatabase bd){
        bd.close();
    }

}
