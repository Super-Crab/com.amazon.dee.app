package com.facebook.react.turbomodule.core;

import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.ReactPackage;
import com.facebook.react.TurboReactPackage;
import com.facebook.react.bridge.CxxModuleWrapper;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
public abstract class ReactPackageTurboModuleManagerDelegate extends TurboModuleManagerDelegate {
    private final List<TurboReactPackage> mPackages = new ArrayList();
    private final ReactApplicationContext mReactApplicationContext;

    /* loaded from: classes2.dex */
    public static abstract class Builder {
        @Nullable
        private ReactApplicationContext mContext;
        @Nullable
        private List<ReactPackage> mPackages;

        public ReactPackageTurboModuleManagerDelegate build() {
            Assertions.assertNotNull(this.mContext, "The ReactApplicationContext must be provided to create ReactPackageTurboModuleManagerDelegate");
            Assertions.assertNotNull(this.mPackages, "A set of ReactPackages must be provided to create ReactPackageTurboModuleManagerDelegate");
            return build(this.mContext, this.mPackages);
        }

        protected abstract ReactPackageTurboModuleManagerDelegate build(ReactApplicationContext reactApplicationContext, List<ReactPackage> list);

        public Builder setPackages(List<ReactPackage> list) {
            this.mPackages = new ArrayList(list);
            return this;
        }

        public Builder setReactApplicationContext(ReactApplicationContext reactApplicationContext) {
            this.mContext = reactApplicationContext;
            return this;
        }
    }

    protected ReactPackageTurboModuleManagerDelegate(ReactApplicationContext reactApplicationContext, List<ReactPackage> list) {
        this.mReactApplicationContext = reactApplicationContext;
        for (ReactPackage reactPackage : list) {
            if (reactPackage instanceof TurboReactPackage) {
                this.mPackages.add((TurboReactPackage) reactPackage);
            }
        }
    }

    @Nullable
    private TurboModule resolveModule(String str) {
        NativeModule nativeModule = null;
        for (TurboReactPackage turboReactPackage : this.mPackages) {
            try {
                NativeModule module = turboReactPackage.getModule(str, this.mReactApplicationContext);
                if (nativeModule == null || (module != null && module.canOverrideExistingModule())) {
                    nativeModule = module;
                }
            } catch (IllegalArgumentException unused) {
            }
        }
        if (nativeModule instanceof TurboModule) {
            return (TurboModule) nativeModule;
        }
        return null;
    }

    @Override // com.facebook.react.turbomodule.core.TurboModuleManagerDelegate
    public List<String> getEagerInitModuleNames() {
        ArrayList arrayList = new ArrayList();
        for (TurboReactPackage turboReactPackage : this.mPackages) {
            for (ReactModuleInfo reactModuleInfo : turboReactPackage.getReactModuleInfoProvider().getReactModuleInfos().values()) {
                if (reactModuleInfo.isTurboModule() && reactModuleInfo.needsEagerInit()) {
                    arrayList.add(reactModuleInfo.name());
                }
            }
        }
        return arrayList;
    }

    @Override // com.facebook.react.turbomodule.core.TurboModuleManagerDelegate
    @Nullable
    public CxxModuleWrapper getLegacyCxxModule(String str) {
        TurboModule resolveModule = resolveModule(str);
        if (resolveModule != null && (resolveModule instanceof CxxModuleWrapper)) {
            return (CxxModuleWrapper) resolveModule;
        }
        return null;
    }

    @Override // com.facebook.react.turbomodule.core.TurboModuleManagerDelegate
    @Nullable
    public TurboModule getModule(String str) {
        TurboModule resolveModule = resolveModule(str);
        if (resolveModule != null && !(resolveModule instanceof CxxModuleWrapper)) {
            return resolveModule;
        }
        return null;
    }
}
