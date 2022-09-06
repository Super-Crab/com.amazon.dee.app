package com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.event.adapter;

import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.event.InternalEvent;
import org.json.JSONException;
@Deprecated
/* loaded from: classes13.dex */
public interface EventAdapter<T> {
    T translateFromEvent(InternalEvent internalEvent);

    InternalEvent translateToEvent(T t) throws JSONException;
}
