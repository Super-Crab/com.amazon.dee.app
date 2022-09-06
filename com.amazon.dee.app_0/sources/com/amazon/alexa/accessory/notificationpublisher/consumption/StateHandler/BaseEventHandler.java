package com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.notificationpublisher.consumption.Payload;
import org.json.JSONObject;
/* loaded from: classes.dex */
public abstract class BaseEventHandler {
    public abstract void handleAudioFocusEvent(int i);

    public abstract void handleGestureEvent(int i, @Nullable Payload payload);

    public abstract void handleNotificationEvent(int i, @NonNull JSONObject jSONObject);

    public abstract void handleRendererEvent(int i, @NonNull JSONObject jSONObject);

    public abstract void handleReplyReadBackEvent(int i, @Nullable Object obj);

    public abstract void handleSpeechRecognizerEvent(int i, @Nullable Object obj);

    public abstract void handleStatusEvent(int i);

    public abstract void handleTimerEvent(int i, @NonNull JSONObject jSONObject);
}
