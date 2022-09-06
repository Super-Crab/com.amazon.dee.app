package com.amazon.alexa.enrollment.unified.speakerid.metrics;
/* loaded from: classes7.dex */
public interface SpeakerIDMetricsConstants {

    /* loaded from: classes7.dex */
    public enum ErrorType {
        SILENCE_RETRYABLE(1),
        SILENCE_NON_RETRYABLE(2),
        LOW_QUALITY_UTTERANCE_RETRYABLE(3),
        LOW_QUALITY_UTTERANCE_NON_RETRYABLE(4),
        NETWORK_UNAVAILABLE(5),
        UNKNOWN_EXCEPTION(6),
        USER_CANCELLED_EXCEPTION(7);
        
        private final int mErrorCode;

        ErrorType(int i) {
            this.mErrorCode = i;
        }

        public int getErrorCode() {
            return this.mErrorCode;
        }
    }

    /* loaded from: classes7.dex */
    public enum PageType {
        SPEAKER_ID_ENROLLMENT
    }

    /* loaded from: classes7.dex */
    public enum SubPageType {
        START_ENROLLMENT,
        UTTERANCE_TRAINING
    }

    /* loaded from: classes7.dex */
    public enum VisualFocusType {
        START_VISUAL_FOCUS,
        STOP_VISUAL_FOCUS,
        TIMEOUT_VISUAL_FOCUS
    }
}
