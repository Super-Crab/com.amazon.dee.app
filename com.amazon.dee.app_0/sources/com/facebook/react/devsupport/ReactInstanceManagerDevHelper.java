package com.facebook.react.devsupport;

import android.app.Activity;
import android.view.View;
import androidx.annotation.Nullable;
import com.facebook.react.bridge.JavaJSExecutor;
import com.facebook.react.bridge.JavaScriptExecutorFactory;
/* loaded from: classes2.dex */
public interface ReactInstanceManagerDevHelper {
    @Nullable
    View createRootView(String str);

    void destroyRootView(View view);

    @Nullable
    Activity getCurrentActivity();

    JavaScriptExecutorFactory getJavaScriptExecutorFactory();

    void onJSBundleLoadedFromServer();

    void onReloadWithJSDebugger(JavaJSExecutor.Factory factory);

    void toggleElementInspector();
}
