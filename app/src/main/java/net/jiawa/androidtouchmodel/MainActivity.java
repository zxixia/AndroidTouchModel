package net.jiawa.androidtouchmodel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import net.jiawa.androidtouchmodel.adapter.BaseRecyclerAdapter;
import net.jiawa.androidtouchmodel.adapter.ControlPanelAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView mControlPanel;
    BaseRecyclerAdapter<TouchLogView> mControlPanelAdapter;
    List<TouchLogView> mList = new ArrayList<TouchLogView>();

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
    }

    private void initData() {
        mControlPanelAdapter = new ControlPanelAdapter(this);
        mList.add((TouchLogView) findViewById(R.id.touch_1));
        mList.add((TouchLogView) findViewById(R.id.touch_2));
        mList.add((TouchLogView) findViewById(R.id.touch_3));
        mList.add((TouchLogView) findViewById(R.id.touch_4));

        mControlPanel.setAdapter(mControlPanelAdapter);
        mControlPanelAdapter.resetItem(mList);
    }

    protected RecyclerView.LayoutManager getLayoutManager(int orientation) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(orientation);
        return linearLayoutManager;
    }
}
