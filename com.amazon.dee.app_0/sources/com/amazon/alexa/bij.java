package com.amazon.alexa;

import com.amazon.alexa.api.AlexaMetricsName;
import java.util.HashMap;
import java.util.Map;
/* compiled from: DialogFailureReason.java */
/* loaded from: classes.dex */
public enum bij implements pRk {
    BINDING_ERROR,
    LOCAL_SERVICE_DISCONNECTED,
    LEADER_SELECTION_ERROR,
    LEADER_DISABLED_ERROR,
    NETWORK_UNAVAILABLE,
    NETWORK_LOST,
    NETWORK_SWITCHED,
    AVS_UNAVAILABLE_MISSING,
    AVS_UNAVAILABLE_DOWNCHANNEL,
    AVS_ERROR,
    AVS_CONNECTION_TIMEOUT,
    AVS_CONNECTION_TIMEOUT_UNINITIALIZED,
    AVS_CONNECTION_TIMEOUT_UNAUTHORIZED,
    AVS_CONNECTION_TIMEOUT_NETWORK,
    AVS_CONNECTION_TIMEOUT_CONNECTION_NOT_ESTABLISHED,
    AVS_CONNECTION_TIMEOUT_CONNECTION_NOT_CONNECTED,
    AVS_CONNECTION_TIMEOUT_DOWNCHANNEL_AVAILABLE,
    AVS_CONNECTION_TIMEOUT_CAPABILITY_PUBLISH,
    AVS_CONNECTION_TIMEOUT_SYNCHRONIZE_STATE,
    TURN_TIMEOUT,
    NETWORK_REQUEST_ERROR,
    START_RECORDING_ERROR,
    RESPONSE_PARSING_ERROR_MULTIPART,
    RESPONSE_PARSING_ERROR,
    REQUEST_PARSING_ERROR,
    AUTHORIZATION_ERROR,
    INTERNAL_CLIENT_ERROR_CLIENT_TIMEOUT_EXCEEDED,
    INTERNAL_CLIENT_ERROR_MESSAGE_TIMEOUT_EXCEEDED,
    INTERNAL_CLIENT_ERROR_CONCURRENT_REGISTER_ATTEMPT,
    INTERNAL_CLIENT_ERROR_INCOMPLETE_INTERACTION,
    INTERNAL_CLIENT_ERROR_UNKNOWN_FAILURE,
    INTERNAL_CLIENT_ERROR_MESSAGING,
    INTERNAL_CLIENT_ERROR_CONCURRENT_WAKEWORD_VERIFICATION,
    INTERNAL_CLIENT_ERROR_WAKEWORD_VERIFICATION_BLOCKED,
    RECORDING_ERROR_AUDIO_RECORD_NOT_INITIALIZED,
    RECORDING_ERROR_FAILED_TO_START_RECORDING,
    RECORDING_ERROR_FAILED_TO_ACQUIRE_MIC,
    RECORDING_ERROR_START_TIMEOUT,
    RECORDING_ERROR_STOP_TIMEOUT,
    RECORDING_ERROR_FAILED_TO_READ_FROM_AUDIO_RECORD,
    RECORDING_ERROR_IO_EXCEPTION,
    RECORDING_ERROR_INITIALIZATION_EXCEPTION,
    RECORDING_ERROR_STOPPED_THREAD,
    CANNOT_EXPECT_SPEECH,
    CANCELLED;
    
    public boolean isTextDialog = false;
    public static Map<bij, AlexaMetricsName> voiceFailureReasonToMetricsName = new HashMap();
    public static Map<bij, AlexaMetricsName> textFailureReasonToMetricsName = new HashMap();

