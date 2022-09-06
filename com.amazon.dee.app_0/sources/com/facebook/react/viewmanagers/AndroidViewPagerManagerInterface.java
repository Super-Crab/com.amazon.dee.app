package com.facebook.react.viewmanagers;

import android.view.View;
import androidx.annotation.Nullable;
/* loaded from: classes2.dex */
public interface AndroidViewPagerManagerInterface<T extends View> {
    void setInitialPage(T t, int i);

    void setKeyboardDismissMode(T t, @Nullable String str);

    void setPage(T t, int i);

    void setPageMargin(T t, int i);

    void setPageWithoutAnimation(T t, int i);

    void setPeekEnabled(T t, boolean z);

    void setScrollEnabled(T t, boolean z);
}
