package com.facebook.react;

import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.devsupport.LogBoxModule;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.module.model.ReactModuleInfoProvider;
import com.facebook.react.modules.bundleloader.NativeDevSplitBundleLoaderModule;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.modules.core.ExceptionsManagerModule;
import com.facebook.react.modules.core.HeadlessJsTaskSupportModule;
import com.facebook.react.modules.core.TimingModule;
import com.facebook.react.modules.debug.DevSettingsModule;
import com.facebook.react.modules.debug.SourceCodeModule;
import com.facebook.react.modules.deviceinfo.DeviceInfoModule;
import com.facebook.react.modules.systeminfo.AndroidInfoModule;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
import com.facebook.react.uimanager.UIImplementationProvider;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.systrace.Systrace;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes2.dex */
public class CoreModulesPackage extends TurboReactPackage implements ReactPackageLogger {
    private final DefaultHardwareBackBtnHandler mHardwareBackBtnHandler;
    private final boolean mLazyViewManagersEnabled;
    private final int mMinTimeLeftInFrameForNonBatchedOperationMs;
    private final ReactInstanceManager mReactInstanceManager;

    public CoreModulesPackage(ReactInstanceManager reactInstanceManager, DefaultHardwareBackBtnHandler defaultHardwareBackBtnHandler, @Nullable UIImplementationProvider uIImplementationProvider, boolean z, int i) {
        this.mReactInstanceManager = reactInstanceManager;
        this.mHardwareBackBtnHandler = defaultHardwareBackBtnHandler;
        this.mLazyViewManagersEnabled = z;
        this.mMinTimeLeftInFrameForNonBatchedOperationMs = i;
    }

    private UIManagerModule createUIManager(ReactApplicationContext reactApplicationContext) {
        ReactMarker.logMarker(ReactMarkerConstants.CREATE_UI_MANAGER_MODULE_START);
        Systrace.beginSection(0L, "createUIManagerModule");
        try {
            if (this.mLazyViewManagersEnabled) {
                return new UIManagerModule(reactApplicationContext, new UIManagerModule.ViewManagerResolver() { // from class: com.facebook.react.CoreModulesPackage.2
                    @Override // com.facebook.react.uimanager.UIManagerModule.ViewManagerResolver
                    @Nullable
                    public ViewManager getViewManager(String str) {
                        return CoreModulesPackage.this.mReactInstanceManager.createViewManager(str);
                    }

                    @Override // com.facebook.react.uimanager.UIManagerModule.ViewManagerResolver
                    public List<String> getViewManagerNames() {
                        return CoreModulesPackage.this.mReactInstanceManager.getViewManagerNames();
                    }
                }, this.mMinTimeLeftInFrameForNonBatchedOperationMs);
            }
            return new UIManagerModule(reactApplicationContext, this.mReactInstanceManager.getOrCreateViewManagers(reactApplicationContext), this.mMinTimeLeftInFrameForNonBatchedOperationMs);
        } finally {
            Systrace.endSection(0L);
            ReactMarker.logMarker(ReactMarkerConstants.CREATE_UI_MANAGER_MODULE_END);
        }
    }

    @Override // com.facebook.react.ReactPackageLogger
    public void endProcessPackage() {
        ReactMarker.logMarker(ReactMarkerConstants.PROCESS_CORE_REACT_PACKAGE_END);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.facebook.react.TurboReactPackage
    public NativeModule getModule(String str, ReactApplicationContext reactApplicationContext) {
        char c;
        switch (str.hashCode()) {
            case -2013505529:
                if (str.equals(LogBoxModule.NAME)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -1789797270:
                if (str.equals(TimingModule.NAME)) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case -1633589448:
                if (str.equals(DevSettingsModule.NAME)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -1520650172:
                if (str.equals(DeviceInfoModule.NAME)) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case -1037217463:
                if (str.equals(DeviceEventManagerModule.NAME)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -790603268:
                if (str.equals(AndroidInfoModule.NAME)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -508954630:
                if (str.equals(NativeDevSplitBundleLoaderModule.NAME)) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case 512434409:
                if (str.equals(ExceptionsManagerModule.NAME)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 881516744:
                if (str.equals(SourceCodeModule.NAME)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 1256514152:
                if (str.equals(HeadlessJsTaskSupportModule.NAME)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1861242489:
                if (str.equals(UIManagerModule.NAME)) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return new AndroidInfoModule(reactApplicationContext);
            case 1:
                return new DeviceEventManagerModule(reactApplicationContext, this.mHardwareBackBtnHandler);
            case 2:
                return new DevSettingsModule(reactApplicationContext, this.mReactInstanceManager.getDevSupportManager());
            case 3:
                return new ExceptionsManagerModule(this.mReactInstanceManager.getDevSupportManager());
            case 4:
                return new LogBoxModule(reactApplicationContext, this.mReactInstanceManager.getDevSupportManager());
            case 5:
                return new HeadlessJsTaskSupportModule(reactApplicationContext);
            case 6:
                return new SourceCodeModule(reactApplicationContext);
            case 7:
                return new TimingModule(reactApplicationContext, this.mReactInstanceManager.getDevSupportManager());
            case '\b':
                return createUIManager(reactApplicationContext);
            case '\t':
                return new DeviceInfoModule(reactApplicationContext);
            case '\n':
                return new NativeDevSplitBundleLoaderModule(reactApplicationContext, this.mReactInstanceManager.getDevSupportManager());
            default:
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("In CoreModulesPackage, could not find Native module for ", str));
        }
    }

    @Override // com.facebook.react.TurboReactPackage
    public ReactModuleInfoProvider getReactModuleInfoProvider() {
        Class[] clsArr;
        try {
            return (ReactModuleInfoProvider) Class.forName("com.facebook.react.CoreModulesPackage$$ReactModuleInfoProvider").newInstance();
        } catch (ClassNotFoundException unused) {
            final HashMap hashMap = new HashMap();
            for (Class cls : new Class[]{AndroidInfoModule.class, DeviceEventManagerModule.class, DeviceInfoModule.class, DevSettingsModule.class, ExceptionsManagerModule.class, LogBoxModule.class, HeadlessJsTaskSupportModule.class, SourceCodeModule.class, TimingModule.class, UIManagerModule.class, NativeDevSplitBundleLoaderModule.class}) {
                ReactModule reactModule = (ReactModule) cls.getAnnotation(ReactModule.class);
                hashMap.put(reactModule.name(), new ReactModuleInfo(reactModule.name(), cls.getName(), reactModule.canOverrideExistingModule(), reactModule.needsEagerInit(), reactModule.hasConstants(), reactModule.isCxxModule(), TurboModule.class.isAssignableFrom(cls)));
            }
            return new ReactModuleInfoProvider() { // from class: com.facebook.react.CoreModulesPackage.1
                @Override // com.facebook.react.module.model.ReactModuleInfoProvider
                public Map<String, ReactModuleInfo> getReactModuleInfos() {
                    return hashMap;
                }
            };
        } catch (IllegalAccessException e) {
            throw new RuntimeException("No ReactModuleInfoProvider for CoreModulesPackage$$ReactModuleInfoProvider", e);
        } catch (InstantiationException e2) {
            throw new RuntimeException("No ReactModuleInfoProvider for CoreModulesPackage$$ReactModuleInfoProvider", e2);
        }
    }

    @Override // com.facebook.react.ReactPackageLogger
    public void startProcessPackage() {
        ReactMarker.logMarker(ReactMarkerConstants.PROCESS_CORE_REACT_PACKAGE_START);
    }
}
