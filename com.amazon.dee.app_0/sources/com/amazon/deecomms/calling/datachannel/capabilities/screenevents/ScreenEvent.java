package com.amazon.deecomms.calling.datachannel.capabilities.screenevents;

import android.view.MotionEvent;
/* loaded from: classes12.dex */
public final class ScreenEvent {
    private String action;
    private MotionEvent.PointerCoords pointerCoords;

    /* loaded from: classes12.dex */
    public static final class Builder {
        private String action;
        private MotionEvent.PointerCoords pointerCoords;

        public ScreenEvent build() {
            return new ScreenEvent(this.pointerCoords, this.action, null);
        }

        public Builder withAction(String str) {
            this.action = str;
            return this;
        }

        public Builder withPointerCoords(MotionEvent.PointerCoords pointerCoords) {
            this.pointerCoords = pointerCoords;
            return this;
        }
    }

    private ScreenEvent(MotionEvent.PointerCoords pointerCoords, String str) {
        this.pointerCoords = pointerCoords;
        this.action = str;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getAction() {
        return this.action;
    }

    public MotionEvent.PointerCoords getPointerCoords() {
        return this.pointerCoords;
    }

    /* synthetic */ ScreenEvent(MotionEvent.PointerCoords pointerCoords, String str, AnonymousClass1 anonymousClass1) {
        this.pointerCoords = pointerCoords;
        this.action = str;
    }
}
