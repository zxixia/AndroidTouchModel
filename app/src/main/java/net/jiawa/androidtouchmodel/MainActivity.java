package net.jiawa.androidtouchmodel;

import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;

import net.jiawa.androidtouchmodel.adapter.BaseRecyclerAdapter;
import net.jiawa.androidtouchmodel.adapter.ControlPanelAdapter;
import net.jiawa.androidtouchmodel.adapter.FirstRouteAdapter;
import net.jiawa.androidtouchmodel.bean.Route;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    RecyclerView mControlPanel;
    BaseRecyclerAdapter<TouchLogView> mControlPanelAdapter;
    List<TouchLogView> mList = new ArrayList<TouchLogView>();

    RecyclerView mFirstTouchActiveRoute;
    BaseRecyclerAdapter<Route> mFirstRouteAdapter;

    RecyclerView mSecondTouchActiveRoute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);
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
    }

    private void initData() {
        mControlPanelAdapter = new ControlPanelAdapter(this);
        mList.add((TouchLogView) findViewById(R.id.touch_1));
        mList.add((TouchLogView) findViewById(R.id.touch_2));
        mList.add((TouchLogView) findViewById(R.id.touch_3));
        mList.add((TouchLogView) findViewById(R.id.touch_4));

        mControlPanel.setAdapter(mControlPanelAdapter);
        mControlPanelAdapter.resetItem(mList);

        mFirstRouteAdapter = new FirstRouteAdapter(this);
        mFirstTouchActiveRoute.setAdapter(mFirstRouteAdapter);
    }

    protected RecyclerView.LayoutManager getLayoutManager(int orientation) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(orientation);
        return linearLayoutManager;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.i("xixia-1", "" + LogUtils.getEvent(event));
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:
                filterFirstRoute(LogUtils.getFirst());
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    void makeList(String key, HashMap<String, Route> map, List<Route> list) {
        Route route = map.get(key);
        if (null != route) {
            list.add(route);
        }
    }

    void filterFirstRoute(final HashMap<String, Route> map) {
        final Resources res = getApplicationContext().getResources();
        new AsyncTask<Void, Void, List<Route>>() {
            @Override
            protected List<Route> doInBackground(Void... params) {
                List<Route> list = new ArrayList<Route>();
                makeList(res.getString(R.string.name_1), map, list);
                makeList(res.getString(R.string.name_2), map, list);
                makeList(res.getString(R.string.name_3), map, list);
                makeList(res.getString(R.string.name_4), map, list);
                makeList(res.getString(R.string.name_5), map, list);
                makeList(res.getString(R.string.name_6), map, list);
                return list;
            }

            @Override
            protected void onPostExecute(List<Route> routes) {
                super.onPostExecute(routes);
                mFirstRouteAdapter.clear();
                mFirstRouteAdapter.resetItem(routes);
            }
        }.execute();
    }
}
