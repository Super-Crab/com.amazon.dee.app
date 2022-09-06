package com.facebook.react.viewmanagers;

import android.view.View;
import androidx.annotation.Nullable;
/* loaded from: classes2.dex */
public interface AndroidProgressBarManagerInterface<T extends View> {
    void setAnimating(T t, boolean z);

    void setColor(T t, @Nullable Integer num);

    void setIndeterminate(T t, boolean z);

    void setProgress(T t, double d);

    void setStyleAttr(T t, @Nullable String str);

    void setTestID(T t, @Nullable String str);

    void setTypeAttr(T t, @Nullable String str);
}
