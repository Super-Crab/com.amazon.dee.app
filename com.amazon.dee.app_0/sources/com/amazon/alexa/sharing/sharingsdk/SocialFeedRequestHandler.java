package com.amazon.alexa.sharing.sharingsdk;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.sharing.Constants;
import com.amazon.alexa.sharing.MetricKeys;
import com.amazon.alexa.sharing.R;
import com.amazon.alexa.sharing.api.models.Payload;
import com.amazon.alexa.sharing.comms.CommsMetricsEmitter;
import com.amazon.alexa.sharing.media.transmitter.MediaDownloadManager;
import com.amazon.alexa.sharing.repo.models.acms.payload.SharingMessagePayload;
import com.amazon.alexa.sharing.repo.models.acms.payload.sharing.GenericSharingMessageEventPayload;
import com.amazon.alexa.sharing.sharingsdk.payload.SharingPayloadError;
import com.amazon.alexa.sharing.sharingsdk.payload.socialFeed.SocialFeedMediaStatus;
import com.amazon.alexa.sharing.sharingsdk.payload.socialFeed.SocialFeedParseRequest;
import com.amazon.alexa.sharing.sharingsdk.payload.socialFeed.SocialFeedRenderingContent;
import com.amazon.alexa.sharing.sharingsdk.payload.socialFeed.SocialFeedRenderingMetadata;
import com.amazon.alexa.sharing.sharingsdk.payload.socialFeed.SocialFeedRenderingPayload;
import com.amazon.comms.log.CommsLogger;
import com.amazon.commscore.api.commsbridge.ResponseResolver;
import com.amazon.commscore.api.identity.AlexaCommsCoreIdentityService;
import com.amazon.dee.app.ui.web.AlexaDeviceBackgroundImageBridge;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerConstant;
import com.amazon.identity.auth.device.authorization.AuthorizationResponseParser;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Collections;
/* loaded from: classes10.dex */
public class SocialFeedRequestHandler {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, SocialFeedRequestHandler.class);
    private static final String SOCIAL_FEED_SOURCE = "SocialFeed";
    private final Context context;
    private final Gson gson = new Gson();
    private final AlexaCommsCoreIdentityService identityService;
    private final MediaDownloadManager mediaDownloadManager;
    private final CommsMetricsEmitter metricsEmitter;

    /* loaded from: classes10.dex */
    public enum SocialFeedParseStatus {
        SUCCESS,
        ERROR
    }

    public SocialFeedRequestHandler(CommsMetricsEmitter commsMetricsEmitter, Context context, MediaDownloadManager mediaDownloadManager, AlexaCommsCoreIdentityService alexaCommsCoreIdentityService) {
        this.metricsEmitter = commsMetricsEmitter;
        this.context = context;
        this.mediaDownloadManager = mediaDownloadManager;
        this.identityService = alexaCommsCoreIdentityService;
    }

    private SocialFeedRenderingContent getContentForSharedContentType(@NonNull SocialFeedParseRequest socialFeedParseRequest) {
        LOG.i("Parsing shared content for SocialFeed.");
        GenericSharingMessageEventPayload sharingMessage = ((SharingMessagePayload) this.gson.fromJson((JsonElement) socialFeedParseRequest.payload, (Class<Object>) SharingMessagePayload.class)).getSharingMessage();
        SocialFeedRenderingContent socialFeedRenderingContent = new SocialFeedRenderingContent();
        socialFeedRenderingContent.deeplinkURL = sharingMessage.getDeepLinkURL();
        socialFeedRenderingContent.imageURL = sharingMessage.getImageURL();
        socialFeedRenderingContent.title = sharingMessage.getName();
        return socialFeedRenderingContent;
    }

    private SocialFeedRenderingContent getContentForSharedMediaType(@NonNull SocialFeedParseRequest socialFeedParseRequest) {
        LOG.i("Parsing shared media for SocialFeed.");
        SocialFeedRenderingContent socialFeedRenderingContent = new SocialFeedRenderingContent();
        JsonObject asJsonObject = socialFeedParseRequest.payload.getAsJsonArray(Payload.SERIALIZED_NAME_ATTACHMENTS).get(0).getAsJsonObject();
        String asString = asJsonObject.get("type").getAsString();
        String localUrlForDownloadedMedia = this.mediaDownloadManager.getLocalUrlForDownloadedMedia(asJsonObject.get("attachmentId").getAsString(), Constants.THUMBNAIL_VIEWBOX.intValue(), Constants.DEFAULT_IMAGE_EXTENSION);
        if (this.mediaDownloadManager.fileExists(localUrlForDownloadedMedia)) {
            LOG.i("Media exists on device, returning local URL.");
            socialFeedRenderingContent.imageURL = localUrlForDownloadedMedia;
            socialFeedRenderingContent.mediaStatus = SocialFeedMediaStatus.AVAILABLE.status;
        } else {
            LOG.i("Media does not exist on device, initiating download.");
            initiateMediaDownload(socialFeedParseRequest, asJsonObject);
            socialFeedRenderingContent.imageURL = null;
            socialFeedRenderingContent.mediaStatus = SocialFeedMediaStatus.DOWNLOADING.status;
        }
        socialFeedRenderingContent.title = getTitleFromMediaType(asString);
        return socialFeedRenderingContent;
    }

    private SocialFeedRenderingPayload getRenderingPayloadForError(SharingPayloadError sharingPayloadError) {
        SocialFeedRenderingMetadata socialFeedRenderingMetadata = new SocialFeedRenderingMetadata();
        socialFeedRenderingMetadata.errorCode = sharingPayloadError.errorCode;
        socialFeedRenderingMetadata.exceptionSignature = sharingPayloadError.getErrorDescription();
        socialFeedRenderingMetadata.status = SocialFeedParseStatus.ERROR.name();
        return new SocialFeedRenderingPayload(null, socialFeedRenderingMetadata);
    }

    private String getSharingMessageType(@NonNull SocialFeedParseRequest socialFeedParseRequest) {
        try {
            return socialFeedParseRequest.payload.get("sharingMessage").getAsJsonObject().get("messageType").getAsString();
        } catch (Exception unused) {
            return null;
        }
    }

    private SocialFeedRenderingMetadata getSuccessMetadata(@NonNull SocialFeedParseRequest socialFeedParseRequest) {
        String str;
        SocialFeedRenderingMetadata socialFeedRenderingMetadata = new SocialFeedRenderingMetadata();
        String sharingMessageType = getSharingMessageType(socialFeedParseRequest);
        String str2 = null;
        try {
            str = this.identityService.getIdentity().getCommsId();
        } catch (Exception unused) {
            LOG.w("Can't get commsId from CommsCore identity service, expect RN to handle");
            str = null;
        }
        if (str == null || !str.equals("")) {
            str2 = str;
        } else {
            LOG.w("Got empty commsId from CommsCore identity service, expect RN to handle");
        }
        if (sharingMessageType == null) {
            sharingMessageType = socialFeedParseRequest.type;
        }
        socialFeedRenderingMetadata.messageType = sharingMessageType;
        String str3 = socialFeedParseRequest.globalMessageId;
        socialFeedRenderingMetadata.templateKey = str3;
        socialFeedRenderingMetadata.globalMessageId = str3;
        socialFeedRenderingMetadata.commsId = str2;
        socialFeedRenderingMetadata.conversationId = socialFeedParseRequest.conversationId;
        socialFeedRenderingMetadata.messageId = socialFeedParseRequest.messageId;
        socialFeedRenderingMetadata.status = SocialFeedParseStatus.SUCCESS.name();
        return socialFeedRenderingMetadata;
    }

    private String getTitleFromMediaType(String str) {
        char c;
        int hashCode = str.hashCode();
        if (hashCode != 69775675) {
            if (hashCode == 81665115 && str.equals("VIDEO")) {
                c = 1;
            }
            c = 65535;
        } else {
            if (str.equals("IMAGE")) {
                c = 0;
            }
            c = 65535;
        }
        if (c != 0) {
            if (c != 1) {
                return this.context.getString(R.string.shared_with_you_unknown_title);
            }
            return this.context.getString(R.string.shared_with_you_video_title);
        }
        return this.context.getString(R.string.shared_with_you_photo_title);
    }

    private void handleError(ResponseResolver responseResolver, SharingPayloadError sharingPayloadError) {
        recordParseErrorMetrics(sharingPayloadError.errorCode);
        responseResolver.resolve(this.gson.toJson(getRenderingPayloadForError(sharingPayloadError)));
    }

    private void initiateMediaDownload(@NonNull SocialFeedParseRequest socialFeedParseRequest, @NonNull JsonObject jsonObject) {
        JsonObject asJsonObject = jsonObject.get("metadata").getAsJsonObject();
        String asString = jsonObject.get("attachmentId").getAsString();
        String asString2 = asJsonObject.get(MessagingControllerConstant.MESSAGING_CONTROLLER_OWNER_ID_KEY).getAsString();
        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.addProperty("source", SOCIAL_FEED_SOURCE);
        jsonObject2.addProperty("globalMessageId", socialFeedParseRequest.globalMessageId);
        jsonObject2.addProperty("conversationId", socialFeedParseRequest.conversationId);
        jsonObject2.addProperty(AuthorizationResponseParser.CLIENT_ID_STATE, socialFeedParseRequest.clientMessageId);
        jsonObject2.addProperty("dimension", Constants.THUMBNAIL_DIMENSION);
        jsonObject2.addProperty(AlexaDeviceBackgroundImageBridge.KEY_NODE_ID, asString);
        jsonObject2.addProperty(MessagingControllerConstant.MESSAGING_CONTROLLER_OWNER_ID_KEY, asString2);
        jsonObject2.addProperty("isThumbnail", (Boolean) true);
        jsonObject2.addProperty("viewbox", Constants.THUMBNAIL_VIEWBOX);
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(jsonObject2);
        this.mediaDownloadManager.downloadFilesFromCDS(jsonArray, null);
    }

    private void recordParseErrorMetrics(String str) {
        String outline72 = GeneratedOutlineSupport1.outline72("alexa.sharing.socialFeed.parse.error.", str);
        this.metricsEmitter.recordOccurrenceMetric(MetricKeys.SOCIAL_FEED_PARSE_ERROR, Collections.EMPTY_MAP);
        this.metricsEmitter.recordOccurrenceMetric(outline72, Collections.EMPTY_MAP);
    }

    private void recordParseSuccessMetrics() {
        this.metricsEmitter.recordNonOccurrenceMetric(MetricKeys.SOCIAL_FEED_PARSE_ERROR, Collections.EMPTY_MAP);
        this.metricsEmitter.recordOccurrenceMetric(MetricKeys.SOCIAL_FEED_PARSE_SUCCESS, Collections.EMPTY_MAP);
    }

    public void onReceiveSocialFeedParseEvent(String str, ResponseResolver responseResolver) {
        SocialFeedRenderingContent contentForSharedMediaType;
        LOG.i("A new SocialFeed parse event has been received from react-native.");
        try {
            SocialFeedParseRequest fromJson = SocialFeedParseRequest.fromJson((JsonObject) this.gson.fromJson(str, (Class<Object>) JsonObject.class));
            if (fromJson == null) {
                handleError(responseResolver, SharingPayloadError.missingRequiredFieldDecodingError);
                return;
            }
            String str2 = fromJson.type;
            char c = 65535;
            int hashCode = str2.hashCode();
            if (hashCode != 1700385756) {
                if (hashCode == 2029986329 && str2.equals("message/shared-content")) {
                    c = 1;
                }
            } else if (str2.equals("message/media")) {
                c = 0;
            }
            if (c == 0) {
                try {
                    contentForSharedMediaType = getContentForSharedMediaType(fromJson);
                } catch (Exception e) {
                    LOG.e("Exception while parsing shared media payload.", e);
                    handleError(responseResolver, SharingPayloadError.sharedMediaDecodingError);
                    return;
                }
            } else if (c != 1) {
                CommsLogger commsLogger = LOG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Received the following unknown message type for social feed parsing: ");
                outline107.append(fromJson.type);
                commsLogger.e(outline107.toString());
                handleError(responseResolver, SharingPayloadError.unknownTypeDecodingError);
                return;
            } else {
                try {
                    contentForSharedMediaType = getContentForSharedContentType(fromJson);
                } catch (Exception e2) {
                    LOG.e("Exception while parsing shared content payload.", e2);
                    handleError(responseResolver, SharingPayloadError.sharedContentDecodingError);
                    return;
                }
            }
            contentForSharedMediaType.fromSender = fromJson.sender;
            contentForSharedMediaType.timestamp = fromJson.time;
            try {
                String json = this.gson.toJson(new SocialFeedRenderingPayload(contentForSharedMediaType, getSuccessMetadata(fromJson)));
                LOG.i("Successfully completed SocialFeed parse event, resolving to react-native");
                recordParseSuccessMetrics();
                responseResolver.resolve(json);
            } catch (Exception e3) {
                LOG.e("Exception while creating response object.", e3);
                handleError(responseResolver, SharingPayloadError.genericDecodingError);
            }
        } catch (Exception e4) {
            LOG.e("Exception while parsing request as JsonObject.", e4);
            handleError(responseResolver, SharingPayloadError.noPayloadData);
        }
    }
}
