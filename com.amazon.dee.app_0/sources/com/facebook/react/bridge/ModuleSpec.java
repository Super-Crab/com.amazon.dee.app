package com.facebook.react.bridge;

import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.common.logging.FLog;
import com.facebook.react.module.annotations.ReactModule;
import javax.inject.Provider;
/* loaded from: classes2.dex */
public class ModuleSpec {
    private static final String TAG = "ModuleSpec";
    private final String mName;
    private final Provider<? extends NativeModule> mProvider;
    @Nullable
    private final Class<? extends NativeModule> mType;

    private ModuleSpec(Provider<? extends NativeModule> provider) {
        this.mType = null;
        this.mProvider = provider;
        this.mName = null;
    }

    public static ModuleSpec nativeModuleSpec(Class<? extends NativeModule> cls, Provider<? extends NativeModule> provider) {
        ReactModule reactModule = (ReactModule) cls.getAnnotation(ReactModule.class);
        if (reactModule == null) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Could not find @ReactModule annotation on ");
            outline107.append(cls.getName());
            outline107.append(". So creating the module eagerly to get the name. Consider adding an annotation to make this Lazy");
            FLog.w(TAG, outline107.toString());
            return new ModuleSpec(provider, provider.mo10268get().getName());
        }
        return new ModuleSpec(provider, reactModule.name());
    }

    public static ModuleSpec viewManagerSpec(Provider<? extends NativeModule> provider) {
        return new ModuleSpec(provider);
    }

    public String getName() {
        return this.mName;
    }

    public Provider<? extends NativeModule> getProvider() {
        return this.mProvider;
    }

    @Nullable
    public Class<? extends NativeModule> getType() {
        return this.mType;
    }

    private ModuleSpec(Provider<? extends NativeModule> provider, String str) {
        this.mType = null;
        this.mProvider = provider;
        this.mName = str;
    }

    public static ModuleSpec nativeModuleSpec(String str, Provider<? extends NativeModule> provider) {
        return new ModuleSpec(provider, str);
    }
}
