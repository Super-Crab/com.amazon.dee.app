package com.amazon.deecomms.calling.model.response;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.deecomms.calling.util.HttpStatusCodeFamily;
import com.amazon.deecomms.calling.util.SetupCallHelper;
import com.amazon.deecomms.common.metrics.MetricsStatsProvider;
/* loaded from: classes12.dex */
public class CallInitResponse implements MetricsStatsProvider {
    private final Long callDuration;
    private final HttpStatusCodeFamily family;
    private final Integer httpStatusCode;

    /* renamed from: com.amazon.deecomms.calling.model.response.CallInitResponse$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$deecomms$calling$util$SetupCallHelper$ResultType = new int[SetupCallHelper.ResultType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$deecomms$calling$util$SetupCallHelper$ResultType[SetupCallHelper.ResultType.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$calling$util$SetupCallHelper$ResultType[SetupCallHelper.ResultType.EXPECTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$calling$util$SetupCallHelper$ResultType[SetupCallHelper.ResultType.UNEXPECTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$calling$util$SetupCallHelper$ResultType[SetupCallHelper.ResultType.UNKNOWN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public CallInitResponse(@Nullable Integer num, @Nullable Long l, @NonNull SetupCallHelper.ResultType resultType) {
        this.httpStatusCode = num;
        this.callDuration = l;
        this.family = getResponseFamily(resultType);
    }

    private HttpStatusCodeFamily getResponseFamily(SetupCallHelper.ResultType resultType) {
        int ordinal = resultType.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return HttpStatusCodeFamily.CLIENT_ERROR;
            }
            if (ordinal != 2) {
                return HttpStatusCodeFamily.UNKNOWN;
            }
            return HttpStatusCodeFamily.SERVER_ERROR;
        }
        return HttpStatusCodeFamily.SUCCESS;
    }

    @Override // com.amazon.deecomms.common.metrics.MetricsStatsProvider
    public Long getCallDuration() {
        return this.callDuration;
    }

    @Override // com.amazon.deecomms.common.metrics.MetricsStatsProvider
    public int getFailureCount() {
        return this.family.getFailureCount();
    }

    @Override // com.amazon.deecomms.common.metrics.MetricsStatsProvider
    public int getFaultCount() {
        return this.family.getFaultCount();
    }

    @Override // com.amazon.deecomms.common.metrics.MetricsStatsProvider
    public Integer getHttpResponseCode() {
        return this.httpStatusCode;
    }

    @Override // com.amazon.deecomms.common.metrics.MetricsStatsProvider
    public String getRequestId() {
        return null;
    }

    @Override // com.amazon.deecomms.common.metrics.MetricsStatsProvider
    public int getSuccessCount() {
        return this.family.getSuccessCount();
    }

    @Override // com.amazon.deecomms.common.metrics.MetricsStatsProvider
    public int getUnknownCount() {
        return this.family.getUnknownCount();
    }

    public CallInitResponse(@Nullable Integer num, @NonNull SetupCallHelper.ResultType resultType) {
        this(num, null, resultType);
    }
}
