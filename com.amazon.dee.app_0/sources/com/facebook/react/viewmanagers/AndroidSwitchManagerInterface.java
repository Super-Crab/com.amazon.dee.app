package com.facebook.react.viewmanagers;

import android.view.View;
import androidx.annotation.Nullable;
/* loaded from: classes2.dex */
public interface AndroidSwitchManagerInterface<T extends View> {
    void setDisabled(T t, boolean z);

    void setEnabled(T t, boolean z);

    void setNativeValue(T t, boolean z);

    void setOn(T t, boolean z);

    void setThumbColor(T t, @Nullable Integer num);

    void setThumbTintColor(T t, @Nullable Integer num);

    void setTrackColorForFalse(T t, @Nullable Integer num);

    void setTrackColorForTrue(T t, @Nullable Integer num);

    void setTrackTintColor(T t, @Nullable Integer num);

    void setValue(T t, boolean z);
}
