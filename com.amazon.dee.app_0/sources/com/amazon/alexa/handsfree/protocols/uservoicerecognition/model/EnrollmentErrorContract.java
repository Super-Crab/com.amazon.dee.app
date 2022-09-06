package com.amazon.alexa.handsfree.protocols.uservoicerecognition.model;

import androidx.annotation.StringRes;
/* loaded from: classes8.dex */
public interface EnrollmentErrorContract {
    public static final int NO_RES_ID = 0;

    /* loaded from: classes8.dex */
    public enum ErrorSource {
        _1PSV(1),
        SPEAKER_ID_AIS(2),
        SPEAKER_ID_OTHER(3),
        SPEAKER_ID_NETWORK_UNAVAILABLE(4),
        SPEAKER_ID_TERMINAL_ERROR(5);
        
        private final int mCode;

        ErrorSource(int i) {
            this.mCode = i;
        }

        public int getCode() {
            return this.mCode;
        }
    }

    String getErrorCode();

    String getName();

    @StringRes
    int getReasonRes();

    @StringRes
    int getReasonTitleRes();

    boolean isTerminalError();
}
