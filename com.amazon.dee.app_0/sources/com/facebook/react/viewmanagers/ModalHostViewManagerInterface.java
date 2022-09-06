package com.facebook.react.viewmanagers;

import android.view.View;
import androidx.annotation.Nullable;
import com.facebook.react.bridge.ReadableArray;
/* loaded from: classes2.dex */
public interface ModalHostViewManagerInterface<T extends View> {
    void setAnimated(T t, boolean z);

    void setAnimationType(T t, @Nullable String str);

    void setHardwareAccelerated(T t, boolean z);

    void setIdentifier(T t, int i);

    void setPresentationStyle(T t, @Nullable String str);

    void setStatusBarTranslucent(T t, boolean z);

    void setSupportedOrientations(T t, @Nullable ReadableArray readableArray);

    void setTransparent(T t, boolean z);
}
