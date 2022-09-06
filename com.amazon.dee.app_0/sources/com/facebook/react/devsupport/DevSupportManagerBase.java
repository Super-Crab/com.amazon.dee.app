package com.facebook.react.devsupport;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.hardware.SensorManager;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.common.logging.FLog;
import com.facebook.debug.holder.PrinterHolder;
import com.facebook.debug.tags.ReactDebugOverlayTags;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.R;
import com.facebook.react.bridge.DefaultNativeModuleCallExceptionHandler;
import com.facebook.react.bridge.JSBundleLoader;
import com.facebook.react.bridge.JavaJSExecutor;
import com.facebook.react.bridge.JavaScriptExecutorFactory;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.DebugServerException;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.common.ShakeDetector;
import com.facebook.react.common.futures.SimpleSettableFuture;
import com.facebook.react.devsupport.BundleDownloader;
import com.facebook.react.devsupport.DevInternalSettings;
import com.facebook.react.devsupport.DevServerHelper;
import com.facebook.react.devsupport.InspectorPackagerConnection;
import com.facebook.react.devsupport.JSCHeapCapture;
import com.facebook.react.devsupport.RedBoxHandler;
import com.facebook.react.devsupport.WebsocketJavaScriptExecutor;
import com.facebook.react.devsupport.interfaces.DevBundleDownloadListener;
import com.facebook.react.devsupport.interfaces.DevOptionHandler;
import com.facebook.react.devsupport.interfaces.DevSplitBundleCallback;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.devsupport.interfaces.ErrorCustomizer;
import com.facebook.react.devsupport.interfaces.PackagerStatusCallback;
import com.facebook.react.devsupport.interfaces.StackFrame;
import com.facebook.react.modules.core.RCTNativeAppEventEmitter;
import com.facebook.react.modules.debug.interfaces.DeveloperSettings;
import com.facebook.react.packagerconnection.RequestHandler;
import com.facebook.react.packagerconnection.Responder;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/* loaded from: classes2.dex */
public abstract class DevSupportManagerBase implements DevSupportManager, DevServerHelper.PackagerCommandListener, DevInternalSettings.Listener {
    public static final String EMOJI_FACE_WITH_NO_GOOD_GESTURE = " 🙅";
    public static final String EMOJI_HUNDRED_POINTS_SYMBOL = " 💯";
    private static final String EXOPACKAGE_LOCATION_FORMAT = "/data/local/tmp/exopackage/%s//secondary-dex";
    private static final String FLIPPER_DEBUGGER_URL = "flipper://null/Hermesdebuggerrn?device=React%20Native";
    private static final String FLIPPER_DEVTOOLS_URL = "flipper://null/React?device=React%20Native";
    private static final int JAVA_ERROR_COOKIE = -1;
    private static final int JSEXCEPTION_ERROR_COOKIE = -1;
    private static final String JS_BUNDLE_FILE_NAME = "ReactNativeDevBundle.js";
    private static final String JS_SPLIT_BUNDLES_DIR_NAME = "dev_js_split_bundles";
    private static final String RELOAD_APP_ACTION_SUFFIX = ".RELOAD_APP_ACTION";
    private final Context mApplicationContext;
    @Nullable
    private DevBundleDownloadListener mBundleDownloadListener;
    private InspectorPackagerConnection.BundleStatus mBundleStatus;
    @Nullable
    private ReactContext mCurrentContext;
    private final LinkedHashMap<String, DevOptionHandler> mCustomDevOptions;
    @Nullable
    private Map<String, RequestHandler> mCustomPackagerCommandHandlers;
    @Nullable
    private DebugOverlayController mDebugOverlayController;
    private final DefaultNativeModuleCallExceptionHandler mDefaultNativeModuleCallExceptionHandler;
    private final DevLoadingViewController mDevLoadingViewController;
    private boolean mDevLoadingViewVisible;
    @Nullable
    private AlertDialog mDevOptionsDialog;
    private final DevServerHelper mDevServerHelper;
    private DevInternalSettings mDevSettings;
    @Nullable
    private List<ErrorCustomizer> mErrorCustomizers;
    private final List<ExceptionLogger> mExceptionLoggers;
    private boolean mIsDevSupportEnabled;
    private boolean mIsReceiverRegistered;
    private boolean mIsSamplingProfilerEnabled;
    private boolean mIsShakeDetectorStarted;
    @Nullable
    private final String mJSAppBundleName;
    private final File mJSBundleTempFile;
    private final File mJSSplitBundlesDir;
    private int mLastErrorCookie;
    @Nullable
    private StackFrame[] mLastErrorStack;
    @Nullable
    private String mLastErrorTitle;
    @Nullable
    private DevSupportManager.PackagerLocationCustomizer mPackagerLocationCustomizer;
    private int mPendingJSSplitBundleRequests;
    private final ReactInstanceManagerDevHelper mReactInstanceManagerHelper;
    @Nullable
    private RedBoxDialog mRedBoxDialog;
    @Nullable
    private RedBoxHandler mRedBoxHandler;
    private final BroadcastReceiver mReloadAppBroadcastReceiver;
    private final ShakeDetector mShakeDetector;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.facebook.react.devsupport.DevSupportManagerBase$20  reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass20 implements Runnable {
        final /* synthetic */ File val$bundleFile;
        final /* synthetic */ String val$bundleUrl;
        final /* synthetic */ CallbackWithBundleLoader val$callback;

        AnonymousClass20(String str, File file, CallbackWithBundleLoader callbackWithBundleLoader) {
            this.val$bundleUrl = str;
            this.val$bundleFile = file;
            this.val$callback = callbackWithBundleLoader;
        }

        @Override // java.lang.Runnable
        public void run() {
            DevSupportManagerBase.this.showSplitBundleDevLoadingView(this.val$bundleUrl);
            DevSupportManagerBase.this.mDevServerHelper.downloadBundleFromURL(new DevBundleDownloadListener() { // from class: com.facebook.react.devsupport.DevSupportManagerBase.20.1
                @Override // com.facebook.react.devsupport.interfaces.DevBundleDownloadListener
                public void onFailure(Exception exc) {
                    UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.devsupport.DevSupportManagerBase.20.1.2
                        @Override // java.lang.Runnable
                        public void run() {
                            DevSupportManagerBase.this.hideSplitBundleDevLoadingView();
                        }
                    });
                    AnonymousClass20 anonymousClass20 = AnonymousClass20.this;
                    anonymousClass20.val$callback.onError(anonymousClass20.val$bundleUrl, exc);
                }

                @Override // com.facebook.react.devsupport.interfaces.DevBundleDownloadListener
                public void onProgress(@Nullable String str, @Nullable Integer num, @Nullable Integer num2) {
                    DevSupportManagerBase.this.mDevLoadingViewController.updateProgress(str, num, num2);
                }

                @Override // com.facebook.react.devsupport.interfaces.DevBundleDownloadListener
                public void onSuccess() {
                    UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.devsupport.DevSupportManagerBase.20.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            DevSupportManagerBase.this.hideSplitBundleDevLoadingView();
                        }
                    });
                    ReactContext reactContext = DevSupportManagerBase.this.mCurrentContext;
                    if (reactContext != null) {
                        if (!reactContext.isBridgeless() && !reactContext.hasActiveCatalystInstance()) {
                            return;
                        }
                        AnonymousClass20 anonymousClass20 = AnonymousClass20.this;
                        AnonymousClass20.this.val$callback.onSuccess(JSBundleLoader.createCachedSplitBundleFromNetworkLoader(anonymousClass20.val$bundleUrl, anonymousClass20.val$bundleFile.getAbsolutePath()));
                    }
                }
            }, this.val$bundleFile, this.val$bundleUrl, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes2.dex */
    public interface BundleLoadCallback {
        void onSuccess();
    }

    /* loaded from: classes2.dex */
    public interface CallbackWithBundleLoader {
        void onError(String str, Throwable th);

        void onSuccess(JSBundleLoader jSBundleLoader);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public enum ErrorType {
        JS,
        NATIVE
    }

    /* loaded from: classes2.dex */
    private interface ExceptionLogger {
        void log(Exception exc);
    }

    /* loaded from: classes2.dex */
    private class JSExceptionLogger implements ExceptionLogger {
        private JSExceptionLogger() {
        }

        @Override // com.facebook.react.devsupport.DevSupportManagerBase.ExceptionLogger
        public void log(Exception exc) {
            StringBuilder sb = new StringBuilder(exc.getMessage() == null ? "Exception in native call from JS" : exc.getMessage());
            for (Throwable cause = exc.getCause(); cause != null; cause = cause.getCause()) {
                sb.append("\n\n");
                sb.append(cause.getMessage());
            }
            if (exc instanceof JSException) {
                FLog.e(ReactConstants.TAG, "Exception in native call from JS", exc);
                String stack = ((JSException) exc).getStack();
                sb.append("\n\n");
                sb.append(stack);
                DevSupportManagerBase.this.showNewError(sb.toString(), new StackFrame[0], -1, ErrorType.JS);
                return;
            }
            DevSupportManagerBase.this.showNewJavaError(sb.toString(), exc);
        }
    }

    public DevSupportManagerBase(Context context, ReactInstanceManagerDevHelper reactInstanceManagerDevHelper, @Nullable String str, boolean z, int i) {
        this(context, reactInstanceManagerDevHelper, str, z, null, null, i, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public WebsocketJavaScriptExecutor.JSExecutorConnectCallback getExecutorConnectCallback(final SimpleSettableFuture<Boolean> simpleSettableFuture) {
        return new WebsocketJavaScriptExecutor.JSExecutorConnectCallback() { // from class: com.facebook.react.devsupport.DevSupportManagerBase.27
            @Override // com.facebook.react.devsupport.WebsocketJavaScriptExecutor.JSExecutorConnectCallback
            public void onFailure(Throwable th) {
                DevSupportManagerBase.this.mDevLoadingViewController.hide();
                DevSupportManagerBase.this.mDevLoadingViewVisible = false;
                FLog.e(ReactConstants.TAG, "Failed to connect to debugger!", th);
                simpleSettableFuture.setException(new IOException(DevSupportManagerBase.this.mApplicationContext.getString(R.string.catalyst_debug_error), th));
            }

            @Override // com.facebook.react.devsupport.WebsocketJavaScriptExecutor.JSExecutorConnectCallback
            public void onSuccess() {
                simpleSettableFuture.set(true);
                DevSupportManagerBase.this.mDevLoadingViewController.hide();
                DevSupportManagerBase.this.mDevLoadingViewVisible = false;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getReloadAppAction(Context context) {
        return context.getPackageName() + RELOAD_APP_ACTION_SUFFIX;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleCaptureHeap(final Responder responder) {
        JSCHeapCapture jSCHeapCapture;
        ReactContext reactContext = this.mCurrentContext;
        if (reactContext == null || (jSCHeapCapture = (JSCHeapCapture) reactContext.getNativeModule(JSCHeapCapture.class)) == null) {
            return;
        }
        jSCHeapCapture.captureHeap(this.mApplicationContext.getCacheDir().getPath(), new JSCHeapCapture.CaptureCallback() { // from class: com.facebook.react.devsupport.DevSupportManagerBase.25
            @Override // com.facebook.react.devsupport.JSCHeapCapture.CaptureCallback
            public void onFailure(JSCHeapCapture.CaptureException captureException) {
                responder.error(captureException.toString());
            }

            @Override // com.facebook.react.devsupport.JSCHeapCapture.CaptureCallback
            public void onSuccess(File file) {
                responder.respond(file.toString());
            }
        });
    }

    private void hideDevOptionsDialog() {
        AlertDialog alertDialog = this.mDevOptionsDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
            this.mDevOptionsDialog = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @UiThread
    public void hideSplitBundleDevLoadingView() {
        int i = this.mPendingJSSplitBundleRequests - 1;
        this.mPendingJSSplitBundleRequests = i;
        if (i == 0) {
            this.mDevLoadingViewController.hide();
            this.mDevLoadingViewVisible = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Pair<String, StackFrame[]> processErrorCustomizers(Pair<String, StackFrame[]> pair) {
        List<ErrorCustomizer> list = this.mErrorCustomizers;
        if (list == null) {
            return pair;
        }
        for (ErrorCustomizer errorCustomizer : list) {
            Pair<String, StackFrame[]> customizeErrorInfo = errorCustomizer.customizeErrorInfo(pair);
            if (customizeErrorInfo != null) {
                pair = customizeErrorInfo;
            }
        }
        return pair;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reload() {
        UiThreadUtil.assertOnUiThread();
        if (this.mIsDevSupportEnabled) {
            DebugOverlayController debugOverlayController = this.mDebugOverlayController;
            if (debugOverlayController != null) {
                debugOverlayController.setFpsDebugViewVisible(this.mDevSettings.isFpsDebugEnabled());
            }
            if (!this.mIsShakeDetectorStarted) {
                this.mShakeDetector.start((SensorManager) this.mApplicationContext.getSystemService("sensor"));
                this.mIsShakeDetectorStarted = true;
            }
            if (!this.mIsReceiverRegistered) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction(getReloadAppAction(this.mApplicationContext));
                this.mApplicationContext.registerReceiver(this.mReloadAppBroadcastReceiver, intentFilter);
                this.mIsReceiverRegistered = true;
            }
            if (this.mDevLoadingViewVisible) {
                this.mDevLoadingViewController.showMessage("Reloading...");
            }
            this.mDevServerHelper.openPackagerConnection(getClass().getSimpleName(), this);
            return;
        }
        DebugOverlayController debugOverlayController2 = this.mDebugOverlayController;
        if (debugOverlayController2 != null) {
            debugOverlayController2.setFpsDebugViewVisible(false);
        }
        if (this.mIsShakeDetectorStarted) {
            this.mShakeDetector.stop();
            this.mIsShakeDetectorStarted = false;
        }
        if (this.mIsReceiverRegistered) {
            this.mApplicationContext.unregisterReceiver(this.mReloadAppBroadcastReceiver);
            this.mIsReceiverRegistered = false;
        }
        hideRedboxDialog();
        hideDevOptionsDialog();
        this.mDevLoadingViewController.hide();
        this.mDevServerHelper.closePackagerConnection();
    }

    private void reloadJSInProxyMode() {
        this.mDevServerHelper.launchJSDevtools();
        this.mReactInstanceManagerHelper.onReloadWithJSDebugger(new JavaJSExecutor.Factory() { // from class: com.facebook.react.devsupport.DevSupportManagerBase.26
            @Override // com.facebook.react.bridge.JavaJSExecutor.Factory
            public JavaJSExecutor create() throws Exception {
                WebsocketJavaScriptExecutor websocketJavaScriptExecutor = new WebsocketJavaScriptExecutor();
                SimpleSettableFuture simpleSettableFuture = new SimpleSettableFuture();
                websocketJavaScriptExecutor.connect(DevSupportManagerBase.this.mDevServerHelper.getWebsocketProxyURL(), DevSupportManagerBase.this.getExecutorConnectCallback(simpleSettableFuture));
                try {
                    simpleSettableFuture.get(90L, TimeUnit.SECONDS);
                    return websocketJavaScriptExecutor;
                } catch (InterruptedException e) {
                    e = e;
                    throw new RuntimeException(e);
                } catch (ExecutionException e2) {
                    throw ((Exception) e2.getCause());
                } catch (TimeoutException e3) {
                    e = e3;
                    throw new RuntimeException(e);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportBundleLoadingFailure(final Exception exc) {
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.devsupport.DevSupportManagerBase.30
            @Override // java.lang.Runnable
            public void run() {
                Exception exc2 = exc;
                if (exc2 instanceof DebugServerException) {
                    DevSupportManagerBase.this.showNewJavaError(((DebugServerException) exc2).getMessage(), exc);
                    return;
                }
                DevSupportManagerBase devSupportManagerBase = DevSupportManagerBase.this;
                devSupportManagerBase.showNewJavaError(devSupportManagerBase.mApplicationContext.getString(R.string.catalyst_reload_error), exc);
            }
        });
    }

    private void resetCurrentContext(@Nullable ReactContext reactContext) {
        if (this.mCurrentContext == reactContext) {
            return;
        }
        this.mCurrentContext = reactContext;
        DebugOverlayController debugOverlayController = this.mDebugOverlayController;
        if (debugOverlayController != null) {
            debugOverlayController.setFpsDebugViewVisible(false);
        }
        if (reactContext != null) {
            this.mDebugOverlayController = new DebugOverlayController(reactContext);
        }
        if (this.mCurrentContext != null) {
            try {
                URL url = new URL(getSourceUrl());
                ((HMRClient) this.mCurrentContext.getJSModule(HMRClient.class)).setup("android", url.getPath().substring(1), url.getHost(), url.getPort(), this.mDevSettings.isHotModuleReplacementEnabled());
            } catch (MalformedURLException e) {
                showNewJavaError(e.getMessage(), e);
            }
        }
        reloadSettings();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showNewError(@Nullable final String str, final StackFrame[] stackFrameArr, final int i, final ErrorType errorType) {
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.devsupport.DevSupportManagerBase.5
            @Override // java.lang.Runnable
            public void run() {
                if (DevSupportManagerBase.this.mRedBoxDialog == null) {
                    Activity currentActivity = DevSupportManagerBase.this.mReactInstanceManagerHelper.getCurrentActivity();
                    if (currentActivity != null && !currentActivity.isFinishing()) {
                        DevSupportManagerBase devSupportManagerBase = DevSupportManagerBase.this;
                        devSupportManagerBase.mRedBoxDialog = new RedBoxDialog(currentActivity, devSupportManagerBase, devSupportManagerBase.mRedBoxHandler);
                    } else {
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unable to launch redbox because react activity is not available, here is the error that redbox would've displayed: ");
                        outline107.append(str);
                        FLog.e(ReactConstants.TAG, outline107.toString());
                        return;
                    }
                }
                if (DevSupportManagerBase.this.mRedBoxDialog.isShowing()) {
                    return;
                }
                Pair processErrorCustomizers = DevSupportManagerBase.this.processErrorCustomizers(Pair.create(str, stackFrameArr));
                DevSupportManagerBase.this.mRedBoxDialog.setExceptionDetails((String) processErrorCustomizers.first, (StackFrame[]) processErrorCustomizers.second);
                DevSupportManagerBase.this.updateLastErrorInfo(str, stackFrameArr, i, errorType);
                if (DevSupportManagerBase.this.mRedBoxHandler != null && errorType == ErrorType.NATIVE) {
                    DevSupportManagerBase.this.mRedBoxHandler.handleRedbox(str, stackFrameArr, RedBoxHandler.ErrorType.NATIVE);
                }
                DevSupportManagerBase.this.mRedBoxDialog.resetReporting();
                DevSupportManagerBase.this.mRedBoxDialog.show();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    @UiThread
    public void showSplitBundleDevLoadingView(String str) {
        this.mDevLoadingViewController.showForUrl(str);
        this.mDevLoadingViewVisible = true;
        this.mPendingJSSplitBundleRequests++;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toggleJSSamplingProfiler() {
        JavaScriptExecutorFactory javaScriptExecutorFactory = this.mReactInstanceManagerHelper.getJavaScriptExecutorFactory();
        try {
            if (!this.mIsSamplingProfilerEnabled) {
                try {
                    try {
                        javaScriptExecutorFactory.startSamplingProfiler();
                        Toast.makeText(this.mApplicationContext, "Starting Sampling Profiler", 0).show();
                    } catch (UnsupportedOperationException unused) {
                        Context context = this.mApplicationContext;
                        Toast.makeText(context, javaScriptExecutorFactory.toString() + " does not support Sampling Profiler", 1).show();
                    }
                    return;
                } finally {
                    this.mIsSamplingProfilerEnabled = true;
                }
            }
            try {
                String path = File.createTempFile("sampling-profiler-trace", ".cpuprofile", this.mApplicationContext.getCacheDir()).getPath();
                javaScriptExecutorFactory.stopSamplingProfiler(path);
                Context context2 = this.mApplicationContext;
                Toast.makeText(context2, "Saved results from Profiler to " + path, 1).show();
            } catch (IOException unused2) {
                FLog.e(ReactConstants.TAG, "Could not create temporary file for saving results from Sampling Profiler");
            } catch (UnsupportedOperationException unused3) {
                Context context3 = this.mApplicationContext;
                Toast.makeText(context3, javaScriptExecutorFactory.toString() + "does not support Sampling Profiler", 1).show();
            }
        } finally {
            this.mIsSamplingProfilerEnabled = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateLastErrorInfo(@Nullable String str, StackFrame[] stackFrameArr, int i, ErrorType errorType) {
        this.mLastErrorTitle = str;
        this.mLastErrorStack = stackFrameArr;
        this.mLastErrorCookie = i;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void addCustomDevOption(String str, DevOptionHandler devOptionHandler) {
        this.mCustomDevOptions.put(str, devOptionHandler);
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    @Nullable
    public View createRootView(String str) {
        return this.mReactInstanceManagerHelper.createRootView(str);
    }

    @Override // com.facebook.react.devsupport.DevServerHelper.PackagerCommandListener
    @Nullable
    public Map<String, RequestHandler> customCommandHandlers() {
        return this.mCustomPackagerCommandHandlers;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void destroyRootView(View view) {
        this.mReactInstanceManagerHelper.destroyRootView(view);
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    @Nullable
    public File downloadBundleResourceFromUrlSync(String str, File file) {
        return this.mDevServerHelper.downloadBundleResourceFromUrlSync(str, file);
    }

    public void fetchSplitBundleAndCreateBundleLoader(String str, CallbackWithBundleLoader callbackWithBundleLoader) {
        String devServerSplitBundleURL = this.mDevServerHelper.getDevServerSplitBundleURL(str);
        File file = this.mJSSplitBundlesDir;
        UiThreadUtil.runOnUiThread(new AnonymousClass20(devServerSplitBundleURL, new File(file, str.replaceAll("/", "_") + ".jsbundle"), callbackWithBundleLoader));
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public DeveloperSettings getDevSettings() {
        return this.mDevSettings;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public boolean getDevSupportEnabled() {
        return this.mIsDevSupportEnabled;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public String getDownloadedJSBundleFile() {
        return this.mJSBundleTempFile.getAbsolutePath();
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public String getJSBundleURLForRemoteDebugging() {
        return this.mDevServerHelper.getJSBundleURLForRemoteDebugging((String) Assertions.assertNotNull(this.mJSAppBundleName));
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    @Nullable
    public StackFrame[] getLastErrorStack() {
        return this.mLastErrorStack;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    @Nullable
    public String getLastErrorTitle() {
        return this.mLastErrorTitle;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public String getSourceMapUrl() {
        String str = this.mJSAppBundleName;
        return str == null ? "" : this.mDevServerHelper.getSourceMapUrl((String) Assertions.assertNotNull(str));
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public String getSourceUrl() {
        String str = this.mJSAppBundleName;
        return str == null ? "" : this.mDevServerHelper.getSourceUrl((String) Assertions.assertNotNull(str));
    }

    @Override // com.facebook.react.bridge.NativeModuleCallExceptionHandler
    public void handleException(Exception exc) {
        if (this.mIsDevSupportEnabled) {
            for (ExceptionLogger exceptionLogger : this.mExceptionLoggers) {
                exceptionLogger.log(exc);
            }
            return;
        }
        this.mDefaultNativeModuleCallExceptionHandler.handleException(exc);
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void handleReloadJS() {
        UiThreadUtil.assertOnUiThread();
        ReactMarker.logMarker(ReactMarkerConstants.RELOAD, this.mDevSettings.getPackagerConnectionSettings().getDebugServerHost());
        hideRedboxDialog();
        if (this.mDevSettings.isRemoteJSDebugEnabled()) {
            PrinterHolder.getPrinter().logMessage(ReactDebugOverlayTags.RN_CORE, "RNCore: load from Proxy");
            this.mDevLoadingViewController.showForRemoteJSEnabled();
            this.mDevLoadingViewVisible = true;
            reloadJSInProxyMode();
            return;
        }
        PrinterHolder.getPrinter().logMessage(ReactDebugOverlayTags.RN_CORE, "RNCore: load from Server");
        reloadJSFromServer(this.mDevServerHelper.getDevServerBundleURL((String) Assertions.assertNotNull(this.mJSAppBundleName)));
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public boolean hasUpToDateJSBundleInCache() {
        if (this.mIsDevSupportEnabled && this.mJSBundleTempFile.exists()) {
            try {
                String packageName = this.mApplicationContext.getPackageName();
                if (this.mJSBundleTempFile.lastModified() > this.mApplicationContext.getPackageManager().getPackageInfo(packageName, 0).lastUpdateTime) {
                    File file = new File(String.format(Locale.US, EXOPACKAGE_LOCATION_FORMAT, packageName));
                    if (!file.exists()) {
                        return true;
                    }
                    return this.mJSBundleTempFile.lastModified() > file.lastModified();
                }
            } catch (PackageManager.NameNotFoundException unused) {
                FLog.e(ReactConstants.TAG, "DevSupport is unable to get current app info");
            }
        }
        return false;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void hideRedboxDialog() {
        RedBoxDialog redBoxDialog = this.mRedBoxDialog;
        if (redBoxDialog != null) {
            redBoxDialog.dismiss();
            this.mRedBoxDialog = null;
        }
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void isPackagerRunning(final PackagerStatusCallback packagerStatusCallback) {
        Runnable runnable = new Runnable() { // from class: com.facebook.react.devsupport.DevSupportManagerBase.21
            @Override // java.lang.Runnable
            public void run() {
                DevSupportManagerBase.this.mDevServerHelper.isPackagerRunning(packagerStatusCallback);
            }
        };
        DevSupportManager.PackagerLocationCustomizer packagerLocationCustomizer = this.mPackagerLocationCustomizer;
        if (packagerLocationCustomizer != null) {
            packagerLocationCustomizer.run(runnable);
        } else {
            runnable.run();
        }
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void loadSplitBundleFromServer(final String str, final DevSplitBundleCallback devSplitBundleCallback) {
        fetchSplitBundleAndCreateBundleLoader(str, new CallbackWithBundleLoader() { // from class: com.facebook.react.devsupport.DevSupportManagerBase.19
            @Override // com.facebook.react.devsupport.DevSupportManagerBase.CallbackWithBundleLoader
            public void onError(String str2, Throwable th) {
                devSplitBundleCallback.onError(str2, th);
            }

            @Override // com.facebook.react.devsupport.DevSupportManagerBase.CallbackWithBundleLoader
            public void onSuccess(JSBundleLoader jSBundleLoader) {
                jSBundleLoader.loadScript(DevSupportManagerBase.this.mCurrentContext.getCatalystInstance());
                ((HMRClient) DevSupportManagerBase.this.mCurrentContext.getJSModule(HMRClient.class)).registerBundle(DevSupportManagerBase.this.mDevServerHelper.getDevServerSplitBundleURL(str));
                devSplitBundleCallback.onSuccess();
            }
        });
    }

    @Override // com.facebook.react.devsupport.DevServerHelper.PackagerCommandListener
    public void onCaptureHeapCommand(final Responder responder) {
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.devsupport.DevSupportManagerBase.24
            @Override // java.lang.Runnable
            public void run() {
                DevSupportManagerBase.this.handleCaptureHeap(responder);
            }
        });
    }

    @Override // com.facebook.react.devsupport.DevInternalSettings.Listener
    public void onInternalSettingsChanged() {
        reloadSettings();
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void onNewReactContextCreated(ReactContext reactContext) {
        resetCurrentContext(reactContext);
    }

    @Override // com.facebook.react.devsupport.DevServerHelper.PackagerCommandListener
    public void onPackagerConnected() {
    }

    @Override // com.facebook.react.devsupport.DevServerHelper.PackagerCommandListener
    public void onPackagerDevMenuCommand() {
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.devsupport.DevSupportManagerBase.23
            @Override // java.lang.Runnable
            public void run() {
                DevSupportManagerBase.this.showDevOptionsDialog();
            }
        });
    }

    @Override // com.facebook.react.devsupport.DevServerHelper.PackagerCommandListener
    public void onPackagerDisconnected() {
    }

    @Override // com.facebook.react.devsupport.DevServerHelper.PackagerCommandListener
    public void onPackagerReloadCommand() {
        this.mDevServerHelper.disableDebugger();
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.devsupport.DevSupportManagerBase.22
            @Override // java.lang.Runnable
            public void run() {
                DevSupportManagerBase.this.handleReloadJS();
            }
        });
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void onReactInstanceDestroyed(ReactContext reactContext) {
        if (reactContext == this.mCurrentContext) {
            resetCurrentContext(null);
        }
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void registerErrorCustomizer(ErrorCustomizer errorCustomizer) {
        if (this.mErrorCustomizers == null) {
            this.mErrorCustomizers = new ArrayList();
        }
        this.mErrorCustomizers.add(errorCustomizer);
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void reloadJSFromServer(String str) {
        reloadJSFromServer(str, new BundleLoadCallback() { // from class: com.facebook.react.devsupport.DevSupportManagerBase.28
            @Override // com.facebook.react.devsupport.DevSupportManagerBase.BundleLoadCallback
            public void onSuccess() {
                UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.devsupport.DevSupportManagerBase.28.1
                    @Override // java.lang.Runnable
                    public void run() {
                        DevSupportManagerBase.this.mReactInstanceManagerHelper.onJSBundleLoadedFromServer();
                    }
                });
            }
        });
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void reloadSettings() {
        if (UiThreadUtil.isOnUiThread()) {
            reload();
        } else {
            UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.devsupport.DevSupportManagerBase.18
                @Override // java.lang.Runnable
                public void run() {
                    DevSupportManagerBase.this.reload();
                }
            });
        }
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void setDevSupportEnabled(boolean z) {
        this.mIsDevSupportEnabled = z;
        reloadSettings();
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void setFpsDebugEnabled(final boolean z) {
        if (!this.mIsDevSupportEnabled) {
            return;
        }
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.devsupport.DevSupportManagerBase.33
            @Override // java.lang.Runnable
            public void run() {
                DevSupportManagerBase.this.mDevSettings.setFpsDebugEnabled(z);
            }
        });
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void setHotModuleReplacementEnabled(final boolean z) {
        if (!this.mIsDevSupportEnabled) {
            return;
        }
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.devsupport.DevSupportManagerBase.31
            @Override // java.lang.Runnable
            public void run() {
                DevSupportManagerBase.this.mDevSettings.setHotModuleReplacementEnabled(z);
                DevSupportManagerBase.this.handleReloadJS();
            }
        });
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void setPackagerLocationCustomizer(DevSupportManager.PackagerLocationCustomizer packagerLocationCustomizer) {
        this.mPackagerLocationCustomizer = packagerLocationCustomizer;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void setRemoteJSDebugEnabled(final boolean z) {
        if (!this.mIsDevSupportEnabled) {
            return;
        }
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.devsupport.DevSupportManagerBase.32
            @Override // java.lang.Runnable
            public void run() {
                DevSupportManagerBase.this.mDevSettings.setRemoteJSDebugEnabled(z);
                DevSupportManagerBase.this.handleReloadJS();
            }
        });
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void showDevOptionsDialog() {
        String string;
        String string2;
        String string3;
        String string4;
        if (this.mDevOptionsDialog != null || !this.mIsDevSupportEnabled || ActivityManager.isUserAMonkey()) {
            return;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(this.mApplicationContext.getString(R.string.catalyst_reload), new DevOptionHandler() { // from class: com.facebook.react.devsupport.DevSupportManagerBase.6
            @Override // com.facebook.react.devsupport.interfaces.DevOptionHandler
            public void onOptionSelected() {
                if (!DevSupportManagerBase.this.mDevSettings.isJSDevModeEnabled() && DevSupportManagerBase.this.mDevSettings.isHotModuleReplacementEnabled()) {
                    Toast.makeText(DevSupportManagerBase.this.mApplicationContext, DevSupportManagerBase.this.mApplicationContext.getString(R.string.catalyst_hot_reloading_auto_disable), 1).show();
                    DevSupportManagerBase.this.mDevSettings.setHotModuleReplacementEnabled(false);
                }
                DevSupportManagerBase.this.handleReloadJS();
            }
        });
        if (this.mDevSettings.isDeviceDebugEnabled()) {
            if (this.mDevSettings.isRemoteJSDebugEnabled()) {
                this.mDevSettings.setRemoteJSDebugEnabled(false);
                handleReloadJS();
            }
            linkedHashMap.put(this.mApplicationContext.getString(R.string.catalyst_debug_open), new DevOptionHandler() { // from class: com.facebook.react.devsupport.DevSupportManagerBase.7
                @Override // com.facebook.react.devsupport.interfaces.DevOptionHandler
                public void onOptionSelected() {
                    DevSupportManagerBase.this.mDevServerHelper.openUrl(DevSupportManagerBase.this.mCurrentContext, DevSupportManagerBase.FLIPPER_DEBUGGER_URL, DevSupportManagerBase.this.mApplicationContext.getString(R.string.catalyst_open_flipper_error));
                }
            });
            linkedHashMap.put(this.mApplicationContext.getString(R.string.catalyst_devtools_open), new DevOptionHandler() { // from class: com.facebook.react.devsupport.DevSupportManagerBase.8
                @Override // com.facebook.react.devsupport.interfaces.DevOptionHandler
                public void onOptionSelected() {
                    DevSupportManagerBase.this.mDevServerHelper.openUrl(DevSupportManagerBase.this.mCurrentContext, DevSupportManagerBase.FLIPPER_DEVTOOLS_URL, DevSupportManagerBase.this.mApplicationContext.getString(R.string.catalyst_open_flipper_error));
                }
            });
        } else {
            if (this.mDevSettings.isRemoteJSDebugEnabled()) {
                string = this.mApplicationContext.getString(R.string.catalyst_debug_stop);
            } else {
                string = this.mApplicationContext.getString(R.string.catalyst_debug);
            }
            linkedHashMap.put(string, new DevOptionHandler() { // from class: com.facebook.react.devsupport.DevSupportManagerBase.9
                @Override // com.facebook.react.devsupport.interfaces.DevOptionHandler
                public void onOptionSelected() {
                    DevSupportManagerBase.this.mDevSettings.setRemoteJSDebugEnabled(!DevSupportManagerBase.this.mDevSettings.isRemoteJSDebugEnabled());
                    DevSupportManagerBase.this.handleReloadJS();
                }
            });
        }
        linkedHashMap.put(this.mApplicationContext.getString(R.string.catalyst_change_bundle_location), new DevOptionHandler() { // from class: com.facebook.react.devsupport.DevSupportManagerBase.10
            @Override // com.facebook.react.devsupport.interfaces.DevOptionHandler
            public void onOptionSelected() {
                Activity currentActivity = DevSupportManagerBase.this.mReactInstanceManagerHelper.getCurrentActivity();
                if (currentActivity != null && !currentActivity.isFinishing()) {
                    final EditText editText = new EditText(currentActivity);
                    editText.setHint("localhost:8081");
                    new AlertDialog.Builder(currentActivity).setTitle(DevSupportManagerBase.this.mApplicationContext.getString(R.string.catalyst_change_bundle_location)).setView(editText).setPositiveButton(17039370, new DialogInterface.OnClickListener() { // from class: com.facebook.react.devsupport.DevSupportManagerBase.10.1
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int i) {
                            DevSupportManagerBase.this.mDevSettings.getPackagerConnectionSettings().setDebugServerHost(editText.getText().toString());
                            DevSupportManagerBase.this.handleReloadJS();
                        }
                    }).create().show();
                    return;
                }
                FLog.e(ReactConstants.TAG, "Unable to launch change bundle location because react activity is not available");
            }
        });
        linkedHashMap.put(this.mApplicationContext.getString(R.string.catalyst_inspector), new DevOptionHandler() { // from class: com.facebook.react.devsupport.DevSupportManagerBase.11
            @Override // com.facebook.react.devsupport.interfaces.DevOptionHandler
            public void onOptionSelected() {
                DevSupportManagerBase.this.mDevSettings.setElementInspectorEnabled(!DevSupportManagerBase.this.mDevSettings.isElementInspectorEnabled());
                DevSupportManagerBase.this.mReactInstanceManagerHelper.toggleElementInspector();
            }
        });
        if (this.mDevSettings.isHotModuleReplacementEnabled()) {
            string2 = this.mApplicationContext.getString(R.string.catalyst_hot_reloading_stop);
        } else {
            string2 = this.mApplicationContext.getString(R.string.catalyst_hot_reloading);
        }
        linkedHashMap.put(string2, new DevOptionHandler() { // from class: com.facebook.react.devsupport.DevSupportManagerBase.12
            @Override // com.facebook.react.devsupport.interfaces.DevOptionHandler
            public void onOptionSelected() {
                boolean z = !DevSupportManagerBase.this.mDevSettings.isHotModuleReplacementEnabled();
                DevSupportManagerBase.this.mDevSettings.setHotModuleReplacementEnabled(z);
                if (DevSupportManagerBase.this.mCurrentContext != null) {
                    if (z) {
                        ((HMRClient) DevSupportManagerBase.this.mCurrentContext.getJSModule(HMRClient.class)).enable();
                    } else {
                        ((HMRClient) DevSupportManagerBase.this.mCurrentContext.getJSModule(HMRClient.class)).disable();
                    }
                }
                if (!z || DevSupportManagerBase.this.mDevSettings.isJSDevModeEnabled()) {
                    return;
                }
                Toast.makeText(DevSupportManagerBase.this.mApplicationContext, DevSupportManagerBase.this.mApplicationContext.getString(R.string.catalyst_hot_reloading_auto_enable), 1).show();
                DevSupportManagerBase.this.mDevSettings.setJSDevModeEnabled(true);
                DevSupportManagerBase.this.handleReloadJS();
            }
        });
        if (this.mIsSamplingProfilerEnabled) {
            string3 = this.mApplicationContext.getString(R.string.catalyst_sample_profiler_disable);
        } else {
            string3 = this.mApplicationContext.getString(R.string.catalyst_sample_profiler_enable);
        }
        linkedHashMap.put(string3, new DevOptionHandler() { // from class: com.facebook.react.devsupport.DevSupportManagerBase.13
            @Override // com.facebook.react.devsupport.interfaces.DevOptionHandler
            public void onOptionSelected() {
                DevSupportManagerBase.this.toggleJSSamplingProfiler();
            }
        });
        if (this.mDevSettings.isFpsDebugEnabled()) {
            string4 = this.mApplicationContext.getString(R.string.catalyst_perf_monitor_stop);
        } else {
            string4 = this.mApplicationContext.getString(R.string.catalyst_perf_monitor);
        }
        linkedHashMap.put(string4, new DevOptionHandler() { // from class: com.facebook.react.devsupport.DevSupportManagerBase.14
            @Override // com.facebook.react.devsupport.interfaces.DevOptionHandler
            public void onOptionSelected() {
                if (!DevSupportManagerBase.this.mDevSettings.isFpsDebugEnabled()) {
                    Activity currentActivity = DevSupportManagerBase.this.mReactInstanceManagerHelper.getCurrentActivity();
                    if (currentActivity == null) {
                        FLog.e(ReactConstants.TAG, "Unable to get reference to react activity");
                    } else {
                        DebugOverlayController.requestPermission(currentActivity);
                    }
                }
                DevSupportManagerBase.this.mDevSettings.setFpsDebugEnabled(!DevSupportManagerBase.this.mDevSettings.isFpsDebugEnabled());
            }
        });
        linkedHashMap.put(this.mApplicationContext.getString(R.string.catalyst_settings), new DevOptionHandler() { // from class: com.facebook.react.devsupport.DevSupportManagerBase.15
            @Override // com.facebook.react.devsupport.interfaces.DevOptionHandler
            public void onOptionSelected() {
                Intent intent = new Intent(DevSupportManagerBase.this.mApplicationContext, DevSettingsActivity.class);
                intent.setFlags(268435456);
                DevSupportManagerBase.this.mApplicationContext.startActivity(intent);
            }
        });
        if (this.mCustomDevOptions.size() > 0) {
            linkedHashMap.putAll(this.mCustomDevOptions);
        }
        final DevOptionHandler[] devOptionHandlerArr = (DevOptionHandler[]) linkedHashMap.values().toArray(new DevOptionHandler[0]);
        Activity currentActivity = this.mReactInstanceManagerHelper.getCurrentActivity();
        if (currentActivity != null && !currentActivity.isFinishing()) {
            this.mDevOptionsDialog = new AlertDialog.Builder(currentActivity).setItems((CharSequence[]) linkedHashMap.keySet().toArray(new String[0]), new DialogInterface.OnClickListener() { // from class: com.facebook.react.devsupport.DevSupportManagerBase.17
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    devOptionHandlerArr[i].onOptionSelected();
                    DevSupportManagerBase.this.mDevOptionsDialog = null;
                }
            }).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.facebook.react.devsupport.DevSupportManagerBase.16
                @Override // android.content.DialogInterface.OnCancelListener
                public void onCancel(DialogInterface dialogInterface) {
                    DevSupportManagerBase.this.mDevOptionsDialog = null;
                }
            }).create();
            this.mDevOptionsDialog.show();
            ReactContext reactContext = this.mCurrentContext;
            if (reactContext == null) {
                return;
            }
            ((RCTNativeAppEventEmitter) reactContext.getJSModule(RCTNativeAppEventEmitter.class)).emit("RCTDevMenuShown", null);
            return;
        }
        FLog.e(ReactConstants.TAG, "Unable to launch dev options menu because react activity isn't available");
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void showNewJSError(String str, ReadableArray readableArray, int i) {
        showNewError(str, StackTraceHelper.convertJsStackTrace(readableArray), i, ErrorType.JS);
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void showNewJavaError(@Nullable String str, Throwable th) {
        FLog.e(ReactConstants.TAG, "Exception in native call", th);
        showNewError(str, StackTraceHelper.convertJavaStackTrace(th), -1, ErrorType.NATIVE);
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void startInspector() {
        if (this.mIsDevSupportEnabled) {
            this.mDevServerHelper.openInspectorConnection();
        }
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void stopInspector() {
        this.mDevServerHelper.closeInspectorConnection();
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void toggleElementInspector() {
        if (!this.mIsDevSupportEnabled) {
            return;
        }
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.devsupport.DevSupportManagerBase.34
            @Override // java.lang.Runnable
            public void run() {
                DevSupportManagerBase.this.mDevSettings.setElementInspectorEnabled(!DevSupportManagerBase.this.mDevSettings.isElementInspectorEnabled());
                DevSupportManagerBase.this.mReactInstanceManagerHelper.toggleElementInspector();
            }
        });
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void updateJSError(final String str, final ReadableArray readableArray, final int i) {
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.devsupport.DevSupportManagerBase.4
            @Override // java.lang.Runnable
            public void run() {
                if (DevSupportManagerBase.this.mRedBoxDialog == null || !DevSupportManagerBase.this.mRedBoxDialog.isShowing() || i != DevSupportManagerBase.this.mLastErrorCookie) {
                    return;
                }
                StackFrame[] convertJsStackTrace = StackTraceHelper.convertJsStackTrace(readableArray);
                Pair processErrorCustomizers = DevSupportManagerBase.this.processErrorCustomizers(Pair.create(str, convertJsStackTrace));
                DevSupportManagerBase.this.mRedBoxDialog.setExceptionDetails((String) processErrorCustomizers.first, (StackFrame[]) processErrorCustomizers.second);
                DevSupportManagerBase.this.updateLastErrorInfo(str, convertJsStackTrace, i, ErrorType.JS);
                if (DevSupportManagerBase.this.mRedBoxHandler != null) {
                    DevSupportManagerBase.this.mRedBoxHandler.handleRedbox(str, convertJsStackTrace, RedBoxHandler.ErrorType.JS);
                    DevSupportManagerBase.this.mRedBoxDialog.resetReporting();
                }
                DevSupportManagerBase.this.mRedBoxDialog.show();
            }
        });
    }

    public DevSupportManagerBase(Context context, ReactInstanceManagerDevHelper reactInstanceManagerDevHelper, @Nullable String str, boolean z, @Nullable RedBoxHandler redBoxHandler, @Nullable DevBundleDownloadListener devBundleDownloadListener, int i, @Nullable Map<String, RequestHandler> map) {
        this.mIsSamplingProfilerEnabled = false;
        this.mExceptionLoggers = new ArrayList();
        this.mCustomDevOptions = new LinkedHashMap<>();
        this.mDevLoadingViewVisible = false;
        this.mPendingJSSplitBundleRequests = 0;
        this.mIsReceiverRegistered = false;
        this.mIsShakeDetectorStarted = false;
        this.mIsDevSupportEnabled = false;
        this.mLastErrorCookie = 0;
        this.mReactInstanceManagerHelper = reactInstanceManagerDevHelper;
        this.mApplicationContext = context;
        this.mJSAppBundleName = str;
        this.mDevSettings = new DevInternalSettings(context, this);
        this.mBundleStatus = new InspectorPackagerConnection.BundleStatus();
        this.mDevServerHelper = new DevServerHelper(this.mDevSettings, this.mApplicationContext.getPackageName(), new InspectorPackagerConnection.BundleStatusProvider() { // from class: com.facebook.react.devsupport.DevSupportManagerBase.1
            @Override // com.facebook.react.devsupport.InspectorPackagerConnection.BundleStatusProvider
            public InspectorPackagerConnection.BundleStatus getBundleStatus() {
                return DevSupportManagerBase.this.mBundleStatus;
            }
        });
        this.mBundleDownloadListener = devBundleDownloadListener;
        this.mShakeDetector = new ShakeDetector(new ShakeDetector.ShakeListener() { // from class: com.facebook.react.devsupport.DevSupportManagerBase.2
            @Override // com.facebook.react.common.ShakeDetector.ShakeListener
            public void onShake() {
                DevSupportManagerBase.this.showDevOptionsDialog();
            }
        }, i);
        this.mCustomPackagerCommandHandlers = map;
        this.mReloadAppBroadcastReceiver = new BroadcastReceiver() { // from class: com.facebook.react.devsupport.DevSupportManagerBase.3
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if (DevSupportManagerBase.getReloadAppAction(context2).equals(intent.getAction())) {
                    if (intent.getBooleanExtra(DevServerHelper.RELOAD_APP_EXTRA_JS_PROXY, false)) {
                        DevSupportManagerBase.this.mDevSettings.setRemoteJSDebugEnabled(true);
                        DevSupportManagerBase.this.mDevServerHelper.launchJSDevtools();
                    } else {
                        DevSupportManagerBase.this.mDevSettings.setRemoteJSDebugEnabled(false);
                    }
                    DevSupportManagerBase.this.handleReloadJS();
                }
            }
        };
        this.mJSBundleTempFile = new File(context.getFilesDir(), JS_BUNDLE_FILE_NAME);
        this.mJSSplitBundlesDir = this.mApplicationContext.getDir(JS_SPLIT_BUNDLES_DIR_NAME, 0);
        this.mDefaultNativeModuleCallExceptionHandler = new DefaultNativeModuleCallExceptionHandler();
        setDevSupportEnabled(z);
        this.mRedBoxHandler = redBoxHandler;
        this.mDevLoadingViewController = new DevLoadingViewController(reactInstanceManagerDevHelper);
        this.mExceptionLoggers.add(new JSExceptionLogger());
        if (this.mDevSettings.isStartSamplingProfilerOnInit()) {
            if (!this.mIsSamplingProfilerEnabled) {
                toggleJSSamplingProfiler();
            } else {
                Toast.makeText(this.mApplicationContext, "JS Sampling Profiler was already running, so did not start the sampling profiler", 1).show();
            }
        }
    }

    protected void reloadJSFromServer(String str, final BundleLoadCallback bundleLoadCallback) {
        ReactMarker.logMarker(ReactMarkerConstants.DOWNLOAD_START);
        this.mDevLoadingViewController.showForUrl(str);
        this.mDevLoadingViewVisible = true;
        final BundleDownloader.BundleInfo bundleInfo = new BundleDownloader.BundleInfo();
        this.mDevServerHelper.downloadBundleFromURL(new DevBundleDownloadListener() { // from class: com.facebook.react.devsupport.DevSupportManagerBase.29
            @Override // com.facebook.react.devsupport.interfaces.DevBundleDownloadListener
            public void onFailure(Exception exc) {
                DevSupportManagerBase.this.mDevLoadingViewController.hide();
                DevSupportManagerBase.this.mDevLoadingViewVisible = false;
                synchronized (DevSupportManagerBase.this) {
                    DevSupportManagerBase.this.mBundleStatus.isLastDownloadSucess = false;
                }
                if (DevSupportManagerBase.this.mBundleDownloadListener != null) {
                    DevSupportManagerBase.this.mBundleDownloadListener.onFailure(exc);
                }
                FLog.e(ReactConstants.TAG, "Unable to download JS bundle", exc);
                DevSupportManagerBase.this.reportBundleLoadingFailure(exc);
            }

            @Override // com.facebook.react.devsupport.interfaces.DevBundleDownloadListener
            public void onProgress(@Nullable String str2, @Nullable Integer num, @Nullable Integer num2) {
                DevSupportManagerBase.this.mDevLoadingViewController.updateProgress(str2, num, num2);
                if (DevSupportManagerBase.this.mBundleDownloadListener != null) {
                    DevSupportManagerBase.this.mBundleDownloadListener.onProgress(str2, num, num2);
                }
            }

            @Override // com.facebook.react.devsupport.interfaces.DevBundleDownloadListener
            public void onSuccess() {
                DevSupportManagerBase.this.mDevLoadingViewController.hide();
                DevSupportManagerBase.this.mDevLoadingViewVisible = false;
                synchronized (DevSupportManagerBase.this) {
                    DevSupportManagerBase.this.mBundleStatus.isLastDownloadSucess = true;
                    DevSupportManagerBase.this.mBundleStatus.updateTimestamp = System.currentTimeMillis();
                }
                if (DevSupportManagerBase.this.mBundleDownloadListener != null) {
                    DevSupportManagerBase.this.mBundleDownloadListener.onSuccess();
                }
                ReactMarker.logMarker(ReactMarkerConstants.DOWNLOAD_END, bundleInfo.toJSONString());
                bundleLoadCallback.onSuccess();
            }
        }, this.mJSBundleTempFile, str, bundleInfo);
    }
}
