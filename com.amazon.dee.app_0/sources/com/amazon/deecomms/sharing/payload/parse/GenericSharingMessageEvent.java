package com.amazon.deecomms.sharing.payload.parse;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes12.dex */
public class GenericSharingMessageEvent {
    @Nullable
    private String globalMessageId;
    @Nullable
    private String name;
    private GenericSharingMessageEventPayload payload;
    @NonNull
    private String templateKey;

    public GenericSharingMessageEvent() {
        this(null, null, null, "TemplateKeyNotFound");
    }

    public String getGlobalMessageId() {
        return this.globalMessageId;
    }

    public String getName() {
        return this.name;
    }

    public GenericSharingMessageEventPayload getPayload() {
        return this.payload;
    }

    public String getTemplateKey() {
        return this.templateKey;
    }

    public void setGlobalMessageId(String str) {
        this.globalMessageId = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setPayload(GenericSharingMessageEventPayload genericSharingMessageEventPayload) {
        this.payload = genericSharingMessageEventPayload;
    }

    public void setTemplateKey(String str) {
        this.templateKey = str;
    }

    public GenericSharingMessageEvent(GenericSharingMessageEventPayload genericSharingMessageEventPayload, @Nullable String str, @Nullable String str2, @NonNull String str3) {
        this.payload = genericSharingMessageEventPayload;
        this.name = str;
        this.globalMessageId = str2;
        this.templateKey = str3;
    }
}
