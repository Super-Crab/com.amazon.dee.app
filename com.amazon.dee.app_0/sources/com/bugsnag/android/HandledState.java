package com.bugsnag.android;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.accessorykit.ModelTransformer;
import com.bugsnag.android.JsonStream;
import java.io.IOException;
/* loaded from: classes.dex */
public final class HandledState implements JsonStream.Streamable {
    static final String REASON_ANR = "anrError";
    static final String REASON_CALLBACK_SPECIFIED = "userCallbackSetSeverity";
    static final String REASON_HANDLED_EXCEPTION = "handledException";
    static final String REASON_LOG = "log";
    static final String REASON_PROMISE_REJECTION = "unhandledPromiseRejection";
    static final String REASON_SIGNAL = "signal";
    static final String REASON_STRICT_MODE = "strictMode";
    static final String REASON_UNHANDLED_EXCEPTION = "unhandledException";
    static final String REASON_USER_SPECIFIED = "userSpecifiedSeverity";
    @Nullable
    private final String attributeValue;
    private Severity currentSeverity;
    private final Severity defaultSeverity;
    private final String severityReasonType;
    private final boolean unhandled;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HandledState(String str, Severity severity, boolean z, @Nullable String str2) {
        this.severityReasonType = str;
        this.defaultSeverity = severity;
        this.unhandled = z;
        this.attributeValue = str2;
        this.currentSeverity = severity;
    }

    static HandledState newInstance(String str) {
        return newInstance(str, null, null);
    }

    String calculateSeverityReasonType() {
        return this.defaultSeverity == this.currentSeverity ? this.severityReasonType : REASON_CALLBACK_SPECIFIED;
    }

    @Nullable
    String getAttributeValue() {
        return this.attributeValue;
    }

    Severity getCurrentSeverity() {
        return this.currentSeverity;
    }

    public boolean isUnhandled() {
        return this.unhandled;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setCurrentSeverity(Severity severity) {
        this.currentSeverity = severity;
    }

    @Override // com.bugsnag.android.JsonStream.Streamable
    public void toStream(@NonNull JsonStream jsonStream) throws IOException {
        jsonStream.beginObject().mo6745name("type").value(calculateSeverityReasonType());
        if (this.attributeValue != null) {
            String str = null;
            String str2 = this.severityReasonType;
            char c = 65535;
            int hashCode = str2.hashCode();
            if (hashCode != -1876197364) {
                if (hashCode == 107332 && str2.equals(REASON_LOG)) {
                    c = 0;
                }
            } else if (str2.equals(REASON_STRICT_MODE)) {
                c = 1;
            }
            if (c == 0) {
                str = ModelTransformer.KEY_BATTERY_LEVEL;
            } else if (c == 1) {
                str = "violationType";
            }
            if (str != null) {
                jsonStream.mo6745name("attributes").beginObject().mo6745name(str).value(this.attributeValue).endObject();
            }
        }
        jsonStream.endObject();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static HandledState newInstance(String str, @Nullable Severity severity, @Nullable String str2) {
        if (str.equals(REASON_STRICT_MODE) && Intrinsics.isEmpty(str2)) {
            throw new IllegalArgumentException("No reason supplied for strictmode");
        }
        if (!str.equals(REASON_STRICT_MODE) && !str.equals(REASON_LOG) && !Intrinsics.isEmpty(str2)) {
            throw new IllegalArgumentException("attributeValue should not be supplied");
        }
        char c = 65535;
        switch (str.hashCode()) {
            case -1876197364:
                if (str.equals(REASON_STRICT_MODE)) {
                    c = 1;
                    break;
                }
                break;
            case -1773746641:
                if (str.equals(REASON_CALLBACK_SPECIFIED)) {
                    c = 4;
                    break;
                }
                break;
            case -1591166708:
                if (str.equals(REASON_UNHANDLED_EXCEPTION)) {
                    c = 0;
                    break;
                }
                break;
            case -1107031998:
                if (str.equals(REASON_USER_SPECIFIED)) {
                    c = 3;
                    break;
                }
                break;
            case -573976797:
                if (str.equals(REASON_ANR)) {
                    c = 7;
                    break;
                }
                break;
            case 107332:
                if (str.equals(REASON_LOG)) {
                    c = 6;
                    break;
                }
                break;
            case 87505361:
                if (str.equals(REASON_PROMISE_REJECTION)) {
                    c = 5;
                    break;
                }
                break;
            case 561970291:
                if (str.equals(REASON_HANDLED_EXCEPTION)) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return new HandledState(str, Severity.ERROR, true, null);
            case 1:
                return new HandledState(str, Severity.WARNING, true, str2);
            case 2:
                return new HandledState(str, Severity.WARNING, false, null);
            case 3:
                return new HandledState(str, severity, false, null);
            case 4:
                return new HandledState(str, severity, false, null);
            case 5:
                return new HandledState(str, Severity.ERROR, true, null);
            case 6:
                return new HandledState(str, severity, false, str2);
            case 7:
                return new HandledState(str, Severity.ERROR, true, null);
            default:
                throw new IllegalArgumentException(String.format("Invalid argument '%s' for severityReason", str));
        }
    }
}
