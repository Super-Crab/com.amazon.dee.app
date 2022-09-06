package com.amazon.alexa.sharing.sharingsdk.payload;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.sharing.sharingsdk.photos.CommonContentProperties;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Objects;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes10.dex */
public class PhotoSharingMessageEventPayload {
    @Nullable
    private final CommonContentProperties contentProperties;
    @NonNull
    private String contentUri;

    public PhotoSharingMessageEventPayload() {
        this(null, null);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || PhotoSharingMessageEventPayload.class != obj.getClass()) {
            return false;
        }
        PhotoSharingMessageEventPayload photoSharingMessageEventPayload = (PhotoSharingMessageEventPayload) obj;
        return Objects.equals(this.contentProperties, photoSharingMessageEventPayload.contentProperties) && this.contentUri.equals(photoSharingMessageEventPayload.contentUri);
    }

    @Nullable
    public CommonContentProperties getContentProperties() {
        return this.contentProperties;
    }

    public String getContentUri() {
        return this.contentUri;
    }

    public int hashCode() {
        return Objects.hash(this.contentProperties, this.contentUri);
    }

    public void setContentUri(@NonNull String str) {
        this.contentUri = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("PhotoSharingMessageEventPayload{contentProperties=");
        outline107.append(this.contentProperties);
        outline107.append(", contentUri='");
        return GeneratedOutlineSupport1.outline90(outline107, this.contentUri, Chars.QUOTE, JsonReaderKt.END_OBJ);
    }

    public PhotoSharingMessageEventPayload(@NonNull String str, @Nullable CommonContentProperties commonContentProperties) {
        this.contentUri = str;
        this.contentProperties = commonContentProperties;
    }
}
