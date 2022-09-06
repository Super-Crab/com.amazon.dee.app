package com.facebook.react.viewmanagers;

import android.view.View;
import androidx.annotation.Nullable;
/* loaded from: classes2.dex */
public interface PullToRefreshViewManagerInterface<T extends View> {
    void setNativeRefreshing(T t, boolean z);

    void setRefreshing(T t, boolean z);

    void setTintColor(T t, @Nullable Integer num);

    void setTitle(T t, @Nullable String str);

    void setTitleColor(T t, @Nullable Integer num);
}
