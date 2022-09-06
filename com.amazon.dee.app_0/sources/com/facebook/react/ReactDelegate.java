package com.facebook.react;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.devsupport.DoubleTapReloadRecognizer;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
/* loaded from: classes2.dex */
public class ReactDelegate {
    private final Activity mActivity;
    @Nullable
    private DoubleTapReloadRecognizer mDoubleTapReloadRecognizer = new DoubleTapReloadRecognizer();
    @Nullable
    private Bundle mLaunchOptions;
    @Nullable
    private final String mMainComponentName;
    private ReactNativeHost mReactNativeHost;
    private ReactRootView mReactRootView;

    public ReactDelegate(Activity activity, ReactNativeHost reactNativeHost, @Nullable String str, @Nullable Bundle bundle) {
        this.mActivity = activity;
        this.mMainComponentName = str;
        this.mLaunchOptions = bundle;
        this.mReactNativeHost = reactNativeHost;
    }

    private ReactNativeHost getReactNativeHost() {
        return this.mReactNativeHost;
    }

    protected ReactRootView createRootView() {
        return new ReactRootView(this.mActivity);
    }

    public ReactInstanceManager getReactInstanceManager() {
        return getReactNativeHost().getReactInstanceManager();
    }

    public ReactRootView getReactRootView() {
        return this.mReactRootView;
    }

    public void loadApp() {
        loadApp(this.mMainComponentName);
    }

    public void onActivityResult(int i, int i2, Intent intent, boolean z) {
        if (!getReactNativeHost().hasInstance() || !z) {
            return;
        }
        getReactNativeHost().getReactInstanceManager().onActivityResult(this.mActivity, i, i2, intent);
    }

    public boolean onBackPressed() {
        if (getReactNativeHost().hasInstance()) {
            getReactNativeHost().getReactInstanceManager().onBackPressed();
            return true;
        }
        return false;
    }

    public void onHostDestroy() {
        ReactRootView reactRootView = this.mReactRootView;
        if (reactRootView != null) {
            reactRootView.unmountReactApplication();
            this.mReactRootView = null;
        }
        if (getReactNativeHost().hasInstance()) {
            getReactNativeHost().getReactInstanceManager().onHostDestroy(this.mActivity);
        }
    }

    public void onHostPause() {
        if (getReactNativeHost().hasInstance()) {
            getReactNativeHost().getReactInstanceManager().onHostPause(this.mActivity);
        }
    }

    public void onHostResume() {
        if (getReactNativeHost().hasInstance()) {
            if (this.mActivity instanceof DefaultHardwareBackBtnHandler) {
                ReactInstanceManager reactInstanceManager = getReactNativeHost().getReactInstanceManager();
                Activity activity = this.mActivity;
                reactInstanceManager.onHostResume(activity, (DefaultHardwareBackBtnHandler) activity);
                return;
            }
            throw new ClassCastException("Host Activity does not implement DefaultHardwareBackBtnHandler");
        }
    }

    public boolean shouldShowDevMenuOrReload(int i, KeyEvent keyEvent) {
        if (!getReactNativeHost().hasInstance() || !getReactNativeHost().getUseDeveloperSupport()) {
            return false;
        }
        if (i == 82) {
            getReactNativeHost().getReactInstanceManager().showDevOptionsDialog();
            return true;
        } else if (!((DoubleTapReloadRecognizer) Assertions.assertNotNull(this.mDoubleTapReloadRecognizer)).didDoubleTapR(i, this.mActivity.getCurrentFocus())) {
            return false;
        } else {
            getReactNativeHost().getReactInstanceManager().getDevSupportManager().handleReloadJS();
            return true;
        }
    }

    public void loadApp(String str) {
        if (this.mReactRootView == null) {
            this.mReactRootView = createRootView();
            this.mReactRootView.startReactApplication(getReactNativeHost().getReactInstanceManager(), str, this.mLaunchOptions);
            return;
        }
        throw new IllegalStateException("Cannot loadApp while app is already running.");
    }
}
