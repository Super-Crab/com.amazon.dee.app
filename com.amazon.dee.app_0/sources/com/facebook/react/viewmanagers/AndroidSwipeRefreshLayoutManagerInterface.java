package com.facebook.react.viewmanagers;

import android.view.View;
import androidx.annotation.Nullable;
import com.facebook.react.bridge.ReadableArray;
/* loaded from: classes2.dex */
public interface AndroidSwipeRefreshLayoutManagerInterface<T extends View> {
    void setColors(T t, @Nullable ReadableArray readableArray);

    void setEnabled(T t, boolean z);

    void setNativeRefreshing(T t, boolean z);

    void setProgressBackgroundColor(T t, @Nullable Integer num);

    void setProgressViewOffset(T t, float f);

    void setRefreshing(T t, boolean z);

    void setSize(T t, int i);
}
