package com.amazon.alexa.mobilytics.event.operational;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.mobilytics.event.DefaultMobilyticsEvent;
import com.amazon.alexa.mobilytics.event.EventType;
import com.amazon.alexa.mobilytics.protobuf.EventDetailsProto;
import java.util.Map;
/* loaded from: classes9.dex */
public class DefaultMobilyticsOperationalEvent extends DefaultMobilyticsEvent<DefaultMobilyticsOperationalEvent> implements MobilyticsOperationalEvent {
    private Map<String, String> debugInfo;
    private Long eventNumericValue;
    protected String operationalEventType;

    public DefaultMobilyticsOperationalEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @Nullable String str4) {
        super(str, str3, str4);
        this.eventNumericValue = null;
        this.operationalEventType = str2;
    }

    @Override // com.amazon.alexa.mobilytics.event.operational.MobilyticsOperationalEvent
    @Nullable
    public Map<String, String> getDebugInfo() {
        return this.debugInfo;
    }

    @NonNull
    public Long getEventNumericValue() {
        return this.eventNumericValue;
    }

    @Override // com.amazon.alexa.mobilytics.event.DefaultMobilyticsEvent, com.amazon.alexa.mobilytics.event.MobilyticsEvent
    public String getEventType() {
        return EventType.OPERATIONAL;
    }

    @Override // com.amazon.alexa.mobilytics.event.operational.MobilyticsOperationalEvent
    public String getOperationalEventType() {
        return this.operationalEventType;
    }

    @Override // com.amazon.alexa.mobilytics.event.DefaultMobilyticsEvent
    public DefaultMobilyticsOperationalEvent getThis() {
        return this;
    }

    @Override // com.amazon.alexa.mobilytics.event.DefaultMobilyticsEvent
    public EventDetailsProto.Builder serialize() {
        EventDetailsProto.Builder serialize = super.serialize();
        String str = this.operationalEventType;
        if (str != null) {
            serialize.setOperationalEventType(str);
        }
        Long l = this.eventNumericValue;
        if (l != null) {
            serialize.setEventNumericValue(l.longValue());
        }
        Map<String, String> map = this.debugInfo;
        if (map != null) {
            serialize.putAllDebugInfo(map);
        }
        return serialize;
    }

    public DefaultMobilyticsOperationalEvent withDebugInfo(@Nullable Map<String, String> map) {
        this.debugInfo = map;
        return this;
    }

    /* renamed from: withEventNumericValue */
    public DefaultMobilyticsOperationalEvent mo1484withEventNumericValue(Long l) {
        this.eventNumericValue = l;
        return this;
    }

    public DefaultMobilyticsOperationalEvent withOperationalEventType(String str) {
        this.operationalEventType = str;
        return this;
    }

    public DefaultMobilyticsOperationalEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @Nullable String str4, String str5) {
        super(str, str3, str4, str5);
        this.eventNumericValue = null;
        this.operationalEventType = str2;
    }

    public DefaultMobilyticsOperationalEvent(@NonNull String str, @NonNull String str2, @Nullable String str3) {
        super(str, str2, str3);
        this.eventNumericValue = null;
        this.operationalEventType = OperationalEventType.COUNTER;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public DefaultMobilyticsOperationalEvent(String str, String str2) {
        super(str, str2);
        this.eventNumericValue = null;
    }
}
