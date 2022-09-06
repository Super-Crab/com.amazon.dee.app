package com.facebook.react;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Process;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.common.logging.FLog;
import com.facebook.debug.holder.PrinterHolder;
import com.facebook.debug.tags.ReactDebugOverlayTags;
import com.facebook.infer.annotation.Assertions;
import com.facebook.infer.annotation.ThreadConfined;
import com.facebook.infer.annotation.ThreadSafe;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.CatalystInstance;
import com.facebook.react.bridge.CatalystInstanceImpl;
import com.facebook.react.bridge.JSBundleLoader;
import com.facebook.react.bridge.JSIModule;
import com.facebook.react.bridge.JSIModulePackage;
import com.facebook.react.bridge.JSIModuleType;
import com.facebook.react.bridge.JavaJSExecutor;
import com.facebook.react.bridge.JavaScriptExecutor;
import com.facebook.react.bridge.JavaScriptExecutorFactory;
import com.facebook.react.bridge.NativeModuleCallExceptionHandler;
import com.facebook.react.bridge.NativeModuleRegistry;
import com.facebook.react.bridge.NotThreadSafeBridgeIdleDebugListener;
import com.facebook.react.bridge.ProxyJavaScriptExecutor;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.bridge.ReactNoCrashSoftException;
import com.facebook.react.bridge.ReactSoftException;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.queue.ReactQueueConfigurationSpec;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.config.ReactFeatureFlags;
import com.facebook.react.devsupport.DevSupportManagerFactory;
import com.facebook.react.devsupport.ReactInstanceManagerDevHelper;
import com.facebook.react.devsupport.RedBoxHandler;
import com.facebook.react.devsupport.interfaces.DevBundleDownloadListener;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.devsupport.interfaces.PackagerStatusCallback;
import com.facebook.react.modules.appearance.AppearanceModule;
import com.facebook.react.modules.appregistry.AppRegistry;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.modules.core.ReactChoreographer;
import com.facebook.react.modules.debug.interfaces.DeveloperSettings;
import com.facebook.react.modules.fabric.ReactFabric;
import com.facebook.react.packagerconnection.RequestHandler;
import com.facebook.react.turbomodule.core.interfaces.TurboModuleRegistry;
import com.facebook.react.uimanager.DisplayMetricsHolder;
import com.facebook.react.uimanager.ReactRoot;
import com.facebook.react.uimanager.UIImplementationProvider;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.views.imagehelper.ResourceDrawableIdHelper;
import com.facebook.soloader.SoLoader;
import com.facebook.systrace.Systrace;
import com.facebook.systrace.SystraceMessage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
@ThreadSafe
/* loaded from: classes2.dex */
public class ReactInstanceManager {
    private static final String TAG = "ReactInstanceManager";
    private final Context mApplicationContext;
    @Nullable
    private final NotThreadSafeBridgeIdleDebugListener mBridgeIdleDebugListener;
    @Nullable
    private final JSBundleLoader mBundleLoader;
    @Nullable
    private volatile Thread mCreateReactContextThread;
    @Nullable
    private Activity mCurrentActivity;
    @Nullable
    private volatile ReactContext mCurrentReactContext;
    @Nullable
    @ThreadConfined(ThreadConfined.UI)
    private DefaultHardwareBackBtnHandler mDefaultBackButtonImpl;
    private final DevSupportManager mDevSupportManager;
    @Nullable
    private final JSIModulePackage mJSIModulePackage;
    @Nullable
    private final String mJSMainModulePath;
    private final JavaScriptExecutorFactory mJavaScriptExecutorFactory;
    private volatile LifecycleState mLifecycleState;
    private final MemoryPressureRouter mMemoryPressureRouter;
    @Nullable
    private final NativeModuleCallExceptionHandler mNativeModuleCallExceptionHandler;
    private final List<ReactPackage> mPackages;
    @Nullable
    @ThreadConfined(ThreadConfined.UI)
    private ReactContextInitParams mPendingReactContextInitParams;
    private final boolean mUseDeveloperSupport;
    private List<ViewManager> mViewManagers;
    private final Set<ReactRoot> mAttachedReactRoots = Collections.synchronizedSet(new HashSet());
    private final Object mReactContextLock = new Object();
    private final Collection<ReactInstanceEventListener> mReactInstanceEventListeners = Collections.synchronizedList(new ArrayList());
    private volatile boolean mHasStartedCreatingInitialContext = false;
    private volatile Boolean mHasStartedDestroying = false;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class ReactContextInitParams {
        private final JSBundleLoader mJsBundleLoader;
        private final JavaScriptExecutorFactory mJsExecutorFactory;

        public ReactContextInitParams(JavaScriptExecutorFactory javaScriptExecutorFactory, JSBundleLoader jSBundleLoader) {
            this.mJsExecutorFactory = (JavaScriptExecutorFactory) Assertions.assertNotNull(javaScriptExecutorFactory);
            this.mJsBundleLoader = (JSBundleLoader) Assertions.assertNotNull(jSBundleLoader);
        }

        public JSBundleLoader getJsBundleLoader() {
            return this.mJsBundleLoader;
        }

        public JavaScriptExecutorFactory getJsExecutorFactory() {
            return this.mJsExecutorFactory;
        }
    }

