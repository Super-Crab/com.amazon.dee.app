package com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.delivery;

import android.util.Log;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.event.ClientContext;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.event.InternalEvent;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.event.adapter.JSONEventAdapter;
import com.amazonaws.services.mobileanalytics.model.Event;
import com.amazonaws.services.mobileanalytics.model.PutEventsRequest;
import com.amazonaws.services.mobileanalytics.model.Session;
import com.amazonaws.util.Base64;
import com.amazonaws.util.DateUtils;
import com.amazonaws.util.StringUtils;
import java.util.ArrayList;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
@Deprecated
/* loaded from: classes13.dex */
public class ERSRequestBuilder {
    private static final String TAG = "ERSRequestBuilder";

    public PutEventsRequest createRecordEventsRequest(JSONArray jSONArray, String str) {
        ClientContext clientContext = null;
        if (jSONArray.toString() == null) {
            return null;
        }
        PutEventsRequest putEventsRequest = new PutEventsRequest();
        ArrayList arrayList = new ArrayList();
        JSONEventAdapter jSONEventAdapter = new JSONEventAdapter();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                InternalEvent translateToEvent = jSONEventAdapter.translateToEvent(jSONArray.getJSONObject(i));
                ClientContext createClientContext = translateToEvent.createClientContext(str);
                Event event = new Event();
                Session session = new Session();
                session.withId(translateToEvent.getSessionId());
                session.withStartTimestamp(DateUtils.formatISO8601Date(new Date(translateToEvent.getSessionStart())));
                if (translateToEvent.getSessionStop() != null && translateToEvent.getSessionStop().longValue() != 0) {
                    session.withStopTimestamp(DateUtils.formatISO8601Date(new Date(translateToEvent.getSessionStop().longValue())));
                }
                if (translateToEvent.getSessionDuration() != null && translateToEvent.getSessionDuration().longValue() != 0) {
                    session.withDuration(translateToEvent.getSessionDuration());
                }
                event.withAttributes(translateToEvent.getAllAttributes()).withMetrics(translateToEvent.getAllMetrics()).withEventType(translateToEvent.getEventType()).withTimestamp(DateUtils.formatISO8601Date(new Date(translateToEvent.getEventTimestamp().longValue()))).withSession(session);
                arrayList.add(event);
                clientContext = createClientContext;
            } catch (JSONException e) {
                Log.e(TAG, "Stored event was invalid JSON", e);
            }
        }
        if (clientContext != null && arrayList.size() > 0) {
            putEventsRequest.withEvents(arrayList).withClientContext(Base64.encodeAsString(clientContext.toJSONObject().toString().getBytes(StringUtils.UTF8)));
        } else {
            Log.e(TAG, "ClientContext is null or event list is empty");
        }
        return putEventsRequest;
    }
}
