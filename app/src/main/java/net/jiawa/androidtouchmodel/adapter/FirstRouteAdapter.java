package net.jiawa.androidtouchmodel.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import net.jiawa.androidtouchmodel.R;
import net.jiawa.androidtouchmodel.bean.Route;

/**
 * Created by zhaoxin5 on 2017/5/3.
 */

public class FirstRouteAdapter extends BaseRecyclerAdapter<Route> {

    public FirstRouteAdapter(Context context) {
        super(context, BaseRecyclerAdapter.NEITHER);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new FirstRouteHolder(mInflater.inflate(R.layout.item_list_touch_active_indicator, parent, false));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, Route item, int position) {
        FirstRouteHolder h = (FirstRouteHolder) holder;
        final int gray = mContext.getResources().getColor(R.color.gray);
        if (item.getDispatchTouchEvent().isActive()) {
            h.dispatchTouchEvent.setText("" + item.getDispatchTouchEvent().getNum());
            h.dispatchTouchEventFl.setBackgroundColor(item.getColor());
        }else {
            h.dispatchTouchEvent.setText("");
            h.dispatchTouchEventFl.setBackgroundColor(gray);
        }

        if (item.getOnInterceptTouchEvent().isActive()) {
            h.onInterceptTouchEvent.setText("" + item.getOnInterceptTouchEvent().getNum());
            h.onInterceptTouchEventFl.setBackgroundColor(item.getColor());
        } else {
            h.onInterceptTouchEvent.setText("");
            h.onInterceptTouchEventFl.setBackgroundColor(gray);
        }

        if (item.getOnTouchEvent().isActive()) {
            h.onTouchEvent.setText("" + item.getOnTouchEvent().getNum());
            h.onTouchEventFl.setBackgroundColor(item.getColor());
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
