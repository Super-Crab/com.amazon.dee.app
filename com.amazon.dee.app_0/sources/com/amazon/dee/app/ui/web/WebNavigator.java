package com.amazon.dee.app.ui.web;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes12.dex */
public interface WebNavigator {
    @Nullable
    String getUri();

    void navigate(@NonNull String str);
}
