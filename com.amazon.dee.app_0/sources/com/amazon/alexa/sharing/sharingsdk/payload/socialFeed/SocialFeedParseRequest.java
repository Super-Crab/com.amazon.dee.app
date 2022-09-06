package com.amazon.alexa.sharing.sharingsdk.payload.socialFeed;

import androidx.annotation.NonNull;
import com.amazon.alexa.sharing.Constants;
import com.amazon.comms.log.CommsLogger;
import com.google.gson.JsonObject;
/* loaded from: classes10.dex */
public class SocialFeedParseRequest {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, SocialFeedParseRequest.class);
    @NonNull
    public String clientMessageId;
    @NonNull
    public String conversationId;
    @NonNull
    public String globalMessageId;
    @NonNull
    public int messageId;
    @NonNull
    public JsonObject payload;
    @NonNull
    public String sender;
    @NonNull
    public String time;
    @NonNull
    public String type;

    public static SocialFeedParseRequest fromJson(@NonNull JsonObject jsonObject) {
        SocialFeedParseRequest socialFeedParseRequest = new SocialFeedParseRequest();
        try {
            socialFeedParseRequest.conversationId = jsonObject.get("conversationId").getAsString();
            try {
                socialFeedParseRequest.clientMessageId = jsonObject.get("clientMessageId").getAsString();
                try {
                    socialFeedParseRequest.messageId = jsonObject.get("messageId").getAsInt();
                    try {
                        socialFeedParseRequest.time = jsonObject.get("time").getAsString();
                        try {
                            socialFeedParseRequest.sender = jsonObject.get("sender").getAsString();
                            try {
                                socialFeedParseRequest.type = jsonObject.get("type").getAsString();
                                try {
                                    socialFeedParseRequest.payload = jsonObject.get("payload").getAsJsonObject();
                                    try {
                                        socialFeedParseRequest.globalMessageId = jsonObject.get("globalMessageId").getAsString();
                                        return socialFeedParseRequest;
                                    } catch (Exception unused) {
                                        LOG.e("Missing required `globalMessageId` field in request payload.");
                                        return null;
                                    }
                                } catch (Exception unused2) {
                                    LOG.e("Missing required `payload` field in request payload.");
                                    return null;
                                }
                            } catch (Exception unused3) {
                                LOG.e("Missing required `type` field in request payload.");
                                return null;
                            }
                        } catch (Exception unused4) {
                            LOG.e("Missing required `sender` field in request payload.");
                            return null;
                        }
                    } catch (Exception unused5) {
                        LOG.e("Missing required `time` field in request payload.");
                        return null;
                    }
                } catch (Exception unused6) {
                    LOG.e("Missing required `messageId` field in request payload.");
                    return null;
                }
            } catch (Exception unused7) {
                LOG.e("Missing required `clientMessageId` field in request payload.");
                return null;
            }
        } catch (Exception unused8) {
            LOG.e("Missing required `conversationId` field in request payload.");
            return null;
        }
    }
}
