package com.amazon.matter.eventbus;

import android.util.Log;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
/* loaded from: classes9.dex */
public class EventBusHelper {
    private static final Gson GSON = new Gson();
    private static final String TAG = "EventBusHelper";
    private final EventBus eventBus;

    public EventBusHelper(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void sendMessageToEventBus(MatterEventType matterEventType, String str) {
        Message build = new Message.Builder().setEventType(matterEventType.toString()).setPayload(str).build();
        String str2 = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Attempting to publish message:");
        outline107.append(GSON.toJson(build));
        Log.i(str2, outline107.toString());
        this.eventBus.publish(build);
    }
}
