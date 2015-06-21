package org.avs.util;

import java.io.File;

/**
 * Created by Administrador on 18/Jun/2015.
 */
public class Constantes {
    public static String URL_SERVIDOR = "http://192.168.56.1:80/Go/";
   // public static String URL_SERVIDOR = "http://10.0.2.2:80/Go/";
    //public static String URL_SERVIDOR = "http://192.168.1.7:80/go/ConsultaPhone.php";
   // public static String URL_SERVIDOR = "http://www.meaxe.com.br/Go/";
    public static String GET_COUNTRY_COD = "getCountry.php";
    //public static String CREATE_USER = "create_user.php";
    //public static String FIND_FRIENDS = "findFriends.php";
    //public static String MATCH_FRIENDS = "matchFriends.php";
    public static String UPLOAD_FILE_PATH = "http://192.168.56.1:80/Go/uploadFile.php";


    /*constantes de msg*/
    public static String EMPTY_FIELD = "A seguinte informacao esta faltando: ";

    /*pasta e arquivos*/

    public static final String FOLDER_GO = File.separator
            + "Go";/* android.os.Environment
            .getExternalStorageDirectory()
            + File.separator
            + "Go";*/
    public static final String FOLDER_MEDIA =  File.separator
            + "Go" + File.separator + "media"; /*android.os.Environment
            .getExternalStorageDirectory()
            + File.separator
            + "Go" + File.separator + "media";*/
    public static final String FOLDER_IMAGE = File.separator
            + "Go" + File.separator + "media" + File.separator + "image"; /*android.os.Environment
            .getExternalStorageDirectory()
            + File.separator
            + "Go" + File.separator + "media" + File.separator + "image";;*/

}
