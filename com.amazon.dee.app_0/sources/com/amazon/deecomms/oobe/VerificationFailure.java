package com.amazon.deecomms.oobe;

import androidx.annotation.NonNull;
import com.amazon.deecomms.common.metrics.AlertSource;
import com.amazon.deecomms.oobe.structures.CVFResult;
/* loaded from: classes12.dex */
public final class VerificationFailure {
    private final AlertSource alertSource;
    private final String clientId;
    private final CVFResult errorClass;
    private final String requestId;

    public VerificationFailure(CVFResult cVFResult, @NonNull AlertSource alertSource) {
        this(cVFResult, null, null, alertSource);
    }

    public AlertSource getAlertSource() {
        return this.alertSource;
    }

    public String getClientId() {
        return this.clientId;
    }

    public CVFResult getErrorClass() {
        return this.errorClass;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public VerificationFailure(CVFResult cVFResult, String str, String str2, @NonNull AlertSource alertSource) {
        this.errorClass = cVFResult;
        this.requestId = str;
        this.clientId = str2;
        this.alertSource = alertSource;
    }
}
