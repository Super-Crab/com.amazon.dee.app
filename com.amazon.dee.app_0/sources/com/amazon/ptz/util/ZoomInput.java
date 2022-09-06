package com.amazon.ptz.util;

import javax.annotation.Nonnull;
/* loaded from: classes13.dex */
public enum ZoomInput {
    PINCH("Pinch"),
    DOUBLE_TAP("DoubleTap"),
    BUTTON_PRESS("ButtonPress");
    
    private final String name;

    ZoomInput(@Nonnull String str) {
        this.name = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.name;
    }
}
