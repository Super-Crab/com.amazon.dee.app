package com.facebook.react.bridge;

import android.view.View;
import androidx.annotation.AnyThread;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import com.facebook.infer.annotation.ThreadConfined;
/* loaded from: classes2.dex */
public interface UIManager extends JSIModule, PerformanceCounter {
    @ThreadConfined(ThreadConfined.UI)
    @UiThread
    <T extends View> int addRootView(T t, WritableMap writableMap, @Nullable String str);

    void addUIManagerEventListener(UIManagerListener uIManagerListener);

    void dispatchCommand(int i, int i2, @Nullable ReadableArray readableArray);

    void dispatchCommand(int i, String str, @Nullable ReadableArray readableArray);

    /* renamed from: getEventDispatcher */
    <T> T mo6949getEventDispatcher();

    void receiveEvent(int i, String str, @Nullable WritableMap writableMap);

    void removeUIManagerEventListener(UIManagerListener uIManagerListener);

    @Nullable
    @Deprecated
    String resolveCustomDirectEventName(@Nullable String str);

    void sendAccessibilityEvent(int i, int i2);

    @AnyThread
    <T extends View> int startSurface(T t, String str, WritableMap writableMap, int i, int i2);

    @AnyThread
    void stopSurface(int i);

    @ThreadConfined(ThreadConfined.UI)
    @UiThread
    void synchronouslyUpdateViewOnUIThread(int i, ReadableMap readableMap);

    @ThreadConfined(ThreadConfined.UI)
    @UiThread
    void updateRootLayoutSpecs(int i, int i2, int i3, int i4, int i5);
}
