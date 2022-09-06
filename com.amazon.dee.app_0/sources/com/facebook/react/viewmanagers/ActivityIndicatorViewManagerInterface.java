package com.facebook.react.viewmanagers;

import android.view.View;
import androidx.annotation.Nullable;
/* loaded from: classes2.dex */
public interface ActivityIndicatorViewManagerInterface<T extends View> {
    void setAnimating(T t, boolean z);

    void setColor(T t, @Nullable Integer num);

    void setHidesWhenStopped(T t, boolean z);

    void setSize(T t, @Nullable String str);
}
