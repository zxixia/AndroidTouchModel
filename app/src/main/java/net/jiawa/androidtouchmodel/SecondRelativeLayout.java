package net.jiawa.androidtouchmodel;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

public class SecondRelativeLayout extends RelativeLayout {

	public SecondRelativeLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// LogUtils.printLog(getClass(), "【程序员】来一任务<%s>:需要{分派}给下一级", ev);
		LogUtils.printLog(getClass(), "dispatchTouchEvent", ev);
		return super.dispatchTouchEvent(ev);
	}
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		boolean is = false;
		// LogUtils.printLog(getClass(), "【程序员】自己分派一任务<%s>:需要{拦截}吗？"+is, ev);
		LogUtils.printLog(getClass(), "onInterceptTouchEvent", ev, is);
		return is;
	}
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		boolean is = false;
		// LogUtils.printLog(getClass(), "【程序员】自己{处理}任务<%s>:实习生毕竟是实习生啊，还是自己干吧。可是任务能解决嘛？"+is, event);
		LogUtils.printLog(getClass(), "onTouchEvent", ev, is);
		return is;
	}
}
