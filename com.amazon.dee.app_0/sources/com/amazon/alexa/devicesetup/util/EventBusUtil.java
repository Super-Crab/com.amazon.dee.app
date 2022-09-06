package com.amazon.alexa.devicesetup.util;

import android.util.Log;
import androidx.annotation.NonNull;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.EventBusException;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes7.dex */
public final class EventBusUtil {
    private static final String TAG = "EventBusUtil";
    private static EventBusUtil instance;

    private EventBusUtil() {
    }

    public static EventBusUtil instance() {
        if (instance == null) {
            instance = new EventBusUtil();
        }
        return instance;
    }

    public void sendMessageToEventBus(String str) {
        sendToEventBus(new Message.Builder().setDate(System.currentTimeMillis()).setEventType(str).setSource(Message.Source.Local).build());
    }

    public void sendToEventBus(@NonNull Message message) {
        try {
            ((EventBus) ComponentRegistry.getInstance().get(EventBus.class).get()).publish(message);
        } catch (EventBusException e) {
            Log.e(TAG, "Publish failed", e);
        } catch (Exception e2) {
            Log.e(TAG, "Catch of all Exceptions", e2);
        }
    }

    public void sendUnexpectedErrorMessageToEventBus(@NonNull String str) {
        sendToEventBus(new Message.Builder().setDate(System.currentTimeMillis()).setEventType(GeneratedOutlineSupport1.outline72("error::", str)).setSource(Message.Source.Local).build());
    }

    public void sendMessageToEventBus(String str, String str2) {
        if (str != null) {
            sendToEventBus(new Message.Builder().setDate(System.currentTimeMillis()).setEventType(str2).setSource(Message.Source.Local).setPayload(str).build());
        }
    }
}
