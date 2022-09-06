package com.amazon.communication;
/* loaded from: classes12.dex */
public interface ScreenEventListener {

    /* loaded from: classes12.dex */
    public enum Event {
        ON,
        OFF
    }

    void onScreenEvent(Event event);
}
