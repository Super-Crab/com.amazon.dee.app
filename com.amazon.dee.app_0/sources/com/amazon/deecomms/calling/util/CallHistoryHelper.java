package com.amazon.deecomms.calling.util;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.comms.calling.service.Call;
import com.amazon.deecomms.calling.enums.CallType;
import com.amazon.deecomms.calling.model.CallQualityMetricsModel;
import com.amazon.deecomms.calling.model.CallingMetricsModel;
import com.amazon.deecomms.contacts.model.ContactPhoneNumber;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import java.util.concurrent.TimeUnit;
/* loaded from: classes12.dex */
public class CallHistoryHelper {
    private static final int HISTORY_MAX_SIZE = 10;
    private static final int HISTORY_TTL_IN_MINS = 240;
    public static final int MISSING_CALL_STATUS_CODE = -1;
    private final Cache<String, Info> CACHE = CacheBuilder.newBuilder().maximumSize(10).expireAfterAccess(240, TimeUnit.MINUTES).build();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class Info {
        private Boolean answeredCall;
        private boolean callInitMetricsWereRecorded;
        private String callProvider;
        private CallQualityMetricsModel callQualityMetrics;
        private CallType callType;
        private ContactPhoneNumber calleePhoneNumber;
        private CallingMetricsModel callingMetrics;
        private String connectionType;
        private Integer statusCode;
        private Boolean userEndedCall;

        private Info() {
        }

        /* synthetic */ Info(AnonymousClass1 anonymousClass1) {
        }
    }

    @NonNull
    private Info getOrCreateCallInfo(@NonNull String str) {
        Info ifPresent = this.CACHE.getIfPresent(str);
        if (ifPresent != null) {
            return ifPresent;
        }
        Info info = new Info(null);
        this.CACHE.put(str, info);
        return info;
    }

    public void answeredCall(@NonNull String str) {
        getOrCreateCallInfo(str).answeredCall = true;
    }

    public boolean didUserAnswerCall(@NonNull String str) {
        Info ifPresent = this.CACHE.getIfPresent(str);
        if (ifPresent == null || ifPresent.answeredCall == null) {
            return false;
        }
        return ifPresent.answeredCall.booleanValue();
    }

    public boolean didUserEndCall(@NonNull String str) {
        Info ifPresent = this.CACHE.getIfPresent(str);
        if (ifPresent == null || ifPresent.userEndedCall == null) {
            return false;
        }
        return ifPresent.userEndedCall.booleanValue();
    }

    @Nullable
    public String getCallConnectionType(@NonNull Call call) {
        return getCallConnectionType(call.getCallId());
    }

    @NonNull
    public String getCallProvider(@NonNull String str) {
        Info ifPresent = this.CACHE.getIfPresent(str);
        return (ifPresent == null || ifPresent.callProvider == null) ? "" : ifPresent.callProvider;
    }

    @Nullable
    public CallQualityMetricsModel getCallQualityMetrics(@NonNull String str) {
        Info ifPresent = this.CACHE.getIfPresent(str);
        if (ifPresent != null) {
            return ifPresent.callQualityMetrics;
        }
        return null;
    }

    public int getCallStatusCode(String str) {
        Info ifPresent = this.CACHE.getIfPresent(str);
        if (ifPresent == null || ifPresent.statusCode == null) {
            return -1;
        }
        return ifPresent.statusCode.intValue();
    }

    @NonNull
    public CallType getCallType(@NonNull String str) {
        Info ifPresent = this.CACHE.getIfPresent(str);
        return (ifPresent == null || ifPresent.callType == null) ? CallType.NONE : ifPresent.callType;
    }

    @Nullable
    public ContactPhoneNumber getCalleePhoneNumber(@NonNull String str) {
        Info ifPresent = this.CACHE.getIfPresent(str);
        if (ifPresent != null) {
            return ifPresent.calleePhoneNumber;
        }
        return null;
    }

    @Nullable
    public CallingMetricsModel getCallingMetrics(@NonNull String str) {
        Info ifPresent = this.CACHE.getIfPresent(str);
        if (ifPresent != null) {
            return ifPresent.callingMetrics;
        }
        return null;
    }

    public void keepAlive(@NonNull String str) {
        this.CACHE.getIfPresent(str);
    }

    public boolean setCallConnectionType(@NonNull Call call) {
        String connectionType = CallUtils.getConnectionType(call);
        Info orCreateCallInfo = getOrCreateCallInfo(call.getCallId());
        if (TextUtils.isEmpty(connectionType) || TextUtils.equals(connectionType, orCreateCallInfo.connectionType)) {
            return false;
        }
        getOrCreateCallInfo(call.getCallId()).connectionType = connectionType;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setCallInitMetricsWereRecorded(@NonNull String str, boolean z) {
        getOrCreateCallInfo(str).callInitMetricsWereRecorded = z;
    }

    public void setCallProvider(@NonNull String str, @NonNull String str2) {
        getOrCreateCallInfo(str).callProvider = str2;
    }

    public void setCallQualityMetrics(@NonNull CallQualityMetricsModel callQualityMetricsModel) {
        getOrCreateCallInfo(callQualityMetricsModel.getCallId()).callQualityMetrics = callQualityMetricsModel;
    }

    public void setCallStatusCode(@NonNull String str, int i) {
        getOrCreateCallInfo(str).statusCode = Integer.valueOf(i);
    }

    public void setCallType(@NonNull String str, @NonNull CallType callType) {
        getOrCreateCallInfo(str).callType = callType;
    }

    public void setCalleePhoneNumber(@NonNull String str, @NonNull ContactPhoneNumber contactPhoneNumber) {
        getOrCreateCallInfo(str).calleePhoneNumber = contactPhoneNumber;
    }

    public void setCallingMetrics(@NonNull CallingMetricsModel callingMetricsModel) {
        getOrCreateCallInfo(callingMetricsModel.getCallId()).callingMetrics = callingMetricsModel;
    }

    public void userEndedCall(@NonNull String str) {
        getOrCreateCallInfo(str).userEndedCall = true;
    }

    public boolean wereCallInitMetricsRecorded(@NonNull String str) {
        Info ifPresent = this.CACHE.getIfPresent(str);
        return ifPresent != null && ifPresent.callInitMetricsWereRecorded;
    }

    @Nullable
    public String getCallConnectionType(@NonNull String str) {
        Info ifPresent = this.CACHE.getIfPresent(str);
        if (ifPresent == null || ifPresent.connectionType == null) {
            return null;
        }
        return ifPresent.connectionType;
    }
}
