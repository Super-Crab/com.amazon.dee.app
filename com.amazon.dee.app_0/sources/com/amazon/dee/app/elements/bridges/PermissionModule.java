package com.amazon.dee.app.elements.bridges;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.SparseBooleanArray;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.ActivityCompat;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.util.PermissionsUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
@ReactModule(name = "Permission")
/* loaded from: classes12.dex */
public class PermissionModule extends ReactContextBaseJavaModule implements ActivityEventListener {
    static final String ERROR = "Error";
    static final String ERROR_INTERRUPTED = "Same permission already requested";
    static final String ERROR_NULL_ACTIVITY = "Null activity";
    static final String ERROR_UNKNOWN = "Unknown";
    static final String MODULE_NAME = "PermissionService";
    private static final String TAG = Log.tag(PermissionModule.class);
    private SparseBooleanArray activeRequest;
    private final WeakReference<Activity> activity;
    private Promise lastNavigateToApplicationDetailsSettingPromise;
    private final ReactApplicationContext reactApplicationContext;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes12.dex */
    @interface PermissionRequest {
        public static final int BACKGROUND_LOCATION = 7;
        public static final int BLUETOOTH_LOCATION = 2;
        public static final int CALL = 9;
        public static final int CAMERA = 1;
        public static final int CONTACTS = 6;
        public static final int LOCATION = 3;
        public static final int MICROPHONE = 4;
        public static final int PHOTO_LIBRARY = 5;
        public static final int SMS = 8;
    }

    /* loaded from: classes12.dex */
    private class PermissionsListener implements PermissionsUtils.Listener {
        private Promise promise;
        private int requestId;

        PermissionsListener(Promise promise, int i) {
            this.promise = promise;
            this.requestId = i;
        }

        @Override // com.amazon.dee.app.util.PermissionsUtils.Listener
        public void complete(boolean z) {
            complete(z, null);
        }

        @Override // com.amazon.dee.app.util.PermissionsUtils.Listener
        public void complete(boolean z, String str) {
            if (z) {
                this.promise.resolve(null);
            } else {
                this.promise.reject("permission not granted", str);
            }
            PermissionModule.this.activeRequest.put(this.requestId, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class PermissionsStatusListener implements PermissionsUtils.Listener {
        private Promise promise;
        private int requestId;

        PermissionsStatusListener(Promise promise, int i) {
            this.promise = promise;
            this.requestId = i;
        }

        @Override // com.amazon.dee.app.util.PermissionsUtils.Listener
        public void complete(boolean z) {
            complete(z, null);
        }

        @Override // com.amazon.dee.app.util.PermissionsUtils.Listener
        public void complete(boolean z, String str) {
            this.promise.resolve(Boolean.valueOf(z));
            PermissionModule.this.releaseActiveRequestLock(this.requestId);
        }
    }

    public PermissionModule(ReactApplicationContext reactApplicationContext, WeakReference<Activity> weakReference) {
        super(reactApplicationContext);
        this.activeRequest = new SparseBooleanArray();
        this.reactApplicationContext = reactApplicationContext;
        this.activity = weakReference;
    }

    private void acquireActiveRequestLock(int i) {
        if (!this.activeRequest.get(i, false)) {
            this.activeRequest.put(i, true);
            return;
        }
        throw new IllegalStateException(ERROR_INTERRUPTED);
    }

    private boolean checkShouldShowRequestPermissionRationale(PermissionsUtils.RequestInfo requestInfo) {
        for (String str : requestInfo.permissions) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getValidActivity(), str)) {
                return true;
            }
        }
        return false;
    }

