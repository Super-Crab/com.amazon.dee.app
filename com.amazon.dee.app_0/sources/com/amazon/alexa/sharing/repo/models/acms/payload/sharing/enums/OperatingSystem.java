package com.amazon.alexa.sharing.repo.models.acms.payload.sharing.enums;
/* loaded from: classes10.dex */
public enum OperatingSystem {
    Android("android"),
    Headless("headless"),
    iOS("ios"),
    MultiModal("multimodal");
    
    private final String serializedName;

    OperatingSystem(String str) {
        this.serializedName = str;
    }

    public String getSerializedName() {
        return this.serializedName;
    }
}
