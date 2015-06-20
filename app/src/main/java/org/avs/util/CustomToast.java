package org.avs.util;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.avs.go.R;

public class CustomToast{

	public static final int TYPE_TOAST_UNDEFINED = 0;
	public static final int TYPE_TOAST_OK = 1;
	public static final int TYPE_TOAST_ALERT = 2;
	public static final int TYPE_TOAST_ERRO = 3;

	public void showToast(String text, Activity activity, int type){
		Context context=activity.getApplicationContext();
		LayoutInflater inflater=activity.getLayoutInflater();

		View customToastroot = null;

		if(type == TYPE_TOAST_UNDEFINED) {
			customToastroot = inflater.inflate(R.layout.mytoast, null);
		}else if(type == TYPE_TOAST_ALERT) {
			customToastroot = inflater.inflate(R.layout.yellow_toast, null);
		}else if(type == TYPE_TOAST_ERRO) {
			customToastroot = inflater.inflate(R.layout.red_toast, null);
		}else if(type == TYPE_TOAST_OK) {
			customToastroot = inflater.inflate(R.layout.blue_toast, null);
		}

		TextView txt = (TextView) customToastroot.findViewById(R.id.txtAlertMsg);
		txt.setText(text);

		Toast customtoast=new Toast(context);

		customtoast.setView(customToastroot);
		customtoast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
		customtoast.setDuration(Toast.LENGTH_LONG);
		customtoast.show();
	}
}
