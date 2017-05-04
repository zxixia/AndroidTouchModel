package net.jiawa.androidtouchmodel;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
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
            LogUtils.clear();
            if (null != clearAllCallback) {
                clearAllCallback.clear(true);
            }
        }
        boolean result = LogUtils.log(mTag, LogUtils.METHOD_DISPATCH_TOUCH_EVENT, ev, mBackgroundColor);
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            dispatchTouchEventDown.setActive(true);
            dispatchTouchEventDown.setNum(LogUtils.getCountNum(ev));
            if (null != mListener) {
                mListener.onDown();
            }
        }
        if ((ev.getAction() == MotionEvent.ACTION_MOVE) && result) {
            dispatchTouchEventUp.setActive(true);
            dispatchTouchEventUp.setNum(LogUtils.getCountNum(ev));
            if (null != mListener) {
                mListener.onUp();
            }
        }
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            dispatchTouchEventUp.setActive(true);
            dispatchTouchEventUp.setNum(LogUtils.getCountNum(ev));
            if (null != mListener) {
                mListener.onUp();
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean result = LogUtils.log(mTag, LogUtils.METHOD_ON_INTERCEPT_TOUCH_EVENT, ev, mInterceptTouchEvent, mBackgroundColor);
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            onInterceptTouchEventDown.setActive(true);
            onInterceptTouchEventDown.setNum(LogUtils.getCountNum(ev));
            if (null != mListener) {
                mListener.onDown();
            }
        }
        if ((ev.getAction() == MotionEvent.ACTION_MOVE) && result) {
            onInterceptTouchEventUp.setActive(true);
            onInterceptTouchEventUp.setNum(LogUtils.getCountNum(ev));
            if (null != mListener) {
                mListener.onUp();
            }
        }
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            onInterceptTouchEventUp.setActive(true);
            onInterceptTouchEventUp.setNum(LogUtils.getCountNum(ev));
            if (null != mListener) {
                mListener.onUp();
            }
        }
        return mInterceptTouchEvent;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = LogUtils.log(mTag, LogUtils.METHOD_ON_TOUCH_EVENT, event, mTouchEvent, mBackgroundColor);
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            onTouchEventDown.setActive(true);
            onTouchEventDown.setNum(LogUtils.getCountNum(event));
            if (null != mListener) {
                mListener.onDown();
            }
        }
        if ((event.getAction() == MotionEvent.ACTION_MOVE) && result) {
            onTouchEventUp.setActive(true);
            onTouchEventUp.setNum(LogUtils.getCountNum(event));
            if (null != mListener) {
                mListener.onUp();
            }
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            onTouchEventUp.setActive(true);
            onTouchEventUp.setNum(LogUtils.getCountNum(event));
            if (null != mListener) {
                mListener.onUp();
            }
        }
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

    public void clear(boolean all) {
        if (all) {
            dispatchTouchEventDown.clear();
            onInterceptTouchEventDown.clear();
            onTouchEventDown.clear();

            dispatchTouchEventUp.clear();
            onInterceptTouchEventUp.clear();
            onTouchEventUp.clear();
        } else {
            dispatchTouchEventUp.clear();
            onInterceptTouchEventUp.clear();
            onTouchEventUp.clear();
        }
    }

    Status dispatchTouchEventDown = new Status();
    Status onInterceptTouchEventDown = new Status();
    Status onTouchEventDown = new Status();

    Status dispatchTouchEventUp = new Status();
    Status onInterceptTouchEventUp = new Status();
    Status onTouchEventUp = new Status();

    public Status getDispatchTouchEventDown() {
        return dispatchTouchEventDown;
    }

    public Status getOnInterceptTouchEventDown() {
        return onInterceptTouchEventDown;
    }

    public Status getOnTouchEventDown() {
        return onTouchEventDown;
    }

    public Status getDispatchTouchEventUp() {
        return dispatchTouchEventUp;
    }

    public Status getOnInterceptTouchEventUp() {
        return onInterceptTouchEventUp;
    }

    public Status getOnTouchEventUp() {
        return onTouchEventUp;
    }

    public class Status {
        boolean active = false;
        int num = 0;

        public boolean isActive() {
            return active;
        }

        public int getNum() {
            return num;
        }

        public void setActive(boolean value) {
            active = value;
        }

        public void setNum(int value) {
            num = value;
        }

        void clear() {
            active = false;
            num = 0;
        }
    }

    public interface onMotionUpdateListener {
        void onDown();
        void onUp();
    }

    onMotionUpdateListener mListener;
    public void setOnMotionUpdateListener(onMotionUpdateListener listener) {
        mListener = listener;
    }

    public interface clearAllCallback {
        void clear(boolean all);
    }
    clearAllCallback clearAllCallback;
    public void setClearAllCallback(clearAllCallback clearAllCallback) {
        this.clearAllCallback = clearAllCallback;
    }
}
