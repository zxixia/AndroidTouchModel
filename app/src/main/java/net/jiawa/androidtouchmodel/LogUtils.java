package net.jiawa.androidtouchmodel;

import android.util.Log;
import android.view.MotionEvent;

import net.jiawa.androidtouchmodel.bean.Route;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class LogUtils {

	final static String TAG = "xixia-1";
	static int sDonwCount = 0;
	static int sMoveCount = 0;
	static int sUpCount = 0;

	public static String METHOD_DISPATCH_TOUCH_EVENT = "dispatchTouchEvent";
	public static String METHOD_ON_INTERCEPT_TOUCH_EVENT = "onInterceptTouchEvent";
	public static String METHOD_ON_TOUCH_EVENT = "onTouchEvent";

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

	public static void log(String name, String method, MotionEvent event, int color) {
		StringBuilder sb = new StringBuilder();
		sb.append(name);
		sb.append(",  ");
		sb.append(format(method, getMethodLength()));
		sb.append(",  ");
		sb.append(format(getEvent(event),5 ));
		if (canLog(name, method, event, sb.toString(), color)) {
			Log.d(TAG, format(getCount(event), 6) + ",  " + sb.toString());
		}
	}

	public static void log(String name, String method, MotionEvent event, boolean eat, int color) {
		StringBuilder sb = new StringBuilder();
		sb.append(name);
		sb.append(",  ");
		sb.append(format(method, getMethodLength()));
		sb.append(",  ");
		sb.append(format(getEvent(event), 5));
		sb.append(",  ");
		sb.append(eat ? " O " : " X ");

		if (canLog(name, method, event, sb.toString(), color)) {
			Log.d(TAG, format(getCount(event), 6) + ",  " + sb.toString());
		}
	}

	private static void logEmptyLine() {
		Log.d(TAG, "\n\n\n\n\n");
	}

	static ArrayList<String> mCache = new ArrayList<String>();
	private static void clearCache() {
		mCache.clear();
	}

	private static boolean canLog(String name, String method, MotionEvent event, String str, int color) {
		if (event.getAction() != MotionEvent.ACTION_MOVE) {
			logFirst(name, method, event, color);
			return true;
		}
		if (mCache.contains(str)) {
			return false;
		}
		mCache.add(str);
		return true;
	}

	private static void printHead() {
		StringBuilder sb = new StringBuilder();
		sb.append(format("Count", 6));
		sb.append(",  ");
		sb.append(format("No", 5));
		sb.append(",  ");
		sb.append(format("Method", getMethodLength()));
		sb.append(",  ");
		sb.append(format("Event", 5));
		sb.append(",  ");
		sb.append("Eat");
		Log.d(TAG, sb.toString());
	}

	private static void clearCount() {
		sDonwCount = 0;
		sMoveCount = 0;
		sUpCount = 0;
	}

	private static String getCount(MotionEvent event) {
		int count = 0;
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				sDonwCount++;
				count = sDonwCount;
				break;
			case MotionEvent.ACTION_MOVE:
				sMoveCount++;
				count = sMoveCount;
				break;
			case MotionEvent.ACTION_UP:
				sUpCount++;
				count = sUpCount;
				break;
		}
		String num = format("" + count, 2);
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			return num + " " + " " + " " + " ";
		}
		if (event.getAction() == MotionEvent.ACTION_MOVE) {
			return " " + " " + num + " " + " ";
		}
		if (event.getAction() == MotionEvent.ACTION_UP) {
			return " " + " " + " " + " " + num;
		}
		return num;
	}

	public static void clear() {
		clearCache();
		clearCount();
		logEmptyLine();
		mFirst.clear();
		printHead();
	}

	static HashMap<String, Route> mFirst = new HashMap<String, Route>();
	public static HashMap<String, Route> getFirst() {
		return mFirst;
	}

	private static void logFirst(String name, String method, MotionEvent event, int color) {
		if (event.getAction() != MotionEvent.ACTION_DOWN) {
			return;
		}
		Route route = mFirst.get(name);
		if (null == route) {
			route = new Route(color);
		}

		updateRoute(route, method, sDonwCount + 1);
		mFirst.put(name, route);
	}

	private static void updateRoute(Route route, String method, int count) {
		if (method.equals(METHOD_DISPATCH_TOUCH_EVENT)) {
			route.getDispatchTouchEvent().setActive(true);
			route.getDispatchTouchEvent().setNum(count);
		}
		if (method.equals(METHOD_ON_INTERCEPT_TOUCH_EVENT)) {
			route.getOnInterceptTouchEvent().setActive(true);
			route.getOnInterceptTouchEvent().setNum(count);

		}
		if (method.equals(METHOD_ON_TOUCH_EVENT)) {
			route.getOnTouchEvent().setActive(true);
			route.getOnTouchEvent().setNum(count);
		}
	}
}
