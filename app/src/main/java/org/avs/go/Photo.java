package org.avs.go;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import org.avs.usuario.Usuario;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


public class Photo extends ActionBarActivity {

    int REQUEST_CAMERA = 0;
    int SELECT_FILE = 1;

    private ImageView ivImage;
    private Bitmap bitmap;
    private File mainFile;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        Bundle extras = getIntent().getExtras();
        usuario = (Usuario) extras.getParcelable("usuario");

        ivImage = (ImageView) findViewById(R.id.ivImage);

    }

    private  void selectImage() {
        final CharSequence[] items = { "Camera", "Library",
                "Cancelar" };

        AlertDialog.Builder builder = new AlertDialog.Builder(Photo.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Camera")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(android.os.Environment
                            .getExternalStorageDirectory(), "temp.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    startActivityForResult(intent, REQUEST_CAMERA);
                } else if (items[item].equals("Library")) {
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(
                            Intent.createChooser(intent, "Select File"),
                            SELECT_FILE);
                } else if (items[item].equals("Cancelar")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CAMERA) {
                File f = new File(Environment.getExternalStorageDirectory()
                        .toString());
                if(f.listFiles()!=null){
                    for (File temp : f.listFiles()) {
                        if (temp.getName().equals("temp.jpg")) {
                            f = temp;
                            break;
                        }
                    }
                }
                try {
                    Bitmap bm;
                    BitmapFactory.Options btmapOptions = new BitmapFactory.Options();

                    bm = BitmapFactory.decodeFile(f.getAbsolutePath(),
                            btmapOptions);

                    bm = Bitmap.createScaledBitmap(bm, 70, 70, true);


                    OutputStream fOut = null;

                    String path1 = android.os.Environment
                            .getExternalStorageDirectory()
                            + File.separator
                            + "Go" + File.separator + "media" + File.separator + "image";


                    File file = new File(path1, "perfil.jpg" );

                    fOut = new FileOutputStream(file);
                    bm.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
                    bm = rotateBitmap(bm,file.getCanonicalPath());

                    fOut.flush();
                    fOut.close();
                    ivImage.setImageBitmap(bm);

                    mainFile = file;

                    //String path = Constantes.LOCAL_PICTURE_PERFIL_FILEPATH;

                    //f.delete();


                    //bitmap = ((BitmapDrawable)ivImage.getDrawable()).getBitmap();
                    //byte[] b = convertBitmapToByteArray(bitmap);
                    //String filePath = saveArrayToInternalStorage("perfil.jpg", bitmap);

//					File file = new File(path1, String.valueOf(System
//							.currentTimeMillis()) + ".jpg");
                    //File file = new File(path1);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == SELECT_FILE) {
                Uri selectedImageUri = data.getData();

                String tempPath = getPath(selectedImageUri, Photo.this);
                Bitmap bm;
                BitmapFactory.Options btmapOptions = new BitmapFactory.Options();
                bm = BitmapFactory.decodeFile(tempPath, btmapOptions);
                ivImage.setImageBitmap(bm);
                bitmap=bm;
            }
        }
    }

    public static Bitmap rotateBitmap(Bitmap bitmap, String fileName) {

        try{
            ExifInterface exif = new ExifInterface(fileName);
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);

            Matrix matrix = new Matrix();
            switch (orientation) {
                case ExifInterface.ORIENTATION_NORMAL:
                    return bitmap;
                case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                    matrix.setScale(-1, 1);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    matrix.setRotate(180);
                    break;
                case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                    matrix.setRotate(180);
                    matrix.postScale(-1, 1);
                    break;
                case ExifInterface.ORIENTATION_TRANSPOSE:
                    matrix.setRotate(90);
                    matrix.postScale(-1, 1);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_90:
                    matrix.setRotate(90);
                    break;
                case ExifInterface.ORIENTATION_TRANSVERSE:
                    matrix.setRotate(-90);
                    matrix.postScale(-1, 1);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    matrix.setRotate(-90);
                    break;
                default:
                    return bitmap;
            }
            try {
                Bitmap bmRotated = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                bitmap.recycle();
                return bmRotated;
            }
            catch (OutOfMemoryError e) {
                e.printStackTrace();
                return null;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getPath(Uri uri, Activity activity) {
        String[] projection = { MediaStore.MediaColumns.DATA };
        @SuppressWarnings("deprecation")
        Cursor cursor = activity
                .managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    public String saveArrayToInternalStorage(String fileName, Bitmap imagem){
        String retorno = null;
        try{
            FileOutputStream fos = this.openFileOutput(fileName, this.MODE_PRIVATE);
            imagem.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.close();
            File filePath = this.getFileStreamPath(fileName);
            retorno = filePath.toString();
        }catch (IOException e) {
            Log.w("InternalStorage", "Error writing", e);
        }
        return retorno;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_photo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case  R.id.mnuAvancar:

              //  Intent intent = new Intent(Pic.this, Termos.class);
                /**pega a image padrao do ImageView**/



                //String bit = GeneralFunctions.bitmapConvertString(bitmap);
                //user.setEncodeCodeBit64Bitmap(bit);

                //user.setPictureLocalPath(mainFile.getPath());
                //user.setPictureRemotePath("media/perfil/perfil.jpg");

                //	}
                //user.setPic(bitmap);
               // intent.putExtra("object", user);
                //startActivity(intent);
                //Ptoho.this.finish();

                break;
            case R.id.mnuImagem:

                selectImage();

                break;
            default:
                break;
        };

        return super.onOptionsItemSelected(item);
    }
}
