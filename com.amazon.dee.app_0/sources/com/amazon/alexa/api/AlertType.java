package com.amazon.alexa.api;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes6.dex */
public enum AlertType {
    ALARM,
    TIMER,
    REMINDER;

    public static AlertType fromOrdinal(int i) {
        if (i < 0 || i >= values().length) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("index ", i, " is out of bound"));
        }
        return values()[i];
    }
}
