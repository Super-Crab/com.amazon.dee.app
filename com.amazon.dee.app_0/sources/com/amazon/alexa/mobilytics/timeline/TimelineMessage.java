package com.amazon.alexa.mobilytics.timeline;

import android.os.SystemClock;
import com.amazon.alexa.mobilytics.event.MobilyticsEvent;
/* loaded from: classes9.dex */
public class TimelineMessage {
    public String abortReason;
    public int action;
    public MobilyticsEvent associatedEvent;
    public long currentTimeMillis;
    public long elapsedRealtime;
    public String name;
    public String namespace;

    public TimelineMessage() {
    }

    public TimelineMessage(int i, String str, String str2) {
        this.action = i;
        this.name = str;
        this.namespace = str2;
        this.currentTimeMillis = System.currentTimeMillis();
        this.elapsedRealtime = SystemClock.elapsedRealtime();
    }

    public TimelineMessage(int i, String str, String str2, MobilyticsEvent mobilyticsEvent) {
        this(i, str, str2);
        this.associatedEvent = mobilyticsEvent;
    }

    public TimelineMessage(int i, String str, String str2, String str3) {
        this(i, str, str2);
        this.abortReason = str3;
    }
}
