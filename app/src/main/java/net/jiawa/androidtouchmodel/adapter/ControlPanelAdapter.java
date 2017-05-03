package net.jiawa.androidtouchmodel.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import net.jiawa.androidtouchmodel.R;
import net.jiawa.androidtouchmodel.TouchLogView;

/**
 * Created by zhaoxin5 on 2017/5/3.
 */

public class ControlPanelAdapter extends BaseRecyclerAdapter<TouchLogView> implements CheckBox.OnCheckedChangeListener {

    public ControlPanelAdapter(Context context) {
        super(context, BaseRecyclerAdapter.NEITHER);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new ControlPanelHolder(mInflater.inflate(R.layout.item_list_checkbox_group, parent, false));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, TouchLogView item, int position) {
        ControlPanelHolder h = (ControlPanelHolder) holder;
        h.touch.setChecked(item.getTouchEvent());
        h.touch.setOnCheckedChangeListener(this);
        h.touch.setTag(R.id.cb_touch, position);
        h.intercept.setChecked(item.getInterceptTouchEvent());
        h.intercept.setOnCheckedChangeListener(this);
        h.intercept.setTag(R.id.cb_intercept, position);
        h.name.setText(item.getName());
        h.name.setBackgroundColor(item.getBackgroundColor());
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int id = buttonView.getId();
        int position = -1;
        TouchLogView touchLogView = null;
        switch (id) {
            case R.id.cb_intercept:
                position = (int) buttonView.getTag(R.id.cb_intercept);
                touchLogView = getItem(position);
                touchLogView.setInterceptTouchEvent(isChecked);
                break;
            case R.id.cb_touch:
                position = (int) buttonView.getTag(R.id.cb_touch);
                touchLogView = getItem(position);
                touchLogView.setTouchEvent(isChecked);
                break;
        }
    }

    private static class ControlPanelHolder extends RecyclerView.ViewHolder {

        View root;
        TextView name;
        CheckBox intercept;
        CheckBox touch;

        public ControlPanelHolder(View itemView) {
            super(itemView);
            root = itemView;
            name = (TextView) itemView.findViewById(R.id.tv_title);
            intercept = (CheckBox) itemView.findViewById(R.id.cb_intercept);
            touch = (CheckBox) itemView.findViewById(R.id.cb_touch);
        }
    }


}
