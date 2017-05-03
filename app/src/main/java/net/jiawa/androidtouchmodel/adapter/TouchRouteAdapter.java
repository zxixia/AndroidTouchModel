package net.jiawa.androidtouchmodel.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import net.jiawa.androidtouchmodel.R;
import net.jiawa.androidtouchmodel.TouchLogView;

/**
 * Created by zhaoxin5 on 2017/5/3.
 */

public class TouchRouteAdapter extends BaseRecyclerAdapter<TouchLogView> {

    public static int TYPE_FIRST_DOWN = 9001;
    public static int TYPE_SECOND_UP   = 9002;

    private int mType = TYPE_FIRST_DOWN;

    public TouchRouteAdapter(Context context, int type) {
        super(context, BaseRecyclerAdapter.NEITHER);
        mType = type;
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new FirstRouteHolder(mInflater.inflate(R.layout.item_list_touch_active_indicator, parent, false));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, TouchLogView item, int position) {
        FirstRouteHolder h = (FirstRouteHolder) holder;
        final int gray = mContext.getResources().getColor(R.color.gray);

        if (mType == TYPE_FIRST_DOWN) {
            bindFirstDown(h, item, gray);
        } else {
            bindSecondUp(h, item, gray);
        }
    }

    private void bindSecondUp(FirstRouteHolder h, TouchLogView item, int gray) {
        if (item.getDispatchTouchEventUp().isActive()) {
            h.dispatchTouchEvent.setText("" + item.getDispatchTouchEventUp().getNum());
            h.dispatchTouchEventFl.setBackgroundColor(item.getBackgroundColor());
        }else {
            h.dispatchTouchEvent.setText("");
            h.dispatchTouchEventFl.setBackgroundColor(gray);
        }

        if (item.getOnInterceptTouchEventUp().isActive()) {
            h.onInterceptTouchEvent.setText("" + item.getOnInterceptTouchEventUp().getNum());
            h.onInterceptTouchEventFl.setBackgroundColor(item.getBackgroundColor());
        } else {
            h.onInterceptTouchEvent.setText("");
            h.onInterceptTouchEventFl.setBackgroundColor(gray);
        }

        if (item.getOnTouchEventUp().isActive()) {
            h.onTouchEvent.setText("" + item.getOnTouchEventUp().getNum());
            h.onTouchEventFl.setBackgroundColor(item.getBackgroundColor());
        } else {
            h.onTouchEvent.setText("");
            h.onTouchEventFl.setBackgroundColor(gray);
        }
    }

    private void bindFirstDown(FirstRouteHolder h, TouchLogView item, int gray) {
        if (item.getDispatchTouchEventDown().isActive()) {
            h.dispatchTouchEvent.setText("" + item.getDispatchTouchEventDown().getNum());
            h.dispatchTouchEventFl.setBackgroundColor(item.getBackgroundColor());
        }else {
            h.dispatchTouchEvent.setText("");
            h.dispatchTouchEventFl.setBackgroundColor(gray);
        }

        if (item.getOnInterceptTouchEventDown().isActive()) {
            h.onInterceptTouchEvent.setText("" + item.getOnInterceptTouchEventDown().getNum());
            h.onInterceptTouchEventFl.setBackgroundColor(item.getBackgroundColor());
        } else {
            h.onInterceptTouchEvent.setText("");
            h.onInterceptTouchEventFl.setBackgroundColor(gray);
        }

        if (item.getOnTouchEventDown().isActive()) {
            h.onTouchEvent.setText("" + item.getOnTouchEventDown().getNum());
            h.onTouchEventFl.setBackgroundColor(item.getBackgroundColor());
        } else {
            h.onTouchEvent.setText("");
            h.onTouchEventFl.setBackgroundColor(gray);
        }
    }

    private static class FirstRouteHolder extends RecyclerView.ViewHolder {

        TextView dispatchTouchEvent;
        TextView onInterceptTouchEvent;
        TextView onTouchEvent;

        FrameLayout dispatchTouchEventFl;
        FrameLayout onInterceptTouchEventFl;
        FrameLayout onTouchEventFl;
        public FirstRouteHolder(View itemView) {
            super(itemView);
            dispatchTouchEvent = (TextView) itemView.findViewById(R.id.tv_dispatch_touch_event);
            onInterceptTouchEvent = (TextView) itemView.findViewById(R.id.tv_intercept_touch_event);
            onTouchEvent = (TextView) itemView.findViewById(R.id.tv_on_touch_event);

            dispatchTouchEventFl = (FrameLayout) itemView.findViewById(R.id.fl_dispatch_touch_event);
            onInterceptTouchEventFl = (FrameLayout) itemView.findViewById(R.id.fl_intercept_touch_event);
            onTouchEventFl = (FrameLayout) itemView.findViewById(R.id.fl_on_touch_event);
        }
    }
}
