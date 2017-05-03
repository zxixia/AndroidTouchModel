package net.jiawa.androidtouchmodel;

import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Locale;

public class LogUtils {

	final static String TAG = "xixia-1";

	public static String METHOD_DISPATCH_TOUCH_EVENT = "dispatchTouchEvent";
	public static String METHOD_ON_INTERCEPT_TOUCH_EVENT = "onInterceptTouchEvent";
	public static String METHOD_ON_TOUCH_EVENT = "onTouchEvent";

	public static String name_1 = "--11--";
	public static String name_2 = "--22--";
	public static String name_3 = "--33--";
	public static String name_4 = "--44--";
	public static String name_5 = "--55--";
	public static String name_6 = "--66--";

	public static String getEvent( MotionEvent event) {
		String motionEvent = "";
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			motionEvent = "V  ";
			break;
		case MotionEvent.ACTION_MOVE:
			motionEvent = "-----";
			break;
		case MotionEvent.ACTION_UP:
			motionEvent = "A  ";
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

	public static void log(String name, String method, MotionEvent event) {
		StringBuilder sb = new StringBuilder();
		sb.append(name);
		sb.append(",  ");
		sb.append(format(method, getMethodLength()));
		sb.append(",  ");
		sb.append(format(getEvent(event),5 ));
		if (canLog(event, sb.toString())) {
			Log.d(TAG, sb.toString());
		}
	}

	public static void log(String name, String method, MotionEvent event, boolean eat) {
		StringBuilder sb = new StringBuilder();
		sb.append(name);
		sb.append(",  ");
		sb.append(format(method, getMethodLength()));
		sb.append(",  ");
		sb.append(format(getEvent(event), 5));
		sb.append(",  ");
		sb.append(eat);
		if (canLog(event, sb.toString())) {
			Log.d(TAG, sb.toString());
		}
	}

	public static void logEmptyLine() {
		Log.d(TAG, "\n\n\n\n\n");
	}

	static ArrayList<String> mCache = new ArrayList<String>();
	public static void clearCache() {
		mCache.clear();
	}

	private static boolean canLog(MotionEvent event, String str) {
		if (event.getAction() != MotionEvent.ACTION_MOVE) {
			return true;
		}
		if (mCache.contains(str)) {
			return false;
		}
		mCache.add(str);
		return true;
	}

	public static void printHead() {
		StringBuilder sb = new StringBuilder();
		sb.append(format("No", 5));
		sb.append(",  ");
		sb.append(format("Method", getMethodLength()));
		sb.append(",  ");
		sb.append(format("Event", 5));
		sb.append(",  ");
		sb.append("Eat");
		Log.d(TAG, sb.toString());
	}
}
