package com.facebook.react.modules.common;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.CatalystInstance;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.common.ReactConstants;
/* loaded from: classes2.dex */
public class ModuleDataCleaner {

    /* loaded from: classes2.dex */
    public interface Cleanable {
        void clearSensitiveData();
    }

    public static void cleanDataFromModules(CatalystInstance catalystInstance) {
        for (NativeModule nativeModule : catalystInstance.getNativeModules()) {
            if (nativeModule instanceof Cleanable) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Cleaning data from ");
                outline107.append(nativeModule.getName());
                FLog.d(ReactConstants.TAG, outline107.toString());
                ((Cleanable) nativeModule).clearSensitiveData();
            }
        }
    }
}
