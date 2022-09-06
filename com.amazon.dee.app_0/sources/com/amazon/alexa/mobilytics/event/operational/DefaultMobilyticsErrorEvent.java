package com.amazon.alexa.mobilytics.event.operational;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.mobilytics.protobuf.EventDetailsProto;
/* loaded from: classes9.dex */
public class DefaultMobilyticsErrorEvent extends DefaultMobilyticsOperationalEvent {
    private String errorLevel;
    private String errorShortMsg;
    private String errorTitle;

    public DefaultMobilyticsErrorEvent(@NonNull String str, String str2, @NonNull String str3, @NonNull String str4, @Nullable String str5, @NonNull String str6) {
        super(str, "error", str4, str5, str6);
        this.errorLevel = str2;
        this.errorTitle = str3;
    }

    @Override // com.amazon.alexa.mobilytics.event.operational.DefaultMobilyticsOperationalEvent, com.amazon.alexa.mobilytics.event.DefaultMobilyticsEvent
    public EventDetailsProto.Builder serialize() {
        EventDetailsProto.Builder serialize = super.serialize();
        String str = this.errorLevel;
        if (str != null) {
            serialize.setErrorLevel(str);
        }
        String str2 = this.errorShortMsg;
        if (str2 != null) {
            serialize.setErrorShortMsg(str2);
        }
        String str3 = this.errorTitle;
        if (str3 != null) {
            serialize.setErrorTitle(str3);
        }
        return serialize;
    }

    public DefaultMobilyticsErrorEvent withShortMessage(String str) {
        this.errorShortMsg = str;
        return this;
    }

    public DefaultMobilyticsErrorEvent(@NonNull String str, String str2, @NonNull String str3, @NonNull String str4, @Nullable String str5) {
        super(str, "error", str4, str5);
        this.errorLevel = str2;
        this.errorTitle = str3;
    }
}
