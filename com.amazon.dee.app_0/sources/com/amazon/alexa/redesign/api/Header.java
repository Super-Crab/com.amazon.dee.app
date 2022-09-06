package com.amazon.alexa.redesign.api;

import androidx.annotation.Nullable;
/* loaded from: classes10.dex */
public interface Header {
    void convertToOffline();

    void convertToOnline();

    int getHeight();

    void rerenderLeftHeader(boolean z, boolean z2);

    void setOutageAlert(@Nullable String str);
}
