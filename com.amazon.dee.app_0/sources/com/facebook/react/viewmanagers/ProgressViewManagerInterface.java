package com.facebook.react.viewmanagers;

import android.view.View;
import androidx.annotation.Nullable;
import com.facebook.react.bridge.ReadableMap;
/* loaded from: classes2.dex */
public interface ProgressViewManagerInterface<T extends View> {
    void setProgress(T t, float f);

    void setProgressImage(T t, @Nullable ReadableMap readableMap);

    void setProgressTintColor(T t, @Nullable Integer num);

    void setProgressViewStyle(T t, @Nullable String str);

    void setTrackImage(T t, @Nullable ReadableMap readableMap);

    void setTrackTintColor(T t, @Nullable Integer num);
}
