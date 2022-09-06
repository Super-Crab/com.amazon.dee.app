package com.amazon.alexa;

import com.amazon.alexa.api.AlexaMetricsName;
import java.util.HashMap;
import java.util.Map;
/* compiled from: DialogAbandonReason.java */
/* loaded from: classes.dex */
public enum mMl implements pRk {
    INVALID_WAKE_WORD,
    INVALID_AUDIO_METADATA,
    SCREEN_LOCKED,
    SOURCE_ARBITRATION,
    SPEECH_PROVIDER_NOT_REGISTERED,
    OUT_OF_TURN_CANNOT_REQUEST_DIALOG,
    OUT_OF_TURN_CANNOT_REQUEST_FIRST_TURN,
    OUT_OF_TURN_REQUEST_NOT_ALLOWED_TO_BARGE_IN,
    OUT_OF_TURN_REQUEST_NOT_ALLOWED_TO_START_FIRST_TURN,
    OUT_OF_TURN_DIALOG_NOT_STARTED,
    OUT_OF_TURN_DIALOG_NOT_CURRENT,
    OUT_OF_TURN_UNEXPECTED_TURN,
    OUT_OF_TURN_UNEXPECTED_NEXT_TURN,
    OUT_OF_TURN_START_DIALOG_NOT_ALLOWED,
    WAKE_WORD_ENGINE_NOT_READY,
    WAKE_WORD_AUDIO_INCOMPLETE,
    TEXT_TRANSFORMATION_FAILURE;
    
    public boolean isTextDialog = false;
    public static Map<mMl, AlexaMetricsName> voiceAbandonReasonToMetricsName = new HashMap();
    public static Map<mMl, AlexaMetricsName> textAbandonReasonToMetricsName = new HashMap();

