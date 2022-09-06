package com.amazon.alexa.audiopersonalization.listeners;

import android.util.Log;
import com.amazon.alexa.audiopersonalization.factory.JSONObjectFactory;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.EventBusException;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes6.dex */
abstract class AbstractEventListener {
    private static final String TAG = "AbstractEventListener";
    private final EventBus mEventBus;
    private final JSONObjectFactory mJSONFactory;
    private final List<UUID> mTrackedEvents = new ArrayList();
    protected final Gson gson = new GsonBuilder().setLenient().create();

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractEventListener(EventBus eventBus, JSONObjectFactory jSONObjectFactory) {
        this.mEventBus = eventBus;
        this.mJSONFactory = jSONObjectFactory;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public <T> String createPayload(String str, T t) {
        try {
            JSONObject createJSONObject = this.mJSONFactory.createJSONObject();
            createJSONObject.put(str, t);
            return createJSONObject.toString();
        } catch (JSONException e) {
            Log.e(TAG, "Could not add to JSON", e);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v5, types: [T, java.lang.String] */
    public <T> T parsePayload(String str, String str2, Class<T> cls) {
        String str3 = "parsePayload with payload " + str + " key " + str2;
        try {
            ?? r3 = (T) this.mJSONFactory.createJSONObject(str).getString(str2);
            if (cls == String.class) {
                return r3;
            }
            String str4 = "parsePayload jsonString is " + ((String) r3);
            return (T) this.gson.fromJson((String) r3, (Class<Object>) cls);
        } catch (JSONException e) {
            Log.e(TAG, "failed to parse JSON exception error is " + e);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void sendErrorMsg(String str) {
        sendEventBusMessage(str, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void sendEventBusMessage(String str, @Nullable String str2) {
        Message.Builder builder = new Message.Builder();
        builder.setEventType(str);
        if (str2 != null) {
            builder.setPayload(str2);
        }
        try {
            this.mEventBus.publish(builder.build());
        } catch (EventBusException e) {
            String str3 = TAG;
            Log.e(str3, "Failed to send the response for message " + str, e);
        }
    }

    @OverridingMethodsMustInvokeSuper
    public void stop() {
        for (UUID uuid : this.mTrackedEvents) {
            this.mEventBus.getSubscriber().unsubscribe(uuid);
        }
        this.mTrackedEvents.clear();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void subscribeToEvent(final String str, MessageHandler messageHandler) {
        this.mTrackedEvents.add(this.mEventBus.getSubscriber().subscribe(new MessageFilter() { // from class: com.amazon.alexa.audiopersonalization.listeners.-$$Lambda$AbstractEventListener$bLXjs9AU1C20l_zaDG_VnNsUo38
            @Override // com.amazon.alexa.eventbus.api.MessageFilter
            public final boolean isMatch(Message message) {
                return str.equals(message.getEventType());
            }
        }, messageHandler));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void sendEventBusMessage(String str) {
        sendEventBusMessage(str, null);
    }
}
