package net.jiawa.androidtouchmodel;

import android.util.Log;
import android.view.MotionEvent;

public class LogUtils {

	final static String TAG = "xixia-1";

	public static String getEvent( MotionEvent event) {
		String motionEvent = "";
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			motionEvent = "DOWN";
			break;
		case MotionEvent.ACTION_MOVE:
			motionEvent = "MOVE";
			break;
		case MotionEvent.ACTION_UP:
			motionEvent = "UP";
			break;
		default:
			break;
		}
		return motionEvent;
	}

	public static void printLog(Class clazz,String method, MotionEvent ev){
		Log.d(TAG, clazz.getSimpleName() + ", " + method + ", " + getEvent(ev));
	}

	public static void printLog(Class clazz,String method, MotionEvent ev, boolean eat){
		Log.d(TAG, clazz.getSimpleName() + ", " + method + ", " + getEvent(ev) + ", EAT: " + eat);
	}

	public static void printEventLog(Class mClass,String position,boolean isDispatch, MotionEvent event){
		Log.d(TAG, mClass.getSimpleName() + ", " + position+"-来一个任务<"+getEvent(event)+">:需要给分派给下一届人做吗？"+isDispatch);
	}
}
