package com.amazon.deecomms.notifications;
/* loaded from: classes12.dex */
public enum PushProcessStatus {
    CONTINUE,
    MISSING_APPLICATION_ID,
    MISSING_RECIPIENT,
    MISSING_TARGET,
    MISSING_AMAZON_PAYLOAD,
    SEPARATOR_FOR_PAYLOAD_AND_AMZN_MSG,
    MISSING_INCOMING_CALL_DURATION,
    MISSING_INCOMING_CALL_DROP_IN,
    MISSING_MISSED_CALL_DROP_IN,
    MISSING_TEXT_MESSAGE_TEXT,
    MISSING_AUDIO_MESSAGE_MEDIA_ID,
    IGNORING_REACTION_REMOVED,
    MISSING_CALLING_PUSH_PAYLOAD,
    MISSING_CALLING_TOKEN_VALUE,
    MISSING_CALLING_TOKEN_VERSION,
    MISSING_CALLING_TOKEN_INFO_VERSION,
    MISSING_CALLING_TOKEN_INFO,
    MISSING_SMS_RELAY_PUSH_PAYLOAD,
    MISSING_SMS_RELAY_CONVERSATION_ID,
    MISSING_SMS_RELAY_SENDER_COMMS_ID,
    MISSING_SMS_RELAY_RECIPIENT,
    SEPARATOR_FOR_PARSING_AND_FILTER,
    INVALID_RECIPIENT,
    UNSUPPORTED_TARGET,
    UNKNOWN_PUSH_TYPE,
    INVALID_APPLICATION_ID,
    INVALID_PUSH_PAYLOAD_DELETE_CONVERSATION,
    INVALID_PUSH_PAYLOAD_MESSAGE,
    INVALID_PUSH_PAYLOAD_READ_RECEIPT,
    INVALID_PUSH_PAYLOAD_TRANSCRIPTION_UPDATE,
    INVALID_SENDER,
    DUPLICATE_PUSH_MESSAGE,
    RECEIVED_FROM_SELF,
    HANDLED_ON_REACT_NATIVE_SIDE,
    IN_DRIVE_MODE
}
