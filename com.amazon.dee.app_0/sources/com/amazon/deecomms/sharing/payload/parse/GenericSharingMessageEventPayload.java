package com.amazon.deecomms.sharing.payload.parse;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.nativemodules.imagepicker.util.Compression;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.util.Objects;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes12.dex */
public class GenericSharingMessageEventPayload {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, GenericSharingMessageEventPayload.class);
    private String type = null;
    private String messageType = null;
    private String name = null;
    private String description = null;
    private URLHolder imageUrl = null;
    private Action action = null;
    private String text = null;

    /* loaded from: classes12.dex */
    public static class Builder {
        private Action action = null;
        private String text = null;
        private String name = null;
        private String description = null;
        private URLHolder imageUrl = null;
        private String type = null;
        private String messageType = null;

        @NonNull
        public GenericSharingMessageEventPayload build() {
            GenericSharingMessageEventPayload genericSharingMessageEventPayload = new GenericSharingMessageEventPayload();
            genericSharingMessageEventPayload.setType(this.type);
            genericSharingMessageEventPayload.setMessageType(this.messageType);
            genericSharingMessageEventPayload.setName(this.name);
            genericSharingMessageEventPayload.setText(this.text);
            genericSharingMessageEventPayload.setDescription(this.description);
            genericSharingMessageEventPayload.setImageUrl(this.imageUrl);
            genericSharingMessageEventPayload.setAction(this.action);
            return genericSharingMessageEventPayload;
        }

        @NonNull
        public Builder setDescription(@NonNull String str) {
            this.description = str;
            return this;
        }

        @NonNull
        public Builder setImageUrl(String str) {
            this.imageUrl = new URLHolder();
            this.imageUrl.setUrl(str);
            return this;
        }

        @NonNull
        public Builder setMessageType(String str) {
            this.messageType = str;
            return this;
        }

        @NonNull
        public Builder setName(@NonNull String str) {
            this.name = str;
            return this;
        }

        @NonNull
        public Builder setText(@NonNull String str) {
            this.text = str;
            return this;
        }

        public Builder setType(String str) {
            this.type = str;
            return this;
        }

        public Builder withDeepLinkAction(String str, String str2) {
            this.action = Action.withMobileDeepLink(str, str2);
            return this;
        }

        public Builder withNoTargets() {
            this.action = Action.withNoTargets();
            return this;
        }
    }

    public static GenericSharingMessageEventPayload fromAndroidIntentExtras(Bundle bundle, Context context, Compression compression) {
        String string = bundle.getString("android.intent.extra.TEXT");
        String string2 = bundle.getString("android.intent.extra.SUBJECT");
        String validPathFromShareIntentUri = getValidPathFromShareIntentUri(context, compression, (Uri) bundle.get("share_screenshot_as_stream"));
        Builder builder = new Builder();
        return builder.setText(string2 + " " + string).setName(string2).setDescription("This is a test where i make use of the description...").setMessageType("text/html").setImageUrl(validPathFromShareIntentUri).withDeepLinkAction(string, "AlexaMobileAndroidSharing - SDK 1.0.0").setType("message/shared-message").setMessageType("message/shared-content").build();
    }

    private String getOriginInternal() {
        Action action = this.action;
        if (action == null) {
            LOG.d("[Content-Sharing:Origin] Action invalid: The <action> key is null");
            return null;
        }
        EntryData androidEntryData = action.getAndroidEntryData();
        if (androidEntryData == null) {
            LOG.d("[Content-Sharing:Origin] EntryData nil: The android entryData was not found");
            return null;
        }
        URLHolder url = androidEntryData.getUrl();
        if (url == null) {
            LOG.d("[Content-Sharing:Origin] EntryData invalid: The android entryData did not contain a <url> key");
            return null;
        }
        String origin = url.getOrigin();
        if (origin != null) {
            return origin;
        }
        LOG.d("[Content-Sharing:Origin] EntryData invalid: The android entryData.url object has no <url> key");
        return null;
    }

    private static String getValidPathFromShareIntentUri(@NonNull Context context, @NonNull Compression compression, @Nullable Uri uri) {
        if (uri == null) {
            return "";
        }
        try {
            return compression.resolveRealPath(uri.toString(), context, uri, false);
        } catch (IOException unused) {
            CommsLogger commsLogger = LOG;
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("Could not resolve real path for file ");
            outline1.append(uri.toString());
            commsLogger.e(outline1.toString());
            return uri.toString();
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || GenericSharingMessageEventPayload.class != obj.getClass()) {
            return false;
        }
        GenericSharingMessageEventPayload genericSharingMessageEventPayload = (GenericSharingMessageEventPayload) obj;
        return Objects.equals(this.action, genericSharingMessageEventPayload.action) && Objects.equals(this.name, genericSharingMessageEventPayload.name) && Objects.equals(this.description, genericSharingMessageEventPayload.description) && Objects.equals(this.imageUrl, genericSharingMessageEventPayload.imageUrl) && Objects.equals(this.type, genericSharingMessageEventPayload.type) && Objects.equals(this.messageType, genericSharingMessageEventPayload.messageType) && Objects.equals(this.text, genericSharingMessageEventPayload.text);
    }

    public Action getAction() {
        return this.action;
    }

    public String getDeepLinkURL() {
        Action action = this.action;
        if (action == null) {
            LOG.d("[Content-Sharing-Service] DeepLink Missing: The <action> key is null");
            return null;
        }
        EntryData androidEntryData = action.getAndroidEntryData();
        if (androidEntryData == null) {
            LOG.d("[Content-Sharing] DeepLink Missing: The android entryData did not contain a <url> key");
            return null;
        }
        URLHolder url = androidEntryData.getUrl();
        if (url == null) {
            LOG.d("[Content-Sharing] DeepLink Missing: The android entryData did not contain a <url> key");
            return null;
        }
        String url2 = url.getUrl();
        if (url2 != null) {
            return url2;
        }
        LOG.d("[Content-Sharing] Target skipped: The android entryData.url object has no <url> key");
        return null;
    }

    public String getDescription() {
        return this.description;
    }

    public String getImageURL() {
        URLHolder uRLHolder = this.imageUrl;
        if (uRLHolder == null) {
            return null;
        }
        return uRLHolder.getUrl();
    }

    public String getMessageType() {
        return this.messageType;
    }

    public String getName() {
        return this.name;
    }

    public String getOrigin() {
        String originInternal = getOriginInternal();
        return originInternal == null ? this.description : originInternal;
    }

    public String getText() {
        return this.text;
    }

    public String getType() {
        return this.type;
    }

    public int hashCode() {
        return Objects.hash(this.action, this.name, this.description, this.imageUrl, this.type, this.messageType, this.text);
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setImageUrl(URLHolder uRLHolder) {
        this.imageUrl = uRLHolder;
    }

    public void setMessageType(String str) {
        this.messageType = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setText(String str) {
        this.text = str;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String toString() {
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("GenericSharingMessageEventPayload{action=");
        outline1.append(this.action);
        outline1.append(", name='");
        GeneratedOutlineSupport1.outline176(outline1, this.name, Chars.QUOTE, ", description='");
        GeneratedOutlineSupport1.outline176(outline1, this.description, Chars.QUOTE, ", imageUrl=");
        outline1.append(this.imageUrl);
        outline1.append(", type='");
        GeneratedOutlineSupport1.outline176(outline1, this.type, Chars.QUOTE, ", messageType='");
        GeneratedOutlineSupport1.outline176(outline1, this.messageType, Chars.QUOTE, ", text='");
        return GeneratedOutlineSupport1.outline90(outline1, this.text, Chars.QUOTE, JsonReaderKt.END_OBJ);
    }
}