    private Activity getValidActivity() throws IllegalStateException {
        Activity activity = getActivity();
        if (activity != null) {
            return activity;
        }
        throw new IllegalStateException(ERROR_NULL_ACTIVITY);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void releaseActiveRequestLock(int i) {
        this.activeRequest.put(i, false);
    }

    private void requestPermissionFor(PermissionsUtils.RequestInfo requestInfo, Activity activity, Promise promise) {
        acquireActiveRequestLock(requestInfo.requestCode);
        PermissionsUtils.requestPermission(activity, new PermissionsStatusListener(promise, requestInfo.requestCode), requestInfo, null);
    }

    @VisibleForTesting
    protected Activity getActivity() {
        Activity contextCurrentActivity = getContextCurrentActivity();
        return contextCurrentActivity == null ? this.activity.get() : contextCurrentActivity;
    }

    @VisibleForTesting
    protected String getAuthStatusForRequestInfo(Activity activity, PermissionsUtils.RequestInfo requestInfo) {
        acquireActiveRequestLock(requestInfo.requestCode);
        try {
            return getPermissionAuthStatus(activity, requestInfo).getName();
        } finally {
            releaseActiveRequestLock(requestInfo.requestCode);
        }
    }

    @ReactMethod
    @TargetApi(29)
    public void getBackgroundLocationPermissionStatus(Promise promise) {
        promise.resolve(PermissionsUtils.getBackgroundLocationPermissionAuthStatus(getValidActivity()).getName());
    }

    @VisibleForTesting
    protected Activity getContextCurrentActivity() {
        return getCurrentActivity();
    }

    @ReactMethod
    public void getLocationPermissionStatus(Promise promise) {
        promise.resolve(PermissionsUtils.getLocationPermissionAuthStatus(getValidActivity()).getName());
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return MODULE_NAME;
    }

    @VisibleForTesting
    protected PermissionsUtils.PermissionAuthStatus getPermissionAuthStatus(Activity activity, PermissionsUtils.RequestInfo requestInfo) {
        return PermissionsUtils.getPermissionAuthStatus(activity, requestInfo);
    }

    @ReactMethod
    public void getPermissionStatus(String str, Promise promise) {
        try {
            promise.resolve(getAuthStatusForRequestInfo(getValidActivity(), getRequestInfoByName(str)));
        } catch (IllegalArgumentException | IllegalStateException e) {
            promise.reject("Error", e.getMessage(), e);
        }
    }

    @VisibleForTesting
    protected PermissionsUtils.RequestInfo getRequestInfoByName(String str) {
        return PermissionsUtils.RequestInfo.byName(str);
    }

    @ReactMethod
    public void hasLocationPermission(Promise promise) {
        Activity activity = getActivity();
        if (activity == null) {
            promise.reject("Error", ERROR_NULL_ACTIVITY);
        } else if (this.activeRequest.get(3, false)) {
            promise.reject("Error", ERROR_INTERRUPTED);
        } else {
            this.activeRequest.put(3, true);
            promise.resolve(Boolean.valueOf(PermissionsUtils.hasLocationPermission(activity)));
            this.activeRequest.put(3, false);
        }
    }

    @ReactMethod
    public void navigateToApplicationDetailsSettings(Promise promise) {
        Intent intent = new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setFlags(268435456);
        intent.addFlags(1073741824);
        intent.addFlags(8388608);
        intent.addCategory("android.intent.category.DEFAULT");
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("package:");
        outline107.append(this.reactApplicationContext.getPackageName());
        intent.setData(Uri.parse(outline107.toString()));
        if (getActivity() != null) {
            Activity activity = getActivity();
            this.lastNavigateToApplicationDetailsSettingPromise = promise;
            activity.startActivityForResult(intent, 13);
            return;
        }
        promise.reject("Error - Cannot find current activity", "Unknown");
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onActivityResult(Activity activity, int i, int i2, Intent intent) {
        try {
            if (this.lastNavigateToApplicationDetailsSettingPromise == null) {
                Log.e(TAG, "No matching promise for activity");
            } else if (i != 13) {
                this.lastNavigateToApplicationDetailsSettingPromise.reject("Invalid request code", "Unknown");
            } else {
                this.lastNavigateToApplicationDetailsSettingPromise.resolve(null);
            }
        } finally {
            this.lastNavigateToApplicationDetailsSettingPromise = null;
        }
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onNewIntent(Intent intent) {
    }

    @ReactMethod
    @TargetApi(29)
    public void requestBackgroundLocationPermission(Promise promise) {
        Activity activity = getActivity();
        if (activity == null) {
            promise.reject("Error", ERROR_NULL_ACTIVITY);
        } else if (this.activeRequest.get(7, false)) {
            promise.reject("Error", ERROR_INTERRUPTED);
        } else {
            this.activeRequest.put(7, true);
            PermissionsUtils.requestBackgroundLocationPermission(activity, new PermissionsListener(promise, 7), null);
        }
    }

    @ReactMethod
    public void requestBluetoothLocationPermission(Promise promise) {
        Activity activity = getActivity();
        if (activity == null) {
            promise.reject("Error", ERROR_NULL_ACTIVITY);
        } else if (this.activeRequest.get(2, false)) {
            promise.reject("Error", ERROR_INTERRUPTED);
        } else {
            this.activeRequest.put(2, true);
            PermissionsUtils.checkBluetoothPermission(activity, new PermissionsListener(promise, 2), null);
        }
    }

    @ReactMethod
    public void requestCameraPermission(Promise promise) {
        Activity activity = getActivity();
        if (activity == null) {
            promise.reject("Error", ERROR_NULL_ACTIVITY);
        } else if (this.activeRequest.get(1, false)) {
            promise.reject("Error", ERROR_INTERRUPTED);
        } else {
            this.activeRequest.put(1, true);
            PermissionsUtils.checkCameraPermission(activity, new PermissionsListener(promise, 1), null);
        }
    }

    @ReactMethod
    public void requestLocationPermission(Promise promise) {
        Activity activity = getActivity();
        if (activity == null) {
            promise.reject("Error", ERROR_NULL_ACTIVITY);
        } else if (this.activeRequest.get(3, false)) {
            promise.reject("Error", ERROR_INTERRUPTED);
        } else {
            this.activeRequest.put(3, true);
            PermissionsUtils.requestLocationPermission(activity, new PermissionsListener(promise, 3), null);
        }
    }

    @ReactMethod
    public void requestPermission(String str, Promise promise) {
        try {
            requestPermissionFor(getRequestInfoByName(str), getValidActivity(), promise);
        } catch (IllegalArgumentException | IllegalStateException e) {
            promise.reject("Error", e.getMessage(), e);
        }
    }

    @ReactMethod
    public void shouldShowRequestPermissionRationale(String str, Promise promise) {
        try {
            promise.resolve(Boolean.valueOf(checkShouldShowRequestPermissionRationale(getRequestInfoByName(str))));
        } catch (IllegalArgumentException e) {
            promise.reject("Error", e.getMessage(), e);
        }
    }
}
