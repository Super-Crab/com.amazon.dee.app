package com.amazon.alexa.sharing.sharingsdk.templates;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.sharing.Constants;
import com.amazon.alexa.sharing.MetricKeys;
import com.amazon.alexa.sharing.api.exceptions.AlexaSharingSDKException;
import com.amazon.alexa.sharing.comms.CommsMetricsEmitter;
import com.amazon.alexa.sharing.repo.models.acms.payload.sharing.GenericSharingMessageEvent;
import com.amazon.alexa.sharing.repo.models.acms.payload.sharing.GenericSharingMessageEventPayload;
import com.amazon.alexa.sharing.sharingsdk.payload.ReactMessageMetadata;
import com.amazon.alexa.sharing.sharingsdk.payload.response.ShareRenderingTemplateResponsePayload;
import com.amazon.alexa.sharing.sharingsdk.payload.response.SharingSDKResponsePayload;
import com.amazon.comms.log.CommsLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.util.HashMap;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes10.dex */
public class HeroImageWithCaptionTemplate implements SharedMessageTemplate {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, HeroImageWithCaptionTemplate.class);
    public static final String TYPE = "HeroImageWithCaptionTemplate";
    @Nullable
    String deepLinkURL;
    @Nullable
    String description;
    @Nullable
    String imageURL;
    @NonNull
    ReactMessageMetadata messageMetadata;
    private CommsMetricsEmitter metricsEmitter;
    @Nullable
    String name;
    @Nullable
    String origin;

    public HeroImageWithCaptionTemplate(@NonNull ReactMessageMetadata reactMessageMetadata) {
        this(null, null, null, null, null, reactMessageMetadata);
    }

    private void emitImageURLMissingForSongMetric() {
        HashMap hashMap = new HashMap();
        hashMap.put("sourceContext", getMessageMetadata().getTemplateName() + "::" + getMessageMetadata().getTemplateKey());
        hashMap.put("requestId", getMessageMetadata().getGlobalMessageId());
        this.metricsEmitter.recordOccurrenceMetric(MetricKeys.SHARING_SDK_SONG_PAYLOAD_MISSING_IMAGE_URL, hashMap);
    }

    public static HeroImageWithCaptionTemplate parseJSONEventPayload(@NonNull ReactMessageMetadata reactMessageMetadata) throws AlexaSharingSDKException, JsonSyntaxException {
        GenericSharingMessageEvent genericSharingMessageEvent = (GenericSharingMessageEvent) new Gson().fromJson(reactMessageMetadata.getRawData(), (Class<Object>) GenericSharingMessageEvent.class);
        if (genericSharingMessageEvent != null) {
            return saveEventToTemplate(reactMessageMetadata, genericSharingMessageEvent);
        }
        CommsLogger commsLogger = LOG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[Content-Sharing-Service] GSON Serialized Class does not match the GenericSharingMessageEvent structure. This payload with this sharing IDcannot be rendered in this app: ");
        outline107.append(reactMessageMetadata.getGlobalMessageId());
        commsLogger.e(outline107.toString());
        throw new JsonSyntaxException("GSON Class Serialization Failure for HeroImageWithCaption template");
    }

    @VisibleForTesting
    public static HeroImageWithCaptionTemplate saveEventToTemplate(ReactMessageMetadata reactMessageMetadata, @NonNull GenericSharingMessageEvent genericSharingMessageEvent) throws AlexaSharingSDKException {
        HeroImageWithCaptionTemplate heroImageWithCaptionTemplate = new HeroImageWithCaptionTemplate(reactMessageMetadata);
        CommsLogger commsLogger = LOG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[Content-Sharing-Service] Parsed a HeroImageWithCaptionTemplate with Key: ");
        outline107.append(reactMessageMetadata.getTemplateKey());
        commsLogger.d(outline107.toString());
        heroImageWithCaptionTemplate.updateTemplateForPayload(genericSharingMessageEvent.getPayload());
        return heroImageWithCaptionTemplate;
    }

    public HashMap<String, Object> getContent() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("name", getName());
        hashMap.put("description", getDescription());
        hashMap.put("origin", getOrigin());
        hashMap.put("imageURL", getImageURL());
        hashMap.put("deeplinkURL", getDeepLinkURL());
        return hashMap;
    }

    @Nullable
    public String getDeepLinkURL() {
        return this.deepLinkURL;
    }

    @Nullable
    public String getDescription() {
        return this.description;
    }

    @Nullable
    public String getImageURL() {
        return this.imageURL;
    }

    @Override // com.amazon.alexa.sharing.sharingsdk.templates.SharedMessageTemplate
    @NonNull
    public ReactMessageMetadata getMessageMetadata() {
        return this.messageMetadata;
    }

    @Override // com.amazon.alexa.sharing.sharingsdk.templates.SharedMessageTemplate
    public HashMap<String, String> getMetadata() {
        return this.messageMetadata.toMap();
    }

    @Nullable
    public String getName() {
        return this.name;
    }

    @Nullable
    public String getOrigin() {
        return this.origin;
    }

    @Override // com.amazon.alexa.sharing.sharingsdk.templates.SharedMessageTemplate
    public String getTemplateName() {
        return "HeroImageWithCaptionTemplate";
    }

    @Override // com.amazon.alexa.sharing.sharingsdk.templates.SharedMessageTemplate
    public boolean isValidTemplate() {
        String str = this.name;
        return str != null && !str.isEmpty();
    }

    public void setDeepLinkURL(@Nullable String str) {
        this.deepLinkURL = str;
    }

    public void setDescription(@Nullable String str) {
        this.description = str;
    }

    public void setImageURL(@Nullable String str) {
        this.imageURL = str;
    }

    public void setMessageMetadata(@NonNull ReactMessageMetadata reactMessageMetadata) {
        this.messageMetadata = reactMessageMetadata;
    }

    public void setMetricsEmitter(CommsMetricsEmitter commsMetricsEmitter) {
        this.metricsEmitter = commsMetricsEmitter;
    }

    public void setName(@Nullable String str) {
        this.name = str;
    }

    public void setOrigin(@Nullable String str) {
        this.origin = str;
    }

    @Override // com.amazon.alexa.sharing.sharingsdk.templates.SharedMessageTemplate
    public SharingSDKResponsePayload toResponsePayload() {
        ShareRenderingTemplateResponsePayload shareRenderingTemplateResponsePayload = new ShareRenderingTemplateResponsePayload();
        if ("alexa-music".equals(getMessageMetadata().getDomainType()) && (getImageURL() == null || getImageURL().isEmpty())) {
            emitImageURLMissingForSongMetric();
        }
        shareRenderingTemplateResponsePayload.name = "HeroImageWithCaptionTemplate";
        shareRenderingTemplateResponsePayload.content = getContent();
        shareRenderingTemplateResponsePayload.metadata = getMetadata();
        shareRenderingTemplateResponsePayload.metadata.put("status", "SUCCESS");
        LOG.d("[Content-Sharing-Service] Created a success payload for globalMessageId:", this.messageMetadata.getGlobalMessageId());
        return shareRenderingTemplateResponsePayload;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("");
        outline107.append(getTemplateName());
        outline107.append(" {templateKey='");
        outline107.append(getMessageMetadata().getTemplateKey());
        outline107.append("', name='");
        outline107.append(this.name);
        outline107.append("', descr='");
        outline107.append(this.description);
        outline107.append("', origin='");
        outline107.append(this.origin);
        outline107.append("', imageURL='");
        outline107.append(this.imageURL);
        outline107.append("', deepLinkURL='");
        return GeneratedOutlineSupport1.outline91(outline107, this.deepLinkURL, "'}");
    }

    @NotNull
    public void updateTemplateForPayload(@Nullable GenericSharingMessageEventPayload genericSharingMessageEventPayload) throws AlexaSharingSDKException {
        if (genericSharingMessageEventPayload != null) {
            setName(genericSharingMessageEventPayload.getText());
            if (genericSharingMessageEventPayload.getName() != null && !genericSharingMessageEventPayload.getName().isEmpty()) {
                setName(genericSharingMessageEventPayload.getName());
            }
            setDescription(genericSharingMessageEventPayload.getDescription());
            setOrigin(genericSharingMessageEventPayload.getOrigin());
            setDeepLinkURL(genericSharingMessageEventPayload.getDeepLinkURL());
            String imageURL = genericSharingMessageEventPayload.getImageURL();
            if (imageURL == null || imageURL.isEmpty()) {
                return;
            }
            setImageURL(genericSharingMessageEventPayload.getImageURL());
            return;
        }
        throw new AlexaSharingSDKException("Invalid Template: Template expected an internal 'payload' attribute.", "payload");
    }

    public HeroImageWithCaptionTemplate(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @NonNull ReactMessageMetadata reactMessageMetadata) {
        this.name = str;
        this.description = str2;
        this.origin = str3;
        this.imageURL = str4;
        this.deepLinkURL = str5;
        this.messageMetadata = reactMessageMetadata;
        this.metricsEmitter = new CommsMetricsEmitter("HeroImageWithCaptionTemplate");
    }
}
