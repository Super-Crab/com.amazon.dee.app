package com.facebook.react.devsupport.interfaces;

import android.view.View;
import androidx.annotation.Nullable;
import com.facebook.react.bridge.NativeModuleCallExceptionHandler;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.modules.debug.interfaces.DeveloperSettings;
import java.io.File;
/* loaded from: classes2.dex */
public interface DevSupportManager extends NativeModuleCallExceptionHandler {

    /* loaded from: classes2.dex */
    public interface PackagerLocationCustomizer {
        void run(Runnable runnable);
    }

    void addCustomDevOption(String str, DevOptionHandler devOptionHandler);

    @Nullable
    View createRootView(String str);

    void destroyRootView(View view);

    @Nullable
    File downloadBundleResourceFromUrlSync(String str, File file);

    DeveloperSettings getDevSettings();

    boolean getDevSupportEnabled();

    String getDownloadedJSBundleFile();

    String getJSBundleURLForRemoteDebugging();

    @Nullable
    StackFrame[] getLastErrorStack();

    @Nullable
    String getLastErrorTitle();

    String getSourceMapUrl();

    String getSourceUrl();

    void handleReloadJS();

    boolean hasUpToDateJSBundleInCache();

    void hideRedboxDialog();

    void isPackagerRunning(PackagerStatusCallback packagerStatusCallback);

    void loadSplitBundleFromServer(String str, DevSplitBundleCallback devSplitBundleCallback);

    void onNewReactContextCreated(ReactContext reactContext);

    void onReactInstanceDestroyed(ReactContext reactContext);

    void registerErrorCustomizer(ErrorCustomizer errorCustomizer);

    void reloadJSFromServer(String str);

    void reloadSettings();

    void setDevSupportEnabled(boolean z);

    void setFpsDebugEnabled(boolean z);

    void setHotModuleReplacementEnabled(boolean z);

    void setPackagerLocationCustomizer(PackagerLocationCustomizer packagerLocationCustomizer);

    void setRemoteJSDebugEnabled(boolean z);

    void showDevOptionsDialog();

    void showNewJSError(String str, ReadableArray readableArray, int i);

    void showNewJavaError(String str, Throwable th);

    void startInspector();

    void stopInspector();

    void toggleElementInspector();

    void updateJSError(String str, ReadableArray readableArray, int i);
}
