package com.amazon.alexa.mobilytics.event.serializer;

import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.mobilytics.event.MobilyticsEvent;
import com.amazon.alexa.mobilytics.event.serializer.EventSerializer;
import com.amazon.alexa.mobilytics.event.serializer.handlers.DataHandler;
import com.amazon.alexa.mobilytics.util.Log;
import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;
import org.json.JSONObject;
@Singleton
/* loaded from: classes9.dex */
public class DefaultEventSerializer implements EventSerializer {
    private static final String TAG = Log.tag(DefaultEventSerializer.class);
    private final List<DataHandler> handlers;

    @Inject
    public DefaultEventSerializer(@NonNull List<DataHandler> list) {
        this.handlers = (List) Preconditions.checkNotNull(list);
    }

    private String doSerialize(@NonNull MobilyticsEvent mobilyticsEvent, @Nullable EventSerializer.Visitor visitor, @Nullable DataHandler[] dataHandlerArr) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        ArrayList<DataHandler> arrayList = new ArrayList(this.handlers);
        if (dataHandlerArr != null) {
            arrayList.addAll(Arrays.asList(dataHandlerArr));
        }
        for (DataHandler dataHandler : arrayList) {
            try {
                Log.v(TAG, "Running handler: %s", dataHandler.getClass().getName());
                Pair<String, JSONObject> process = dataHandler.process(mobilyticsEvent);
                if (process != null) {
                    if (visitor != null) {
                        try {
                            visitor.visit((String) process.first, (JSONObject) process.second);
                        } catch (Exception e) {
                            Log.e(TAG, e, "Visitor failed", new Object[0]);
                        }
                    }
                    jSONObject.put((String) process.first, process.second);
                }
            } catch (Exception e2) {
                Log.e(TAG, e2, "Handler %s failed to run", dataHandler.getClass().getName());
            }
        }
        return jSONObject.toString();
    }

    @Override // com.amazon.alexa.mobilytics.event.serializer.EventSerializer
    public String serialize(@NonNull MobilyticsEvent mobilyticsEvent) throws JSONException {
        return doSerialize(mobilyticsEvent, null, null);
    }

    @Override // com.amazon.alexa.mobilytics.event.serializer.EventSerializer
    public String serialize(@NonNull MobilyticsEvent mobilyticsEvent, @NonNull EventSerializer.Visitor visitor) throws JSONException {
        return doSerialize(mobilyticsEvent, visitor, null);
    }

    @Override // com.amazon.alexa.mobilytics.event.serializer.EventSerializer
    public String serialize(@NonNull MobilyticsEvent mobilyticsEvent, @NonNull DataHandler... dataHandlerArr) throws JSONException {
        return doSerialize(mobilyticsEvent, null, dataHandlerArr);
    }

    @Override // com.amazon.alexa.mobilytics.event.serializer.EventSerializer
    public String serialize(@NonNull MobilyticsEvent mobilyticsEvent, @NonNull EventSerializer.Visitor visitor, @NonNull DataHandler... dataHandlerArr) throws JSONException {
        return doSerialize(mobilyticsEvent, visitor, dataHandlerArr);
    }
}
