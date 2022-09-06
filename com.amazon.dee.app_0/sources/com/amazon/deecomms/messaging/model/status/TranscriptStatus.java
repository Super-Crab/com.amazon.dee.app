package com.amazon.deecomms.messaging.model.status;
/* loaded from: classes12.dex */
public enum TranscriptStatus {
    Success,
    UnsupportedMediaType,
    MediaOversized,
    TranscodingError,
    BluefrontError,
    LowConfidence,
    TimedOut,
    UnknownError,
    InvalidTranscript,
    InvalidConversation,
    InvalidMessage,
    Transcribing;

    public static TranscriptStatus fromString(String str) {
        TranscriptStatus[] values;
        if (str == null) {
            return Transcribing;
        }
        for (TranscriptStatus transcriptStatus : values()) {
            if (transcriptStatus.name().equalsIgnoreCase(str)) {
                return transcriptStatus;
            }
        }
        return UnknownError;
    }
}
