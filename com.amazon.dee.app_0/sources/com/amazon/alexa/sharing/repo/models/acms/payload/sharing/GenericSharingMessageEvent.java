package com.amazon.alexa.sharing.repo.models.acms.payload.sharing;
/* loaded from: classes10.dex */
public class GenericSharingMessageEvent {
    private String globalMessageId;
    private String name;
    private GenericSharingMessageEventPayload payload;
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

    public GenericSharingMessageEvent(GenericSharingMessageEventPayload genericSharingMessageEventPayload, String str, String str2, String str3) {
        this.payload = genericSharingMessageEventPayload;
        this.name = str;
        this.globalMessageId = str2;
        this.templateKey = str3;
    }
}
