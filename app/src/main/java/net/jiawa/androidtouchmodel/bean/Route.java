package net.jiawa.androidtouchmodel.bean;

/**
 * Created by zhaoxin5 on 2017/5/3.
 */

public class Route {
    String name;
    Status dispatchTouchEvent = new Status();
    Status onInterceptTouchEvent = new Status();
    Status onTouchEvent = new Status();
    final int color;

    public Route(int color) {
        this.color = color;
    }

    public Status getDispatchTouchEvent() {
        return dispatchTouchEvent;
    }

    public Status getOnInterceptTouchEvent() {
        return onInterceptTouchEvent;
    }

    public Status getOnTouchEvent() {
        return onTouchEvent;
    }

    public int getColor() {
        return color;
    }

    public class Status {
        boolean active;
        int num;

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
    }
}