    /* loaded from: classes2.dex */
    public interface ReactInstanceEventListener {
        void onReactContextInitialized(ReactContext reactContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReactInstanceManager(Context context, @Nullable Activity activity, @Nullable DefaultHardwareBackBtnHandler defaultHardwareBackBtnHandler, JavaScriptExecutorFactory javaScriptExecutorFactory, @Nullable JSBundleLoader jSBundleLoader, @Nullable String str, List<ReactPackage> list, boolean z, @Nullable NotThreadSafeBridgeIdleDebugListener notThreadSafeBridgeIdleDebugListener, LifecycleState lifecycleState, @Nullable UIImplementationProvider uIImplementationProvider, NativeModuleCallExceptionHandler nativeModuleCallExceptionHandler, @Nullable RedBoxHandler redBoxHandler, boolean z2, @Nullable DevBundleDownloadListener devBundleDownloadListener, int i, int i2, @Nullable JSIModulePackage jSIModulePackage, @Nullable Map<String, RequestHandler> map) {
        FLog.d(TAG, "ReactInstanceManager.ctor()");
        initializeSoLoaderIfNecessary(context);
        DisplayMetricsHolder.initDisplayMetricsIfNotInitialized(context);
        this.mApplicationContext = context;
        this.mCurrentActivity = activity;
        this.mDefaultBackButtonImpl = defaultHardwareBackBtnHandler;
        this.mJavaScriptExecutorFactory = javaScriptExecutorFactory;
        this.mBundleLoader = jSBundleLoader;
        this.mJSMainModulePath = str;
        this.mPackages = new ArrayList();
        this.mUseDeveloperSupport = z;
        Systrace.beginSection(0L, "ReactInstanceManager.initDevSupportManager");
        this.mDevSupportManager = DevSupportManagerFactory.create(context, createDevHelperInterface(), this.mJSMainModulePath, z, redBoxHandler, devBundleDownloadListener, i, map);
        Systrace.endSection(0L);
        this.mBridgeIdleDebugListener = notThreadSafeBridgeIdleDebugListener;
        this.mLifecycleState = lifecycleState;
        this.mMemoryPressureRouter = new MemoryPressureRouter(context);
        this.mNativeModuleCallExceptionHandler = nativeModuleCallExceptionHandler;
        synchronized (this.mPackages) {
            PrinterHolder.getPrinter().logMessage(ReactDebugOverlayTags.RN_CORE, "RNCore: Use Split Packages");
            this.mPackages.add(new CoreModulesPackage(this, new DefaultHardwareBackBtnHandler() { // from class: com.facebook.react.ReactInstanceManager.1
                @Override // com.facebook.react.modules.core.DefaultHardwareBackBtnHandler
                public void invokeDefaultOnBackPressed() {
                    ReactInstanceManager.this.invokeDefaultOnBackPressed();
                }
            }, uIImplementationProvider, z2, i2));
            if (this.mUseDeveloperSupport) {
                this.mPackages.add(new DebugCorePackage());
            }
            this.mPackages.addAll(list);
        }
        this.mJSIModulePackage = jSIModulePackage;
        ReactChoreographer.initialize();
        if (this.mUseDeveloperSupport) {
            this.mDevSupportManager.startInspector();
        }
    }

    private void attachRootViewToInstance(final ReactRoot reactRoot) {
        final int addRootView;
        FLog.d(ReactConstants.TAG, "ReactInstanceManager.attachRootViewToInstance()");
        Systrace.beginSection(0L, "attachRootViewToInstance");
        UIManager uIManager = UIManagerHelper.getUIManager(this.mCurrentReactContext, reactRoot.getUIManagerType());
        if (uIManager != null) {
            Bundle appProperties = reactRoot.getAppProperties();
            if (reactRoot.getUIManagerType() == 2) {
                addRootView = uIManager.startSurface(reactRoot.getRootViewGroup(), reactRoot.getJSModuleName(), appProperties == null ? new WritableNativeMap() : Arguments.fromBundle(appProperties), reactRoot.getWidthMeasureSpec(), reactRoot.getHeightMeasureSpec());
                reactRoot.setRootViewTag(addRootView);
                reactRoot.setShouldLogContentAppeared(true);
            } else {
                addRootView = uIManager.addRootView(reactRoot.getRootViewGroup(), appProperties == null ? new WritableNativeMap() : Arguments.fromBundle(appProperties), reactRoot.getInitialUITemplate());
                reactRoot.setRootViewTag(addRootView);
                reactRoot.runApplication();
            }
            Systrace.beginAsyncSection(0L, "pre_rootView.onAttachedToReactInstance", addRootView);
            UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.ReactInstanceManager.9
                @Override // java.lang.Runnable
                public void run() {
                    Systrace.endAsyncSection(0L, "pre_rootView.onAttachedToReactInstance", addRootView);
                    reactRoot.onStage(101);
                }
            });
            Systrace.endSection(0L);
            return;
        }
        throw new IllegalStateException("Unable to attach a rootView to ReactInstance when UIManager is not properly initialized.");
    }

    public static ReactInstanceManagerBuilder builder() {
        return new ReactInstanceManagerBuilder();
    }

    private void clearReactRoot(ReactRoot reactRoot) {
        reactRoot.getRootViewGroup().removeAllViews();
        reactRoot.getRootViewGroup().setId(-1);
    }

