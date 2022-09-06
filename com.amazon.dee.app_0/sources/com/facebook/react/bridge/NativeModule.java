package com.facebook.react.bridge;

import androidx.annotation.NonNull;
/* loaded from: classes2.dex */
public interface NativeModule {

    /* loaded from: classes2.dex */
    public interface NativeMethod {
        String getType();

        void invoke(JSInstance jSInstance, ReadableArray readableArray);
    }

    boolean canOverrideExistingModule();

    @NonNull
    String getName();

    void initialize();

    void onCatalystInstanceDestroy();
}