    static {
        voiceFailureReasonToMetricsName.put(BINDING_ERROR, AlexaMetricsName.VoiceInteraction.Failure.BINDING_ERROR);
        voiceFailureReasonToMetricsName.put(LOCAL_SERVICE_DISCONNECTED, AlexaMetricsName.VoiceInteraction.Failure.LOCAL_SERVICE_DISCONNECTED);
        voiceFailureReasonToMetricsName.put(LEADER_SELECTION_ERROR, AlexaMetricsName.VoiceInteraction.Failure.LEADER_SELECTION_ERROR);
        voiceFailureReasonToMetricsName.put(LEADER_DISABLED_ERROR, AlexaMetricsName.VoiceInteraction.Failure.LEADER_DISABLED_ERROR);
        voiceFailureReasonToMetricsName.put(NETWORK_UNAVAILABLE, AlexaMetricsName.VoiceInteraction.Failure.NETWORK_UNAVAILABLE);
        voiceFailureReasonToMetricsName.put(NETWORK_LOST, AlexaMetricsName.VoiceInteraction.Failure.NETWORK_LOST);
        voiceFailureReasonToMetricsName.put(NETWORK_SWITCHED, AlexaMetricsName.VoiceInteraction.Failure.NETWORK_SWITCHED);
        voiceFailureReasonToMetricsName.put(AVS_UNAVAILABLE_MISSING, AlexaMetricsName.VoiceInteraction.Failure.AVS_UNAVAILABLE_MISSING);
        voiceFailureReasonToMetricsName.put(AVS_UNAVAILABLE_DOWNCHANNEL, AlexaMetricsName.VoiceInteraction.Failure.AVS_UNAVAILABLE_DOWNCHANNEL);
        voiceFailureReasonToMetricsName.put(AVS_ERROR, AlexaMetricsName.VoiceInteraction.Failure.AVS_ERROR);
        voiceFailureReasonToMetricsName.put(AVS_CONNECTION_TIMEOUT, AlexaMetricsName.VoiceInteraction.Failure.AVS_CONNECTION_TIMEOUT);
        voiceFailureReasonToMetricsName.put(AVS_CONNECTION_TIMEOUT_UNINITIALIZED, AlexaMetricsName.VoiceInteraction.Failure.AVS_CONNECTION_TIMEOUT_UNINITIALIZED);
        voiceFailureReasonToMetricsName.put(AVS_CONNECTION_TIMEOUT_UNAUTHORIZED, AlexaMetricsName.VoiceInteraction.Failure.AVS_CONNECTION_TIMEOUT_UNAUTHORIZED);
        voiceFailureReasonToMetricsName.put(AVS_CONNECTION_TIMEOUT_NETWORK, AlexaMetricsName.VoiceInteraction.Failure.AVS_CONNECTION_TIMEOUT_NETWORK);
        voiceFailureReasonToMetricsName.put(AVS_CONNECTION_TIMEOUT_CONNECTION_NOT_ESTABLISHED, AlexaMetricsName.VoiceInteraction.Failure.AVS_CONNECTION_TIMEOUT_CONNECTION_NOT_ESTABLISHED);
        voiceFailureReasonToMetricsName.put(AVS_CONNECTION_TIMEOUT_CONNECTION_NOT_CONNECTED, AlexaMetricsName.VoiceInteraction.Failure.AVS_CONNECTION_TIMEOUT_CONNECTION_NOT_CONNECTED);
        voiceFailureReasonToMetricsName.put(AVS_CONNECTION_TIMEOUT_DOWNCHANNEL_AVAILABLE, AlexaMetricsName.VoiceInteraction.Failure.AVS_CONNECTION_TIMEOUT_DOWNCHANNEL_AVAILABLE);
        voiceFailureReasonToMetricsName.put(AVS_CONNECTION_TIMEOUT_CAPABILITY_PUBLISH, AlexaMetricsName.VoiceInteraction.Failure.AVS_CONNECTION_TIMEOUT_CAPABILITY_PUBLISH);
        voiceFailureReasonToMetricsName.put(AVS_CONNECTION_TIMEOUT_SYNCHRONIZE_STATE, AlexaMetricsName.VoiceInteraction.Failure.AVS_CONNECTION_TIMEOUT_SYNCHRONIZE_STATE);
        voiceFailureReasonToMetricsName.put(TURN_TIMEOUT, AlexaMetricsName.VoiceInteraction.Failure.TURN_TIMEOUT);
        voiceFailureReasonToMetricsName.put(NETWORK_REQUEST_ERROR, AlexaMetricsName.VoiceInteraction.Failure.NETWORK_REQUEST_ERROR);
        voiceFailureReasonToMetricsName.put(START_RECORDING_ERROR, AlexaMetricsName.VoiceInteraction.Failure.START_RECORDING_ERROR);
        voiceFailureReasonToMetricsName.put(RESPONSE_PARSING_ERROR_MULTIPART, AlexaMetricsName.VoiceInteraction.Failure.RESPONSE_PARSING_ERROR_MULTIPART);
        voiceFailureReasonToMetricsName.put(RESPONSE_PARSING_ERROR, AlexaMetricsName.VoiceInteraction.Failure.RESPONSE_PARSING_ERROR);
        voiceFailureReasonToMetricsName.put(REQUEST_PARSING_ERROR, AlexaMetricsName.VoiceInteraction.Failure.REQUEST_PARSING_ERROR);
        voiceFailureReasonToMetricsName.put(AUTHORIZATION_ERROR, AlexaMetricsName.VoiceInteraction.Failure.AUTHORIZATION_ERROR);
        voiceFailureReasonToMetricsName.put(INTERNAL_CLIENT_ERROR_CLIENT_TIMEOUT_EXCEEDED, AlexaMetricsName.VoiceInteraction.Failure.INTERNAL_CLIENT_ERROR_CLIENT_TIMEOUT_EXCEEDED);
        voiceFailureReasonToMetricsName.put(INTERNAL_CLIENT_ERROR_MESSAGE_TIMEOUT_EXCEEDED, AlexaMetricsName.VoiceInteraction.Failure.INTERNAL_CLIENT_ERROR_MESSAGE_TIMEOUT_EXCEEDED);
        voiceFailureReasonToMetricsName.put(INTERNAL_CLIENT_ERROR_CONCURRENT_REGISTER_ATTEMPT, AlexaMetricsName.VoiceInteraction.Failure.INTERNAL_CLIENT_ERROR_CONCURRENT_REGISTER_ATTEMPT);
        voiceFailureReasonToMetricsName.put(INTERNAL_CLIENT_ERROR_INCOMPLETE_INTERACTION, AlexaMetricsName.VoiceInteraction.Failure.INTERNAL_CLIENT_ERROR_INCOMPLETE_INTERACTION);
        voiceFailureReasonToMetricsName.put(INTERNAL_CLIENT_ERROR_UNKNOWN_FAILURE, AlexaMetricsName.VoiceInteraction.Failure.INTERNAL_CLIENT_ERROR_UNKNOWN_FAILURE);
        voiceFailureReasonToMetricsName.put(INTERNAL_CLIENT_ERROR_MESSAGING, AlexaMetricsName.VoiceInteraction.Failure.INTERNAL_CLIENT_ERROR_MESSAGING);
        voiceFailureReasonToMetricsName.put(INTERNAL_CLIENT_ERROR_CONCURRENT_WAKEWORD_VERIFICATION, AlexaMetricsName.VoiceInteraction.Failure.INTERNAL_CLIENT_ERROR_CONCURRENT_WAKEWORD_VERIFICATION);
        voiceFailureReasonToMetricsName.put(INTERNAL_CLIENT_ERROR_WAKEWORD_VERIFICATION_BLOCKED, AlexaMetricsName.VoiceInteraction.Failure.INTERNAL_CLIENT_ERROR_WAKEWORD_VERIFICATION_BLOCKED);
        voiceFailureReasonToMetricsName.put(RECORDING_ERROR_AUDIO_RECORD_NOT_INITIALIZED, AlexaMetricsName.VoiceInteraction.Failure.RECORDING_ERROR_AUDIO_RECORD_NOT_INITIALIZED);
        voiceFailureReasonToMetricsName.put(RECORDING_ERROR_FAILED_TO_START_RECORDING, AlexaMetricsName.VoiceInteraction.Failure.RECORDING_ERROR_FAILED_TO_START_RECORDING);
        voiceFailureReasonToMetricsName.put(RECORDING_ERROR_FAILED_TO_ACQUIRE_MIC, AlexaMetricsName.VoiceInteraction.Failure.RECORDING_ERROR_FAILED_TO_ACQUIRE_MIC);
        voiceFailureReasonToMetricsName.put(RECORDING_ERROR_START_TIMEOUT, AlexaMetricsName.VoiceInteraction.Failure.RECORDING_ERROR_START_TIMEOUT);
        voiceFailureReasonToMetricsName.put(RECORDING_ERROR_STOP_TIMEOUT, AlexaMetricsName.VoiceInteraction.Failure.RECORDING_ERROR_STOP_TIMEOUT);
        voiceFailureReasonToMetricsName.put(RECORDING_ERROR_IO_EXCEPTION, AlexaMetricsName.VoiceInteraction.Failure.RECORDING_ERROR_IO_EXCEPTION);
        voiceFailureReasonToMetricsName.put(RECORDING_ERROR_INITIALIZATION_EXCEPTION, AlexaMetricsName.VoiceInteraction.Failure.RECORDING_ERROR_INITIALIZATION_EXCEPTION);
        voiceFailureReasonToMetricsName.put(RECORDING_ERROR_STOPPED_THREAD, AlexaMetricsName.VoiceInteraction.Failure.RECORDING_ERROR_STOPPED_THREAD);
        voiceFailureReasonToMetricsName.put(CANNOT_EXPECT_SPEECH, AlexaMetricsName.VoiceInteraction.Failure.CANNOT_EXPECT_SPEECH);
        voiceFailureReasonToMetricsName.put(RECORDING_ERROR_FAILED_TO_READ_FROM_AUDIO_RECORD, AlexaMetricsName.VoiceInteraction.Failure.RECORDING_ERROR_FAILED_TO_READ_FROM_AUDIO_RECORD);
        textFailureReasonToMetricsName.put(BINDING_ERROR, AlexaMetricsName.TextInteraction.Failure.BINDING_ERROR);
        textFailureReasonToMetricsName.put(LOCAL_SERVICE_DISCONNECTED, AlexaMetricsName.TextInteraction.Failure.LOCAL_SERVICE_DISCONNECTED);
        textFailureReasonToMetricsName.put(LEADER_SELECTION_ERROR, AlexaMetricsName.TextInteraction.Failure.LEADER_SELECTION_ERROR);
        textFailureReasonToMetricsName.put(LEADER_DISABLED_ERROR, AlexaMetricsName.TextInteraction.Failure.LEADER_DISABLED_ERROR);
        textFailureReasonToMetricsName.put(NETWORK_UNAVAILABLE, AlexaMetricsName.TextInteraction.Failure.NETWORK_UNAVAILABLE);
        textFailureReasonToMetricsName.put(AVS_UNAVAILABLE_MISSING, AlexaMetricsName.TextInteraction.Failure.AVS_UNAVAILABLE_MISSING);
        textFailureReasonToMetricsName.put(AVS_UNAVAILABLE_DOWNCHANNEL, AlexaMetricsName.TextInteraction.Failure.AVS_UNAVAILABLE_DOWNCHANNEL);
        textFailureReasonToMetricsName.put(AVS_ERROR, AlexaMetricsName.TextInteraction.Failure.AVS_ERROR);
        textFailureReasonToMetricsName.put(AVS_CONNECTION_TIMEOUT, AlexaMetricsName.TextInteraction.Failure.AVS_CONNECTION_TIMEOUT);
        textFailureReasonToMetricsName.put(AVS_CONNECTION_TIMEOUT_UNINITIALIZED, AlexaMetricsName.TextInteraction.Failure.AVS_CONNECTION_TIMEOUT_UNINITIALIZED);
        textFailureReasonToMetricsName.put(AVS_CONNECTION_TIMEOUT_UNAUTHORIZED, AlexaMetricsName.TextInteraction.Failure.AVS_CONNECTION_TIMEOUT_UNAUTHORIZED);
        textFailureReasonToMetricsName.put(AVS_CONNECTION_TIMEOUT_NETWORK, AlexaMetricsName.TextInteraction.Failure.AVS_CONNECTION_TIMEOUT_NETWORK);
        textFailureReasonToMetricsName.put(AVS_CONNECTION_TIMEOUT_CONNECTION_NOT_ESTABLISHED, AlexaMetricsName.TextInteraction.Failure.AVS_CONNECTION_TIMEOUT_CONNECTION_NOT_ESTABLISHED);
        textFailureReasonToMetricsName.put(AVS_CONNECTION_TIMEOUT_CONNECTION_NOT_CONNECTED, AlexaMetricsName.TextInteraction.Failure.AVS_CONNECTION_TIMEOUT_CONNECTION_NOT_CONNECTED);
        textFailureReasonToMetricsName.put(AVS_CONNECTION_TIMEOUT_DOWNCHANNEL_AVAILABLE, AlexaMetricsName.TextInteraction.Failure.AVS_CONNECTION_TIMEOUT_DOWNCHANNEL_AVAILABLE);
        textFailureReasonToMetricsName.put(AVS_CONNECTION_TIMEOUT_CAPABILITY_PUBLISH, AlexaMetricsName.TextInteraction.Failure.AVS_CONNECTION_TIMEOUT_CAPABILITY_PUBLISH);
        textFailureReasonToMetricsName.put(AVS_CONNECTION_TIMEOUT_SYNCHRONIZE_STATE, AlexaMetricsName.TextInteraction.Failure.AVS_CONNECTION_TIMEOUT_SYNCHRONIZE_STATE);
        textFailureReasonToMetricsName.put(TURN_TIMEOUT, AlexaMetricsName.TextInteraction.Failure.TURN_TIMEOUT);
        textFailureReasonToMetricsName.put(NETWORK_REQUEST_ERROR, AlexaMetricsName.TextInteraction.Failure.NETWORK_REQUEST_ERROR);
        textFailureReasonToMetricsName.put(RESPONSE_PARSING_ERROR_MULTIPART, AlexaMetricsName.TextInteraction.Failure.RESPONSE_PARSING_ERROR_MULTIPART);
        textFailureReasonToMetricsName.put(RESPONSE_PARSING_ERROR, AlexaMetricsName.TextInteraction.Failure.RESPONSE_PARSING_ERROR);
        textFailureReasonToMetricsName.put(REQUEST_PARSING_ERROR, AlexaMetricsName.TextInteraction.Failure.REQUEST_PARSING_ERROR);
        textFailureReasonToMetricsName.put(AUTHORIZATION_ERROR, AlexaMetricsName.TextInteraction.Failure.AUTHORIZATION_ERROR);
        textFailureReasonToMetricsName.put(INTERNAL_CLIENT_ERROR_CLIENT_TIMEOUT_EXCEEDED, AlexaMetricsName.TextInteraction.Failure.INTERNAL_CLIENT_ERROR_CLIENT_TIMEOUT_EXCEEDED);
        textFailureReasonToMetricsName.put(INTERNAL_CLIENT_ERROR_MESSAGE_TIMEOUT_EXCEEDED, AlexaMetricsName.TextInteraction.Failure.INTERNAL_CLIENT_ERROR_MESSAGE_TIMEOUT_EXCEEDED);
        textFailureReasonToMetricsName.put(INTERNAL_CLIENT_ERROR_CONCURRENT_REGISTER_ATTEMPT, AlexaMetricsName.TextInteraction.Failure.INTERNAL_CLIENT_ERROR_CONCURRENT_REGISTER_ATTEMPT);
        textFailureReasonToMetricsName.put(INTERNAL_CLIENT_ERROR_INCOMPLETE_INTERACTION, AlexaMetricsName.TextInteraction.Failure.INTERNAL_CLIENT_ERROR_INCOMPLETE_INTERACTION);
        textFailureReasonToMetricsName.put(INTERNAL_CLIENT_ERROR_UNKNOWN_FAILURE, AlexaMetricsName.TextInteraction.Failure.INTERNAL_CLIENT_ERROR_UNKNOWN_FAILURE);
        textFailureReasonToMetricsName.put(INTERNAL_CLIENT_ERROR_MESSAGING, AlexaMetricsName.TextInteraction.Failure.INTERNAL_CLIENT_ERROR_MESSAGING);
    }

    bij() {
    }

    @Override // com.amazon.alexa.pRk
    public Pmp getType() {
        return Pmp.FAILURE;
    }

    @Override // com.amazon.alexa.pRk
    public AlexaMetricsName zZm() {
        return this.isTextDialog ? textFailureReasonToMetricsName.get(this) : voiceFailureReasonToMetricsName.get(this);
    }

    @Override // com.amazon.alexa.pRk
    public void zZm(boolean z) {
        this.isTextDialog = z;
    }
}
