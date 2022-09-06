package com.bugsnag.android;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes.dex */
public enum NotifyType {
    ALL(1),
    USER(2),
    APP(3),
    DEVICE(4),
    CONTEXT(5),
    RELEASE_STAGES(6),
    FILTERS(7),
    BREADCRUMB(8),
    META(9);
    
    private final Integer intValue;

    NotifyType(Integer num) {
        this.intValue = num;
    }

    @Nullable
    public static NotifyType fromInt(@Nullable Integer num) {
        NotifyType[] values;
        if (num != null) {
            for (NotifyType notifyType : values()) {
                if (num.equals(notifyType.getValue())) {
                    return notifyType;
                }
            }
            return null;
        }
        return null;
    }

    @NonNull
    public Integer getValue() {
        return this.intValue;
    }
}
