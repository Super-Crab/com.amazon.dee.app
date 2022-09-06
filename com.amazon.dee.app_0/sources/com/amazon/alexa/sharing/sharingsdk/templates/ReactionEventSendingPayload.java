package com.amazon.alexa.sharing.sharingsdk.templates;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.sharing.repo.models.acms.payload.sharing.ACMSPayloadReaction;
import com.amazon.alexa.sharing.repo.models.acms.payload.sharing.UpdateReactionEvent;
import com.amazon.alexa.sharing.sharingsdk.payload.ReactMessageMetadata;
import com.amazon.alexa.sharing.sharingsdk.payload.response.SendReactionResponsePayload;
import com.amazon.alexa.sharing.sharingsdk.payload.response.SharingSDKResponsePayload;
import com.google.gson.Gson;
import java.util.HashMap;
/* loaded from: classes10.dex */
public class ReactionEventSendingPayload implements SharedMessageTemplate {
    public static final String ADD_OPERATION = "ADD";
    public static final String ADD_REACTION_TYPE = "AddReactionEvent";
    public static final String REACTION_MESSAGE_TYPE = "reaction/default";
    public static final String REMOVE_OPERATION = "REMOVE";
    public static final String REMOVE_REACTION_TYPE = "RemoveReactionEvent";
    @NonNull
    private ReactMessageMetadata messageMetadata;
    @NonNull
    private String reactionType = "";
    @NonNull
    private String parentGlobalMessageId = "";
    @Nullable
    private String globalMessageId = "";

    public ReactionEventSendingPayload(@NonNull ReactMessageMetadata reactMessageMetadata) {
        this.messageMetadata = reactMessageMetadata;
    }

    public static ReactionEventSendingPayload createFromEventPayload(@NonNull ReactMessageMetadata reactMessageMetadata) {
        UpdateReactionEvent updateReactionEvent = (UpdateReactionEvent) new Gson().fromJson(reactMessageMetadata.getRawData(), (Class<Object>) UpdateReactionEvent.class);
        ReactionEventSendingPayload reactionEventSendingPayload = new ReactionEventSendingPayload(reactMessageMetadata);
        reactionEventSendingPayload.setGlobalMessageId(updateReactionEvent.getGlobalMessageId());
        reactionEventSendingPayload.setParentGlobalMessageId(updateReactionEvent.getParentGlobalMessageId());
        reactionEventSendingPayload.setReactionType(updateReactionEvent.getReactionType());
        return reactionEventSendingPayload;
    }

    @Nullable
    public String getGlobalMessageId() {
        return this.globalMessageId;
    }

    @Override // com.amazon.alexa.sharing.sharingsdk.templates.SharedMessageTemplate
    @NonNull
    public ReactMessageMetadata getMessageMetadata() {
        return this.messageMetadata;
    }

    @Override // com.amazon.alexa.sharing.sharingsdk.templates.SharedMessageTemplate
    public HashMap<String, String> getMetadata() {
        HashMap<String, String> map = this.messageMetadata.toMap();
        map.put("reactionId", map.get("templateKey"));
        map.remove("templateKey");
        map.put("globalMessageId", getGlobalMessageId());
        map.put("messageType", "reaction/default");
        map.put("parentGlobalMessageId", getParentGlobalMessageId());
        return map;
    }

    @NonNull
    public String getParentGlobalMessageId() {
        return this.parentGlobalMessageId;
    }

    @NonNull
    public String getReactionType() {
        return this.reactionType;
    }

    @Override // com.amazon.alexa.sharing.sharingsdk.templates.SharedMessageTemplate
    public String getTemplateName() {
        return this.messageMetadata.getTemplateName();
    }

    @Override // com.amazon.alexa.sharing.sharingsdk.templates.SharedMessageTemplate
    public boolean isValidTemplate() {
        return !getMessageMetadata().getGlobalMessageId().equals("") && !getMessageMetadata().getTemplateKey().equals("");
    }

    public void setGlobalMessageId(@Nullable String str) {
        this.globalMessageId = str;
    }

    public void setParentGlobalMessageId(@NonNull String str) {
        this.parentGlobalMessageId = str;
    }

    public void setReactionType(@NonNull String str) {
        this.reactionType = str;
    }

    @Override // com.amazon.alexa.sharing.sharingsdk.templates.SharedMessageTemplate
    public SharingSDKResponsePayload toResponsePayload() {
        SendReactionResponsePayload sendReactionResponsePayload = new SendReactionResponsePayload();
        sendReactionResponsePayload.name = getMessageMetadata().getTemplateName();
        ACMSPayloadReaction aCMSPayloadReaction = new ACMSPayloadReaction();
        aCMSPayloadReaction.setText(ACMSPayloadReaction.convertTypeToUnicode(getReactionType()));
        sendReactionResponsePayload.payload = aCMSPayloadReaction;
        sendReactionResponsePayload.metadata = getMetadata();
        sendReactionResponsePayload.metadata.put("status", "SUCCESS");
        return sendReactionResponsePayload;
    }
}
