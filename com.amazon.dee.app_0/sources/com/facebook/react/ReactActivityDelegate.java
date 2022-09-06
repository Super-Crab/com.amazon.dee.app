package com.facebook.react;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.Callback;
import com.facebook.react.modules.core.PermissionListener;
/* loaded from: classes2.dex */
public class ReactActivityDelegate {
    @Nullable
    private final Activity mActivity;
    @Nullable
    private final String mMainComponentName;
    @Nullable
    private PermissionListener mPermissionListener;
    @Nullable
    private Callback mPermissionsCallback;
    private ReactDelegate mReactDelegate;

    @Deprecated
    public ReactActivityDelegate(Activity activity, @Nullable String str) {
        this.mActivity = activity;
        this.mMainComponentName = str;
    }

    protected ReactRootView createRootView() {
        return new ReactRootView(getContext());
    }

    protected Context getContext() {
        return (Context) Assertions.assertNotNull(this.mActivity);
    }

    @Nullable
    protected Bundle getLaunchOptions() {
        return null;
    }

    public String getMainComponentName() {
        return this.mMainComponentName;
    }

    protected Activity getPlainActivity() {
        return (Activity) getContext();
    }

    public ReactInstanceManager getReactInstanceManager() {
        return this.mReactDelegate.getReactInstanceManager();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ReactNativeHost getReactNativeHost() {
        return ((ReactApplication) getPlainActivity().getApplication()).getReactNativeHost();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void loadApp(String str) {
        this.mReactDelegate.loadApp(str);
        getPlainActivity().setContentView(this.mReactDelegate.getReactRootView());
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        this.mReactDelegate.onActivityResult(i, i2, intent, true);
    }

    public boolean onBackPressed() {
        return this.mReactDelegate.onBackPressed();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        String mainComponentName = getMainComponentName();
        this.mReactDelegate = new ReactDelegate(getPlainActivity(), getReactNativeHost(), mainComponentName, getLaunchOptions()) { // from class: com.facebook.react.ReactActivityDelegate.1
            @Override // com.facebook.react.ReactDelegate
            protected ReactRootView createRootView() {
                return ReactActivityDelegate.this.createRootView();
            }
        };
        if (this.mMainComponentName != null) {
            loadApp(mainComponentName);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onDestroy() {
        this.mReactDelegate.onHostDestroy();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (!getReactNativeHost().hasInstance() || !getReactNativeHost().getUseDeveloperSupport() || i != 90) {
            return false;
        }
        keyEvent.startTracking();
        return true;
    }

    public boolean onKeyLongPress(int i, KeyEvent keyEvent) {
        if (!getReactNativeHost().hasInstance() || !getReactNativeHost().getUseDeveloperSupport() || i != 90) {
            return false;
        }
        getReactNativeHost().getReactInstanceManager().showDevOptionsDialog();
        return true;
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        return this.mReactDelegate.shouldShowDevMenuOrReload(i, keyEvent);
    }

    public boolean onNewIntent(Intent intent) {
        if (getReactNativeHost().hasInstance()) {
            getReactNativeHost().getReactInstanceManager().onNewIntent(intent);
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onPause() {
        this.mReactDelegate.onHostPause();
    }

    public void onRequestPermissionsResult(final int i, final String[] strArr, final int[] iArr) {
        this.mPermissionsCallback = new Callback() { // from class: com.facebook.react.ReactActivityDelegate.2
            @Override // com.facebook.react.bridge.Callback
            public void invoke(Object... objArr) {
                if (ReactActivityDelegate.this.mPermissionListener == null || !ReactActivityDelegate.this.mPermissionListener.onRequestPermissionsResult(i, strArr, iArr)) {
                    return;
                }
                ReactActivityDelegate.this.mPermissionListener = null;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onResume() {
        this.mReactDelegate.onHostResume();
        Callback callback = this.mPermissionsCallback;
        if (callback != null) {
            callback.invoke(new Object[0]);
            this.mPermissionsCallback = null;
        }
    }

    public void onWindowFocusChanged(boolean z) {
        if (getReactNativeHost().hasInstance()) {
            getReactNativeHost().getReactInstanceManager().onWindowFocusChange(z);
        }
    }

    @TargetApi(23)
    public void requestPermissions(String[] strArr, int i, PermissionListener permissionListener) {
        this.mPermissionListener = permissionListener;
        getPlainActivity().requestPermissions(strArr, i);
    }

    public ReactActivityDelegate(ReactActivity reactActivity, @Nullable String str) {
        this.mActivity = reactActivity;
        this.mMainComponentName = str;
    }
}
