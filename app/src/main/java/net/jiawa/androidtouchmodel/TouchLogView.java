package net.jiawa.androidtouchmodel;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * Created by zhaoxin5 on 2017/5/3.
 */

public class TouchLogView extends FrameLayout {

    private String mTag = "TouchLogView";
    private boolean mInterceptTouchEvent = false;
    private boolean mTouchEvent = false;
    private int mBackgroundColor = -1;
    private boolean mIsRoot = false;

    public TouchLogView(Context context) {
        super(context);
        init(context, null, 0, 0);
    }

    public TouchLogView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0, 0);
    }

    public TouchLogView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TouchLogView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        if (null != attrs) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TouchLogView);
            if (ta != null) {
                mTag = ta.getString(R.styleable.TouchLogView_tvTag);
                mInterceptTouchEvent = ta.getBoolean(R.styleable.TouchLogView_tvInterceptTouchEvent, false);
                mTouchEvent = ta.getBoolean(R.styleable.TouchLogView_tvTouchEvent, false);
                mBackgroundColor = ta.getColor(R.styleable.TouchLogView_tvBackgroundColor, context.getResources().getColor(R.color.cheng_light));
                mIsRoot = ta.getBoolean(R.styleable.TouchLogView_tvIsRoot, false);
                ta.recycle();
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (mIsRoot && ev.getAction() == MotionEvent.ACTION_DOWN) {
            LogUtils.clearCache();
            LogUtils.logEmptyLine();
            LogUtils.printHead();
        }
        LogUtils.log(mTag, LogUtils.METHOD_DISPATCH_TOUCH_EVENT, ev);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        LogUtils.log(mTag, LogUtils.METHOD_ON_INTERCEPT_TOUCH_EVENT, ev, mInterceptTouchEvent);
        return mInterceptTouchEvent;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtils.log(mTag, LogUtils.METHOD_ON_TOUCH_EVENT, event, mTouchEvent);
        return mTouchEvent;
    }

    public void setInterceptTouchEvent(boolean value) {
        mInterceptTouchEvent = value;
    }
    public boolean getInterceptTouchEvent() {
        return mInterceptTouchEvent;
    }
    public void setTouchEvent(boolean value) {
        mTouchEvent = value;
    }
    public boolean getTouchEvent() {
        return mTouchEvent;
    }
    public String getName() {
        return mTag;
    }

    public int getBackgroundColor() {
        return mBackgroundColor;
    }
}
