package com.facebook.react;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.ModuleHolder;
import com.facebook.react.bridge.NativeModuleRegistry;
import com.facebook.react.bridge.ReactApplicationContext;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes2.dex */
public class NativeModuleRegistryBuilder {
    private final Map<String, ModuleHolder> mModules = new HashMap();
    private final ReactApplicationContext mReactApplicationContext;
    private final ReactInstanceManager mReactInstanceManager;

    public NativeModuleRegistryBuilder(ReactApplicationContext reactApplicationContext, ReactInstanceManager reactInstanceManager) {
        this.mReactApplicationContext = reactApplicationContext;
        this.mReactInstanceManager = reactInstanceManager;
    }

    public NativeModuleRegistry build() {
        return new NativeModuleRegistry(this.mReactApplicationContext, this.mModules);
    }

    public void processPackage(ReactPackage reactPackage) {
        Iterable<ModuleHolder> nativeModuleIterator;
        if (reactPackage instanceof LazyReactPackage) {
            nativeModuleIterator = ((LazyReactPackage) reactPackage).getNativeModuleIterator(this.mReactApplicationContext);
        } else if (reactPackage instanceof TurboReactPackage) {
            nativeModuleIterator = ((TurboReactPackage) reactPackage).getNativeModuleIterator(this.mReactApplicationContext);
        } else {
            nativeModuleIterator = ReactPackageHelper.getNativeModuleIterator(reactPackage, this.mReactApplicationContext, this.mReactInstanceManager);
        }
        for (ModuleHolder moduleHolder : nativeModuleIterator) {
            String name = moduleHolder.getName();
            if (this.mModules.containsKey(name)) {
                ModuleHolder moduleHolder2 = this.mModules.get(name);
                if (moduleHolder.getCanOverrideExistingModule()) {
                    this.mModules.remove(moduleHolder2);
                } else {
                    StringBuilder outline115 = GeneratedOutlineSupport1.outline115("Native module ", name, " tried to override ");
                    outline115.append(moduleHolder2.getClassName());
                    outline115.append(". Check the getPackages() method in MainApplication.java, it might be that module is being created twice. If this was your intention, set canOverrideExistingModule=true. This error may also be present if the package is present only once in getPackages() but is also automatically added later during build time by autolinking. Try removing the existing entry and rebuild.");
                    throw new IllegalStateException(outline115.toString());
                }
            }
            this.mModules.put(name, moduleHolder);
        }
    }
}
