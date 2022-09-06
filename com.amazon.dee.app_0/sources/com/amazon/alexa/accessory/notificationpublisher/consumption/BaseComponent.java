package com.amazon.alexa.accessory.notificationpublisher.consumption;

import androidx.annotation.Nullable;
/* loaded from: classes.dex */
public abstract class BaseComponent {
    public static final int COMPONENT_ID_AUDIO_FOCUS = 7;
    public static final int COMPONENT_ID_GESTURE = 2;
    public static final int COMPONENT_ID_NOTIFICATION = 1;
    public static final int COMPONENT_ID_QUEUE = 5;
    public static final int COMPONENT_ID_RENDERER = 3;
    public static final int COMPONENT_ID_REPLY_READ_BACK = 9;
    public static final int COMPONENT_ID_SPEECH_RECOGNIZER = 8;
    public static final int COMPONENT_ID_STATUS = 6;
    public static final int COMPONENT_ID_TIMER = 4;
    private final int componentId;

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseComponent(int i) {
        this.componentId = i;
    }

    protected int getComponentId() {
        return this.componentId;
    }

    protected void handleEventMessage(int i, @Nullable Object obj) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean postEventMessage(int i) {
        return postEventMessage(i, null);
    }

    protected boolean postEventMessageDelayed(int i, @Nullable Object obj, long j) {
        return ConsumptionEngine.getInstance().postEventMessageDelayed(1, this.componentId, i, obj, j);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean postEventMessage(int i, @Nullable Object obj) {
        return ConsumptionEngine.getInstance().postEventMessage(1, this.componentId, i, obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean postEventMessageDelayed(int i, long j) {
        return postEventMessageDelayed(i, null, j);
    }
}
