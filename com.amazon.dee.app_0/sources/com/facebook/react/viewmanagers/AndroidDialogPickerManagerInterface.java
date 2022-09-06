package com.facebook.react.viewmanagers;

import android.view.View;
import androidx.annotation.Nullable;
import com.facebook.react.bridge.ReadableArray;
/* loaded from: classes2.dex */
public interface AndroidDialogPickerManagerInterface<T extends View> {
    void setBackgroundColor(T t, @Nullable Integer num);

    void setColor(T t, @Nullable Integer num);

    void setEnabled(T t, boolean z);

    void setItems(T t, @Nullable ReadableArray readableArray);

    void setPrompt(T t, @Nullable String str);

    void setSelected(T t, int i);
}
