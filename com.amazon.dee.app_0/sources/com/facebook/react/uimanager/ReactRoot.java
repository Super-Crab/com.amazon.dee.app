package com.facebook.react.uimanager;

import android.os.Bundle;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
/* loaded from: classes2.dex */
public interface ReactRoot {
    @Nullable
    Bundle getAppProperties();

    int getHeightMeasureSpec();

    @Nullable
    String getInitialUITemplate();

    String getJSModuleName();

    ViewGroup getRootViewGroup();

    int getRootViewTag();

    @Nullable
    @Deprecated
    String getSurfaceID();

    int getUIManagerType();

    int getWidthMeasureSpec();

    void onStage(int i);

    void runApplication();

    void setRootViewTag(int i);

    void setShouldLogContentAppeared(boolean z);
}
