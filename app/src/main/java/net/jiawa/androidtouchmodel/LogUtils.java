package net.jiawa.androidtouchmodel;

import android.util.Log;
import android.view.MotionEvent;

import java.util.Locale;

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

	static String format(String str, int length) {
		return String.format(Locale.ENGLISH, "%" + length + "s", str);
	}

	static int getMaxLength(String str1, String str2, String str3) {
		return Math.max(Math.max(str1.length(), str2.length()), str3.length());
	}

	static int getClassLength() {
		return Math.max(getMaxLength("MainActivity", "FirstFrameLayout", "SecondRelativeLayout"), "ThirdTextView".length());
	}

	static int getMethodLength() {
		return getMaxLength("dispatchTouchEvent", "onInterceptTouchEvent", "onTouchEvent");
	}

	static int getEventLength() {
		return getMaxLength("DOWN", "MOVE", "UP");
	}

	public static void printLog(Class clazz,String method, MotionEvent ev){
		Log.d(TAG,
				format(clazz.getSimpleName(), getClassLength())
					   + ", " +
				format(method, getMethodLength())
					   + ", " +
				format(getEvent(ev), getEventLength())
			 );
	}

	public static void printLog(Class clazz,String method, MotionEvent ev, boolean eat){
		Log.d(TAG,
				format(clazz.getSimpleName(), getClassLength())
						+ ", " +
						format(method, getMethodLength())
						+ ", " +
						format(getEvent(ev), getEventLength())
						+ ", " +
						"EAT: " + eat
		);
	}

	public static void printEventLog(Class mClass,String position,boolean isDispatch, MotionEvent event){
		Log.d(TAG, mClass.getSimpleName() + ", " + position+"-来一个任务<"+getEvent(event)+">:需要给分派给下一届人做吗？"+isDispatch);
	}
}
