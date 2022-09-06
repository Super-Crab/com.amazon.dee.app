package com.amazon.deecomms.notifications.filters;

import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.processor.Task;
import com.amazon.deecomms.common.util.JacksonJSONConverter;
import com.amazon.deecomms.messaging.model.Message;
import com.amazon.deecomms.messaging.model.payload.AudioMessagePayload;
import com.amazon.deecomms.messaging.model.payload.CallEventPayload;
import com.amazon.deecomms.messaging.model.payload.ReactionEventMessagePayload;
import com.amazon.deecomms.messaging.model.payload.TextMessagePayload;
import com.amazon.deecomms.notifications.PushProcessStatus;
import com.amazon.deecomms.notifications.PushTypeHelper;
import com.amazon.deecomms.notifications.model.ConversationDeletePush;
import com.amazon.deecomms.notifications.model.ReadReceiptPush;
import com.amazon.deecomms.notifications.model.TranscriptUpdatePush;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
/* loaded from: classes12.dex */
public class ValidatePayloadAndAvoidDuplicationFilter implements Task<PushProcessStatus, Bundle> {
    private static final int NOTIFICATION_HISTORY_MAXIMUM_SIZE = 1024;
    private static final int NOTIFICATION_HISTORY_MESSAGE_TIME_TO_LIVE_IN_MINS = 120;
    private static final Object sDummyHolder = new Object();
    private final Cache<String, Object> sNotificationHistory = CacheBuilder.newBuilder().maximumSize(1024).expireAfterWrite(120, TimeUnit.MINUTES).build();

    @Override // com.amazon.deecomms.common.processor.Task
    public PushProcessStatus execute(Bundle bundle) {
        String str;
        JacksonJSONConverter jacksonJSONConverter = new JacksonJSONConverter();
        String string = bundle.getString(Constants.AMP_KEY);
        PushTypeHelper.PushType determineType = PushTypeHelper.determineType(string);
        if (determineType == PushTypeHelper.PushType.Message) {
            Message message = (Message) jacksonJSONConverter.fromJson(string, Message.class);
            if (message == null) {
                return PushProcessStatus.INVALID_PUSH_PAYLOAD_MESSAGE;
            }
            HashMap hashMap = (HashMap) jacksonJSONConverter.fromJson(string, new TypeReference<HashMap<String, Object>>() { // from class: com.amazon.deecomms.notifications.filters.ValidatePayloadAndAvoidDuplicationFilter.1
            });
            String type = message.getType();
            char c = 65535;
            switch (type.hashCode()) {
                case -1330413003:
                    if (type.equals("message/text")) {
                        c = 0;
                        break;
                    }
                    break;
                case -966584834:
                    if (type.equals("event/reaction")) {
                        c = 4;
                        break;
                    }
                    break;
                case 849467995:
                    if (type.equals("event/missed-call")) {
                        c = 1;
                        break;
                    }
                    break;
                case 939518131:
                    if (type.equals("event/call")) {
                        c = 2;
                        break;
                    }
                    break;
                case 1689780174:
                    if (type.equals("message/audio")) {
                        c = 3;
                        break;
                    }
                    break;
            }
            if (c != 0) {
                if (c != 1) {
                    if (c == 2) {
                        CallEventPayload callEventPayload = (CallEventPayload) message.getPayload();
                        if (!((HashMap) hashMap.get("payload")).containsKey("dropIn")) {
                            return PushProcessStatus.MISSING_INCOMING_CALL_DROP_IN;
                        }
                        if (callEventPayload.getDuration() == -1) {
                            return PushProcessStatus.MISSING_INCOMING_CALL_DURATION;
                        }
                    } else if (c != 3) {
                        if (c == 4 && ((ReactionEventMessagePayload) message.getPayload()).getWasRemoved().booleanValue()) {
                            return PushProcessStatus.IGNORING_REACTION_REMOVED;
                        }
                    } else if (TextUtils.isEmpty(((AudioMessagePayload) message.getPayload()).getMediaId())) {
                        return PushProcessStatus.MISSING_AUDIO_MESSAGE_MEDIA_ID;
                    }
                } else if (!((HashMap) hashMap.get("payload")).containsKey("dropIn")) {
                    return PushProcessStatus.MISSING_MISSED_CALL_DROP_IN;
                }
            } else if (TextUtils.isEmpty(((TextMessagePayload) message.getPayload()).getText())) {
                return PushProcessStatus.MISSING_TEXT_MESSAGE_TEXT;
            }
            str = String.format("%s_%s_%s_%s_%s", PushTypeHelper.PushType.Message, message.getConversationId(), Long.valueOf(message.getSequenceId()), bundle.getString(Constants.BUNDLE_KEY_NOTIFICATION_RECIPIENT), bundle.getString("target"));
        } else if (determineType == PushTypeHelper.PushType.ConversationDelete) {
            ConversationDeletePush conversationDeletePush = (ConversationDeletePush) jacksonJSONConverter.fromJson(string, ConversationDeletePush.class);
            if (conversationDeletePush != null && !TextUtils.isEmpty(conversationDeletePush.getConversationId()) && !TextUtils.isEmpty(conversationDeletePush.getUserCommsId())) {
                String.format("%s_%s_%s", PushTypeHelper.PushType.ConversationDelete, conversationDeletePush.getConversationId(), conversationDeletePush.getUserCommsId());
                return PushProcessStatus.CONTINUE;
            }
            return PushProcessStatus.INVALID_PUSH_PAYLOAD_DELETE_CONVERSATION;
        } else if (determineType == PushTypeHelper.PushType.ReadReceipt) {
            ReadReceiptPush readReceiptPush = (ReadReceiptPush) jacksonJSONConverter.fromJson(string, ReadReceiptPush.class);
            if (readReceiptPush != null && !TextUtils.isEmpty(readReceiptPush.getUserCommsId()) && !TextUtils.isEmpty(readReceiptPush.getConversationId())) {
                str = String.format("%s_%s_%s_%d", PushTypeHelper.PushType.ReadReceipt, readReceiptPush.getUserCommsId(), readReceiptPush.getConversationId(), Long.valueOf(readReceiptPush.getMessageId()));
            } else {
                return PushProcessStatus.INVALID_PUSH_PAYLOAD_READ_RECEIPT;
            }
        } else if (determineType == PushTypeHelper.PushType.TranscriptUpdate) {
            TranscriptUpdatePush transcriptUpdatePush = (TranscriptUpdatePush) jacksonJSONConverter.fromJson(string, TranscriptUpdatePush.class);
            if (transcriptUpdatePush != null && !TextUtils.isEmpty(transcriptUpdatePush.getConversationId())) {
                str = String.format("%s_%s_%d_%d_%s", PushTypeHelper.PushType.TranscriptUpdate, transcriptUpdatePush.getConversationId(), Long.valueOf(transcriptUpdatePush.getMessageId()), Long.valueOf(transcriptUpdatePush.getSequenceId()), transcriptUpdatePush.getTranscriptStatus());
            } else {
                return PushProcessStatus.INVALID_PUSH_PAYLOAD_TRANSCRIPTION_UPDATE;
            }
        } else {
            str = null;
        }
        if (!TextUtils.isEmpty(str)) {
            if (this.sNotificationHistory.getIfPresent(str) != null) {
                return PushProcessStatus.DUPLICATE_PUSH_MESSAGE;
            }
            this.sNotificationHistory.put(str, sDummyHolder);
        }
        return PushProcessStatus.CONTINUE;
    }
}