    static {
        voiceAbandonReasonToMetricsName.put(INVALID_WAKE_WORD, AlexaMetricsName.VoiceInteraction.Abandoned.INVALID_WAKE_WORD);
        voiceAbandonReasonToMetricsName.put(INVALID_AUDIO_METADATA, AlexaMetricsName.VoiceInteraction.Abandoned.INVALID_AUDIO_METADATA);
        voiceAbandonReasonToMetricsName.put(SCREEN_LOCKED, AlexaMetricsName.VoiceInteraction.Abandoned.SCREEN_LOCKED);
        voiceAbandonReasonToMetricsName.put(SOURCE_ARBITRATION, AlexaMetricsName.VoiceInteraction.Abandoned.SOURCE_ARBITRATION);
        voiceAbandonReasonToMetricsName.put(SPEECH_PROVIDER_NOT_REGISTERED, AlexaMetricsName.VoiceInteraction.Abandoned.SPEECH_PROVIDER_NOT_REGISTERED);
        voiceAbandonReasonToMetricsName.put(OUT_OF_TURN_CANNOT_REQUEST_DIALOG, AlexaMetricsName.VoiceInteraction.Abandoned.OUT_OF_TURN_CANNOT_REQUEST_DIALOG);
        voiceAbandonReasonToMetricsName.put(OUT_OF_TURN_CANNOT_REQUEST_FIRST_TURN, AlexaMetricsName.VoiceInteraction.Abandoned.OUT_OF_TURN_CANNOT_REQUEST_FIRST_TURN);
        voiceAbandonReasonToMetricsName.put(OUT_OF_TURN_REQUEST_NOT_ALLOWED_TO_BARGE_IN, AlexaMetricsName.VoiceInteraction.Abandoned.OUT_OF_TURN_REQUEST_NOT_ALLOWED_TO_BARGE_IN);
        voiceAbandonReasonToMetricsName.put(OUT_OF_TURN_REQUEST_NOT_ALLOWED_TO_START_FIRST_TURN, AlexaMetricsName.VoiceInteraction.Abandoned.OUT_OF_TURN_REQUEST_NOT_ALLOWED_TO_START_FIRST_TURN);
        voiceAbandonReasonToMetricsName.put(OUT_OF_TURN_DIALOG_NOT_STARTED, AlexaMetricsName.VoiceInteraction.Abandoned.OUT_OF_TURN_DIALOG_NOT_STARTED);
        voiceAbandonReasonToMetricsName.put(OUT_OF_TURN_DIALOG_NOT_CURRENT, AlexaMetricsName.VoiceInteraction.Abandoned.OUT_OF_TURN_DIALOG_NOT_CURRENT);
        voiceAbandonReasonToMetricsName.put(OUT_OF_TURN_UNEXPECTED_TURN, AlexaMetricsName.VoiceInteraction.Abandoned.OUT_OF_TURN_UNEXPECTED_TURN);
        voiceAbandonReasonToMetricsName.put(OUT_OF_TURN_UNEXPECTED_NEXT_TURN, AlexaMetricsName.VoiceInteraction.Abandoned.OUT_OF_TURN_UNEXPECTED_NEXT_TURN);
        voiceAbandonReasonToMetricsName.put(OUT_OF_TURN_START_DIALOG_NOT_ALLOWED, AlexaMetricsName.VoiceInteraction.Abandoned.OUT_OF_TURN_START_DIALOG_NOT_ALLOWED);
        voiceAbandonReasonToMetricsName.put(WAKE_WORD_ENGINE_NOT_READY, AlexaMetricsName.VoiceInteraction.Abandoned.WAKE_WORD_ENGINE_NOT_READY);
        voiceAbandonReasonToMetricsName.put(WAKE_WORD_AUDIO_INCOMPLETE, AlexaMetricsName.VoiceInteraction.Abandoned.WAKE_WORD_AUDIO_INCOMPLETE);
        textAbandonReasonToMetricsName.put(SCREEN_LOCKED, AlexaMetricsName.TextInteraction.Abandoned.SCREEN_LOCKED);
        textAbandonReasonToMetricsName.put(SOURCE_ARBITRATION, AlexaMetricsName.TextInteraction.Abandoned.SOURCE_ARBITRATION);
        textAbandonReasonToMetricsName.put(OUT_OF_TURN_CANNOT_REQUEST_DIALOG, AlexaMetricsName.TextInteraction.Abandoned.OUT_OF_TURN_CANNOT_REQUEST_DIALOG);
        textAbandonReasonToMetricsName.put(OUT_OF_TURN_CANNOT_REQUEST_FIRST_TURN, AlexaMetricsName.TextInteraction.Abandoned.OUT_OF_TURN_CANNOT_REQUEST_FIRST_TURN);
        textAbandonReasonToMetricsName.put(OUT_OF_TURN_REQUEST_NOT_ALLOWED_TO_BARGE_IN, AlexaMetricsName.TextInteraction.Abandoned.OUT_OF_TURN_REQUEST_NOT_ALLOWED_TO_BARGE_IN);
        textAbandonReasonToMetricsName.put(OUT_OF_TURN_REQUEST_NOT_ALLOWED_TO_START_FIRST_TURN, AlexaMetricsName.TextInteraction.Abandoned.OUT_OF_TURN_REQUEST_NOT_ALLOWED_TO_START_FIRST_TURN);
        textAbandonReasonToMetricsName.put(OUT_OF_TURN_DIALOG_NOT_STARTED, AlexaMetricsName.TextInteraction.Abandoned.OUT_OF_TURN_DIALOG_NOT_STARTED);
        textAbandonReasonToMetricsName.put(OUT_OF_TURN_DIALOG_NOT_CURRENT, AlexaMetricsName.TextInteraction.Abandoned.OUT_OF_TURN_DIALOG_NOT_CURRENT);
        textAbandonReasonToMetricsName.put(OUT_OF_TURN_UNEXPECTED_TURN, AlexaMetricsName.TextInteraction.Abandoned.OUT_OF_TURN_UNEXPECTED_TURN);
        textAbandonReasonToMetricsName.put(OUT_OF_TURN_UNEXPECTED_NEXT_TURN, AlexaMetricsName.TextInteraction.Abandoned.OUT_OF_TURN_UNEXPECTED_NEXT_TURN);
        textAbandonReasonToMetricsName.put(OUT_OF_TURN_START_DIALOG_NOT_ALLOWED, AlexaMetricsName.TextInteraction.Abandoned.OUT_OF_TURN_START_DIALOG_NOT_ALLOWED);
        textAbandonReasonToMetricsName.put(TEXT_TRANSFORMATION_FAILURE, AlexaMetricsName.TextInteraction.Abandoned.TEXT_TRANSFORMATION_FAILURE);
    }

    mMl() {
    }

    @Override // com.amazon.alexa.pRk
    public Pmp getType() {
        return Pmp.ABANDONED;
    }

    @Override // com.amazon.alexa.pRk
    public AlexaMetricsName zZm() {
        return this.isTextDialog ? textAbandonReasonToMetricsName.get(this) : voiceAbandonReasonToMetricsName.get(this);
    }

    @Override // com.amazon.alexa.pRk
    public void zZm(boolean z) {
        this.isTextDialog = z;
    }
}
