package com.amazon.alexa.sharing.sharingsdk.payload.response;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Objects;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes10.dex */
public class ShareRenderingTemplateResponsePayload implements SharingSDKResponsePayload {
    public HashMap<String, Object> content;
    public HashMap<String, String> metadata;
    public String name;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ShareRenderingTemplateResponsePayload)) {
            return false;
        }
        ShareRenderingTemplateResponsePayload shareRenderingTemplateResponsePayload = (ShareRenderingTemplateResponsePayload) obj;
        return Objects.equals(this.content, shareRenderingTemplateResponsePayload.content) && Objects.equals(this.metadata, shareRenderingTemplateResponsePayload.metadata);
    }

    public int hashCode() {
        return Objects.hash(this.content, this.metadata);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SharedMessageEventBusPayload{name=");
        outline107.append(this.name);
        outline107.append(", content=");
        outline107.append(this.content);
        outline107.append(", metadata=");
        outline107.append(this.metadata);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }
}
