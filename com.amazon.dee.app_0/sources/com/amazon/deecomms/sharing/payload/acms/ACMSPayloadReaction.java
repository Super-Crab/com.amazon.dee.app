package com.amazon.deecomms.sharing.payload.acms;

import com.amazon.clouddrive.extended.model.ReactionKind;
import com.amazon.deecomms.sharing.payload.parse.Action;
import com.amazon.deecomms.sharing.templates.ReactionEventSendingPayload;
/* loaded from: classes12.dex */
public class ACMSPayloadReaction {
    public static final String DEFAULT_REACTION_PAYLOAD_MESSAGE_TYPE = "reaction/default";
    public static final String SHARED_CONTENT_MESSAGE_TYPE = "message/shared-content";
    public static final String SHARED_MESSAGE_TYPE = "message/shared-message";
    public static final String SHOPPING_LIST_MESSAGE_TYPE = "alexa-shopping/shoppingList";
    public static final String SONG_MESSAGE_TYPE = "alexa-music/song";
    private Action action;
    private String messageType;
    private String text;
    private String type;

    public ACMSPayloadReaction() {
        this.type = "message/shared-message";
        this.messageType = "reaction/default";
        this.text = "";
        this.action = Action.withNoTargets();
    }

    public static String convertTypeToUnicode(String str) {
        return str.equals(ReactionKind.LIKE) ? "❤️" : str.equals(ReactionKind.SURPRISE) ? "😮" : str.equals(ReactionKind.LAUGH) ? "😂" : "";
    }

    public Action getAction() {
        return this.action;
    }

    public String getMessageType() {
        return this.messageType;
    }

    public String getText() {
        return this.text;
    }

    public String getType() {
        return this.type;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public void setText(String str) {
        this.text = str;
    }

    public ACMSPayloadReaction(ReactionEventSendingPayload reactionEventSendingPayload) {
        this.type = "message/shared-message";
        this.messageType = "reaction/default";
        this.text = convertTypeToUnicode(reactionEventSendingPayload.getReactionType());
        this.action = Action.withNoTargets();
    }
}
