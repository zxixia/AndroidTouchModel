package net.jiawa.androidtouchmodel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * onInterceptTouchEvent  拦截事件
     * dispatchTouchEvent 分派事件
     * onTouchEvent 处理事件
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // LogUtils.printLog(getClass(), "【开发经理】来一任务<%s>:需要{分派}给下一级", ev);
        LogUtils.printLog(getClass(), "dispatchTouchEvent", ev);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean is = true;
        // LogUtils.printLog(getClass(), "【开发经理】自己{处理}任务<%s>:连组长都不会，算了，这任务我还是自己来吧！"+is, event);
        LogUtils.printLog(getClass(), "onTouchEvent", event, is);
        return is;
    }
}
