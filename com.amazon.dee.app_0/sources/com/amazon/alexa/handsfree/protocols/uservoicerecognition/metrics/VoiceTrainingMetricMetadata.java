package com.amazon.alexa.handsfree.protocols.uservoicerecognition.metrics;
/* loaded from: classes8.dex */
public interface VoiceTrainingMetricMetadata {

    /* loaded from: classes8.dex */
    public enum Component {
        VOICE_TRAINING
    }

    /* loaded from: classes8.dex */
    public enum EventType {
        UTTERANCE,
        CLICK,
        DISMISS,
        NEXT_STEP,
        PAGE_LOAD,
        BACK_BUTTON,
        LEAVE,
        NONE
    }

    /* loaded from: classes8.dex */
    public enum PageType {
        TRAINING_START,
        TRAINING_PRIMER,
        TRAINING_PRIMER_SKIP_DIALOG,
        TRAINING_PRIMER_INTERNET_DIALOG,
        TRAINING_PRIMER_SPEAKER_ID_ALREADY_ENROLLED,
        TRAINING_START_CONFIRM_DIALOG,
        TRAINING_PRIMER_CONFIRM_DIALOG,
        TRAINING_MAIN,
        TRAINING_CANCEL_DIALOG,
        TRAINING_INTERNET_DIALOG,
        LOCK_SCREEN_CONFIRM_DIALOG,
        TRAINING_FINISH,
        TRAINING_GETTING_READY,
        TRAINING_GETTING_READY_GENERIC_TIMEOUT_ALERT,
        TRAINING_GETTING_READY_INTERNET_ALERT,
        TRAINING_PRIMER_START_ENROLLMENT_ERROR_DIALOG
    }

    /* loaded from: classes8.dex */
    public enum SubPageType {
        START_BUTTON,
        ALERT_DIALOG_POSITIVE_BUTTON,
        ALERT_DIALOG_NEGATIVE_BUTTON,
        ALERT_DIALOG_DISMISS,
        START_LATER_BUTTON,
        CONFIRM_START_BUTTON,
        CONFIRM_BACK_BUTTON,
        MAX_ERRORS_DIALOG,
        FINISH_LATER_BUTTON,
        DIALOG_CONFIRM_BUTTON,
        DIALOG_RETRY_BUTTON,
        DIALOG_SKIP_BUTTON,
        DONE_BUTTON,
        LOCK_SCREEN_ALLOW_BUTTON,
        LOCK_SCREEN_DENY_BUTTON,
        RADIO_LOCK_SCREEN_OFF,
        RADIO_LOCK_SCREEN_ON,
        TERMINAL_ERROR_DIALOG,
        DISCLAIMER_TEXT,
        VOICE_PRIVACY,
        NONE
    }
}
