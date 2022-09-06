package com.amazon.alexa.viewprovider.api.view;

import android.view.View;
/* loaded from: classes.dex */
public interface ViewModule {
    void cleanUp();

    View getView();

    void prepare();
}
