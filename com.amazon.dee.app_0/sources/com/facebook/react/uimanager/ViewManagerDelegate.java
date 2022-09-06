package com.facebook.react.uimanager;

import android.view.View;
import androidx.annotation.Nullable;
import com.facebook.react.bridge.ReadableArray;
/* loaded from: classes2.dex */
public interface ViewManagerDelegate<T extends View> {
    void receiveCommand(T t, String str, ReadableArray readableArray);

    void setProperty(T t, String str, @Nullable Object obj);
}
