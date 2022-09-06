package com.amazon.deecomms.sharing.payload.parse.enums;
/* loaded from: classes12.dex */
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
