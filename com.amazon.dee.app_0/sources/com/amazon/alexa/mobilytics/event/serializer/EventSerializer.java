package com.amazon.alexa.mobilytics.event.serializer;

import androidx.annotation.NonNull;
import com.amazon.alexa.mobilytics.event.MobilyticsEvent;
import com.amazon.alexa.mobilytics.event.serializer.handlers.DataHandler;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes9.dex */
public interface EventSerializer {

    /* loaded from: classes9.dex */
    public interface Visitor {
        void visit(@NonNull String str, @NonNull JSONObject jSONObject);
    }

    String serialize(@NonNull MobilyticsEvent mobilyticsEvent) throws JSONException;

    String serialize(@NonNull MobilyticsEvent mobilyticsEvent, @NonNull Visitor visitor) throws JSONException;

    String serialize(@NonNull MobilyticsEvent mobilyticsEvent, @NonNull Visitor visitor, @NonNull DataHandler... dataHandlerArr) throws JSONException;

    String serialize(@NonNull MobilyticsEvent mobilyticsEvent, @NonNull DataHandler... dataHandlerArr) throws JSONException;
}
