package com.amazon.alexa.mobilytics.event.serializer.handlers;

import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.mobilytics.event.MobilyticsEvent;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes9.dex */
public interface DataHandler {
    @Nullable
    Pair<String, JSONObject> process(@NonNull MobilyticsEvent mobilyticsEvent) throws JSONException;
}
