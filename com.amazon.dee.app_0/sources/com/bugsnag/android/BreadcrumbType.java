package com.bugsnag.android;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
/* loaded from: classes.dex */
public enum BreadcrumbType {
    ERROR("error"),
    LOG("log"),
    MANUAL("manual"),
    NAVIGATION(NotificationCompat.CATEGORY_NAVIGATION),
    PROCESS("process"),
    REQUEST("request"),
    STATE("state"),
    USER("user");
    
    private final String type;

    BreadcrumbType(@NonNull String str) {
        this.type = str;
    }

    @Override // java.lang.Enum
    @NonNull
    public String toString() {
        return this.type;
    }
}
