package com.amazon.alexa.cantilever;
/* loaded from: classes6.dex */
public interface HelpWebViewLoadListener {
    void onTimeout();

    boolean onUrlChanged(String str);

    void onWebViewLoaded(String str);
}
