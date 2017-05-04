package net.jiawa.androidtouchmodel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.MotionEvent;

import net.jiawa.androidtouchmodel.adapter.BaseRecyclerAdapter;
import net.jiawa.androidtouchmodel.adapter.ControlPanelAdapter;
import net.jiawa.androidtouchmodel.adapter.TouchRouteAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TouchLogView.onMotionUpdateListener,
        TouchLogView.clearAllCallback {

    RecyclerView mControlPanel;
    BaseRecyclerAdapter<TouchLogView> mControlPanelAdapter;
    List<TouchLogView> mList = new ArrayList<TouchLogView>();

    RecyclerView mFirstTouchActiveRoute;
    BaseRecyclerAdapter<TouchLogView> mFirstRouteAdapter;

    RecyclerView mSecondTouchActiveRoute;
    BaseRecyclerAdapter<TouchLogView> mSecondRouteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //隐藏标题栏,有效
        getSupportActionBar().hide();

        initWidget();
        initData();
    }

    private void initWidget() {
        mControlPanel = (RecyclerView) this.findViewById(R.id.rv_control_panel);
        mControlPanel.setLayoutManager(getLayoutManager(LinearLayoutManager.HORIZONTAL));

        mFirstTouchActiveRoute = (RecyclerView) this.findViewById(R.id.rv_first_active_route);
        mFirstTouchActiveRoute.setLayoutManager(getLayoutManager(LinearLayoutManager.HORIZONTAL));

        mSecondTouchActiveRoute = (RecyclerView) this.findViewById(R.id.rv_second_active_route);
        mSecondTouchActiveRoute.setLayoutManager(getLayoutManager(LinearLayoutManager.HORIZONTAL));
    }

    private void initData() {
        mControlPanelAdapter = new ControlPanelAdapter(this);
        TouchLogView touchLogView = (TouchLogView) findViewById(R.id.touch_1);
        touchLogView.setOnMotionUpdateListener(this);
        touchLogView.setClearAllCallback(this);
        mList.add(touchLogView);

        touchLogView = (TouchLogView) findViewById(R.id.touch_2);
        touchLogView.setOnMotionUpdateListener(this);
        mList.add(touchLogView);

        touchLogView = (TouchLogView) findViewById(R.id.touch_3);
        touchLogView.setOnMotionUpdateListener(this);
        mList.add(touchLogView);

        touchLogView = (TouchLogView) findViewById(R.id.touch_4);
        touchLogView.setOnMotionUpdateListener(this);
        mList.add(touchLogView);

        touchLogView = (TouchLogView) findViewById(R.id.touch_5);
        touchLogView.setOnMotionUpdateListener(this);
        mList.add(touchLogView);

        mControlPanel.setAdapter(mControlPanelAdapter);
        mControlPanelAdapter.resetItem(mList);

        mFirstRouteAdapter = new TouchRouteAdapter(this, TouchRouteAdapter.TYPE_FIRST_DOWN);
        mFirstTouchActiveRoute.setAdapter(mFirstRouteAdapter);
        mFirstRouteAdapter.resetItem(mList);

        mSecondRouteAdapter = new TouchRouteAdapter(this, TouchRouteAdapter.TYPE_SECOND_UP);
        mSecondTouchActiveRoute.setAdapter(mSecondRouteAdapter);
        mSecondRouteAdapter.resetItem(mList);
    }

    protected RecyclerView.LayoutManager getLayoutManager(int orientation) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(orientation);
        return linearLayoutManager;
    }

    @Override
    public void onDown() {
        mFirstRouteAdapter.resetItem(mList);
    }

    @Override
    public void onUp() {
        mSecondRouteAdapter.resetItem(mList);
    }

    @Override
    public void clear(boolean all) {
        for (TouchLogView touchLogView : mList) {
            touchLogView.clear(all);
        }
        mFirstRouteAdapter.resetItem(mList);
        mSecondRouteAdapter.resetItem(mList);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            clear(false);
        }
        return super.dispatchTouchEvent(ev);
    }
}
