package com.amazon.alexa.accessory.engagement;

import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public enum DemType {
    APP_CONNECTION("ANDROID", "PowerReporter", "DevicePowerStats"),
    INTERNET_CONNECTED("ANDROID", "InternetReporter", "InternetStats"),
    USER_PRESENT("ANDROID", "UsageReporter", "UsageStats");
    
    private final String pageType;
    private final String reasonCode;
    private final String sourceName;

    DemType(String str, String str2, String str3) {
        this.reasonCode = (String) Objects.requireNonNull(str);
        this.sourceName = (String) Objects.requireNonNull(str2);
        this.pageType = (String) Objects.requireNonNull(str3);
    }

    public String getPageAction() {
        return name();
    }

    public String getPageType() {
        return this.pageType;
    }

    public String getReasonCode() {
        return this.reasonCode;
    }

    public String getSourceName() {
        return this.sourceName;
    }
}
