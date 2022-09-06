package com.amazon.deecomms.sharing.payload.parse;

import android.content.Context;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.nativemodules.imagepicker.util.Compression;
import com.amazon.deecomms.sharing.exceptions.SharingSDKException;
import com.amazon.deecomms.sharing.photos.CommonContentProperties;
import com.amazon.deecomms.sharing.templates.PhotoTemplate;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.Objects;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes12.dex */
public class PhotoSharingMessageEvent {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, PhotoTemplate.class);
    @Nullable
    private String globalMessageId;
    @Nullable
    private String name;
    @Nullable
    private PhotoSharingMessageEventPayload payload;
    @Nullable
    private String templateKey;

    public PhotoSharingMessageEvent() {
        this(null, null, null, null);
    }

    public static PhotoSharingMessageEventPayload getPhotoSharingEventPayloadForPath(@NonNull String str, @NonNull Compression compression) throws IOException, SharingSDKException {
        return new PhotoSharingMessageEventPayload(str, compression.getCommonContentProperties(Uri.parse(str)));
    }

    public static PhotoSharingMessageEvent parseContentUri(@NonNull ReactMessageMetadata reactMessageMetadata, @NonNull Context context, @NonNull Compression compression) throws IOException, SharingSDKException {
        return new PhotoSharingMessageEvent(getPhotoSharingEventPayloadForPath(compression.resolveRealPath(reactMessageMetadata.getRawData(), context, Uri.parse(reactMessageMetadata.getRawData()), false), compression), reactMessageMetadata.getTemplateName(), reactMessageMetadata.getGlobalMessageId(), reactMessageMetadata.getTemplateKey());
    }

    public static PhotoSharingMessageEvent parseJson(@NonNull ReactMessageMetadata reactMessageMetadata, @NonNull Context context, @NonNull Compression compression) throws IOException, SharingSDKException {
        PhotoSharingMessageEvent photoSharingMessageEvent = (PhotoSharingMessageEvent) new Gson().fromJson(reactMessageMetadata.getRawData(), (Class<Object>) PhotoSharingMessageEvent.class);
        if (photoSharingMessageEvent != null) {
            updateContentPropertiesIfUnknown(photoSharingMessageEvent, context, compression);
            return photoSharingMessageEvent;
        }
        LOG.e("[Content-Sharing-Service] GSON Serialized Class does not match the SharingMessage payload. The payload with this metadata cannot be rendered in this app: ", reactMessageMetadata);
        CommsLogger commsLogger = LOG;
        commsLogger.d("[Content-Sharing-Service] GSON Serialized Class does not match the SharingMessage payload. The payload with this metadata cannot be rendered in this app: ", commsLogger.sensitive(reactMessageMetadata.getRawData()));
        throw new SharingSDKException("GSON Class Serialization failed", "json");
    }

    private static void updateContentPropertiesIfUnknown(@NonNull PhotoSharingMessageEvent photoSharingMessageEvent, @NonNull Context context, @NonNull Compression compression) throws IOException, SharingSDKException {
        CommonContentProperties contentProperties;
        PhotoSharingMessageEventPayload payload = photoSharingMessageEvent.getPayload();
        if (payload == null || (contentProperties = payload.getContentProperties()) == null || !"unknown/permission-denied".equals(contentProperties.getMimeType())) {
            return;
        }
        photoSharingMessageEvent.setPayload(getPhotoSharingEventPayloadForPath(payload.getContentUri(), compression));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || PhotoSharingMessageEvent.class != obj.getClass()) {
            return false;
        }
        PhotoSharingMessageEvent photoSharingMessageEvent = (PhotoSharingMessageEvent) obj;
        return Objects.equals(this.payload, photoSharingMessageEvent.payload) && Objects.equals(this.name, photoSharingMessageEvent.name) && Objects.equals(this.globalMessageId, photoSharingMessageEvent.globalMessageId) && Objects.equals(this.templateKey, photoSharingMessageEvent.templateKey);
    }

    public String getGlobalMessageId() {
        return this.globalMessageId;
    }

    public String getName() {
        return this.name;
    }

    @Nullable
    public PhotoSharingMessageEventPayload getPayload() {
        return this.payload;
    }

    public String getTemplateKey() {
        return this.templateKey;
    }

    public int hashCode() {
        return Objects.hash(this.payload, this.name, this.globalMessageId, this.templateKey);
    }

    public void setGlobalMessageId(String str) {
        this.globalMessageId = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setPayload(PhotoSharingMessageEventPayload photoSharingMessageEventPayload) {
        this.payload = photoSharingMessageEventPayload;
    }

    public void setTemplateKey(String str) {
        this.templateKey = str;
    }

    public String toString() {
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("PhotoSharingMessageEvent{name='");
        GeneratedOutlineSupport1.outline176(outline1, this.name, Chars.QUOTE, ", templateKey='");
        GeneratedOutlineSupport1.outline176(outline1, this.templateKey, Chars.QUOTE, ", globalMessageId='");
        GeneratedOutlineSupport1.outline176(outline1, this.globalMessageId, Chars.QUOTE, ", payload=");
        outline1.append(this.payload);
        outline1.append(JsonReaderKt.END_OBJ);
        return outline1.toString();
    }

    public PhotoSharingMessageEvent(@Nullable PhotoSharingMessageEventPayload photoSharingMessageEventPayload, @Nullable String str, @Nullable String str2, @Nullable String str3) {
        this.payload = photoSharingMessageEventPayload;
        this.name = str;
        this.globalMessageId = str2;
        this.templateKey = str3;
    }
}
