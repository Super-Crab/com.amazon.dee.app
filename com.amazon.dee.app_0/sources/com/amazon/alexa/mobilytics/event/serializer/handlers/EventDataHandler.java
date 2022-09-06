package com.amazon.alexa.mobilytics.event.serializer.handlers;

import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.handsfree.metrics.caching.JsonFields;
import com.amazon.alexa.mobilytics.MobilyticsConfiguration;
import com.amazon.alexa.mobilytics.event.MobilyticsEvent;
import com.amazon.alexa.mobilytics.event.metadata.EventMetadata;
import com.amazon.alexa.mobilytics.event.metadata.EventMetadataType;
import com.amazon.alexa.mobilytics.internal.JsonConverter;
import com.amazon.alexa.mobilytics.util.Utils;
import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.UUID;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;
import org.json.JSONObject;
@Singleton
/* loaded from: classes9.dex */
public class EventDataHandler implements DataHandler {
    private static final String KEY = "event";
    private final JsonConverter jsonConverter;
    private final MobilyticsConfiguration mobilyticsConfiguration;
    private final String processName;
    private final String serviceName;

    @Inject
    public EventDataHandler(@NonNull MobilyticsConfiguration mobilyticsConfiguration, @NonNull JsonConverter jsonConverter) {
        this.mobilyticsConfiguration = (MobilyticsConfiguration) Preconditions.checkNotNull(mobilyticsConfiguration);
        this.jsonConverter = (JsonConverter) Preconditions.checkNotNull(jsonConverter);
        StringBuilder sb = new StringBuilder();
        sb.append(this.mobilyticsConfiguration.serviceName());
        if (this.mobilyticsConfiguration.isDebug()) {
            sb.append("_debug");
        }
        this.serviceName = sb.toString();
        this.processName = Utils.getProcessName(this.mobilyticsConfiguration.context());
    }

    private JSONObject processEventDetails(MobilyticsEvent mobilyticsEvent) throws JSONException {
        Collection<EventMetadata> eventMetadata = mobilyticsEvent.getEventMetadata();
        if (eventMetadata != null) {
            JSONObject jSONObject = new JSONObject();
            for (EventMetadata eventMetadata2 : eventMetadata) {
                JSONObject jSONObject2 = new JSONObject(this.jsonConverter.toJson(eventMetadata2));
                String metadataType = eventMetadata2.getMetadataType();
                char c = 65535;
                switch (metadataType.hashCode()) {
                    case -1466943860:
                        if (metadataType.equals(EventMetadataType.A4ASDK)) {
                            c = 5;
                            break;
                        }
                        break;
                    case -635064287:
                        if (metadataType.equals(EventMetadataType.A4ALAUNCH)) {
                            c = 6;
                            break;
                        }
                        break;
                    case 96693:
                        if (metadataType.equals(EventMetadataType.AMA)) {
                            c = 0;
                            break;
                        }
                        break;
                    case 2998048:
                        if (metadataType.equals(EventMetadataType.AMPD)) {
                            c = 1;
                            break;
                        }
                        break;
                    case 94843623:
                        if (metadataType.equals("comms")) {
                            c = 2;
                            break;
                        }
                        break;
                    case 95848451:
                        if (metadataType.equals(EventMetadataType.DREAM)) {
                            c = 3;
                            break;
                        }
                        break;
                    case 500006792:
                        if (metadataType.equals("entertainment")) {
                            c = 4;
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                        jSONObject.put(EventMetadataType.AMA, jSONObject2);
                        break;
                    case 1:
                        jSONObject.put(EventMetadataType.AMPD, jSONObject2);
                        break;
                    case 2:
                        jSONObject.put("comms", jSONObject2);
                        break;
                    case 3:
                        jSONObject.put(EventMetadataType.DREAM, jSONObject2);
                        break;
                    case 4:
                        jSONObject.put("entertainment", jSONObject2);
                        break;
                    case 5:
                        jSONObject.put(EventMetadataType.A4ASDK, jSONObject2);
                        break;
                    case 6:
                        jSONObject.put(EventMetadataType.A4ALAUNCH, jSONObject2);
                        break;
                }
            }
            return jSONObject;
        }
        return null;
    }

    @Override // com.amazon.alexa.mobilytics.event.serializer.handlers.DataHandler
    @Nullable
    public Pair<String, JSONObject> process(@NonNull MobilyticsEvent mobilyticsEvent) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("eventId", UUID.randomUUID().toString());
        jSONObject.put("sourceName", this.serviceName);
        jSONObject.put("eventType", mobilyticsEvent.getEventType());
        jSONObject.put(JsonFields.EVENT_TIMESTAMP, mobilyticsEvent.getEventTimestamp());
        jSONObject.put("applicationForegrounded", Utils.isAppOnForeground(this.mobilyticsConfiguration.context()));
        JSONObject jSONObject2 = new JSONObject(this.jsonConverter.toJson(mobilyticsEvent));
        JSONObject processEventDetails = processEventDetails(mobilyticsEvent);
        if (processEventDetails != null) {
            jSONObject2.put("metadata", processEventDetails);
        }
        String str = this.processName;
        if (str != null) {
            jSONObject2.put("processName", str);
        }
        jSONObject.put("eventDetails", jSONObject2);
        return Pair.create("event", jSONObject);
    }
}
