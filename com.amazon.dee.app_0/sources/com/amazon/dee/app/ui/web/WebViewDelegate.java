package com.amazon.dee.app.ui.web;

import androidx.annotation.NonNull;
/* loaded from: classes12.dex */
public interface WebViewDelegate {
    void clearCache();

    void clearHistory();

    void loadUrl(String str);

    void reload();

    void runOnUiThread(@NonNull Runnable runnable);
}
