package com.amazon.deecomms.sharing.payload.eventBus;

import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.HashMap;
import java.util.Objects;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes12.dex */
public class ShareRenderingTemplateEventBusPayload implements SharingEventBusPayload {
    public HashMap<String, Object> content;
    public HashMap<String, String> metadata;
    public String name;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ShareRenderingTemplateEventBusPayload)) {
            return false;
        }
        ShareRenderingTemplateEventBusPayload shareRenderingTemplateEventBusPayload = (ShareRenderingTemplateEventBusPayload) obj;
        return Objects.equals(this.content, shareRenderingTemplateEventBusPayload.content) && Objects.equals(this.metadata, shareRenderingTemplateEventBusPayload.metadata);
    }

    public int hashCode() {
        return Objects.hash(this.content, this.metadata);
    }

    public String toString() {
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("SharedMessageEventBusPayload{name=");
        outline1.append(this.name);
        outline1.append(", content=");
        outline1.append(this.content);
        outline1.append(", metadata=");
        outline1.append(this.metadata);
        outline1.append(JsonReaderKt.END_OBJ);
        return outline1.toString();
    }
}
