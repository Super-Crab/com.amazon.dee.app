package com.facebook.react.modules.permissions;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.SparseArray;
import com.amazon.tarazed.core.metrics.BizMetricsConstants;
import com.facebook.fbreact.specs.NativePermissionsAndroidSpec;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.PermissionAwareActivity;
import com.facebook.react.modules.core.PermissionListener;
import java.util.ArrayList;
@ReactModule(name = PermissionsModule.NAME)
/* loaded from: classes2.dex */
public class PermissionsModule extends NativePermissionsAndroidSpec implements PermissionListener {
    private static final String ERROR_INVALID_ACTIVITY = "E_INVALID_ACTIVITY";
    public static final String NAME = "PermissionsAndroid";
    private final String DENIED;
    private final String GRANTED;
    private final String NEVER_ASK_AGAIN;
    private final SparseArray<Callback> mCallbacks;
    private int mRequestCode;

    public PermissionsModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mRequestCode = 0;
        this.GRANTED = BizMetricsConstants.OS_PERMISSION_GRANTED_TRUE_VALUE;
        this.DENIED = "denied";
        this.NEVER_ASK_AGAIN = "never_ask_again";
        this.mCallbacks = new SparseArray<>();
    }

    private PermissionAwareActivity getPermissionAwareActivity() {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity != null) {
            if (currentActivity instanceof PermissionAwareActivity) {
                return (PermissionAwareActivity) currentActivity;
            }
            throw new IllegalStateException("Tried to use permissions API but the host Activity doesn't implement PermissionAwareActivity.");
        }
        throw new IllegalStateException("Tried to use permissions API while not attached to an Activity.");
    }

    @Override // com.facebook.fbreact.specs.NativePermissionsAndroidSpec
    public void checkPermission(String str, Promise promise) {
        Context baseContext = getReactApplicationContext().getBaseContext();
        int i = Build.VERSION.SDK_INT;
        promise.resolve(Boolean.valueOf(baseContext.checkSelfPermission(str) == 0));
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @Override // com.facebook.react.modules.core.PermissionListener
    public boolean onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        this.mCallbacks.get(i).invoke(iArr, getPermissionAwareActivity());
        this.mCallbacks.remove(i);
        return this.mCallbacks.size() == 0;
    }

    @Override // com.facebook.fbreact.specs.NativePermissionsAndroidSpec
    public void requestMultiplePermissions(ReadableArray readableArray, final Promise promise) {
        final WritableNativeMap writableNativeMap = new WritableNativeMap();
        final ArrayList arrayList = new ArrayList();
        Context baseContext = getReactApplicationContext().getBaseContext();
        int i = 0;
        for (int i2 = 0; i2 < readableArray.size(); i2++) {
            String string = readableArray.getString(i2);
            int i3 = Build.VERSION.SDK_INT;
            if (baseContext.checkSelfPermission(string) == 0) {
                writableNativeMap.putString(string, BizMetricsConstants.OS_PERMISSION_GRANTED_TRUE_VALUE);
                i++;
            } else {
                arrayList.add(string);
            }
        }
        if (readableArray.size() == i) {
            promise.resolve(writableNativeMap);
            return;
        }
        try {
            PermissionAwareActivity permissionAwareActivity = getPermissionAwareActivity();
            this.mCallbacks.put(this.mRequestCode, new Callback() { // from class: com.facebook.react.modules.permissions.PermissionsModule.2
                @Override // com.facebook.react.bridge.Callback
                public void invoke(Object... objArr) {
                    int[] iArr = (int[]) objArr[0];
                    PermissionAwareActivity permissionAwareActivity2 = (PermissionAwareActivity) objArr[1];
                    for (int i4 = 0; i4 < arrayList.size(); i4++) {
                        String str = (String) arrayList.get(i4);
                        if (iArr.length > 0 && iArr[i4] == 0) {
                            writableNativeMap.putString(str, BizMetricsConstants.OS_PERMISSION_GRANTED_TRUE_VALUE);
                        } else if (permissionAwareActivity2.shouldShowRequestPermissionRationale(str)) {
                            writableNativeMap.putString(str, "denied");
                        } else {
                            writableNativeMap.putString(str, "never_ask_again");
                        }
                    }
                    promise.resolve(writableNativeMap);
                }
            });
            permissionAwareActivity.requestPermissions((String[]) arrayList.toArray(new String[0]), this.mRequestCode, this);
            this.mRequestCode++;
        } catch (IllegalStateException e) {
            promise.reject(ERROR_INVALID_ACTIVITY, e);
        }
    }

    @Override // com.facebook.fbreact.specs.NativePermissionsAndroidSpec
    public void requestPermission(final String str, final Promise promise) {
        Context baseContext = getReactApplicationContext().getBaseContext();
        int i = Build.VERSION.SDK_INT;
        if (baseContext.checkSelfPermission(str) == 0) {
            promise.resolve(BizMetricsConstants.OS_PERMISSION_GRANTED_TRUE_VALUE);
            return;
        }
        try {
            PermissionAwareActivity permissionAwareActivity = getPermissionAwareActivity();
            this.mCallbacks.put(this.mRequestCode, new Callback() { // from class: com.facebook.react.modules.permissions.PermissionsModule.1
                @Override // com.facebook.react.bridge.Callback
                public void invoke(Object... objArr) {
                    int[] iArr = (int[]) objArr[0];
                    if (iArr.length > 0 && iArr[0] == 0) {
                        promise.resolve(BizMetricsConstants.OS_PERMISSION_GRANTED_TRUE_VALUE);
                    } else if (((PermissionAwareActivity) objArr[1]).shouldShowRequestPermissionRationale(str)) {
                        promise.resolve("denied");
                    } else {
                        promise.resolve("never_ask_again");
                    }
                }
            });
            permissionAwareActivity.requestPermissions(new String[]{str}, this.mRequestCode, this);
            this.mRequestCode++;
        } catch (IllegalStateException e) {
            promise.reject(ERROR_INVALID_ACTIVITY, e);
        }
    }

    @Override // com.facebook.fbreact.specs.NativePermissionsAndroidSpec
    public void shouldShowRequestPermissionRationale(String str, Promise promise) {
        int i = Build.VERSION.SDK_INT;
        try {
            promise.resolve(Boolean.valueOf(getPermissionAwareActivity().shouldShowRequestPermissionRationale(str)));
        } catch (IllegalStateException e) {
            promise.reject(ERROR_INVALID_ACTIVITY, e);
        }
    }
}
