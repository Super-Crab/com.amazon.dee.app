package com.amazon.dee.app.ui.web;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes12.dex */
public interface JavaScriptResponseQueue {
    boolean enqueue(@NonNull JavaScriptResponse javaScriptResponse);

    @Nullable
    JavaScriptResponse poll();
}