    private ReactInstanceManagerDevHelper createDevHelperInterface() {
        return new ReactInstanceManagerDevHelper() { // from class: com.facebook.react.ReactInstanceManager.2
            @Override // com.facebook.react.devsupport.ReactInstanceManagerDevHelper
            @Nullable
            public View createRootView(String str) {
                Activity currentActivity = getCurrentActivity();
                if (currentActivity != null) {
                    ReactRootView reactRootView = new ReactRootView(currentActivity);
                    reactRootView.startReactApplication(ReactInstanceManager.this, str, null);
                    return reactRootView;
                }
                return null;
            }

            @Override // com.facebook.react.devsupport.ReactInstanceManagerDevHelper
            public void destroyRootView(View view) {
                FLog.e(ReactInstanceManager.TAG, "destroyRootView called");
                if (view instanceof ReactRootView) {
                    FLog.e(ReactInstanceManager.TAG, "destroyRootView called, unmountReactApplication");
                    ((ReactRootView) view).unmountReactApplication();
                }
            }

            @Override // com.facebook.react.devsupport.ReactInstanceManagerDevHelper
            @Nullable
            public Activity getCurrentActivity() {
                return ReactInstanceManager.this.mCurrentActivity;
            }

            @Override // com.facebook.react.devsupport.ReactInstanceManagerDevHelper
            public JavaScriptExecutorFactory getJavaScriptExecutorFactory() {
                return ReactInstanceManager.this.getJSExecutorFactory();
            }

            @Override // com.facebook.react.devsupport.ReactInstanceManagerDevHelper
            public void onJSBundleLoadedFromServer() {
                ReactInstanceManager.this.onJSBundleLoadedFromServer();
            }

            @Override // com.facebook.react.devsupport.ReactInstanceManagerDevHelper
            public void onReloadWithJSDebugger(JavaJSExecutor.Factory factory) {
                ReactInstanceManager.this.onReloadWithJSDebugger(factory);
            }

            @Override // com.facebook.react.devsupport.ReactInstanceManagerDevHelper
            public void toggleElementInspector() {
                ReactInstanceManager.this.toggleElementInspector();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ReactApplicationContext createReactContext(JavaScriptExecutor javaScriptExecutor, JSBundleLoader jSBundleLoader) {
        FLog.d(ReactConstants.TAG, "ReactInstanceManager.createReactContext()");
        ReactMarker.logMarker(ReactMarkerConstants.CREATE_REACT_CONTEXT_START, javaScriptExecutor.getName());
        ReactApplicationContext reactApplicationContext = new ReactApplicationContext(this.mApplicationContext);
        NativeModuleCallExceptionHandler nativeModuleCallExceptionHandler = this.mNativeModuleCallExceptionHandler;
        if (nativeModuleCallExceptionHandler == null) {
            nativeModuleCallExceptionHandler = this.mDevSupportManager;
        }
        reactApplicationContext.setNativeModuleCallExceptionHandler(nativeModuleCallExceptionHandler);
        CatalystInstanceImpl.Builder nativeModuleCallExceptionHandler2 = new CatalystInstanceImpl.Builder().setReactQueueConfigurationSpec(ReactQueueConfigurationSpec.createDefault()).setJSExecutor(javaScriptExecutor).setRegistry(processPackages(reactApplicationContext, this.mPackages, false)).setJSBundleLoader(jSBundleLoader).setNativeModuleCallExceptionHandler(nativeModuleCallExceptionHandler);
        ReactMarker.logMarker(ReactMarkerConstants.CREATE_CATALYST_INSTANCE_START);
        Systrace.beginSection(0L, "createCatalystInstance");
        try {
            CatalystInstanceImpl build = nativeModuleCallExceptionHandler2.build();
            Systrace.endSection(0L);
            ReactMarker.logMarker(ReactMarkerConstants.CREATE_CATALYST_INSTANCE_END);
            reactApplicationContext.initializeWithInstance(build);
            JSIModulePackage jSIModulePackage = this.mJSIModulePackage;
            if (jSIModulePackage != null) {
                build.addJSIModules(jSIModulePackage.getJSIModules(reactApplicationContext, build.getJavaScriptContextHolder()));
                if (ReactFeatureFlags.useTurboModules) {
                    JSIModule jSIModule = build.getJSIModule(JSIModuleType.TurboModuleManager);
                    build.setTurboModuleManager(jSIModule);
                    TurboModuleRegistry turboModuleRegistry = (TurboModuleRegistry) jSIModule;
                    for (String str : turboModuleRegistry.getEagerInitModuleNames()) {
                        turboModuleRegistry.getModule(str);
                    }
                }
            }
            if (ReactFeatureFlags.eagerInitializeFabric) {
                build.getJSIModule(JSIModuleType.UIManager);
            }
            NotThreadSafeBridgeIdleDebugListener notThreadSafeBridgeIdleDebugListener = this.mBridgeIdleDebugListener;
            if (notThreadSafeBridgeIdleDebugListener != null) {
                build.addBridgeIdleDebugListener(notThreadSafeBridgeIdleDebugListener);
            }
            if (Systrace.isTracing(0L)) {
                build.setGlobalVariable("__RCTProfileIsProfiling", "true");
            }
            ReactMarker.logMarker(ReactMarkerConstants.PRE_RUN_JS_BUNDLE_START);
            Systrace.beginSection(0L, "runJSBundle");
            build.runJSBundle();
            Systrace.endSection(0L);
            return reactApplicationContext;
        } catch (Throwable th) {
            Systrace.endSection(0L);
            ReactMarker.logMarker(ReactMarkerConstants.CREATE_CATALYST_INSTANCE_END);
            throw th;
        }
    }

    private void detachViewFromInstance(ReactRoot reactRoot, CatalystInstance catalystInstance) {
        FLog.d(ReactConstants.TAG, "ReactInstanceManager.detachViewFromInstance()");
        UiThreadUtil.assertOnUiThread();
        if (reactRoot.getUIManagerType() == 2) {
            ((ReactFabric) catalystInstance.getJSModule(ReactFabric.class)).unmountComponentAtNode(reactRoot.getRootViewTag());
        } else {
            ((AppRegistry) catalystInstance.getJSModule(AppRegistry.class)).unmountApplicationComponentAtRootTag(reactRoot.getRootViewTag());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JavaScriptExecutorFactory getJSExecutorFactory() {
        return this.mJavaScriptExecutorFactory;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void initializeSoLoaderIfNecessary(Context context) {
        SoLoader.init(context, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void invokeDefaultOnBackPressed() {
        UiThreadUtil.assertOnUiThread();
        DefaultHardwareBackBtnHandler defaultHardwareBackBtnHandler = this.mDefaultBackButtonImpl;
        if (defaultHardwareBackBtnHandler != null) {
            defaultHardwareBackBtnHandler.invokeDefaultOnBackPressed();
        }
    }

    private void logOnDestroy() {
        FLog.d(TAG, "ReactInstanceManager.destroy called", (Throwable) new RuntimeException("ReactInstanceManager.destroy called"));
    }

    private synchronized void moveReactContextToCurrentLifecycleState() {
        if (this.mLifecycleState == LifecycleState.RESUMED) {
            moveToResumedLifecycleState(true);
        }
    }

    private synchronized void moveToBeforeCreateLifecycleState() {
        ReactContext currentReactContext = getCurrentReactContext();
        if (currentReactContext != null) {
            if (this.mLifecycleState == LifecycleState.RESUMED) {
                currentReactContext.onHostPause();
                this.mLifecycleState = LifecycleState.BEFORE_RESUME;
            }
            if (this.mLifecycleState == LifecycleState.BEFORE_RESUME) {
                currentReactContext.onHostDestroy();
            }
        }
        this.mLifecycleState = LifecycleState.BEFORE_CREATE;
    }

    private synchronized void moveToBeforeResumeLifecycleState() {
        ReactContext currentReactContext = getCurrentReactContext();
        if (currentReactContext != null) {
            if (this.mLifecycleState == LifecycleState.BEFORE_CREATE) {
                currentReactContext.onHostResume(this.mCurrentActivity);
                currentReactContext.onHostPause();
            } else if (this.mLifecycleState == LifecycleState.RESUMED) {
                currentReactContext.onHostPause();
            }
        }
        this.mLifecycleState = LifecycleState.BEFORE_RESUME;
    }

    private synchronized void moveToResumedLifecycleState(boolean z) {
        ReactContext currentReactContext = getCurrentReactContext();
        if (currentReactContext != null && (z || this.mLifecycleState == LifecycleState.BEFORE_RESUME || this.mLifecycleState == LifecycleState.BEFORE_CREATE)) {
            currentReactContext.onHostResume(this.mCurrentActivity);
        }
        this.mLifecycleState = LifecycleState.RESUMED;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @ThreadConfined(ThreadConfined.UI)
    public void onJSBundleLoadedFromServer() {
        FLog.d(ReactConstants.TAG, "ReactInstanceManager.onJSBundleLoadedFromServer()");
        recreateReactContextInBackground(this.mJavaScriptExecutorFactory, JSBundleLoader.createCachedBundleFromNetworkLoader(this.mDevSupportManager.getSourceUrl(), this.mDevSupportManager.getDownloadedJSBundleFile()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    @ThreadConfined(ThreadConfined.UI)
    public void onReloadWithJSDebugger(JavaJSExecutor.Factory factory) {
        FLog.d(ReactConstants.TAG, "ReactInstanceManager.onReloadWithJSDebugger()");
        recreateReactContextInBackground(new ProxyJavaScriptExecutor.Factory(factory), JSBundleLoader.createRemoteDebuggerBundleLoader(this.mDevSupportManager.getJSBundleURLForRemoteDebugging(), this.mDevSupportManager.getSourceUrl()));
    }

    private void processPackage(ReactPackage reactPackage, NativeModuleRegistryBuilder nativeModuleRegistryBuilder) {
        SystraceMessage.beginSection(0L, "processPackage").arg("className", reactPackage.getClass().getSimpleName()).flush();
        boolean z = reactPackage instanceof ReactPackageLogger;
        if (z) {
            ((ReactPackageLogger) reactPackage).startProcessPackage();
        }
        nativeModuleRegistryBuilder.processPackage(reactPackage);
        if (z) {
            ((ReactPackageLogger) reactPackage).endProcessPackage();
        }
        SystraceMessage.endSection(0L).flush();
    }

    private NativeModuleRegistry processPackages(ReactApplicationContext reactApplicationContext, List<ReactPackage> list, boolean z) {
        NativeModuleRegistryBuilder nativeModuleRegistryBuilder = new NativeModuleRegistryBuilder(reactApplicationContext, this);
        ReactMarker.logMarker(ReactMarkerConstants.PROCESS_PACKAGES_START);
        synchronized (this.mPackages) {
            Iterator<ReactPackage> it2 = list.iterator();
            while (true) {
                if (it2.hasNext()) {
                    ReactPackage next = it2.next();
                    if (!z || !this.mPackages.contains(next)) {
                        Systrace.beginSection(0L, "createAndProcessCustomReactPackage");
                        if (z) {
                            this.mPackages.add(next);
                        }
                        processPackage(next, nativeModuleRegistryBuilder);
                        Systrace.endSection(0L);
                    }
                }
            }
        }
        ReactMarker.logMarker(ReactMarkerConstants.PROCESS_PACKAGES_END);
        ReactMarker.logMarker(ReactMarkerConstants.BUILD_NATIVE_MODULE_REGISTRY_START);
        Systrace.beginSection(0L, "buildNativeModuleRegistry");
        try {
            return nativeModuleRegistryBuilder.build();
        } finally {
            Systrace.endSection(0L);
            ReactMarker.logMarker(ReactMarkerConstants.BUILD_NATIVE_MODULE_REGISTRY_END);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @ThreadConfined(ThreadConfined.UI)
    public void recreateReactContextInBackgroundFromBundleLoader() {
        FLog.d(TAG, "ReactInstanceManager.recreateReactContextInBackgroundFromBundleLoader()");
        PrinterHolder.getPrinter().logMessage(ReactDebugOverlayTags.RN_CORE, "RNCore: load from BundleLoader");
        recreateReactContextInBackground(this.mJavaScriptExecutorFactory, this.mBundleLoader);
    }

    @ThreadConfined(ThreadConfined.UI)
    private void recreateReactContextInBackgroundInner() {
        FLog.d(TAG, "ReactInstanceManager.recreateReactContextInBackgroundInner()");
        PrinterHolder.getPrinter().logMessage(ReactDebugOverlayTags.RN_CORE, "RNCore: recreateReactContextInBackground");
        UiThreadUtil.assertOnUiThread();
        if (this.mUseDeveloperSupport && this.mJSMainModulePath != null) {
            final DeveloperSettings devSettings = this.mDevSupportManager.getDevSettings();
            if (!Systrace.isTracing(0L)) {
                if (this.mBundleLoader == null) {
                    this.mDevSupportManager.handleReloadJS();
                    return;
                } else {
                    this.mDevSupportManager.isPackagerRunning(new PackagerStatusCallback() { // from class: com.facebook.react.ReactInstanceManager.3
                        @Override // com.facebook.react.devsupport.interfaces.PackagerStatusCallback
                        public void onPackagerStatusFetched(final boolean z) {
                            UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.ReactInstanceManager.3.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    if (z) {
                                        ReactInstanceManager.this.mDevSupportManager.handleReloadJS();
                                    } else if (ReactInstanceManager.this.mDevSupportManager.hasUpToDateJSBundleInCache() && !devSettings.isRemoteJSDebugEnabled()) {
                                        ReactInstanceManager.this.onJSBundleLoadedFromServer();
                                    } else {
                                        devSettings.setRemoteJSDebugEnabled(false);
                                        ReactInstanceManager.this.recreateReactContextInBackgroundFromBundleLoader();
                                    }
                                }
                            });
                        }
                    });
                    return;
                }
            }
        }
        recreateReactContextInBackgroundFromBundleLoader();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @ThreadConfined(ThreadConfined.UI)
    public void runCreateReactContextOnNewThread(final ReactContextInitParams reactContextInitParams) {
        FLog.d(ReactConstants.TAG, "ReactInstanceManager.runCreateReactContextOnNewThread()");
        UiThreadUtil.assertOnUiThread();
        synchronized (this.mAttachedReactRoots) {
            synchronized (this.mReactContextLock) {
                if (this.mCurrentReactContext != null) {
                    tearDownReactContext(this.mCurrentReactContext);
                    this.mCurrentReactContext = null;
                }
            }
        }
        this.mCreateReactContextThread = new Thread(null, new Runnable() { // from class: com.facebook.react.ReactInstanceManager.5
            @Override // java.lang.Runnable
            public void run() {
                ReactMarker.logMarker(ReactMarkerConstants.REACT_CONTEXT_THREAD_END);
                synchronized (ReactInstanceManager.this.mHasStartedDestroying) {
                    while (ReactInstanceManager.this.mHasStartedDestroying.booleanValue()) {
                        try {
                            ReactInstanceManager.this.mHasStartedDestroying.wait();
                        } catch (InterruptedException unused) {
                        }
                    }
                }
                ReactInstanceManager.this.mHasStartedCreatingInitialContext = true;
                try {
                    Process.setThreadPriority(-4);
                    ReactMarker.logMarker(ReactMarkerConstants.VM_INIT);
                    final ReactApplicationContext createReactContext = ReactInstanceManager.this.createReactContext(reactContextInitParams.getJsExecutorFactory().create(), reactContextInitParams.getJsBundleLoader());
                    ReactInstanceManager.this.mCreateReactContextThread = null;
                    ReactMarker.logMarker(ReactMarkerConstants.PRE_SETUP_REACT_CONTEXT_START);
                    Runnable runnable = new Runnable() { // from class: com.facebook.react.ReactInstanceManager.5.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (ReactInstanceManager.this.mPendingReactContextInitParams != null) {
                                ReactInstanceManager reactInstanceManager = ReactInstanceManager.this;
                                reactInstanceManager.runCreateReactContextOnNewThread(reactInstanceManager.mPendingReactContextInitParams);
                                ReactInstanceManager.this.mPendingReactContextInitParams = null;
                            }
                        }
                    };
                    createReactContext.runOnNativeModulesQueueThread(new Runnable() { // from class: com.facebook.react.ReactInstanceManager.5.2
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                ReactInstanceManager.this.setupReactContext(createReactContext);
                            } catch (Exception e) {
                                FLog.e(ReactConstants.TAG, "ReactInstanceManager caught exception in setupReactContext", e);
                                ReactInstanceManager.this.mDevSupportManager.handleException(e);
                            }
                        }
                    });
                    UiThreadUtil.runOnUiThread(runnable);
                } catch (Exception e) {
                    ReactInstanceManager.this.mDevSupportManager.handleException(e);
                }
            }
        }, "create_react_context");
        ReactMarker.logMarker(ReactMarkerConstants.REACT_CONTEXT_THREAD_START);
        this.mCreateReactContextThread.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setupReactContext(final ReactApplicationContext reactApplicationContext) {
        FLog.d(ReactConstants.TAG, "ReactInstanceManager.setupReactContext()");
        ReactMarker.logMarker(ReactMarkerConstants.PRE_SETUP_REACT_CONTEXT_END);
        ReactMarker.logMarker(ReactMarkerConstants.SETUP_REACT_CONTEXT_START);
        Systrace.beginSection(0L, "setupReactContext");
        synchronized (this.mAttachedReactRoots) {
            synchronized (this.mReactContextLock) {
                this.mCurrentReactContext = (ReactContext) Assertions.assertNotNull(reactApplicationContext);
            }
            CatalystInstance catalystInstance = (CatalystInstance) Assertions.assertNotNull(reactApplicationContext.getCatalystInstance());
            catalystInstance.initialize();
            this.mDevSupportManager.onNewReactContextCreated(reactApplicationContext);
            this.mMemoryPressureRouter.addMemoryPressureListener(catalystInstance);
            moveReactContextToCurrentLifecycleState();
            ReactMarker.logMarker(ReactMarkerConstants.ATTACH_MEASURED_ROOT_VIEWS_START);
            for (ReactRoot reactRoot : this.mAttachedReactRoots) {
                attachRootViewToInstance(reactRoot);
            }
            ReactMarker.logMarker(ReactMarkerConstants.ATTACH_MEASURED_ROOT_VIEWS_END);
        }
        final ReactInstanceEventListener[] reactInstanceEventListenerArr = (ReactInstanceEventListener[]) this.mReactInstanceEventListeners.toArray(new ReactInstanceEventListener[this.mReactInstanceEventListeners.size()]);
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.ReactInstanceManager.6
            @Override // java.lang.Runnable
            public void run() {
                ReactInstanceEventListener[] reactInstanceEventListenerArr2;
                for (ReactInstanceEventListener reactInstanceEventListener : reactInstanceEventListenerArr) {
                    if (reactInstanceEventListener != null) {
                        reactInstanceEventListener.onReactContextInitialized(reactApplicationContext);
                    }
                }
            }
        });
        Systrace.endSection(0L);
        ReactMarker.logMarker(ReactMarkerConstants.SETUP_REACT_CONTEXT_END);
        reactApplicationContext.runOnJSQueueThread(new Runnable() { // from class: com.facebook.react.ReactInstanceManager.7
            @Override // java.lang.Runnable
            public void run() {
                Process.setThreadPriority(0);
                ReactMarker.logMarker(ReactMarkerConstants.CHANGE_THREAD_PRIORITY, "js_default");
            }
        });
        reactApplicationContext.runOnNativeModulesQueueThread(new Runnable() { // from class: com.facebook.react.ReactInstanceManager.8
            @Override // java.lang.Runnable
            public void run() {
                Process.setThreadPriority(0);
            }
        });
    }

    private void tearDownReactContext(ReactContext reactContext) {
        FLog.d(ReactConstants.TAG, "ReactInstanceManager.tearDownReactContext()");
        UiThreadUtil.assertOnUiThread();
        if (this.mLifecycleState == LifecycleState.RESUMED) {
            reactContext.onHostPause();
        }
        synchronized (this.mAttachedReactRoots) {
            for (ReactRoot reactRoot : this.mAttachedReactRoots) {
                clearReactRoot(reactRoot);
            }
        }
        this.mMemoryPressureRouter.removeMemoryPressureListener(reactContext.getCatalystInstance());
        reactContext.destroy();
        this.mDevSupportManager.onReactInstanceDestroyed(reactContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toggleElementInspector() {
        ReactContext currentReactContext = getCurrentReactContext();
        if (currentReactContext != null && currentReactContext.hasActiveCatalystInstance()) {
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) currentReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("toggleElementInspector", null);
        } else {
            ReactSoftException.logSoftException(TAG, new ReactNoCrashSoftException("Cannot toggleElementInspector, CatalystInstance not available"));
        }
    }

    public void addReactInstanceEventListener(ReactInstanceEventListener reactInstanceEventListener) {
        this.mReactInstanceEventListeners.add(reactInstanceEventListener);
    }

    @ThreadConfined(ThreadConfined.UI)
    public void attachRootView(ReactRoot reactRoot) {
        UiThreadUtil.assertOnUiThread();
        this.mAttachedReactRoots.add(reactRoot);
        clearReactRoot(reactRoot);
        ReactContext currentReactContext = getCurrentReactContext();
        if (this.mCreateReactContextThread != null || currentReactContext == null) {
            return;
        }
        attachRootViewToInstance(reactRoot);
    }

    @ThreadConfined(ThreadConfined.UI)
    public void createReactContextInBackground() {
        FLog.d(TAG, "ReactInstanceManager.createReactContextInBackground()");
        UiThreadUtil.assertOnUiThread();
        if (!this.mHasStartedCreatingInitialContext) {
            this.mHasStartedCreatingInitialContext = true;
            recreateReactContextInBackgroundInner();
        }
    }

    @Nullable
    public ViewManager createViewManager(String str) {
        ViewManager createViewManager;
        synchronized (this.mReactContextLock) {
            ReactApplicationContext reactApplicationContext = (ReactApplicationContext) getCurrentReactContext();
            if (reactApplicationContext != null && reactApplicationContext.hasActiveCatalystInstance()) {
                synchronized (this.mPackages) {
                    for (ReactPackage reactPackage : this.mPackages) {
                        if ((reactPackage instanceof ViewManagerOnDemandReactPackage) && (createViewManager = ((ViewManagerOnDemandReactPackage) reactPackage).createViewManager(reactApplicationContext, str)) != null) {
                            return createViewManager;
                        }
                    }
                    return null;
                }
            }
            return null;
        }
    }

    @ThreadConfined(ThreadConfined.UI)
    public void destroy() {
        UiThreadUtil.assertOnUiThread();
        PrinterHolder.getPrinter().logMessage(ReactDebugOverlayTags.RN_CORE, "RNCore: Destroy");
        logOnDestroy();
        if (this.mHasStartedDestroying.booleanValue()) {
            FLog.e(ReactConstants.TAG, "ReactInstanceManager.destroy called: bail out, already destroying");
            return;
        }
        this.mHasStartedDestroying = true;
        if (this.mUseDeveloperSupport) {
            this.mDevSupportManager.setDevSupportEnabled(false);
            this.mDevSupportManager.stopInspector();
        }
        moveToBeforeCreateLifecycleState();
        if (this.mCreateReactContextThread != null) {
            this.mCreateReactContextThread = null;
        }
        this.mMemoryPressureRouter.destroy(this.mApplicationContext);
        synchronized (this.mReactContextLock) {
            if (this.mCurrentReactContext != null) {
                this.mCurrentReactContext.destroy();
                this.mCurrentReactContext = null;
            }
        }
        this.mHasStartedCreatingInitialContext = false;
        this.mCurrentActivity = null;
        ResourceDrawableIdHelper.getInstance().clear();
        this.mHasStartedDestroying = false;
        synchronized (this.mHasStartedDestroying) {
            this.mHasStartedDestroying.notifyAll();
        }
        FLog.d(ReactConstants.TAG, "ReactInstanceManager has been destroyed");
    }

    @ThreadConfined(ThreadConfined.UI)
    public void detachRootView(ReactRoot reactRoot) {
        UiThreadUtil.assertOnUiThread();
        synchronized (this.mAttachedReactRoots) {
            if (this.mAttachedReactRoots.contains(reactRoot)) {
                ReactContext currentReactContext = getCurrentReactContext();
                this.mAttachedReactRoots.remove(reactRoot);
                if (currentReactContext != null && currentReactContext.hasActiveCatalystInstance()) {
                    detachViewFromInstance(reactRoot, currentReactContext.getCatalystInstance());
                }
            }
        }
    }

    @Nullable
    @VisibleForTesting
    public ReactContext getCurrentReactContext() {
        ReactContext reactContext;
        synchronized (this.mReactContextLock) {
            reactContext = this.mCurrentReactContext;
        }
        return reactContext;
    }

    public DevSupportManager getDevSupportManager() {
        return this.mDevSupportManager;
    }

    public String getJsExecutorName() {
        return this.mJavaScriptExecutorFactory.toString();
    }

    public LifecycleState getLifecycleState() {
        return this.mLifecycleState;
    }

    public MemoryPressureRouter getMemoryPressureRouter() {
        return this.mMemoryPressureRouter;
    }

    public List<ViewManager> getOrCreateViewManagers(ReactApplicationContext reactApplicationContext) {
        List<ViewManager> list;
        ReactMarker.logMarker(ReactMarkerConstants.CREATE_VIEW_MANAGERS_START);
        Systrace.beginSection(0L, "createAllViewManagers");
        try {
            if (this.mViewManagers == null) {
                synchronized (this.mPackages) {
                    if (this.mViewManagers == null) {
                        this.mViewManagers = new ArrayList();
                        for (ReactPackage reactPackage : this.mPackages) {
                            this.mViewManagers.addAll(reactPackage.createViewManagers(reactApplicationContext));
                        }
                        list = this.mViewManagers;
                    }
                }
                return list;
            }
            list = this.mViewManagers;
            return list;
        } finally {
            Systrace.endSection(0L);
            ReactMarker.logMarker(ReactMarkerConstants.CREATE_VIEW_MANAGERS_END);
        }
    }

    public List<ReactPackage> getPackages() {
        return new ArrayList(this.mPackages);
    }

    @Nullable
    public List<String> getViewManagerNames() {
        ArrayList arrayList;
        List<String> viewManagerNames;
        Systrace.beginSection(0L, "ReactInstanceManager.getViewManagerNames");
        synchronized (this.mReactContextLock) {
            ReactApplicationContext reactApplicationContext = (ReactApplicationContext) getCurrentReactContext();
            if (reactApplicationContext != null && reactApplicationContext.hasActiveCatalystInstance()) {
                synchronized (this.mPackages) {
                    HashSet hashSet = new HashSet();
                    for (ReactPackage reactPackage : this.mPackages) {
                        SystraceMessage.beginSection(0L, "ReactInstanceManager.getViewManagerName").arg("Package", reactPackage.getClass().getSimpleName()).flush();
                        if ((reactPackage instanceof ViewManagerOnDemandReactPackage) && (viewManagerNames = ((ViewManagerOnDemandReactPackage) reactPackage).getViewManagerNames(reactApplicationContext)) != null) {
                            hashSet.addAll(viewManagerNames);
                        }
                        SystraceMessage.endSection(0L).flush();
                    }
                    Systrace.endSection(0L);
                    arrayList = new ArrayList(hashSet);
                }
                return arrayList;
            }
            return null;
        }
    }

    public boolean hasStartedCreatingInitialContext() {
        return this.mHasStartedCreatingInitialContext;
    }

    @ThreadConfined(ThreadConfined.UI)
    public void onActivityResult(Activity activity, int i, int i2, Intent intent) {
        ReactContext currentReactContext = getCurrentReactContext();
        if (currentReactContext != null) {
            currentReactContext.onActivityResult(activity, i, i2, intent);
        }
    }

    public void onBackPressed() {
        UiThreadUtil.assertOnUiThread();
        ReactContext reactContext = this.mCurrentReactContext;
        if (reactContext == null) {
            FLog.w(TAG, "Instance detached from instance manager");
            invokeDefaultOnBackPressed();
            return;
        }
        DeviceEventManagerModule deviceEventManagerModule = (DeviceEventManagerModule) reactContext.getNativeModule(DeviceEventManagerModule.class);
        if (deviceEventManagerModule == null) {
            return;
        }
        deviceEventManagerModule.emitHardwareBackPressed();
    }

    @ThreadConfined(ThreadConfined.UI)
    public void onConfigurationChanged(Context context, @Nullable Configuration configuration) {
        AppearanceModule appearanceModule;
        UiThreadUtil.assertOnUiThread();
        ReactContext currentReactContext = getCurrentReactContext();
        if (currentReactContext == null || (appearanceModule = (AppearanceModule) currentReactContext.getNativeModule(AppearanceModule.class)) == null) {
            return;
        }
        appearanceModule.onConfigurationChanged(context);
    }

    @ThreadConfined(ThreadConfined.UI)
    public void onHostDestroy() {
        UiThreadUtil.assertOnUiThread();
        if (this.mUseDeveloperSupport) {
            this.mDevSupportManager.setDevSupportEnabled(false);
        }
        moveToBeforeCreateLifecycleState();
        this.mCurrentActivity = null;
    }

    @ThreadConfined(ThreadConfined.UI)
    public void onHostPause() {
        UiThreadUtil.assertOnUiThread();
        this.mDefaultBackButtonImpl = null;
        if (this.mUseDeveloperSupport) {
            this.mDevSupportManager.setDevSupportEnabled(false);
        }
        moveToBeforeResumeLifecycleState();
    }

    @ThreadConfined(ThreadConfined.UI)
    public void onHostResume(Activity activity, DefaultHardwareBackBtnHandler defaultHardwareBackBtnHandler) {
        UiThreadUtil.assertOnUiThread();
        this.mDefaultBackButtonImpl = defaultHardwareBackBtnHandler;
        onHostResume(activity);
    }

    @ThreadConfined(ThreadConfined.UI)
    public void onNewIntent(Intent intent) {
        DeviceEventManagerModule deviceEventManagerModule;
        UiThreadUtil.assertOnUiThread();
        ReactContext currentReactContext = getCurrentReactContext();
        if (currentReactContext == null) {
            FLog.w(TAG, "Instance detached from instance manager");
            return;
        }
        String action = intent.getAction();
        Uri data = intent.getData();
        if (data != null && (("android.intent.action.VIEW".equals(action) || "android.nfc.action.NDEF_DISCOVERED".equals(action)) && (deviceEventManagerModule = (DeviceEventManagerModule) currentReactContext.getNativeModule(DeviceEventManagerModule.class)) != null)) {
            deviceEventManagerModule.emitNewIntentReceived(data);
        }
        currentReactContext.onNewIntent(this.mCurrentActivity, intent);
    }

    @ThreadConfined(ThreadConfined.UI)
    public void onWindowFocusChange(boolean z) {
        UiThreadUtil.assertOnUiThread();
        ReactContext currentReactContext = getCurrentReactContext();
        if (currentReactContext != null) {
            currentReactContext.onWindowFocusChange(z);
        }
    }

    @ThreadConfined(ThreadConfined.UI)
    public void recreateReactContextInBackground() {
        Assertions.assertCondition(this.mHasStartedCreatingInitialContext, "recreateReactContextInBackground should only be called after the initial createReactContextInBackground call.");
        recreateReactContextInBackgroundInner();
    }

    public void removeReactInstanceEventListener(ReactInstanceEventListener reactInstanceEventListener) {
        this.mReactInstanceEventListeners.remove(reactInstanceEventListener);
    }

    @ThreadConfined(ThreadConfined.UI)
    public void showDevOptionsDialog() {
        UiThreadUtil.assertOnUiThread();
        this.mDevSupportManager.showDevOptionsDialog();
    }

    @ThreadConfined(ThreadConfined.UI)
    private void recreateReactContextInBackground(JavaScriptExecutorFactory javaScriptExecutorFactory, JSBundleLoader jSBundleLoader) {
        FLog.d(ReactConstants.TAG, "ReactInstanceManager.recreateReactContextInBackground()");
        UiThreadUtil.assertOnUiThread();
        ReactContextInitParams reactContextInitParams = new ReactContextInitParams(javaScriptExecutorFactory, jSBundleLoader);
        if (this.mCreateReactContextThread == null) {
            runCreateReactContextOnNewThread(reactContextInitParams);
        } else {
            this.mPendingReactContextInitParams = reactContextInitParams;
        }
    }

    @ThreadConfined(ThreadConfined.UI)
    public void onHostResume(Activity activity) {
        UiThreadUtil.assertOnUiThread();
        this.mCurrentActivity = activity;
        if (this.mUseDeveloperSupport) {
            final View decorView = this.mCurrentActivity.getWindow().getDecorView();
            if (!ViewCompat.isAttachedToWindow(decorView)) {
                decorView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.facebook.react.ReactInstanceManager.4
                    @Override // android.view.View.OnAttachStateChangeListener
                    public void onViewAttachedToWindow(View view) {
                        decorView.removeOnAttachStateChangeListener(this);
                        ReactInstanceManager.this.mDevSupportManager.setDevSupportEnabled(true);
                    }

                    @Override // android.view.View.OnAttachStateChangeListener
                    public void onViewDetachedFromWindow(View view) {
                    }
                });
            } else {
                this.mDevSupportManager.setDevSupportEnabled(true);
            }
        }
        moveToResumedLifecycleState(false);
    }

    @ThreadConfined(ThreadConfined.UI)
    public void onHostDestroy(Activity activity) {
        if (activity == this.mCurrentActivity) {
            onHostDestroy();
        }
    }

    @ThreadConfined(ThreadConfined.UI)
    public void onHostPause(Activity activity) {
        Assertions.assertNotNull(this.mCurrentActivity);
        boolean z = activity == this.mCurrentActivity;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Pausing an activity that is not the current activity, this is incorrect! Current activity: ");
        outline107.append(this.mCurrentActivity.getClass().getSimpleName());
        outline107.append(" Paused activity: ");
        outline107.append(activity.getClass().getSimpleName());
        Assertions.assertCondition(z, outline107.toString());
        onHostPause();
    }
}
