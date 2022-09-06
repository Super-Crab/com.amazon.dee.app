package com.amazon.deecomms.sharing.templates;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.sharing.exceptions.SharingSDKException;
import com.amazon.deecomms.sharing.payload.eventBus.ShareRenderingTemplateEventBusPayload;
import com.amazon.deecomms.sharing.payload.eventBus.SharingEventBusPayload;
import com.amazon.deecomms.sharing.payload.parse.GenericSharingMessageEvent;
import com.amazon.deecomms.sharing.payload.parse.GenericSharingMessageEventPayload;
import com.amazon.deecomms.sharing.payload.parse.ReactMessageMetadata;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
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
    @Nullable
    String name;
    @Nullable
    String origin;

    public HeroImageWithCaptionTemplate(@NonNull ReactMessageMetadata reactMessageMetadata) {
        this(null, null, null, null, null, reactMessageMetadata);
    }

    private void emitImageURLMissingForSongMetric() {
        CounterMetric generateOperational = CounterMetric.generateOperational(MetricKeys.SHARING_SDK_SONG_PAYLOAD_MISSING_IMAGE_URL);
        generateOperational.setCounter(Double.valueOf(1.0d));
        Map<String, Object> metadata = generateOperational.getMetadata();
        metadata.put("source", getMessageMetadata().getTemplateName() + "::" + getMessageMetadata().getTemplateKey());
        metadata.put("requestId", getMessageMetadata().getGlobalMessageId());
        generateOperational.setMetadata(metadata);
        MetricsHelper.recordSingleOccurrence(generateOperational);
    }

    public static HeroImageWithCaptionTemplate parseJSONEventPayload(@NonNull ReactMessageMetadata reactMessageMetadata) throws SharingSDKException, JsonSyntaxException {
        GenericSharingMessageEvent genericSharingMessageEvent = (GenericSharingMessageEvent) new Gson().fromJson(reactMessageMetadata.getRawData(), (Class<Object>) GenericSharingMessageEvent.class);
        if (genericSharingMessageEvent != null) {
            return saveEventToTemplate(reactMessageMetadata, genericSharingMessageEvent);
        }
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("[Content-Sharing-Service] GSON Serialized Class does not match the GenericSharingMessageEvent structure. This payload with this sharing IDcannot be rendered in this app: ");
        outline1.append(reactMessageMetadata.getGlobalMessageId());
        commsLogger.e(outline1.toString());
        throw new JsonSyntaxException("GSON Class Serialization Failure for HeroImageWithCaption template");
    }

    private void processLinkPayload(GenericSharingMessageEventPayload genericSharingMessageEventPayload) {
        setImageURL(null);
        setOrigin(null);
        setName(genericSharingMessageEventPayload.getText());
        setDescription(genericSharingMessageEventPayload.getDescription());
        String text = genericSharingMessageEventPayload.getText();
        if (genericSharingMessageEventPayload.getAction() != null && genericSharingMessageEventPayload.getAction().getAndroidEntryData() != null) {
            text = genericSharingMessageEventPayload.getAction().getAndroidEntryData().getUrl().getUrl();
        }
        setDeepLinkURL(text);
    }

    @VisibleForTesting
    public static HeroImageWithCaptionTemplate saveEventToTemplate(ReactMessageMetadata reactMessageMetadata, @NonNull GenericSharingMessageEvent genericSharingMessageEvent) throws SharingSDKException {
        HeroImageWithCaptionTemplate heroImageWithCaptionTemplate = new HeroImageWithCaptionTemplate(reactMessageMetadata);
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("[Content-Sharing-Service] Parsed a HeroImageWithCaptionTemplate with Key: ");
        outline1.append(reactMessageMetadata.getTemplateKey());
        commsLogger.d(outline1.toString());
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

    @Override // com.amazon.deecomms.sharing.templates.SharedMessageTemplate
    @NonNull
    public ReactMessageMetadata getMessageMetadata() {
        return this.messageMetadata;
    }

    @Override // com.amazon.deecomms.sharing.templates.SharedMessageTemplate
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

    @Override // com.amazon.deecomms.sharing.templates.SharedMessageTemplate
    public String getTemplateName() {
        return "HeroImageWithCaptionTemplate";
    }

    @Override // com.amazon.deecomms.sharing.templates.SharedMessageTemplate
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

    public void setName(@Nullable String str) {
        this.name = str;
    }

    public void setOrigin(@Nullable String str) {
        this.origin = str;
    }

    @Override // com.amazon.deecomms.sharing.templates.SharedMessageTemplate
    public SharingEventBusPayload toEventBusPayload() {
        ShareRenderingTemplateEventBusPayload shareRenderingTemplateEventBusPayload = new ShareRenderingTemplateEventBusPayload();
        if ("alexa-music".equals(getMessageMetadata().getDomainType()) && (getImageURL() == null || getImageURL().isEmpty())) {
            emitImageURLMissingForSongMetric();
        }
        shareRenderingTemplateEventBusPayload.name = "HeroImageWithCaptionTemplate";
        shareRenderingTemplateEventBusPayload.content = getContent();
        shareRenderingTemplateEventBusPayload.metadata = getMetadata();
        shareRenderingTemplateEventBusPayload.metadata.put("status", "SUCCESS");
        LOG.d("[Content-Sharing-Service] Created a success payload for globalMessageId:", this.messageMetadata.getGlobalMessageId());
        return shareRenderingTemplateEventBusPayload;
    }

    public String toString() {
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("");
        outline1.append(getTemplateName());
        outline1.append(" {templateKey='");
        outline1.append(getMessageMetadata().getTemplateKey());
        outline1.append("', name='");
        outline1.append(this.name);
        outline1.append("', descr='");
        outline1.append(this.description);
        outline1.append("', origin='");
        outline1.append(this.origin);
        outline1.append("', imageURL='");
        outline1.append(this.imageURL);
        outline1.append("', deepLinkURL='");
        return GeneratedOutlineSupport1.outline91(outline1, this.deepLinkURL, "'}");
    }

    @NotNull
    public void updateTemplateForPayload(@Nullable GenericSharingMessageEventPayload genericSharingMessageEventPayload) throws SharingSDKException {
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
        throw new SharingSDKException("Invalid Template: Template expected an internal 'payload' attribute.", "payload");
    }

    public HeroImageWithCaptionTemplate(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @NonNull ReactMessageMetadata reactMessageMetadata) {
        this.name = str;
        this.description = str2;
        this.origin = str3;
        this.imageURL = str4;
        this.deepLinkURL = str5;
        this.messageMetadata = reactMessageMetadata;
    }
}
