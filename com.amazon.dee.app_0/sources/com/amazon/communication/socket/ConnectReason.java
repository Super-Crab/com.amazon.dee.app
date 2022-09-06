package com.amazon.communication.socket;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public class ConnectReason {
    private static final String REASON_ATTEMPT_SEPARATOR = ";";
    private static final String REASON_SUB_REASON_SEPARATOR = "And";
    private final int mAttempt;
    private final ReasonString mReasonString;
    private final ReasonString mSubReasonString;

    /* loaded from: classes12.dex */
    public enum ReasonString {
        ClientInitiated,
        ServiceStarted,
        ConnectivityAvailable,
        ConnectivityStabilized,
        ScreenEvent,
        PreferredInterfaceAvailable,
        AccountChange,
        HeartbeatFailure,
        NoRecentHeartbeats,
        HeartbeatIntervalExpired,
        RemoteSettingsDrasticChange,
        PolicyChange,
        ConnectionClosed,
        ConnectionFailed
    }

    public ConnectReason(ReasonString reasonString, int i) {
        this(reasonString, null, i);
    }

    public static ConnectReason fromString(String str) {
        ReasonString reasonString;
        if (str != null && !str.trim().isEmpty()) {
            String[] split = str.split(REASON_ATTEMPT_SEPARATOR);
            if (split.length == 2) {
                try {
                    int parseInt = Integer.parseInt(split[1]);
                    int indexOf = split[0].indexOf(REASON_SUB_REASON_SEPARATOR);
                    ReasonString reasonString2 = null;
                    if (indexOf > 0) {
                        ReasonString reasonString3 = (ReasonString) Enum.valueOf(ReasonString.class, split[0].substring(0, indexOf));
                        reasonString2 = (ReasonString) Enum.valueOf(ReasonString.class, split[0].substring(indexOf + 3));
                        reasonString = reasonString3;
                    } else {
                        reasonString = (ReasonString) Enum.valueOf(ReasonString.class, split[0]);
                    }
                    return new ConnectReason(reasonString, reasonString2, parseInt);
                } catch (NumberFormatException e) {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unable to parse token in to attempt: ");
                    outline107.append(split[1]);
                    throw new IllegalArgumentException(outline107.toString(), e);
                }
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("Invalid serialized text. Expecting exactly two parts: ", str));
        }
        throw new IllegalArgumentException("serialized String must not be null or empty");
    }

    public static ConnectReason nextAttempt(ConnectReason connectReason) {
        return new ConnectReason(connectReason.getReasonString(), connectReason.getSubReasonString(), connectReason.getAttempt() + 1);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ConnectReason)) {
            return false;
        }
        ConnectReason connectReason = (ConnectReason) obj;
        if (this.mReasonString == null && connectReason.mReasonString != null) {
            return false;
        }
        ReasonString reasonString = this.mReasonString;
        if (reasonString != null && !reasonString.equals(connectReason.mReasonString)) {
            return false;
        }
        if (this.mSubReasonString == null && connectReason.mSubReasonString != null) {
            return false;
        }
        ReasonString reasonString2 = this.mSubReasonString;
        return (reasonString2 == null || reasonString2.equals(connectReason.mSubReasonString)) && this.mAttempt == connectReason.mAttempt;
    }

    public int getAttempt() {
        return this.mAttempt;
    }

    public ReasonString getReasonString() {
        return this.mReasonString;
    }

    public ReasonString getSubReasonString() {
        return this.mSubReasonString;
    }

    public int hashCode() {
        ReasonString reasonString = this.mReasonString;
        int i = 0;
        int hashCode = ((reasonString == null ? 0 : reasonString.hashCode()) + 19) * 19;
        ReasonString reasonString2 = this.mSubReasonString;
        if (reasonString2 != null) {
            i = reasonString2.hashCode();
        }
        return ((hashCode + i) * 19) + this.mAttempt;
    }

    public String toString() {
        String sb;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.mReasonString.toString());
        if (this.mSubReasonString == null) {
            sb = "";
        } else {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(REASON_SUB_REASON_SEPARATOR);
            outline107.append(this.mSubReasonString.toString());
            sb = outline107.toString();
        }
        sb2.append(sb);
        sb2.append(REASON_ATTEMPT_SEPARATOR);
        sb2.append(this.mAttempt);
        return sb2.toString();
    }

    public ConnectReason(ReasonString reasonString, ReasonString reasonString2, int i) {
        if (reasonString != null) {
            if (i >= 1) {
                this.mReasonString = reasonString;
                this.mSubReasonString = reasonString2;
                this.mAttempt = i;
                return;
            }
            throw new IllegalArgumentException("Attempt must be >= 1");
        }
        throw new IllegalArgumentException("Reason must not be null");
    }
}
