package com.amazon.alexa.handsfree.protocols.utils;

import androidx.annotation.Nullable;
/* loaded from: classes8.dex */
public interface ApplicationInformationProvider {
    @Nullable
    Integer getDspAppBuildCode();

    @Nullable
    Integer getFalcoLibBuildCode();

    @Nullable
    Integer getVoxAppBuildCode();
}
