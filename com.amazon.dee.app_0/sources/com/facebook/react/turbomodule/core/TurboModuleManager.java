package com.facebook.react.turbomodule.core;

import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Assertions;
import com.facebook.jni.HybridData;
import com.facebook.react.bridge.CxxModuleWrapper;
import com.facebook.react.bridge.JSIModule;
import com.facebook.react.bridge.JavaScriptContextHolder;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.turbomodule.core.interfaces.CallInvokerHolder;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
import com.facebook.react.turbomodule.core.interfaces.TurboModuleRegistry;
import com.facebook.soloader.SoLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes2.dex */
public class TurboModuleManager implements JSIModule, TurboModuleRegistry {
    private static volatile boolean sIsSoLibraryLoaded;
    private final TurboModuleProvider mCxxModuleProvider;
    private final List<String> mEagerInitModuleNames;
    private final HybridData mHybridData;
    private final TurboModuleProvider mJavaModuleProvider;
    private final Object mTurboModuleCleanupLock = new Object();
    @GuardedBy("mTurboModuleCleanupLock")
    private boolean mTurboModuleCleanupStarted = false;
    @GuardedBy("mTurboModuleCleanupLock")
    private final Map<String, TurboModuleHolder> mTurboModuleHolders = new HashMap();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class TurboModuleHolder {
        private static volatile int sHolderCount;
        private volatile TurboModule mModule = null;
        private volatile boolean mIsTryingToCreate = false;
        private volatile boolean mIsDoneCreatingModule = false;
        private volatile int mModuleId = sHolderCount;

        public TurboModuleHolder() {
            sHolderCount++;
        }

        void endCreatingModule() {
            this.mIsTryingToCreate = false;
            this.mIsDoneCreatingModule = true;
        }

        @Nullable
        TurboModule getModule() {
            return this.mModule;
        }

        int getModuleId() {
            return this.mModuleId;
        }

        boolean isCreatingModule() {
            return this.mIsTryingToCreate;
        }

        boolean isDoneCreatingModule() {
            return this.mIsDoneCreatingModule;
        }

        void setModule(@NonNull TurboModule turboModule) {
            this.mModule = turboModule;
        }

        void startCreatingModule() {
            this.mIsTryingToCreate = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public interface TurboModuleProvider {
        @Nullable
        TurboModule getModule(String str);
    }

    public TurboModuleManager(JavaScriptContextHolder javaScriptContextHolder, @Nullable final TurboModuleManagerDelegate turboModuleManagerDelegate, CallInvokerHolder callInvokerHolder, CallInvokerHolder callInvokerHolder2) {
        List<String> eagerInitModuleNames;
        maybeLoadSoLibrary();
        this.mHybridData = initHybrid(javaScriptContextHolder.get(), (CallInvokerHolderImpl) callInvokerHolder, (CallInvokerHolderImpl) callInvokerHolder2, turboModuleManagerDelegate, false);
        installJSIBindings();
        if (turboModuleManagerDelegate == null) {
            eagerInitModuleNames = new ArrayList<>();
        } else {
            eagerInitModuleNames = turboModuleManagerDelegate.getEagerInitModuleNames();
        }
        this.mEagerInitModuleNames = eagerInitModuleNames;
        this.mJavaModuleProvider = new TurboModuleProvider() { // from class: com.facebook.react.turbomodule.core.TurboModuleManager.1
            @Override // com.facebook.react.turbomodule.core.TurboModuleManager.TurboModuleProvider
            @Nullable
            public TurboModule getModule(String str) {
                TurboModuleManagerDelegate turboModuleManagerDelegate2 = turboModuleManagerDelegate;
                if (turboModuleManagerDelegate2 == null) {
                    return null;
                }
                return turboModuleManagerDelegate2.getModule(str);
            }
        };
        this.mCxxModuleProvider = new TurboModuleProvider() { // from class: com.facebook.react.turbomodule.core.TurboModuleManager.2
            @Override // com.facebook.react.turbomodule.core.TurboModuleManager.TurboModuleProvider
            @Nullable
            public TurboModule getModule(String str) {
                CxxModuleWrapper legacyCxxModule;
                TurboModuleManagerDelegate turboModuleManagerDelegate2 = turboModuleManagerDelegate;
                if (turboModuleManagerDelegate2 == null || (legacyCxxModule = turboModuleManagerDelegate2.getLegacyCxxModule(str)) == null) {
                    return null;
                }
                Assertions.assertCondition(legacyCxxModule instanceof TurboModule, "CxxModuleWrapper \"" + str + "\" is not a TurboModule");
                return (TurboModule) legacyCxxModule;
            }
        };
    }

    @Nullable
    private TurboModule getJavaModule(String str) {
        TurboModule module = getModule(str);
        if (module instanceof CxxModuleWrapper) {
            return null;
        }
        return module;
    }

    @Nullable
    private CxxModuleWrapper getLegacyCxxModule(String str) {
        TurboModule module = getModule(str);
        if (!(module instanceof CxxModuleWrapper)) {
            return null;
        }
        return (CxxModuleWrapper) module;
    }

    private native HybridData initHybrid(long j, CallInvokerHolderImpl callInvokerHolderImpl, CallInvokerHolderImpl callInvokerHolderImpl2, TurboModuleManagerDelegate turboModuleManagerDelegate, boolean z);

    private native void installJSIBindings();

    private static synchronized void maybeLoadSoLibrary() {
        synchronized (TurboModuleManager.class) {
            if (!sIsSoLibraryLoaded) {
                SoLoader.loadLibrary("turbomodulejsijni");
                sIsSoLibraryLoaded = true;
            }
        }
    }

    @Override // com.facebook.react.turbomodule.core.interfaces.TurboModuleRegistry
    public List<String> getEagerInitModuleNames() {
        return this.mEagerInitModuleNames;
    }

    @Override // com.facebook.react.turbomodule.core.interfaces.TurboModuleRegistry
    @Nullable
    public TurboModule getModule(String str) {
        synchronized (this.mTurboModuleCleanupLock) {
            if (this.mTurboModuleCleanupStarted) {
                return null;
            }
            if (!this.mTurboModuleHolders.containsKey(str)) {
                this.mTurboModuleHolders.put(str, new TurboModuleHolder());
            }
            TurboModuleHolder turboModuleHolder = this.mTurboModuleHolders.get(str);
            TurboModulePerfLogger.moduleCreateStart(str, turboModuleHolder.getModuleId());
            TurboModule module = getModule(str, turboModuleHolder, true);
            if (module != null) {
                TurboModulePerfLogger.moduleCreateEnd(str, turboModuleHolder.getModuleId());
            } else {
                TurboModulePerfLogger.moduleCreateFail(str, turboModuleHolder.getModuleId());
            }
            return module;
        }
    }

    @Override // com.facebook.react.turbomodule.core.interfaces.TurboModuleRegistry
    public Collection<TurboModule> getModules() {
        ArrayList<TurboModuleHolder> arrayList = new ArrayList();
        synchronized (this.mTurboModuleCleanupLock) {
            arrayList.addAll(this.mTurboModuleHolders.values());
        }
        ArrayList arrayList2 = new ArrayList();
        for (TurboModuleHolder turboModuleHolder : arrayList) {
            synchronized (turboModuleHolder) {
                if (turboModuleHolder.getModule() != null) {
                    arrayList2.add(turboModuleHolder.getModule());
                }
            }
        }
        return arrayList2;
    }

    @Override // com.facebook.react.turbomodule.core.interfaces.TurboModuleRegistry
    public boolean hasModule(String str) {
        TurboModuleHolder turboModuleHolder;
        synchronized (this.mTurboModuleCleanupLock) {
            turboModuleHolder = this.mTurboModuleHolders.get(str);
        }
        if (turboModuleHolder != null) {
            synchronized (turboModuleHolder) {
                return turboModuleHolder.getModule() != null;
            }
        }
        return false;
    }

    @Override // com.facebook.react.bridge.JSIModule
    public void initialize() {
    }

    @Override // com.facebook.react.bridge.JSIModule
    public void onCatalystInstanceDestroy() {
        synchronized (this.mTurboModuleCleanupLock) {
            this.mTurboModuleCleanupStarted = true;
        }
        for (Map.Entry<String, TurboModuleHolder> entry : this.mTurboModuleHolders.entrySet()) {
            TurboModule module = getModule(entry.getKey(), entry.getValue(), false);
            if (module != null) {
                ((NativeModule) module).onCatalystInstanceDestroy();
            }
        }
        this.mTurboModuleHolders.clear();
        this.mHybridData.resetNative();
    }

    @Nullable
    private TurboModule getModule(String str, @NonNull TurboModuleHolder turboModuleHolder, boolean z) {
        boolean z2;
        TurboModule module;
        synchronized (turboModuleHolder) {
            if (turboModuleHolder.isDoneCreatingModule()) {
                if (z) {
                    TurboModulePerfLogger.moduleCreateCacheHit(str, turboModuleHolder.getModuleId());
                }
                return turboModuleHolder.getModule();
            }
            boolean z3 = false;
            if (!turboModuleHolder.isCreatingModule()) {
                turboModuleHolder.startCreatingModule();
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2) {
                TurboModulePerfLogger.moduleCreateConstructStart(str, turboModuleHolder.getModuleId());
                TurboModule module2 = this.mJavaModuleProvider.getModule(str);
                if (module2 == null) {
                    module2 = this.mCxxModuleProvider.getModule(str);
                }
                TurboModulePerfLogger.moduleCreateConstructEnd(str, turboModuleHolder.getModuleId());
                TurboModulePerfLogger.moduleCreateSetUpStart(str, turboModuleHolder.getModuleId());
                if (module2 != null) {
                    synchronized (turboModuleHolder) {
                        turboModuleHolder.setModule(module2);
                    }
                    ((NativeModule) module2).initialize();
                }
                TurboModulePerfLogger.moduleCreateSetUpEnd(str, turboModuleHolder.getModuleId());
                synchronized (turboModuleHolder) {
                    turboModuleHolder.endCreatingModule();
                    turboModuleHolder.notifyAll();
                }
                return module2;
            }
            synchronized (turboModuleHolder) {
                while (turboModuleHolder.isCreatingModule()) {
                    try {
                        turboModuleHolder.wait();
                    } catch (InterruptedException unused) {
                        z3 = true;
                    }
                }
                if (z3) {
                    Thread.currentThread().interrupt();
                }
                module = turboModuleHolder.getModule();
            }
            return module;
        }
    }
}
