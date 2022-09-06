package com.amazon.alexa.handsfree.protocols.uservoicerecognition.locale;

import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolScope;
@FalcoProtocolScope
/* loaded from: classes8.dex */
public class UVRLocaleProvider {
    private UVRLocale mUVRLocale;

    /* loaded from: classes8.dex */
    private static class InstanceHolder {
        private static final UVRLocaleProvider INSTANCE = new UVRLocaleProvider();

        private InstanceHolder() {
        }
    }

    public static UVRLocaleProvider getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public UVRLocale getUVRLocale() {
        UVRLocale uVRLocale = this.mUVRLocale;
        if (uVRLocale != null) {
            return uVRLocale;
        }
        throw new IllegalStateException("UVRLocaleProvider wasn't initialized.");
    }

    public void setUVRLocale(@NonNull UVRLocale uVRLocale) {
        this.mUVRLocale = uVRLocale;
    }
}
