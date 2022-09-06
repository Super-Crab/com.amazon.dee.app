package com.amazon.deviceevents.model.event;

import com.amazon.deviceevents.com.google.gson.annotations.SerializedName;
import com.amazon.deviceevents.utils.Preconditions;
/* loaded from: classes12.dex */
public final class UplMetric {
    @SerializedName("eventID")
    private String mEventID;
    @SerializedName("metricName")
    private String mMetricName;
    @SerializedName("value")
    private long mValue;

    public UplMetric(String str, long j, String str2) {
        Preconditions.checkNotNull(str, "metricName");
        Preconditions.checkNotNull(str2, "eventtID");
        this.mMetricName = str;
        this.mValue = j;
        this.mEventID = str2;
    }

    public String getEventID() {
        return this.mEventID;
    }

    public String getMetricName() {
        return this.mMetricName;
    }

    public long getValue() {
        return this.mValue;
    }

    private UplMetric() {
    }
}
