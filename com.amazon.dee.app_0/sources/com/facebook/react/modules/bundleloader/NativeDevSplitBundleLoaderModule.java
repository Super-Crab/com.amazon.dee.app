package com.facebook.react.modules.bundleloader;

import androidx.annotation.NonNull;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.fbreact.specs.NativeDevSplitBundleLoaderSpec;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.common.DebugServerException;
import com.facebook.react.devsupport.interfaces.DevSplitBundleCallback;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.module.annotations.ReactModule;
@ReactModule(name = NativeDevSplitBundleLoaderModule.NAME)
/* loaded from: classes2.dex */
public class NativeDevSplitBundleLoaderModule extends NativeDevSplitBundleLoaderSpec {
    public static final String NAME = "DevSplitBundleLoader";
    private static final String REJECTION_CODE = "E_BUNDLE_LOAD_ERROR";
    private final DevSupportManager mDevSupportManager;

    public NativeDevSplitBundleLoaderModule(ReactApplicationContext reactApplicationContext, DevSupportManager devSupportManager) {
        super(reactApplicationContext);
        this.mDevSupportManager = devSupportManager;
    }

    @Override // com.facebook.react.bridge.NativeModule
    @NonNull
    public String getName() {
        return NAME;
    }

    @Override // com.facebook.fbreact.specs.NativeDevSplitBundleLoaderSpec
    public void loadBundle(String str, final Promise promise) {
        this.mDevSupportManager.loadSplitBundleFromServer(str, new DevSplitBundleCallback() { // from class: com.facebook.react.modules.bundleloader.NativeDevSplitBundleLoaderModule.1
            @Override // com.facebook.react.devsupport.interfaces.DevSplitBundleCallback
            public void onError(String str2, Throwable th) {
                promise.reject(NativeDevSplitBundleLoaderModule.REJECTION_CODE, th instanceof DebugServerException ? ((DebugServerException) th).getOriginalMessage() : GeneratedOutlineSupport1.outline75("Unknown error fetching '", str2, "'."), th);
            }

            @Override // com.facebook.react.devsupport.interfaces.DevSplitBundleCallback
            public void onSuccess() {
                promise.resolve(true);
            }
        });
    }
}
